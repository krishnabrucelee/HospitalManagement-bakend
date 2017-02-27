package com.hospital.leave.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.hospital.model.Staff;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hospital.leave.model.EmployeeFiscalYearLeaveDetails;
import com.hospital.leave.model.EmployeeLeaveTransaction;
import com.hospital.leave.model.Financialyear;
import com.hospital.leave.model.Leavedetails;
import com.monitorjbl.json.JsonView;
import com.monitorjbl.json.JsonViewSerializer;

@Repository
public class EmployeeLeaveDao {
	
	@Autowired
	SessionFactory sf;
	
	@Autowired
	@Qualifier("jsonViewObjectMapper")
	private ObjectMapper jsonViewObjectMapper;
	
	@Autowired
	@Qualifier("om")
	private ObjectMapper objectMapper;
	
	@SuppressWarnings("unchecked")
	public JSONObject getLeaveRequestHistoryById(JSONObject employee) {
		Session session = null;
		Transaction tr = null;
		JSONObject status = new JSONObject();
		status.put("status",true);
		Integer eid =(Integer)employee.get("employee_id");
		Calendar cc = Calendar.getInstance();									
		int currentYear = cc.get(Calendar.YEAR);		
		int currentMonth = cc.get(Calendar.MONTH);			
		int financialFromYear = 0;	
		if(currentMonth<=2){
			financialFromYear = currentYear - 1;	
		}else{
			financialFromYear = currentYear;
		
		}			
		try{
			session=this.sf.getCurrentSession();
					tr = session.beginTransaction();
			 Query query2 = session.createQuery("From Financialyear WHERE fromyear=:searchA");
		        List<Financialyear> financialyears = query2.setParameter("searchA", financialFromYear).list();
 		Integer fid = financialyears.get(0).getFinancialyearid();
		Query query = session.createQuery("FROM EmployeeLeaveTransaction WHERE employee_id=:searchA AND financialyearid=:searchB");
		
		List<EmployeeLeaveTransaction> leaveTransaction=query.setParameter("searchA", eid).setParameter("searchB", fid).list();
		if (leaveTransaction!= null && !leaveTransaction.isEmpty()) {
			status.put("result", leaveTransaction);
		} else {
			status.put("result", new ArrayList<EmployeeLeaveTransaction>() );
		}
		
		tr.commit();
		}catch(Exception ex){
			ex.printStackTrace();
			status.put("status",false);
			status.put("reason","Error happend");
			status.put("originalErrorMsg", ex.getMessage());
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		
	}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject getLeavedataList() {
		Session session = null;
		Transaction tr = null;
		JSONObject status = new JSONObject();
		status.put("status",true);
		Calendar cc = Calendar.getInstance();								
		int currentYear = cc.get(Calendar.YEAR);		
		int currentMonth = cc.get(Calendar.MONTH);			
		int financialFromYear = 0;		
		if(currentMonth<=2){
			financialFromYear = currentYear - 1;		
		}else{
			financialFromYear = currentYear;		
		}		
		try{
			session = this.sf.getCurrentSession();
			tr = session.beginTransaction();
			 Query query2 = session.createQuery("From Financialyear WHERE fromyear=:searchA");
		        List<Financialyear> financialyears = query2.setParameter("searchA", financialFromYear).list();
 		Integer fid = financialyears.get(0).getFinancialyearid();			
		 Query query = session.createQuery("FROM Leavedetails WHERE financialyear_id=:searchA");
	        List<Leavedetails> leavedetails = query.setParameter("searchA", fid).list();
	        System.out.println("return data");
	        System.out.println("Size="+leavedetails.size());
	       
	        if (leavedetails!=null &&!leavedetails.isEmpty()) {
			System.out.println("return data");
			status.put("leaveData", leavedetails);
		} else {
			status.put("result",new ArrayList<Leavedetails>());
		}
		tr.commit();
		}catch(Exception ex){
			ex.printStackTrace();
			status.put("status",false);
			status.put("reason","Error happend");
			status.put("originalErrorMsg", ex.getMessage());
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}	
	    }
		return status;
	}
	//Employee login based Leave request details//
	@SuppressWarnings("unchecked")
	public JSONObject getEmployeeleaveRequestDataById(JSONObject employedata) {
		System.out.println("Inside Employee getEmployeeleaveRequestDataById DAO==");
		JSONObject status = new JSONObject();	
		status.put("status",true);
		Integer empid = (Integer)employedata.get("employee_id");
		Calendar cc = Calendar.getInstance();								
		int currentYear = cc.get(Calendar.YEAR);		
		int currentMonth = cc.get(Calendar.MONTH);		
		int financialFromYear = 0;		
		if(currentMonth<=2){
			financialFromYear = currentYear - 1;		
		}else{
			financialFromYear = currentYear;		
		}
		Session session = null;
		Transaction tr = null;
		ArrayList<Object> listOfDetailss = new ArrayList<Object>();
		HashMap<String, Object> leaveDatahistory = new HashMap<>();
		List<Staff> user =null;
		try {
			session = this.sf.getCurrentSession();
			tr= session.beginTransaction();
			 Query query = session.createQuery("From Financialyear WHERE fromyear=:searchA");
		        List<Financialyear> financialyears = query.setParameter("searchA", financialFromYear).list();
		Integer fid = financialyears.get(0).getFinancialyearid();
		System.out.println("Financial year Id="+fid);
			Query query2 = session.createQuery("FROM EmployeeLeaveTransaction WHERE financialyearid=:searchA AND employee_id=:searchB");
			List<EmployeeLeaveTransaction> employeeLeaveTransaction =query2.setParameter("searchA", fid).setParameter("searchB", empid).list();
		   System.out.println("Size="+employeeLeaveTransaction.size());
		   Integer levcofid =0;
		  for (EmployeeLeaveTransaction employeeLeaveTransaction2 : employeeLeaveTransaction) {
		 levcofid = employeeLeaveTransaction2.getLeaveConfigurationId();
		}
		
		 if (employeeLeaveTransaction!=null && !employeeLeaveTransaction.isEmpty()) {
			
			 leaveDatahistory.put("leaveRequestTransaction", employeeLeaveTransaction);
			 Query query3 = session.createQuery("FROM Leavedetails WHERE financialyear_id=:searchA AND id=:searchB");
				List<Leavedetails>	leavedetails = query3.setParameter("searchA", fid).setParameter("searchB", levcofid).list();
				leaveDatahistory.put("Leavedetail", leavedetails.get(0));
				Query query4 = session.createQuery("FROM EmployeeFiscalYearLeaveDetails WHERE	employee_id=:searchA AND financialyear_id=:searchB AND leaveConfiguration_Id=:searchC ");
				List<EmployeeFiscalYearLeaveDetails> employeeFiscalYearLeaveDetail = query4.setParameter("searchA", empid).setParameter("searchB", fid).setParameter("searchC", levcofid).list();
				leaveDatahistory.put("EmployeeFiscalYearLeaveDetails", employeeFiscalYearLeaveDetail.get(0));
				Query query5 = session.createQuery("FROM Staff WHERE user_id=:searchA");
				
				 user = query5.setParameter("searchA", empid).list();
				 
				 String json = jsonViewObjectMapper.writeValueAsString(com.monitorjbl.json.JsonView.with(user).onClass(Staff.class, 
							com.monitorjbl.json.Match.match().exclude("clients")));
				 ArrayList<Staff> convertedValue = objectMapper.readValue(json,new TypeReference<ArrayList<Staff>>() {});
				 user = convertedValue;
				 
				leaveDatahistory.put("EmployeeDetail", user.get(0));
				listOfDetailss.add(leaveDatahistory);
				status.put("result", listOfDetailss);
		 
		 } else {
			status.put("result",new ArrayList<EmployeeLeaveTransaction>());
		}
		tr.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			status.put("status",false);
			status.put("reason","Error happend");
			status.put("originalErrorMsg", ex.getMessage());
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		
	    }
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject getEmployeeFiscalyearleaveByEmpId(JSONObject employeid) {
		System.out.println("Inside employee dao1");
		Session session = null;
		Transaction tr = null;
		JSONObject status = new JSONObject();
		status.put("status",true);
		Calendar cc = Calendar.getInstance();							
		int currentYear = cc.get(Calendar.YEAR);		
		int currentMonth = cc.get(Calendar.MONTH);		
		int financialFromYear = 0;		
		if(currentMonth<=2){
			financialFromYear = currentYear - 1;
		}else{
			financialFromYear = currentYear;
		}	
		Integer empid = (Integer)employeid.get("employee_id");
		try {
			session = this.sf.getCurrentSession();
			tr = session.beginTransaction();
			 Query query = session.createQuery("From Financialyear WHERE fromyear=:searchA");
		        List<Financialyear> financialyears = query.setParameter("searchA", financialFromYear).list();
		Integer fid = financialyears.get(0).getFinancialyearid();
			Query query1 = session.createQuery("FROM EmployeeFiscalYearLeaveDetails WHERE financialyear_id=:searchA AND employee_id=:searchB");
			List<EmployeeFiscalYearLeaveDetails> employeeFiscalYearLeaveDetails =query1.setParameter("searchA", fid).setParameter("searchB", empid).list();
		if (employeeFiscalYearLeaveDetails!=null && !employeeFiscalYearLeaveDetails.isEmpty()) {
			status.put("FiscalYearleavedata", employeeFiscalYearLeaveDetails);
		} else {
			status.put("result",new ArrayList<EmployeeFiscalYearLeaveDetails>());
		}
		tr.commit();
		System.out.println("Inside employee dao333");
		} catch (Exception ex) {
			ex.printStackTrace();
			status.put("status",false);
			status.put("reason","Error happend");
			status.put("originalErrorMsg", ex.getMessage());
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		
	    }
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject getEmployeeFiscalyearleavedeatils() {
		Session session = null;
		Transaction tr = null;
		JSONObject status = new JSONObject();
		status.put("status",true);
		Calendar cc = Calendar.getInstance();									
		int currentYear = cc.get(Calendar.YEAR);		
		int currentMonth = cc.get(Calendar.MONTH);			
		int financialFromYear = 0;
		
		if(currentMonth<=2){
			financialFromYear = currentYear - 1;
		
		}else{
			financialFromYear = currentYear;
			
		}	
		try {
			session = this.sf.getCurrentSession();
			tr = session.beginTransaction();
			 Query query = session.createQuery("From Financialyear WHERE fromyear=:searchA");
		        List<Financialyear> financialyears = query.setParameter("searchA", financialFromYear).list();
		Integer fid = financialyears.get(0).getFinancialyearid();
			Query query1 = session.createQuery("FROM EmployeeFiscalYearLeaveDetails WHERE financialyear_id=:searchA");
			List<EmployeeFiscalYearLeaveDetails> employeeFiscalYearLeaveDetails =query1.setParameter("searchA", fid).list();
		if (employeeFiscalYearLeaveDetails!=null && !employeeFiscalYearLeaveDetails.isEmpty()) {
			status.put("FiscalYearleavedata", employeeFiscalYearLeaveDetails);
		} else {
			status.put("result",new ArrayList<EmployeeFiscalYearLeaveDetails>());
		}
		tr.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			status.put("status",false);
			status.put("reason","Error happend");
			status.put("originalErrorMsg", ex.getMessage());
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		
	    }
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject getParticularLeaveDataByEFLId(JSONObject employeleave) {
		Integer empid = (Integer)employeleave.get("employee_id");
		Integer leaveconfid = (Integer)employeleave.get("leaveConfigurationId");
		Integer finid = (Integer)employeleave.get("financialyearid");
		Session session = null;
		Transaction tr = null;
		JSONObject status = new JSONObject();
		status.put("status",true);
		try {
			session = this.sf.getCurrentSession();
			tr = session.beginTransaction();
			 Query query = session.createQuery("FROM Leavedetails WHERE financialyear_id=:searchA AND ");
		        List<Leavedetails> leavedetails = query.setParameter("searchA", finid).list();
		        status.put("Leavedetails", leavedetails);
			tr.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			status.put("status",false);
			status.put("reason","Error happend");
			status.put("originalErrorMsg", ex.getMessage());
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		
	    }
		return status;
	}
/////*****Get All Data by Employee*****/////
	@SuppressWarnings("unchecked")
	public JSONObject getAllDataEmployeeById(JSONObject employid) {
System.out.println("getAllDataEmployeeById1");
		Session session = null;
		Transaction tr = null;
		JSONObject status = new JSONObject();
		status.put("status",true);
		Calendar cc = Calendar.getInstance();					
					
		int currentYear = cc.get(Calendar.YEAR);		
		int currentMonth = cc.get(Calendar.MONTH);	
		
		int financialFromYear = 0;
		int  employeid = (Integer)employid.get("employee_id");
		if(currentMonth<=2){
			financialFromYear = currentYear - 1;
		
		}else{
			financialFromYear = currentYear;
			
		}	
		ArrayList<Object> listOfDetailss = new ArrayList<Object>();
		List<Staff> user = null;
		try {
			session=this.sf.getCurrentSession();
			tr = session.beginTransaction();
			System.out.println("getAllDataEmployeeById1 try 1");
			 Query query = session.createQuery("From Financialyear WHERE fromyear=:searchA");
		        List<Financialyear> financialyears = query.setParameter("searchA", financialFromYear).list();
		Integer fid = financialyears.get(0).getFinancialyearid();
			Query query1 = session.createQuery("FROM EmployeeFiscalYearLeaveDetails WHERE financialyear_id=:searchA AND employee_id=:searchB");
			List<EmployeeFiscalYearLeaveDetails> employeeFiscalYearLeaveDetails =query1.setParameter("searchA", fid).setParameter("searchB", employeid).list();
			if (employeeFiscalYearLeaveDetails!=null && !employeeFiscalYearLeaveDetails.isEmpty()) {
				HashMap<String, Object> leaveRequestData = new HashMap<>();
				leaveRequestData.put("employeeFiscalYear", employeeFiscalYearLeaveDetails);
				Query query2 = session.createQuery("FROM EmployeeLeaveTransaction WHERE financialyearid=:searchA AND employee_id=:searchB");
				List<EmployeeLeaveTransaction> employeeLeaveTransaction = query2.setParameter("searchA", fid).setParameter("searchB", employeid).list();
				leaveRequestData.put("leaveTransaction", employeeLeaveTransaction);
				Query query3 = session.createQuery("FROM Staff WHERE user_id=:searchA");
				 user = query3.setParameter("searchA", employeid).list();
				 
				 String json = jsonViewObjectMapper.writeValueAsString(com.monitorjbl.json.JsonView.with(user).onClass(Staff.class, 
							com.monitorjbl.json.Match.match().exclude("clients")));
				 ArrayList<Staff> convertedValue = objectMapper.readValue(json,new TypeReference<ArrayList<Staff>>() {});
				 user = convertedValue;
				 
				leaveRequestData.put("Employee", user.get(0));
				Query query4 = session.createQuery("FROM Leavedetails WHERE financialyear_id=:searchA");
				List<Leavedetails> leavedetails = query4.setParameter("searchA", fid).list();
				leaveRequestData.put("LeaveDetails", leavedetails);
				listOfDetailss.add(leaveRequestData);			
			       status.put("result", listOfDetailss);		      
			} else {
				status.put("result", new ArrayList<EmployeeLeaveTransaction>());
			}
			if (employeeFiscalYearLeaveDetails!=null && !employeeFiscalYearLeaveDetails.isEmpty()) {					
				  ObjectMapper om = new ObjectMapper();	                
	                om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);	                
	                ArrayList<Object> map_employee =  om.convertValue(employeeFiscalYearLeaveDetails, ArrayList.class);
			       //status.put("result", convertClientObjToQuote(map_quotes));
	                
			} else {

			}
			tr.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			status.put("status",false);
			status.put("reason","Error happend");
			status.put("originalErrorMsg", ex.getMessage());
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		
	    }
		return status;
	}
	
	 /////******Bind All Data1 Start******/////
	@SuppressWarnings("unchecked")
	public JSONObject getAllDataMerge(){
		System.out.println("Enter into getAllDataMerge() ");
		JSONObject status = new JSONObject();
		status.put("status",true);		
		Session session = null;
		Transaction td = null;		
		Integer financialId =getCurrentFinancialYearId();
		//boolean value=false;
		HashMap<String, Object> resultDatas = new HashMap<String, Object>();
		List<EmployeeLeaveTransaction> value = null;
		value = getData();
		HashSet<Integer> employe = new HashSet<Integer>();
		HashSet<Integer> leave = new HashSet<Integer>();
		int empid =0;
		int leaveid = 0;
		if(value.size()>0){
			  ObjectMapper om = new ObjectMapper();           
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            ArrayList object = om.convertValue(value, ArrayList.class);
            int objectSize = object.size();
		     for(int i=0;i<objectSize;i++)
		     {
		    	 System.out.println("Convert object to java pojo EmployeeLeaveTransaction");
		    	  HashMap<String, Object> data = om.convertValue( object.get(i) , new TypeReference<HashMap<String, Object>>() {});	
		    	  employe.add((Integer)data.get("employee_id"));
		    	leave.add((Integer)data.get("leaveConfigurationId"));
		    	//
		    	empid  = (Integer)data.get("employee_id");
		    	leaveid = (Integer)data.get("leaveConfigurationId");
		    	//		    	
		    	object.set(i, data);			    	
		     }       
            Iterator<Integer> eid = (Iterator<Integer>)employe.iterator();
           while (eid.hasNext()) {
				Integer integer = (Integer) eid.next();
				System.out.println("EMP Id="+integer);				
			}                     
            List<Staff> users = null;          
			try {
				session = this.sf.getCurrentSession();
				td = session.beginTransaction();
				 Criteria crt = session.createCriteria(Staff.class);
		            crt.add(Property.forName("user_id").in(employe.toArray()));
		            users = crt.list();
		            String json = jsonViewObjectMapper.writeValueAsString(com.monitorjbl.json.JsonView.with(users).onClass(Staff.class, 
							com.monitorjbl.json.Match.match().exclude("clients")));
				 ArrayList<Staff> convertedValue = objectMapper.readValue(json,new TypeReference<ArrayList<Staff>>() {});
				 users = convertedValue;         
		 Query query3 = session.createQuery("FROM Leavedetails WHERE financialyear_id=:searchA");
			List<Leavedetails>	leavedetails = query3.setParameter("searchA", financialId).list();
			
			Criteria criteria = session.createCriteria(EmployeeFiscalYearLeaveDetails.class);			
			criteria.add(Property.forName("employee_id").in(employe.toArray()));
			criteria.add(Property.forName("leaveConfiguration_Id").in(leave.toArray()));
			criteria.add(Restrictions.eq("financialyear_id", financialId));
			List<EmployeeFiscalYearLeaveDetails> fiscal = criteria.list();
			System.out.println("fiscal.size() Size="+fiscal.size());
			System.out.println("New Code");
			int size = object.size();
			int users_size = users.size();
			int leave_details_size = leavedetails.size();
			int fisal_size = fiscal.size();
			Map<String,Object> leaveTransaction=null;
			for(int i=0;i<size;i++)
			{
				 leaveTransaction = (Map<String,Object>)object.get(i);	
				int emppid = (Integer)leaveTransaction.get("employee_id");
				int leavecofid =(Integer)leaveTransaction.get("leaveConfigurationId"); 
				for(Integer j=0;j< users_size;j++){		
					 Staff user_detail = users.get(j);					
					if(emppid == user_detail.getStaffId() ){
						leaveTransaction.put("employeeinfo",user_detail);
						break;
					}
				}
				for(Integer k=0;k< leave_details_size;k++){
					
					Leavedetails  leavedata = leavedetails.get(k);
					if(leavecofid== leavedata.getId()){
						leaveTransaction.put("LeaveData", leavedata);
						break;
					}
				}
				for(Integer l=0;l<fisal_size;l++){
				
					EmployeeFiscalYearLeaveDetails fiscalData = fiscal.get(l);
					if(emppid== fiscalData.getEmployee_id()&&leavecofid==fiscalData.getLeaveConfiguration_Id()){
						leaveTransaction.put("FiscalData", fiscalData);
						break;
					}
				}
			}
			System.out.println("New Code2");
			resultDatas.put("result", leaveTransaction);
			status.put("result", object);
			//System.out.println(object);
			System.out.println("New Code3");
			td.commit();
			}catch (Exception ex) {
				ex.printStackTrace();
				status.put("status",false);
				status.put("reason","Error happend");
				status.put("originalErrorMsg", ex.getMessage());
			}finally{
				if(session!=null&&session.isOpen()){
					session.close();
				}			
		    }
		}else{
			System.err.println("LeaveRequest Table is empty");
			status.put("Reason", "LeaveRequest Table is empty");
			status.put("status", false);
		}
		return status;
	}	
	 /////******Bind All Data1 End******/////
	 /////******Bind All Data[pending,approve,reject] Particular employee Start******/////
	@SuppressWarnings("unchecked")
	public JSONObject getEmployeeAllLeaveRequestByEid(JSONObject employyeid) {
		JSONObject status = new JSONObject();
		status.put("status",true);
		Integer financialId =  getCurrentFinancialYearId();
		Session session = null;
		Transaction td = null;
		ObjectMapper mapper = new ObjectMapper();		
		HashMap<String, Object> resultDatas = new HashMap<String, Object>();
		List<EmployeeLeaveTransaction> value = null;	
		Integer epmid = (Integer)employyeid.get("employee_id");
		value = getAllDataByEmpId(epmid);//call method		
		HashSet<Integer> employe = new HashSet<Integer>();
		HashSet<Integer> leave = new HashSet<Integer>();
		int empid =0;
		int leaveid = 0;
		if(value !=null && value.size()>0){
			  ObjectMapper om = new ObjectMapper();           
          om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
          ArrayList<Object> object = om.convertValue(value, ArrayList.class);
          int objectSize = object.size();
		     for(int i=0;i<objectSize;i++){
		    	 System.out.println("Convert object to java pojo EmployeeLeaveTransaction");
		    	  HashMap<String, Object> data = om.convertValue( object.get(i) , new TypeReference<HashMap<String, Object>>() {});		    	
		    	  employe.add((Integer)data.get("employee_id"));
		    	leave.add((Integer)data.get("leaveConfigurationId"));
		    	//
		    	empid  = (Integer)data.get("employee_id");
		    	leaveid = (Integer)data.get("leaveConfigurationId");
		    	//		    	
		    	object.set(i, data);			    	
		     }
		     Iterator<Integer> eid = (Iterator<Integer>)employe.iterator();
         while (eid.hasNext()) {
				Integer integer = (Integer) eid.next();
				System.out.println("EMP Id="+integer);				
			}
         List<Staff> users =null;      
         try {
        	 session = this.sf.getCurrentSession();
      		td = session.beginTransaction();
        	 Query querya = session.createQuery("FROM Staff WHERE user_id=:searchA");
             users = querya.setParameter("searchA", empid).list();
 //                   	 
			String json = jsonViewObjectMapper.writeValueAsString(com.monitorjbl.json.JsonView.with(users).onClass(Staff.class, 
						com.monitorjbl.json.Match.match().exclude("clients")));
			 ArrayList<Staff> convertedValue = mapper.readValue(json,new TypeReference<ArrayList<Staff>>() {});
			 users = convertedValue;
	//         
		System.out.println("New type find FinancialyearId value is returned");
			   Query query = session.createQuery("From Financialyear WHERE 	financialyearid=:searchA");
		        List<Financialyear> financialyears = query.setParameter("searchA", financialId).list();
		Integer fid = financialyears.get(0).getFinancialyearid();
		 Query query3 = session.createQuery("FROM Leavedetails WHERE financialyear_id=:searchA");
			List<Leavedetails>	leavedetails = query3.setParameter("searchA", fid).list();			
			Criteria criteria = session.createCriteria(EmployeeFiscalYearLeaveDetails.class);			
			criteria.add(Property.forName("employee_id").in(empid));
			criteria.add(Property.forName("leaveConfiguration_Id").in(leave.toArray()));
			criteria.add(Restrictions.eq("financialyear_id", fid));
			List<EmployeeFiscalYearLeaveDetails> fiscal = criteria.list();
			System.out.println("fiscal.size() Size="+fiscal.size());		
			// New Code
			System.out.println("New Code");
			int size = object.size();
			int users_size = users.size();
			int leave_details_size = leavedetails.size();
			int fisal_size = fiscal.size();
			Map<String,Object> leaveTransaction=null;
			for(int i=0;i<size;i++){
				 leaveTransaction =(Map<String,Object>) object.get(i); 	
				int emppid = (Integer)leaveTransaction.get("employee_id");
				int leavecofid =(Integer)leaveTransaction.get("leaveConfigurationId"); 
				for(Integer j=0;j< users_size;j++){									
					 Staff user_detail = users.get(j);					
					if(emppid == user_detail.getStaffId() ){
						leaveTransaction.put("employeeinfo",user_detail);
						break;
					}
				}
				for(Integer k=0;k< leave_details_size;k++){
					
					Leavedetails  leavedata = leavedetails.get(k);
					if(leavecofid== leavedata.getId()){
						leaveTransaction.put("LeaveData", leavedata);
						break;
					}
				}
				for(Integer l=0;l<fisal_size;l++){			
					EmployeeFiscalYearLeaveDetails fiscalData = fiscal.get(l);
					if(emppid== fiscalData.getEmployee_id()&&leavecofid==fiscalData.getLeaveConfiguration_Id()){
						leaveTransaction.put("FiscalData", fiscalData);
						break;
					}
				}
			}
			System.out.println("New Code2");
			resultDatas.put("result", leaveTransaction);
			status.put("result", object);		
			System.out.println("New Code3");
			td.commit();
			} catch (Exception ex) {
				ex.printStackTrace();
				status.put("status",false);
				status.put("reason","Error happend");
				status.put("originalErrorMsg", ex.getMessage());
			}finally{
				if(session!=null&&session.isOpen()){
					session.close();
				}			
		    }
		}else{
			System.err.println("Admin STILL not configure Financial,Leave types,Employee not yet to APPLY leaveRequest table value is empty ");
			status.put("reason","Admin STILL not configure Financial,Leave types,Employee not yet to APPLY  leaveRequest table value is empty ");
			status.put("status",false);			
		}		
		return status;
	}
	 /////******Bind All Data1 End******/////
	///********Fiscal data With leave details*****///
	@SuppressWarnings("unchecked")
	public JSONObject getEmployeeFiscalleaveDetailsByEmpId(JSONObject employee) {
		System.out.println("getEmployee FiscalLeaveDetailsByEmpId DAO");
		JSONObject status = new JSONObject(); status.put("status",true);
		Integer financialId = getCurrentFinancialYearId();		
		Session session = null; Transaction td = null;
		Integer epmid = (Integer)employee.get("employee_id");
		HashMap<String, Object> resultDatas = new HashMap<String, Object>();
		List<EmployeeFiscalYearLeaveDetails> fiscalData = null;
		fiscalData=getAllLeaveremainingByEid(epmid);			
		HashSet<Integer> employe = new HashSet<Integer>();
		HashSet<Integer> leave = new HashSet<Integer>();
		int empid =0,leaveid = 0;		
		 ObjectMapper om = new ObjectMapper();  
		try {					
			if(fiscalData != null ){			            
			      om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			      ArrayList object = om.convertValue(fiscalData, ArrayList.class);   		     
			      int objectSize = object.size();
			     for(int i=0;i<objectSize;i++){
			    	 System.out.println("Convert object to java pojo EmployeeLeaveTransaction");
			    	  HashMap<String, Object> data = om.convertValue( object.get(i) , new TypeReference<HashMap<String, Object>>() {});			    	  			    
			    	  employe.add((Integer)data.get("employee_id"));
			    	leave.add((Integer)data.get("leaveConfiguration_Id"));
			    	//
			    	empid  = (Integer)data.get("employee_id");
			    	leaveid = (Integer)data.get("leaveConfiguration_Id");
			    	//			    	
			    	object.set(i, data);			    	
			     }			     	    
			      Iterator<Integer> eid = (Iterator<Integer>)employe.iterator();
			     while (eid.hasNext()) {
						Integer integer = (Integer) eid.next();									
					}
			      try {
					System.out.println("Inside try");
					session = this.sf.getCurrentSession();
					td = session.beginTransaction();				
				     Query query3 = session.createQuery("FROM Leavedetails WHERE financialyear_id=:searchA");
						List<Leavedetails>	leavedetails = query3.setParameter("searchA", financialId).list();
						int size = object.size();						
						int leave_details_size = leavedetails.size();	
						Map<String,Object> remainingdays=null;
						for (int i = 0; i <size; i++) {
							remainingdays=(Map<String,Object>)object.get(i); //bind Fiscal leave details
							int leavecofid =(Integer)remainingdays.get("leaveConfiguration_Id"); 
							for(Integer k=0;k< leave_details_size;k++){								
								Leavedetails  leavedata = leavedetails.get(k);
								if(leavecofid== leavedata.getId()){									
									remainingdays.put("LeaveData", leavedata);//Bind Matched leave data									
									break;
								}
							}
						}
						System.out.println("New Code2");
						resultDatas.put("result", remainingdays);
						status.put("result", object);
				     td.commit();
				} catch (Exception ex) {
					ex.printStackTrace();
					status.put("status",false);
					status.put("reason","Error happend");
					status.put("originalErrorMsg", ex.getMessage());
				}finally{
					if(session!=null&&session.isOpen()){
						session.close();
					}
				}			      
			}else{
				System.err.println("Admin still not configure Financial year && Leave types details.So there is no leave remaining days for this year FiscalLeaveDetails table is empty.");
				status.put("reason","Admin still not yet to configure Leave details!.So there is no Leave remaining days for this year.FiscalLeaveDetails table is empty.");
				status.put("status",false);
			}			
		} catch (Exception e) {		
			e.printStackTrace();
		} 
		return status;
	}
	 /////******Bind All Data2 Start******/////
	@SuppressWarnings("unchecked")
	 public   List<EmployeeLeaveTransaction> getData(){
		    System.out.println("call sp method");		
		    Session session = null;Transaction tr = null;	
		    Integer financialId = getCurrentFinancialYearId();			
			List<EmployeeLeaveTransaction> employeeLeaveTransaction =null;
			try {
				session = this.sf.getCurrentSession();
				tr = session.beginTransaction();
				Query query1 = session.createQuery("FROM EmployeeLeaveTransaction WHERE status=:searchA AND financialyearid=:searchB");
				employeeLeaveTransaction =query1.setParameter("searchA", new String("pending")).setParameter("searchB", financialId).list();				
				tr.commit();
				if (employeeLeaveTransaction!=null && !employeeLeaveTransaction.isEmpty()) {
					
				} else {
					System.err.println("EmployeeLeaveTransaction is empty");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				
			}finally{
				if(session!=null&&session.isOpen()){
					session.close();
				}
			}
				if (employeeLeaveTransaction!=null&&!employeeLeaveTransaction.isEmpty()) {
					return employeeLeaveTransaction;
					
				} else {
					return null;
				}	    
	   }
	
     /////******Bind All Data2 End******/////
	 
	 @SuppressWarnings("unchecked")
	public   List<EmployeeLeaveTransaction> getAllDataByEmpId(Integer employeeid){
		 System.out.println("call sp method");	
		    Session session =null;
			Transaction tr = null;	  
			Integer financialId = getCurrentFinancialYearId();
		
			Integer fid =0;
			List<EmployeeLeaveTransaction> employeeLeaveTransaction =null;
			 List<Financialyear> financialyears =null;
			try {
				System.out.println("Inside try");
				session = this.sf.getCurrentSession();
				tr = session.beginTransaction();
				 Query query = session.createQuery("From Financialyear WHERE financialyearid=:searchA");
			       financialyears = query.setParameter("searchA", financialId).list();
			       if (financialyears!=null && !financialyears.isEmpty()) {
			    	   fid = financialyears.get(0).getFinancialyearid();
						Query query1 = session.createQuery("FROM EmployeeLeaveTransaction WHERE financialyearid=:searchA AND employee_id=:searchB");
						employeeLeaveTransaction =query1.setParameter("searchA", fid).setParameter("searchB", employeeid).list();			
						tr.commit();
				} else {
					System.err.println("Admin still not configur Financialyear ");
					System.err.println("So employee not yet to Request LEAVE ");
				} 
			    
			} catch (Exception ex) {
				ex.printStackTrace();
				
			}finally{
				if(session!=null&&session.isOpen()){
					session.close();
				}
			}
				if (employeeLeaveTransaction!=null&&!employeeLeaveTransaction.isEmpty()) {
					return employeeLeaveTransaction;
					
				} else {
					return null;
				}
		    
	   }
	 ///*******Leaveconfig withLeave data********///
	 @SuppressWarnings("unchecked")
	public  List<EmployeeFiscalYearLeaveDetails> getAllLeaveremainingByEid(Integer eid){
		 System.out.println("call sp method");	
		Session session = null;Transaction tr = null;	
		Integer financialId = getCurrentFinancialYearId();	
			Calendar cc = Calendar.getInstance();									
			int currentYear = cc.get(Calendar.YEAR);		
			int currentMonth = cc.get(Calendar.MONTH);			
			int financialFromYear = 0;
			//int  employeid = 12;
			if(currentMonth<=2){
				financialFromYear = currentYear - 1;			
			}else{
				financialFromYear = currentYear;			
			}
			List<EmployeeFiscalYearLeaveDetails> employeeFiscalYearLeaveDetails=null;
			try {
				System.out.println("Inside try");
				session = this.sf.getCurrentSession();
				tr = session.beginTransaction();
				/* Query query = session.createQuery("From Financialyear WHERE financialyearid=:searchA");
			        List<Financialyear> financialyears = query.setParameter("searchA", financialId).list();
			        if (financialyears!= null&&!financialyears.isEmpty()&&financialyears.size()>0) {
			        	 Integer fid = financialyears.get(0).getFinancialyearid();
					     Query query2 = session.createQuery("FROM EmployeeFiscalYearLeaveDetails WHERE financialyear_id=:searchA AND employee_id=:searchB");
					     employeeFiscalYearLeaveDetails=query2.setParameter("searchA", fid).setParameter("searchB", eid).list();
					     tr.commit();
					} else {
						System.err.println("Admin still not configure Financial year && Leave type details");
						//break;
						//return "Admin still not configure Financial year details";
						//return false;
					}*/
			        Query query2 = session.createQuery("FROM EmployeeFiscalYearLeaveDetails WHERE financialyear_id=:searchA AND employee_id=:searchB");
				     employeeFiscalYearLeaveDetails=query2.setParameter("searchA", financialId).setParameter("searchB", eid).list();
				     tr.commit();
				     if (employeeFiscalYearLeaveDetails!=null && !employeeFiscalYearLeaveDetails.isEmpty()) {
				    	 System.err.println("Admin still not configure Financial year && Leave type details");
					} else {

					}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(session!=null&&session.isOpen()){
					session.close();
				}
			}
			if (employeeFiscalYearLeaveDetails!=null && !employeeFiscalYearLeaveDetails.isEmpty()) {
				return employeeFiscalYearLeaveDetails;
			} else {
				return null;
			}	 
	 }
	 
	 @SuppressWarnings("unchecked")
		public Integer getCurrentFinancialYearId(){
			JSONObject result = new JSONObject();
			result.put("status",true);
			Session session = null;
			Integer financialYearId=0;
			try {
				
				session = this.sf.getCurrentSession();
				session.beginTransaction();
				Query query = session.createQuery("FROM Financialyear");			
				ArrayList<Financialyear> financialYearDetails = (ArrayList<Financialyear>) query.list();
				
				boolean isMatched = false;
				if(financialYearDetails != null && !financialYearDetails.isEmpty())
				{
					Date currentDate =null;
					for (Financialyear financialyear : financialYearDetails) {
						Date fromFinancialYear = financialyear.getFinancialYear_From();
						Date toFinancialYear = financialyear.getFinancialYear_To();
						
						currentDate = new Date();
						
						if(fromFinancialYear.before(currentDate) && toFinancialYear.after(currentDate))
						{
							financialYearId=financialyear.getFinancialyearid();
							result.put("financialyearid",financialyear.getFinancialyearid());
							isMatched = true;
							break;
						}
					}
					
					if(!isMatched)
					{
						result.put("status",false);
						result.put("reason","Financial Year id not found for [ "+currentDate+" ]");
					}
				}
				else
				{
					result.put("status",false);
					result.put("reason","Financial year table is empty");
				}	
				
			} catch (Exception e) {
				result.put("status",false);
				result.put("reason","error happend");
				result.put("error message",e.getMessage());
				e.printStackTrace();
			}finally{
				if(session!=null&&session.isOpen()){
					session.close();
				}
			}
			return financialYearId;
		}

}

