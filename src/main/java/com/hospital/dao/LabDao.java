package com.hospital.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.loader.plan.exec.spi.LockModeResolver;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Department;
import com.hospital.model.Doctor;
import com.hospital.model.ExternalLab;
import com.hospital.model.LabMasterName;
import com.hospital.model.LabMasterSubCategories;
import com.hospital.model.LabPatientRequestTest;
import com.hospital.model.LabTest;
import com.hospital.model.LabTestMasterReport;
import com.hospital.model.LabTestName;
import com.hospital.model.LabrequestByPatient;
import com.hospital.model.LabtestTypes;
import com.hospital.model.MedicineEntryMaster;
import com.hospital.model.MedicineItemMaster;
import com.hospital.model.NewLabRequest;
import com.hospital.model.Patient;
import com.hospital.model.PatientData;
import com.hospital.model.PatientLabTestStatus;
import com.hospital.model.PatientLabtestReportNames;
import com.hospital.model.PatientRequestRadiologyTest;
@Repository
public class LabDao {
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	@Qualifier("jsonViewObjectMapper")
	ObjectMapper jsonViewObjectMapper;
	
	
	@SuppressWarnings("unchecked")
	public JSONObject addLabConfigure(JSONObject labconfigure) {
		System.out.println("Inside Dao1Patient");
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   LabTest labtest = om.convertValue(labconfigure, LabTest.class);
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(labtest);
			session.getTransaction().commit();
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject addLabTypes(JSONObject labTypes) {
		System.out.println("Inside Dao1Patient");
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   LabtestTypes labtesttypes = om.convertValue(labTypes, LabtestTypes.class);
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(labtesttypes);
			session.getTransaction().commit();
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject addExternalLab(JSONObject externalLab) {
		System.out.println("Inside addExternalLab");
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   ExternalLab externallab = om.convertValue(externalLab, ExternalLab.class);
		  
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(externallab);
			session.getTransaction().commit();
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject saveMasterLabTestReport(JSONObject masterLabTestReport) {//MasterReport
		System.out.println("Inside Dao1Patient");
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   LabTestMasterReport labTestMasterReport = om.convertValue(masterLabTestReport, LabTestMasterReport.class);
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(labTestMasterReport);
			session.getTransaction().commit();
			status.put("status", true);
			status.put("status", "LabMAsterReport  with sub categeries are added");
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public JSONObject labrequestToPatient(JSONObject labrequestToPatient) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   LabrequestByPatient labRequests = om.convertValue(labrequestToPatient, LabrequestByPatient.class);		  
		try {			
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(labRequests);		
			session.getTransaction().commit();
			status.put("status", true);
			status.put("status", "Lab request saved");		
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject approveTestByLab(JSONObject approveTestByLab) {
		System.out.println("Inside approveTestByLab");
		JSONObject status = new JSONObject();
		status.put("status", true);
		Integer requestId = (Integer)approveTestByLab.get("labrequestId");
		String requestStatus = approveTestByLab.get("requestStatus").toString();
		Session session = null;List<LabrequestByPatient> labrequestByPatients=null;
		LabrequestByPatient labrequestByPatient=null;
		try {
			System.out.println("Inside try");
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM LabrequestByPatient WHERE labrequestId =:searchA");
			labrequestByPatients = query.setParameter("searchA", requestId).list();
			labrequestByPatient= labrequestByPatients.get(0);
			labrequestByPatient.setRequestStatus(requestStatus);
			System.out.println("Before update approveTestByLab ");
			session.merge(labrequestByPatient);
			System.out.println("After update approveTestByLab");
			session.getTransaction().commit();
			status.put("status", true);
			status.put("status", "Lab request saved");
			System.out.println("Lab request for patient is saved");
		} catch (Exception e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	/*
	 * @SuppressWarnings("unchecked")
	public JSONObject patientRequestLabtest(JSONObject patientRequestLabtest) {
		System.out.println("Inside labrequestToPatient");
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   //NewLabRequest labRequests = om.convertValue(labrequestToPatient, NewLabRequest.class);
		   System.out.println("Inside try66");//RequestLabtestByPatient
		   //LabrequestByPatient labRequests = om.convertValue(patientRequestLabtest, LabrequestByPatient.class);
		   RequestLabtestByPatient labRequests = om.convertValue(patientRequestLabtest, RequestLabtestByPatient.class);
		   //PatientLabRequestNames patientLabRequestNames = new PatientLabRequestNames();
		try {
			System.out.println("Inside try");
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(labRequests);
			
			  PatientLabRequestNames	patientLabRequestNames =om.convertValue(patientRequestLabtest, PatientLabRequestNames.class);
			patientLabRequestNames.setLabRequestId(labRequests.getLabrequestId());
			session.save(patientLabRequestNames);
			System.out.println("After save  LabrequestByPatient labRequests");
			session.getTransaction().commit();
			status.put("status", true);
			status.put("status", "Lab request saved");
			System.out.println("Lab request for patient is saved");
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}*/
	@SuppressWarnings("unchecked")
	public JSONObject genarateLabTestResultMaster(JSONObject genarateLabResultMaster) {
		System.out.println("Inside approveTestByLab");
		JSONObject status = new JSONObject();
		status.put("status", true);
		Integer testId = (Integer)genarateLabResultMaster.get("testId");
		
		Session session = null;List<LabTestName> labTestNames=null;
		LabTestName labTestName=null;
		try {
			System.out.println("Inside try");
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM LabTestName WHERE testId =:searchA");
			labTestNames = query.setParameter("searchA", testId).list();
			labTestName= labTestNames.get(0);
			
			System.out.println("After update approveTestByLab");
			session.getTransaction().commit();
		
			status.put("Labtest",labTestName);
			status.put("status", true);
			System.out.println("Lab request for patient is saved");
		} catch (Exception e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject labtestReportForPatient(JSONObject labtestReportForPatient) {
		System.out.println("Inside approveTestByLab");
		JSONObject status = new JSONObject();
		status.put("status", true);
		Integer requestId = (Integer)labtestReportForPatient.get("labrequestId");		
		Session session = null;
		List<LabrequestByPatient> patientRequestLabTests=null;//$$##
		LabrequestByPatient patientRequestLabTest=null;
		try {
			System.out.println("Inside try");
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM PatientRequestLabTest WHERE labrequestId =:searchA");
			patientRequestLabTests = query.setParameter("searchA", requestId ).list();
			patientRequestLabTest= patientRequestLabTests.get(0);			
			System.out.println("After update approveTestByLab");
			session.getTransaction().commit();		
			status.put("Labtest",patientRequestLabTests );
			status.put("status", true);
			System.out.println("Lab request for patient is saved");
		} catch (Exception e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	///// Save Test Report////
	@SuppressWarnings("unchecked")
	public JSONObject savepatientLabtestReport(JSONObject savepatientLabtestReport) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);		
		   System.out.println("Inside try66");//RequestLabtestByPatient		 
		   PatientLabtestReportNames labRequests = om.convertValue(savepatientLabtestReport, PatientLabtestReportNames.class);		  
		try {			
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(labRequests);					
			session.getTransaction().commit();
			status.put("status", true);			
		} catch (Exception e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject viewPatientLabtestReport(JSONObject labtestReportId) {	
		JSONObject status = new JSONObject();
		status.put("status", true);
		Integer labtestReportIdd = (Integer)labtestReportId.get("labtestReportId");		
		Session session = null;List<PatientLabtestReportNames> patientLabtestReportNames=null;
		PatientLabtestReportNames patientLabtestReportName=null;
		try {	
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();	
			Query query = session.createQuery("FROM PatientLabtestReportNames WHERE labtestReportId =:searchA");
			patientLabtestReportNames = query.setParameter("searchA", labtestReportIdd).list();
			patientLabtestReportName= patientLabtestReportNames.get(0);						
			session.getTransaction().commit();		
			status.put("Labtest",patientLabtestReportName);
			status.put("status", true);	
		} catch (Exception e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	
	///New Type//
	
	
	@SuppressWarnings("unchecked")
	public JSONObject addMasterLabConfigure(JSONObject masterLabConfigure) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   LabMasterName labMasterTest = om.convertValue(masterLabConfigure, LabMasterName.class);
		try {		
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();			
			session.save(labMasterTest);		
			
			
			session.getTransaction().commit();
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject listMasterLabTest() {
		JSONObject status = new JSONObject();
		status.put("status", true);	
		Session session = null;
		List<LabMasterName> master =null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM LabMasterName");
			master = query.list();	
			System.out.println("Master lab size="+master.size());
			System.out.println("Master ="+master);
			if (master!=null && !master.isEmpty()) {
				status.put("LabMasterTestName", master);
				status.put("status", true);	
				
			} else {
				status.put("status", "Error happened");
				status.put("originalErrorMsg", "LabMasterName table have empty");
				status.put("status", false);
			}
		} catch (Exception e) {
			status.put("status", "Error happened");
			status.put("originalErrorMsg", e.getMessage());
			status.put("status", false);
			e.printStackTrace();
				
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject updateMasterLabTest(JSONObject masterLabTest) {
		return null;
	}
	@SuppressWarnings("unchecked")
	public JSONObject deleteMasterLabTest(JSONObject masterLabTestId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;		
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();	
			String hql = "DELETE FROM LabMasterName WHERE materLabTestId='"+masterLabTestId.get("materLabTestId")+"'";
			session.createQuery(hql);
			session.getTransaction().commit();
			status.put("status", true);
			status.put("status", "LabMasterName is deleted");
			
		} catch (Exception e) {
			status.put("status",false);			
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return status;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getMasterLabTestReportByAgeRangeId(
			JSONObject masterLabTestReportId) {
		JSONObject status = new JSONObject();
		status.put("status", true);	
		Session session = null;
		List<LabTestMasterReport> master =null;LabTestMasterReport labTestMasterReport=null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM LabTestMasterReport WHERE ageRange=:searchA");
			master = query.setParameter("searchA", masterLabTestReportId.get("ageRange").toString()).list();			
			if (master!=null && !master.isEmpty()) {
				labTestMasterReport=master.get(0);
				status.put("LabTestMasterReport", labTestMasterReport);
				status.put("status", true);	
				
			} else {
				status.put("status", "Error happened");
				status.put("originalErrorMsg", "LabMasterName table have empty");
				status.put("status", false);
			}
		} catch (Exception e) {
			status.put("status", "Error happened");
			status.put("originalErrorMsg", e.getMessage());
			status.put("status", false);
			e.printStackTrace();
				
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject updateMasterLabTestReport(JSONObject masterLabTestReport) {
		return null;
	}
	@SuppressWarnings("unchecked")
	public JSONObject deleteMasterLabTestReportById(
			JSONObject masterLabTestReportId) {
		return null;
	}
	@SuppressWarnings("unchecked")
	public JSONObject savePatientRequestLabTest(JSONObject patientRequestLabTest) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		System.out.println("savePatientLabRequest DAO");
		try {
			System.out.println("savePatientLabRequest DAO inside try");
			Session session = null;			
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			System.out.println("savePatientLabRequest DAO inside  LabPatientRequestTesttry");
		
			Integer  doctorId=Integer.parseInt(patientRequestLabTest.get("doctorId").toString());
			Integer  patientId=Integer.parseInt(patientRequestLabTest.get("patientId").toString());
			Integer  departmentId=Integer.parseInt(patientRequestLabTest.get("departmentId").toString());
			System.out.println("savePatientLabRequest DAO inside Doctortry");
			Doctor doctorDetails = session.get(Doctor.class, doctorId);
			
			if(doctorDetails == null)
			{
				status.put("status", false);
				status.put("reason","doctorId is wrong please check it");
				return status;
			}
			System.out.println("savePatientLabRequest DAO inside  Patienttry");
			Patient patientDetails = session.get(Patient.class, patientId);
			
			if(patientDetails == null)
			{
				status.put("status", false);
				status.put("reason","patientId is wrong please check it");
				return status;
			}
			System.out.println("savePatientLabRequest DAO inside Department try");
			Department departmentDetails = session.get(Department.class, departmentId);
			
			if(departmentDetails == null)
			{
				status.put("status", false);
				status.put("reason","departmentId is wrong please check it");
				return status;
			}
			
			System.out.println("savePatientLabRequest DAO inside try createCriteria");
			Criteria cri = session.createCriteria(LabMasterSubCategories.class);
			cri.add(Restrictions.in("materLabSubcategoryId",(List<Integer>)patientRequestLabTest.get("materLabSubcategoryId") ));
			
			List<LabMasterSubCategories> labMasterSubCategorieslist = cri.list();
			
			ArrayList<PatientLabTestStatus> patientLabTestStatus = new ArrayList<PatientLabTestStatus>();
			
			labMasterSubCategorieslist.forEach((v)->{
				PatientLabTestStatus testStatus = new PatientLabTestStatus();
				testStatus.setTestCompleted(false);
				testStatus.setTestDetails(v);
				patientLabTestStatus.add(testStatus);
			});
						
			// Set Required Fields and Save LabPatientRequestTest
			
			LabPatientRequestTest patientRequestLabtest =new LabPatientRequestTest();		  
			
			patientRequestLabtest.setDoctor(doctorDetails);
			patientRequestLabtest.setDepartment(departmentDetails);
			patientRequestLabtest.setPatient(patientDetails);
			patientRequestLabtest.setLabRequestDate(new Date());
			patientRequestLabtest.setAllTestCompleted(false);
			
			//patientRequestLabtest.setPatientlabtestsubcategories(labMasterSubCategorieslist);
			
			patientRequestLabtest.setPatientLabTestStatus(patientLabTestStatus);
			
			System.out.println("savePatientLabRequest before call session save");	
			session.save(patientRequestLabtest);	
			System.out.println("savePatientLabRequest after call sesion save");		
			session.getTransaction().commit();
			status.put("status", true);			
		} catch (Exception e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject listPatientRequestLabTest() {
		JSONObject status = new JSONObject();
		status.put("status", true);	
		Session session = null;
		List< LabPatientRequestTest> master =null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			
			Query query = session.createQuery("FROM  LabPatientRequestTest");
			master = query.list();	
			System.out.println("Master lab size="+master.size());
			System.out.println("Master ="+master);
						
			if (master!=null && !master.isEmpty()) {
				
				String json = jsonViewObjectMapper.writeValueAsString(com.monitorjbl.json.JsonView.with(master).onClass(Patient.class, 
						com.monitorjbl.json.Match.match().exclude("drugtopatient")));
				
				
				master= jsonViewObjectMapper.readValue(json,new TypeReference<ArrayList<Object>>() {});
		         
				
				status.put("LabPatientRequestTest", master);
				status.put("status", true);					
			} else {
				status.put("status", "Error happened");
				status.put("originalErrorMsg", "LabPatientRequestTest table have empty");
				status.put("status", false);
			}
		} catch (Exception e) {
			status.put("status", "Error happened");
			status.put("originalErrorMsg", e.getMessage());
			status.put("status", false);
			e.printStackTrace();				
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject getPatientRequestLabTestById(
			JSONObject patientRequestLabTestId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Integer labrequestId = (Integer)patientRequestLabTestId.get("patientLabRequestId");
		Session session = null;
		List< LabPatientRequestTest> master =null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM  LabPatientRequestTest WHERE  patientLabRequestId=:searchA");
			master = query.setParameter("searchA", labrequestId).list();				
			if (master!=null && !master.isEmpty()) {
				LabPatientRequestTest labPatientRequestTest=master.get(0);
				status.put(" LabPatientRequestTest", labPatientRequestTest);
				status.put("status", true);					
			} else {
				status.put("status", "Error happened");
				status.put("originalErrorMsg", "LabPatientRequestTest table have empty");
				status.put("status", false);
			}
		} catch (Exception e) {
			status.put("status", "Error happened");
			status.put("originalErrorMsg", e.getMessage());
			status.put("status", false);
			e.printStackTrace();				
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return status;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getPatientRequestLabTestByPatientId(
			JSONObject patientRequestLabTestByPatientId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		
		Integer patientid = (Integer)patientRequestLabTestByPatientId.get("patientId");
		Session session = null;
		List< LabPatientRequestTest> master =null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM  LabPatientRequestTest WHERE patientId=:searchA");
			master = query.setParameter("searchA", patientid).list();			
			if (master!=null && !master.isEmpty()) {
				LabPatientRequestTest labPatientRequestTest = master.get(0);
				status.put(" LabPatientRequestTest", labPatientRequestTest);
				status.put("status", true);					
			} else {
				status.put("status", "Error happened");
				status.put("originalErrorMsg", "LabPatientRequestTest table have empty");
				status.put("status", false);
			}
		} catch (Exception e) {
			status.put("status", "Error happened");
			status.put("originalErrorMsg", e.getMessage());
			status.put("status", false);
			e.printStackTrace();
				
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return status;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject updatePatientRequestLabTest(JSONObject patientLabTestStatus) {
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		try {			
			
			session = sessionFactory.openSession();
			session.beginTransaction();			
			// Update PatientLabTestStatus Table
			
			PatientLabTestStatus patientLabTest = session.get(PatientLabTestStatus.class,(int)patientLabTestStatus.get("patientLabTestStatus"));
			
			if(patientLabTest == null)
			{
				result.put("status",false);
				result.put("reason","PatientLabTestStatus is not found. Please check this field patientLabTestStatus");
				return result;
			}
			
			// TODO attach testPreparedBy field based on user login
			
			patientLabTest.setTestResult((String)patientLabTestStatus.get("testResult"));
			patientLabTest.setNotes((String)patientLabTestStatus.get("notes"));
			patientLabTest.setCompletedDate(new Date());
			patientLabTest.setTestCompleted(true);
			
			
			if((boolean)patientLabTestStatus.get("isUpdateLabPatientRequest"))
			{
				
				LabPatientRequestTest labPatientRequest = 
						session.get(LabPatientRequestTest.class,(int)patientLabTestStatus.get("patientLabRequestId"));
				
				if(labPatientRequest == null)
				{
					result.put("status",false);
					result.put("reason","LabPatientRequestTest is not found. Please check this field patientLabRequestId");
					return result;
				}
				
				labPatientRequest.setAllTestCompleted(true);
			}
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("reason","error happend");
			result.put("message",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject deletePatientRequestLabTestById(
			JSONObject patientRequestLabTest) {
		return null;
	}
	@SuppressWarnings("unchecked")
	public JSONObject savePatientLabTestReport(JSONObject patientLabTestReport) {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject listPatientLabTestReport() {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getPatientLabTestReportById(
			JSONObject patientLabTestReport) {
		return null;
	}
}
