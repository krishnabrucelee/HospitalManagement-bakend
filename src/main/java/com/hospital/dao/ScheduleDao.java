/**
 * 
 */
package com.hospital.dao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stax.StAXResult;
import javax.xml.transform.stax.StAXSource;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONObject;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.EnvironmentMode;
import org.optaplanner.core.config.solver.termination.TerminationConfig;
import org.optaplanner.examples.common.persistence.SolutionDao;
import org.optaplanner.examples.nurserostering.persistence.NurseRosteringDao;
import org.optaplanner.examples.nurserostering.persistence.NurseRosteringExporter;
import org.optaplanner.examples.nurserostering.persistence.NurseRosteringImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.config.AnnotationConfiguration;
import com.hospital.model.Doctor;
import com.hospital.model.DoctorAttendance;
import com.hospital.model.Nurse;
import com.hospital.model.NurseAttendance;
import com.monitorjbl.json.JsonView;
import com.monitorjbl.json.Match;

import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLInputFactory;
import de.odysseus.staxon.json.JsonXMLOutputFactory;
import de.odysseus.staxon.xml.util.PrettyXMLStreamWriter;

/**
 * @author admin
 *
 */
@Repository
public class ScheduleDao {

	@Autowired
	SessionFactory sessionFactory;
	
	
	@Autowired
	@Qualifier("jsonViewObjectMapper")
	ObjectMapper jsonViewObjectMapper;


	/**
	 * @param scheduleInformations
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public JSONObject generateNurseSchedule(JSONObject scheduleInformations) {
		
		JSONObject result = new JSONObject();
		result.put("status",true);
		
		try {
			
			JSONObject fileResult = generateInputFile(scheduleInformations);
			
			if(!(boolean)fileResult.get("status"))
			{
				System.err.println("Error happend while generation input file");
				return fileResult;
			}
			
			
			String inputFilePath = (String)fileResult.get("path");
			
			JSONObject solveStatus = solveScheduling(inputFilePath);
			
			if(!(boolean)solveStatus.get("status"))
			{
				System.err.println("Error happend while solveScheduling input file");
				return solveStatus;
			}
			
		    JSONObject nurseSchudlingResult = convertXMLtoJSON((File)solveStatus.get("outputFile"));
		    
		    if(!(boolean)nurseSchudlingResult.get("status"))
			{
				System.err.println("Error happend while convertXMLtoJSON input file");
				return nurseSchudlingResult;
			}
		    
		    LinkedHashMap<String,Object> jsonResult = (LinkedHashMap<String, Object>) nurseSchudlingResult.get("jsonObject");
		   		   
		    // Get Details from solution object
		    Map<String,Object> solution = (Map<String, Object>) jsonResult.get("Solution");
		    
		    List<LinkedHashMap<String,Object>> assignments = (List<LinkedHashMap<String, Object>>) solution.get("Assignment");
		    
		    //List<String> uniqueDates = assignments.stream().map(v-> (String)v.get("Date")).distinct().collect(Collectors.toList());
		    
		    // Group by Date
		    Map<Object,List<Object>> list= assignments.stream().collect(Collectors.groupingBy(p->{
		    	LinkedHashMap<String, Object> assignment = (LinkedHashMap<String, Object>) p;
		    	return assignment.get("Date");
		    }));
		     
		   
		    // Sorting by Date 
		    Map<String, Object> sortedGroup = new LinkedHashMap<>();
		    DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");			   
		    list.keySet().stream().sorted((n,e)->{	
		    	return LocalDate.parse(n.toString(),pattern).compareTo(LocalDate.parse(e.toString(),pattern));
		    }).forEachOrdered(p->{
		    	sortedGroup.put(p.toString(),list.get(p));
		    });
		    
		    result.put("result",jsonResult);
		    result.put("groupByResult",sortedGroup);
		    
		} catch (Exception e) {
			result.put("status",false);
			result.put("reason","error happend");
			result.put("message",e.getMessage());
			e.printStackTrace();
		}
		return result;
	}


	@SuppressWarnings("unchecked")
	public JSONObject generateDoctorSchedule(JSONObject scheduleInformations) {
		
		JSONObject result = new JSONObject();
		result.put("status",true);
		
		try {
			
			JSONObject fileResult = generateInputFile(scheduleInformations);
			
			if(!(boolean)fileResult.get("status"))
			{
				System.err.println("Error happend while generation input file");
				return fileResult;
			}
			
			
			String inputFilePath = (String)fileResult.get("path");
			
			JSONObject solveStatus = solveScheduling(inputFilePath);
			
			if(!(boolean)solveStatus.get("status"))
			{
				System.err.println("Error happend while solveScheduling input file");
				return solveStatus;
			}
			
		    JSONObject doctorSchudlingResult = convertXMLtoJSON((File)solveStatus.get("outputFile"));
		    
		    if(!(boolean)doctorSchudlingResult.get("status"))
			{
				System.err.println("Error happend while convertXMLtoJSON input file");
				return doctorSchudlingResult;
			}
		    
		    LinkedHashMap<String,Object> jsonResult = (LinkedHashMap<String, Object>) doctorSchudlingResult.get("jsonObject");
		   
		    // Get Details from solution object
		    Map<String,Object> solution = (Map<String, Object>) jsonResult.get("Solution");
		    
		    List<LinkedHashMap<String,Object>> assignments = (List<LinkedHashMap<String, Object>>) solution.get("Assignment");
		    
		    //List<String> uniqueDates = assignments.stream().map(v-> (String)v.get("Date")).distinct().collect(Collectors.toList());
		    
		    // Group by Date
		    Map<Object,List<Object>> list= assignments.stream().collect(Collectors.groupingBy(p->{
		    	LinkedHashMap<String, Object> assignment = (LinkedHashMap<String, Object>) p;
		    	return assignment.get("Date");
		    }));
		     
		   
		    // Sorting by Date 
		    Map<String, Object> sortedGroup = new LinkedHashMap<>();
		    DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");			   
		    list.keySet().stream().sorted((n,e)->{	
		    	return LocalDate.parse(n.toString(),pattern).compareTo(LocalDate.parse(e.toString(),pattern));
		    }).forEachOrdered(p->{
		    	sortedGroup.put(p.toString(),list.get(p));
		    });
		    
		    result.put("result",jsonResult);
		    result.put("groupByResult",sortedGroup);
		    
		} catch (Exception e) {
			result.put("status",false);
			result.put("reason","error happend");
			result.put("message",e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	
	/**
	 * @param sheduleInformation
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONObject saveNurseSchedule(JSONObject sheduleInformation) {
		JSONObject result = new JSONObject();
		result.put("status",true);
		
		Session session = null;
		
		try {
			
			session = sessionFactory.getCurrentSession();
			
			session.beginTransaction();
			
			// Insert to Db		    		    	
	    	Set<Map.Entry<String, Object>> entrySets = ((Map)sheduleInformation.get("groupByResult")).entrySet();
	    	List<LinkedHashMap<String,Object>> defaultEmployeeList = (List<LinkedHashMap<String, Object>>) sheduleInformation.get("Employee");
			 
	    	
	    	Iterator<Map.Entry<String, Object>> itr = entrySets.iterator();
	    	
	    	session = this.sessionFactory.getCurrentSession();
	    	session.beginTransaction();
	    	
	    	int batchSize = 1;
	    	
	    	DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    	
	    	while(itr.hasNext())
	    	{
	    		Map.Entry<String, Object> obj = itr.next();
	    		
	    		ArrayList<LinkedHashMap<String,Object>> assignedEmployees = (ArrayList<LinkedHashMap<String,Object>>)obj.getValue();
	    		
	    		ArrayList<LinkedHashMap<String,Object>> unassignedEmployees = new ArrayList<LinkedHashMap<String,Object>>();			    	
	    		
	    		int defaultEmployeesSize = defaultEmployeeList.size();
	    		
	    		for(int j=0;j<defaultEmployeesSize;j++)
	    		{
	    			LinkedHashMap<String,Object> defaultEmployeeDetails = defaultEmployeeList.get(j);
	    			
	    			String defaultEmployeeID = String.valueOf(defaultEmployeeDetails.get("@ID"));
	    			boolean isMatched = false;
	    			for(LinkedHashMap<String,Object> assignedEmployee : assignedEmployees)
	    			{
	    				if(assignedEmployee.get("Employee").toString().equals(defaultEmployeeID))
	    				{
	    					isMatched = true;
	    					break;
	    				}		    				
	    			}		    			
	    			if(!isMatched)
	    			{
	    				unassignedEmployees.add(defaultEmployeeDetails);
	    			}
	    		}
	    		
	    		// Save Solved Employee to db	    		
	    		for(LinkedHashMap<String,Object> assignedEmployee : assignedEmployees)
    			{
	    			NurseAttendance nurseAttendance = new NurseAttendance();	    
	    			LocalDateTime ldt = LocalDate.parse((String)assignedEmployee.get("Date"),pattern).atStartOfDay();
	    			
	    			nurseAttendance.setDate(Date.from(ldt.atZone(ZoneId.of("UTC")).toInstant()));
	    			nurseAttendance.setShiftType((String)assignedEmployee.get("ShiftType"));
	    			
	    			Nurse nurseDetails = session.get(Nurse.class,(int)assignedEmployee.get("Employee"));
	    			
	    			if( nurseDetails != null)
	    			{
	    				nurseAttendance.setNursedetails(nurseDetails);		    			
		    			session.save(nurseAttendance);
		    			continue;
	    			}
	    			
	    			if(batchSize == 50)
	    			{
	    				// Update batches for Every 50 object
	    				session.flush();
	    				session.clear();
	    			}
	    			else
	    				batchSize++;	    			
    			}
	    		
	    		// Save UnSolved Employee to db	    
	    		for(LinkedHashMap<String,Object> unassignedEmployee : unassignedEmployees)
    			{
	    			NurseAttendance nurseAttendance = new NurseAttendance();	    
	    			LocalDateTime ldt = LocalDate.parse((String)obj.getKey(),pattern).atStartOfDay(); 
	    			
	    			nurseAttendance.setDate(Date.from(ldt.atZone(ZoneId.of("UTC")).toInstant()));
	    			
	    			nurseAttendance.setShiftType("NILL");
	    			
	    			
	    			Nurse nurseDetails = session.get(Nurse.class,Integer.parseInt(unassignedEmployee.get("@ID").toString()));
	    			
	    			if( nurseDetails != null)
	    			{
	    				nurseAttendance.setNursedetails(nurseDetails);		    			
		    			session.save(nurseAttendance);
		    			continue;
	    			}
	    			
	    			if(batchSize == 50)
	    			{
	    				// Update batches for Every 50 object
	    				session.flush();
	    				session.clear();
	    			}
	    			else
	    				batchSize++;	    			
    			}		
	    		
	    	}
		    
	    	session.getTransaction().commit();
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("reason",e.getMessage());
			result.put("message","error happend");
			e.printStackTrace();
		}
		return result;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONObject saveDoctorSchedule(JSONObject sheduleInformation) {
		JSONObject result = new JSONObject();
		result.put("status",true);
		
		Session session = null;
		
		try {
			
			session = sessionFactory.getCurrentSession();
			
			session.beginTransaction();
			
			// Insert to Db		    		    	
	    	Set<Map.Entry<String, Object>> entrySets = ((Map)sheduleInformation.get("groupByResult")).entrySet();
	    	List<LinkedHashMap<String,Object>> defaultEmployeeList = (List<LinkedHashMap<String, Object>>) sheduleInformation.get("Employee");
			 
	    	
	    	Iterator<Map.Entry<String, Object>> itr = entrySets.iterator();
	    	
	    	session = this.sessionFactory.getCurrentSession();
	    	session.beginTransaction();
	    	
	    	int batchSize = 1;
	    	
	    	DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    	
	    	while(itr.hasNext())
	    	{
	    		Map.Entry<String, Object> obj = itr.next();
	    		
	    		ArrayList<LinkedHashMap<String,Object>> assignedEmployees = (ArrayList<LinkedHashMap<String,Object>>)obj.getValue();
	    		
	    		ArrayList<LinkedHashMap<String,Object>> unassignedEmployees = new ArrayList<LinkedHashMap<String,Object>>();			    	
	    		
	    		int defaultEmployeesSize = defaultEmployeeList.size();
	    		
	    		for(int j=0;j<defaultEmployeesSize;j++)
	    		{
	    			LinkedHashMap<String,Object> defaultEmployeeDetails = defaultEmployeeList.get(j);
	    			
	    			String defaultEmployeeID = String.valueOf(defaultEmployeeDetails.get("@ID"));
	    			boolean isMatched = false;
	    			for(LinkedHashMap<String,Object> assignedEmployee : assignedEmployees)
	    			{
	    				if(assignedEmployee.get("Employee").toString().equals(defaultEmployeeID))
	    				{
	    					isMatched = true;
	    					break;
	    				}		    				
	    			}		    			
	    			if(!isMatched)
	    			{
	    				unassignedEmployees.add(defaultEmployeeDetails);
	    			}
	    		}
	    		
	    		// Save Solved Employee to db	    		
	    		for(LinkedHashMap<String,Object> assignedEmployee : assignedEmployees)
    			{
	    			DoctorAttendance doctorAttendance = new DoctorAttendance();	    
	    			LocalDateTime ldt = LocalDate.parse((String)assignedEmployee.get("Date"),pattern).atStartOfDay();
	    			
	    			doctorAttendance.setDate(Date.from(ldt.atZone(ZoneId.of("UTC")).toInstant()));
	    			doctorAttendance.setShiftType((String)assignedEmployee.get("ShiftType"));
	    			
	    			Doctor doctorDetails = session.get(Doctor.class,(int)assignedEmployee.get("Employee"));
	    			
	    			if( doctorDetails != null)
	    			{
	    				doctorAttendance.setDoctordetails(doctorDetails);	    			
		    			session.save(doctorAttendance);
		    			continue;
	    			}
	    			
	    			if(batchSize == 50)
	    			{
	    				// Update batches for Every 50 object
	    				session.flush();
	    				session.clear();
	    			}
	    			else
	    				batchSize++;	    			
    			}
	    		
	    		// Save UnSolved Employee to db	    
	    		for(LinkedHashMap<String,Object> unassignedEmployee : unassignedEmployees)
    			{
	    			DoctorAttendance doctorAttendance = new DoctorAttendance();	  	    
	    			LocalDateTime ldt = LocalDate.parse((String)obj.getKey(),pattern).atStartOfDay(); 
	    			
	    			doctorAttendance.setDate(Date.from(ldt.atZone(ZoneId.of("UTC")).toInstant()));
	    			
	    			doctorAttendance.setShiftType("NILL");
	    			
	    			
	    			Doctor doctorDetails = session.get(Doctor.class,Integer.parseInt(unassignedEmployee.get("@ID").toString()));
	    			
	    			if( doctorDetails != null)
	    			{
	    				doctorAttendance.setDoctordetails(doctorDetails);		    			
		    			session.save(doctorAttendance);
		    			continue;
	    			}
	    			
	    			if(batchSize == 50)
	    			{
	    				// Update batches for Every 50 object
	    				session.flush();
	    				session.clear();
	    			}
	    			else
	    				batchSize++;	    			
    			}		
	    		
	    	}
		    
	    	session.getTransaction().commit();
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("reason",e.getMessage());
			result.put("message","error happend");
			e.printStackTrace();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject solveScheduling(String inputFilePath) {
		JSONObject result = new JSONObject();
		result.put("status",true);
		try {
			
			SolverFactory<Solution> solverFactory = buildSolverFactory();		
			SolutionDao solutionDao = new NurseRosteringDao();
				
			
			File unsolvedFile = new File(inputFilePath);
			
			NurseRosteringImporter importer = new NurseRosteringImporter();
			
			//File outpFile = new File("data/nurserostering/unsolved/output1234.xml");
			
			String foldername = "";
			
			String OS = System.getProperty("os.name").toLowerCase();
			if(OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 )
			{
				foldername = AnnotationConfiguration.LINUX_OUTPUT_PATH_NURSEROSTERING;
			}
			else if(OS.indexOf("win") >= 0)
			{
				foldername = AnnotationConfiguration.WIN_OUTPUT_PATH_NURSEROSTERING;
			}
			
			File outpFile = new File(foldername+"Output_"+new Date().getTime()+"_"+RandomStringUtils.randomAlphabetic(5)+".xml");		
			
			Solver<Solution> solver = solverFactory.buildSolver();
			Solution bestSolution = solver.solve(importer.readSolution(unsolvedFile));
			
	  
			 NurseRosteringExporter exporter = new NurseRosteringExporter();
			 exporter.writeSolution(bestSolution, outpFile);
			 
			 result.put("outputFile",outpFile);
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("message",e.getMessage());
			e.printStackTrace();
		}		
		return result;
	}
	
	private static SolverFactory<Solution> buildSolverFactory(){
		
		SolverFactory<Solution> solverFactory = SolverFactory.createFromXmlResource(AnnotationConfiguration.SOLVER_CONFIG);
        solverFactory.getSolverConfig().setEnvironmentMode(EnvironmentMode.PRODUCTION);
        TerminationConfig terminationConfig = new TerminationConfig();
        terminationConfig.setBestScoreLimit("0hard/-350soft");
        solverFactory.getSolverConfig().setTerminationConfig(terminationConfig);
        
        return solverFactory;
	}
	
	
	@SuppressWarnings("unchecked")
	private JSONObject generateInputFile(JSONObject constraintDetails){
		JSONObject result = new JSONObject();
		result.put("status",true);
		ObjectMapper om = new ObjectMapper();		
		BufferedWriter bw  = null;
		try {
			String value = om.writeValueAsString(constraintDetails);
			
			JsonXMLConfig config = new JsonXMLConfigBuilder().multiplePI(false).build();
			
			XMLInputFactory inputFactory = new JsonXMLInputFactory(config);
			XMLStreamReader reader = inputFactory.createXMLStreamReader(new StringReader(value));
			
			Source source = new StAXSource(reader);
			
			//File ff = new File("C:\\Users\\admin\\Desktop\\Nurse Scheduling\\sample.xml");
			
			String foldername = "";
			
			String OS = System.getProperty("os.name").toLowerCase();
			if(OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 )
			{
				foldername = AnnotationConfiguration.LINUX_BASE_PATH_NURSEROSTERING;
			}
			else if(OS.indexOf("win") >= 0)
			{
				foldername = AnnotationConfiguration.WIN_BASE_PATH_NURSEROSTERING;
			}
			
			File inputPath = new File(foldername+"Input_"+new Date().getTime()+"_"+RandomStringUtils.randomAlphabetic(5)+".xml");		
			
			inputPath.createNewFile();
			
			inputPath.getAbsolutePath();
			
			bw  = new BufferedWriter(new FileWriter(inputPath));
			XMLStreamWriter writer = XMLOutputFactory.newInstance().createXMLStreamWriter(bw);
			Result Writer_Result = new StAXResult(new PrettyXMLStreamWriter(writer));
			
			TransformerFactory.newInstance().newTransformer().transform(source,Writer_Result);
			
			result.put("path",inputPath.getAbsolutePath());
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status",false);
			result.put("reason","error happend");
			result.put("message",e.getMessage());
		}
		finally{
			try {
				if(bw != null )
					bw.close();
			} catch (IOException e) {
				e.printStackTrace();
				result.put("status",false);
				result.put("reason","error happend");
				result.put("message",e.getMessage());
			}
		}
		return result;
  }
	
	@SuppressWarnings("unchecked")
	private JSONObject convertXMLtoJSON(File outputfile){
		JSONObject result = new JSONObject();
		result.put("status",true);
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(outputfile));
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			
			JsonXMLConfig config = new JsonXMLConfigBuilder()
					.autoArray(true)
		            .autoPrimitive(true)
		            .prettyPrint(true)
		            .build();
			
			XMLEventReader reader = XMLInputFactory.newInstance().createXMLEventReader(br);
			
		
			XMLEventWriter writer = new JsonXMLOutputFactory(config).createXMLEventWriter(output);
			
			writer.add(reader);
			
			reader.close();
	        writer.close();
	        
	        LinkedHashMap<String,Object> object = jsonViewObjectMapper.readValue(output.toByteArray(),new TypeReference<LinkedHashMap<String, Object>>() {
			});
	        
	        result.put("jsonObject", object);
	        
		} catch (Exception e) {
			result.put("status",false);
			result.put("reason",e.getMessage());
			e.printStackTrace();
		}
		return result;
	}


	@SuppressWarnings("unchecked")
	public JSONObject getCurrentMonthNurseSchedule(JSONObject nurseDetail) {
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		try {			
			session = sessionFactory.getCurrentSession();		
			session.beginTransaction();
			
			Nurse nurse = session.get(Nurse.class,(int)nurseDetail.get("nurseId"));
			
			if(nurse == null)
			{
				result.put("status",false);
				result.put("reason","Nurse is not found. Please check nurseId field.");
				return result;
			}
			
			
			LocalDate currentDate = LocalDate.now();
			
			Date MonthFromDate = Date.from(currentDate.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant());
			
			Date toFromDate = Date.from(currentDate.with(TemporalAdjusters.lastDayOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant());
			
			Criteria cri = session.createCriteria(NurseAttendance.class);
			cri.createAlias("nursedetails", "nurse");
			cri.add(Restrictions.eq("nurse.nurseId",nurseDetail.get("nurseId")));
			cri.add(Restrictions.between("date", MonthFromDate, toFromDate));
			
			List<NurseAttendance> nurseAttendanceDetails = cri.list();
			
			String json = jsonViewObjectMapper.writeValueAsString(JsonView.with(nurseAttendanceDetails)
					.onClass(NurseAttendance.class, Match.match().exclude("nursedetails"))
					);
			
			result.put("result",jsonViewObjectMapper.readValue(json,new TypeReference<ArrayList<HashMap<String,Object>>>() {
			}));
			
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("reason","error happend");
			result.put("msg",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return result;
	}


	@SuppressWarnings("unchecked")
	public JSONObject getCurrentMonthDoctorSchedule(JSONObject doctorDetail) {
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		try {			
			session = sessionFactory.getCurrentSession();		
			session.beginTransaction();
			
			Doctor doctorDetails = session.get(Doctor.class,(int)doctorDetail.get("doctorId"));
			
			if(doctorDetails == null)
			{
				result.put("status",false);
				result.put("reason","Doctor is not found. Please check doctorId field.");
				return result;
			}
			
			LocalDate currentDate = LocalDate.now();
			
			Date MonthFromDate = Date.from(currentDate.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant());
			
			Date toFromDate = Date.from(currentDate.with(TemporalAdjusters.lastDayOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant());
			
			Criteria cri = session.createCriteria(DoctorAttendance.class);
			cri.createAlias("doctordetails", "doctor");
			cri.add(Restrictions.eq("doctor.doctorId",doctorDetail.get("doctorId")));
			cri.add(Restrictions.between("date", MonthFromDate, toFromDate));
			
			List<DoctorAttendance> doctorAttendanceDetails = cri.list();
			
			String json = jsonViewObjectMapper.writeValueAsString(JsonView.with(doctorAttendanceDetails)
					.onClass(DoctorAttendance.class, Match.match().exclude("doctordetails"))					
					);
			
			result.put("result",jsonViewObjectMapper.readValue(json,new TypeReference<ArrayList<HashMap<String,Object>>>() {
			}));		
			
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("reason","error happend");
			result.put("msg",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return result;
	}


	
}
