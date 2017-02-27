package com.hospital.leave.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.hospital.model.Staff;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.leave.model.EmployeeFiscalYearLeaveDetails;
import com.hospital.leave.model.EmployeeLeaveTransaction;
import com.hospital.leave.model.Financialyear;
import com.hospital.leave.model.Holidays;
import com.hospital.leave.model.Leavedetails;
import com.hospital.leave.model.Weekoff;

@Repository 
public class AdminDao {	
	
	@Autowired
	SessionFactory  sf;
	
	@Autowired
	@Qualifier("jsonViewObjectMapper")
	private ObjectMapper jsonViewObjectMapper;
	
	@Autowired
	@Qualifier("om")
	private ObjectMapper objectMapper;
	
	@SuppressWarnings("unchecked")
	public JSONObject saveLeaveData(JSONObject leaveData) {
		System.out.println("Inside saveLeaveData controller");
		JSONObject status = new JSONObject();
		status.put("status",true);
		 String leavetype = null; String proleave = null;
		 Date joindate =null; Date dbtodate=null;
			int joinmonth =0; int joinyear=0;int dbfromyear=0;int dbtoyear=0;int allowedday=0;
			 double totholiday=0.0;
			int diff=0;			
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		 Leavedetails leavedetails = om.convertValue(leaveData,  Leavedetails.class);		
		 System.out.println("Pro leave="+leavedetails.getPro_leave());	
		 leavetype = leavedetails.getLeave_days_type();	
		 proleave = leavedetails.getPro_leave();
		 Session session = null;
		   Transaction tr = null;
		   Integer financialYearId=getCurrentFinancialYearId();
		   System.out.println("Current financial year id="+financialYearId);
		   List<Financialyear> financialyears = null;
		   try {
			    session = sf.openSession();
			    tr = session.beginTransaction();
			   System.out.println("Data before saved");   
			   Query query = session.createQuery("From Financialyear WHERE 	financialyearid=:searchA");
		    financialyears = query.setParameter("searchA", financialYearId).list();
		    for (Financialyear financialyear : financialyears) {		    	
		    	 dbtodate=financialyear.getFinancialYear_To();
			}		   
			    leavedetails.setFinancialyear_id(financialYearId);
			session.save(leavedetails);	
			System.out.println("Finanacial year id="+leavedetails.getFinancialyear_id());
			 System.out.println("LEAVE DETAILS ARE  saved");   		
			JSONObject object = getEmployeeList();					       		     
			  if((boolean) object.get("status")){
				  List<Staff> userlist = (List<Staff>) object.get("result");
				  System.out.println("Staff list size="+userlist.size());
				
				  if(!userlist.isEmpty()){	
					  System.out.println("Enter into Staff list size=");
					 for (Staff user : userlist) {
						
						 leavetype = leavedetails.getLeave_days_type();	
						 joindate = user.getStaffDoj();
							SimpleDateFormat sddf = new SimpleDateFormat("dd-MM-yyyy");
							
						 if((leavetype.equals("yearly"))&&(proleave.equals("yes"))){
							 
							 System.out.println("Enter  yearly leave Inside ADD");
								Integer lid = leavedetails.getId();
								System.out.println("Pro leave type ID=" + lid);
								allowedday = leavedetails.getAllowed_days();
								System.out.println("DB yearly allowed days if it is pro leave="+allowedday);
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(joindate);
								joinmonth = calendar.get(Calendar.MONTH);
								joinyear = calendar.get(Calendar.YEAR);								
								diff = differenceInMonths(joindate, dbtodate);								
								totholiday = (double) allowedday;
								/*double permonth = totholiday / 12;								
								double proleavedays = permonth * diff;								
								double finall = Math
										.round(proleavedays * 10000.0) / 10000.0;								
								double round = Math.ceil(finall);
								int remainingdays = (int) round;
								System.out.println("Total is   leave date="
										+ allowedday);
								System.out.println("Total is  Pro leave date after calculation="
										+ remainingdays);
							 */
								double permonth = totholiday / 12;							
								double proleavedays = permonth * diff;	
								System.out.println("proleavedays before Math.round="+proleavedays);
								double finall = Math.round(proleavedays * 10000.0) / 10000.0;							
								double round = Math.ceil(finall);
								int remainingdays = (int) round;
								System.out.println("Total   leave date="+ allowedday);
								System.out.println("Total  Pro leave date after calculation="+ remainingdays);
								
							 EmployeeFiscalYearLeaveDetails financialyear = new EmployeeFiscalYearLeaveDetails();								
							 System.out.println("leave type yearly add start");		
							 financialyear.setLeaveConfiguration_Id(leavedetails.getId());						
								financialyear.setEmployee_id(user.getStaffId());
							 financialyear.setFromDate(financialyears.get(0).getFinancialYear_From());
							 financialyear.setToDate(financialyears.get(0).getFinancialYear_To());
							 financialyear.setRemainingdays(remainingdays);	
								financialyear.setFinancialyear_id(leavedetails.getFinancialyear_id());
								session.save(financialyear);
								System.out.println("leave type yearly add  with proleave calculation end");
						 }
						 if(leavetype.equals("halfyearly")){
							 System.out.println("Inside ADD HALFYEARLY");
							 Date from = financialyears.get(0).getFinancialYear_From();							 
							 Date to = financialyears.get(0).getFinancialYear_To();
								ArrayList<Object> allowedDays = calculateLeaveDuration(from, to, LeaveDuration.Halfyearly.addmonthCount);									
								int size  = allowedDays.size();								
								for(int k=0;k<size;k++){
										HashMap<Object,Object> datesss = (HashMap<Object, Object>) allowedDays.get(k);
										EmployeeFiscalYearLeaveDetails financialyear = new EmployeeFiscalYearLeaveDetails();									
									for(Map.Entry<Object, Object> ss : datesss.entrySet()){
										Calendar fromD = (Calendar)ss.getKey();									
										Calendar toD = (Calendar)ss.getValue();
										 System.out.println("leave type half yearly add start");
										 financialyear.setLeaveConfiguration_Id(leavedetails.getId());						
											financialyear.setEmployee_id(user.getStaffId());
										 financialyear.setFromDate(fromD.getTime());
										 financialyear.setToDate(toD.getTime());
										 financialyear.setRemainingdays(leavedetails.getAllowed_days());	
											financialyear.setFinancialyear_id(leavedetails.getFinancialyear_id());
											session.save(financialyear);
											 System.out.println("leave type half yearly add end");													
									}
								}															     																							       							    							       
						 }	
						 //Quarterly
						 if(leavetype.equals("quarterly")){
							 System.out.println("Inside ADD quarterly");
							 Date from = financialyears.get(0).getFinancialYear_From();							 
							 Date to = financialyears.get(0).getFinancialYear_To();
								ArrayList<Object> allowedDays = calculateLeaveDuration(from, to, LeaveDuration.Quartarly.addmonthCount);									
								int size  = allowedDays.size();								
								for(int k=0;k<size;k++){
										HashMap<Object,Object> datesss = (HashMap<Object, Object>) allowedDays.get(k);
										EmployeeFiscalYearLeaveDetails financialyear = new EmployeeFiscalYearLeaveDetails();									
									for(Map.Entry<Object, Object> ss : datesss.entrySet()){
										Calendar fromD = (Calendar)ss.getKey();									
										Calendar toD = (Calendar)ss.getValue();
										 System.out.println("leave type quarterly  add start");
										 financialyear.setLeaveConfiguration_Id(leavedetails.getId());						
											financialyear.setEmployee_id(user.getStaffId());
										 financialyear.setFromDate(fromD.getTime());
										 financialyear.setToDate(toD.getTime());
										 financialyear.setRemainingdays(leavedetails.getAllowed_days());	
											financialyear.setFinancialyear_id(leavedetails.getFinancialyear_id());
											session.save(financialyear);
											 System.out.println("leave type quarterly  add end");													
									}
								}															     																							       							    							       
						 }
						 //MONTHLY
						 if(leavetype.equals("monthly")){
							 System.out.println("Inside ADD monthly");
							 Date from = financialyears.get(0).getFinancialYear_From();							 
							 Date to = financialyears.get(0).getFinancialYear_To();
								ArrayList<Object> allowedDays = calculateLeaveDuration(from, to, LeaveDuration.Month.addmonthCount);									
								int size  = allowedDays.size();								
								for(int k=0;k<size;k++){
										HashMap<Object,Object> datesss = (HashMap<Object, Object>) allowedDays.get(k);
										EmployeeFiscalYearLeaveDetails financialyear = new EmployeeFiscalYearLeaveDetails();									
									for(Map.Entry<Object, Object> ss : datesss.entrySet()){
										Calendar fromD = (Calendar)ss.getKey();									
										Calendar toD = (Calendar)ss.getValue();
										 System.out.println("leave type monthly add start");
										 financialyear.setLeaveConfiguration_Id(leavedetails.getId());						
											financialyear.setEmployee_id(user.getStaffId());
										 financialyear.setFromDate(fromD.getTime());
										 financialyear.setToDate(toD.getTime());
										 financialyear.setRemainingdays(leavedetails.getAllowed_days());	
											financialyear.setFinancialyear_id(leavedetails.getFinancialyear_id());
											session.save(financialyear);
											 System.out.println("leave type monthly add end");													
									}
								}															     																							       							    							       
						 }					 
					}		  
				  } else{
					  System.out.println("Staff list is empty so we won't insert leave configuration it");
				  }	  
			  }else{
				  System.out.println("Some Error happend while reteriving data");
				  System.err.println("Admin still not configured Emplolyee details");
				  status.put("Reason", "Data saved Leave data details");
			  }	
			  status.put("success", "Data saved Leave data with financial details");
			System.out.println("Data saved");
			tr.commit();
		} catch (Exception e) {	
			e.printStackTrace();
		}
		   finally{
		   if(session !=null &&session.isOpen()){
			   session.close();
		   }
		   }
		return status;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getEmployeeList() {
		JSONObject status = new JSONObject();
		status.put("status",true);
		Session session = null;	
		try {		
			session = this.sf.getCurrentSession();			
			session.beginTransaction();			
			List<Staff> users = session.createQuery("from Staff").list();	
			
			if(users != null && !users.isEmpty()){				
				status.put("result",users);
			}else{
				status.put("result",new ArrayList<Staff>());
			}	
		} catch (Exception e) {
			e.printStackTrace();
			status.put("status",false);
			status.put("reason","Error happend");
			status.put("originalErrorMsg", e.getMessage());
		}
		 finally{
			   if(session !=null &&session.isOpen()){
				   session.close();
			   }
			   }
		return status;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getFinancialyearList() {
		JSONObject status = new JSONObject();
		status.put("status",true);
		Session session = null;
		try {
			session = this.sf.getCurrentSession();		
			session.beginTransaction();			
			List<Financialyear> financialyear = session.createQuery("from Financialyear").list();		
			if(financialyear != null && !financialyear.isEmpty()){			
				status.put("result",financialyear);
			}else{
				status.put("result",new ArrayList<Financialyear>());
			}	
		} catch (Exception e) {
			e.printStackTrace();
			status.put("status",false);
			status.put("reason","Error happend");
			status.put("originalErrorMsg", e.getMessage());
		}
		 finally{
			   if(session !=null &&session.isOpen()){
				   session.close();
			   }
			   }
		return status;
	}
	
	
	//*****Employee Request Leave*****//
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getEmployeeLeaveList() {
		System.out.println("Inside dao1");
		JSONObject status = new JSONObject();
		status.put("status",true);
		Session session =null;
		try {			
			 session = this.sf.getCurrentSession();			
			session.beginTransaction();												
			List<EmployeeLeaveTransaction> eltd = session.createQuery("from EmployeeLeaveTransaction").list();			
			ArrayList<Object> listOfRequests = new ArrayList<Object>();			
			if(eltd != null && !eltd.isEmpty()){
				
				int size = eltd.size();
				
				for(int i=0;i<size;i++)
				{
					EmployeeLeaveTransaction el = eltd.get(i);
					HashMap<String, Object> leaveRequest = new HashMap<>();
					Integer id = el.getLeaveConfigurationId();					
					Integer empid = el.getEmployee_id();					
					Staff userdetails = session.get(Staff.class,id);					
					leaveRequest.put("employee", userdetails);					
					leaveRequest.put("leaveTransaction", el);					
					List<EmployeeFiscalYearLeaveDetails> EFYD  =session.createQuery("From EmployeeFiscalYearLeaveDetails where leaveConfiguration_Id= :id").setParameter("id",id).list();					
					EFYD.get(0);					
					leaveRequest.put("leaveFinancialDetails", EFYD.get(0));										
				   Leavedetails leave_details  = session.get(Leavedetails.class,EFYD.get(0).getLeaveConfiguration_Id());					
				   leaveRequest.put("leaveInfo", leave_details);					
					listOfRequests.add(leaveRequest);
					System.out.println("Data returned2");	
				}
				
				status.put("result",listOfRequests);
			}else{
				status.put("result",new ArrayList<Staff>());
			}	
		} catch (Exception e) {
			e.printStackTrace();
			status.put("status",false);
			status.put("reason","Error happend");
			status.put("originalErrorMsg", e.getMessage());
		}
		 finally{
			   if(session !=null &&session.isOpen()){
				   session.close();
			   }
			   }
		return status;
	}
	/////*********Admin  view All leave request by FinancialYear******////
	@SuppressWarnings("unchecked")
	public JSONObject getAllEmployeeLeaverequestByFid(JSONObject financialyearid) {
		JSONObject status = new JSONObject();
		status.put("status",true);
		Integer fiancialyearid=(Integer)financialyearid.get("financialyear_id");
		Session session = null;			
		Transaction tr=null;	
		try {
			 session = this.sf.getCurrentSession();			
			 tr=session.beginTransaction();	
			Query query = session.createQuery("FROM  EmployeeLeaveTransaction WHERE financialyearid=:searchA");
		List<EmployeeLeaveTransaction> leavetransaction = query.setParameter("searchA", fiancialyearid).list();
		status.put("result", leavetransaction);
		} catch (Exception e) {	
			e.printStackTrace();
			status.put("status",false);
			status.put("reason","Error happend");
			status.put("originalErrorMsg", e.getMessage());
		}
		 finally{
			   if(session !=null &&session.isOpen()){
				   session.close();
			   }
			   }
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject getEmployeeLeaveRequestByYearByAdmin() {
		System.out.println("Inside dao1 admin");
		JSONObject status = new JSONObject();
		status.put("status",true);
		
		Calendar cc = Calendar.getInstance();							
		int currentYear = cc.get(Calendar.YEAR);		
		int currentMonth = cc.get(Calendar.MONTH);	
		int financialFromYear = 0;
		String staattus = "pending";	
		if(currentMonth<=2){
			financialFromYear = currentYear - 1;			
		}else{
			financialFromYear = currentYear;		
		}	
		System.out.println("Inside dao1 admin11");
		ArrayList<Object> listOfDetail = new ArrayList<Object>();
		HashMap<String, Object> leavehistory = new HashMap<>();
		Session session = null;			
		Transaction tr=null;
		List<Staff> users = null;
		try {
			 session = this.sf.getCurrentSession();			
			 tr=session.beginTransaction();	
			System.out.println("Inside dao1 admin22");
			 Query query = session.createQuery("From Financialyear WHERE fromyear=:searchA");
		        List<Financialyear> financialyears = query.setParameter("searchA", financialFromYear).list();
 		Integer fid = financialyears.get(0).getFinancialyearid();
 		Query query2 = session.createQuery("FROM EmployeeLeaveTransaction WHERE financialyearid=:searchA AND status=:searchB");
 		List<EmployeeLeaveTransaction> employeeLeaveTransaction = query2.setParameter("searchA", fid).setParameter("searchB", staattus).list();
 			System.out.println("SIZE="+employeeLeaveTransaction.size());	
 		System.out.println("Inside dao1 admin33");
 		if (employeeLeaveTransaction!=null && !employeeLeaveTransaction.isEmpty()) {
 			leavehistory.put("LeaveTransaction", employeeLeaveTransaction);
			Query query5 = session.createQuery("FROM Staff");
			 users = query5.list();
			
			  String json = jsonViewObjectMapper.writeValueAsString(com.monitorjbl.json.JsonView.with(users).onClass(Staff.class, 
						com.monitorjbl.json.Match.match().exclude("clients")));
			 ArrayList<Staff> convertedValue = objectMapper.readValue(json,new TypeReference<ArrayList<Staff>>() {});
			 users = convertedValue;    
			
			
			leavehistory.put("EmployeeDetails", users);
			 Query query3 = session.createQuery("FROM Leavedetails WHERE financialyear_id=:searchA");
				List<Leavedetails>	leavedetails = query3.setParameter("searchA", fid).list();
				leavehistory.put("Leavedetails", leavedetails);
				Query query4 = session.createQuery("FROM EmployeeFiscalYearLeaveDetails WHERE financialyear_id=:searchA ");
			List<EmployeeFiscalYearLeaveDetails> employeeFiscalYearLeaveDetails = query4.setParameter("searchA", fid).list();
				leavehistory.put("EmployeeFiscalYearLeaveDetails", employeeFiscalYearLeaveDetails);
			listOfDetail.add(leavehistory);
			status.put("result", listOfDetail);
		} else {
			status.put("result",new ArrayList<EmployeeLeaveTransaction>());
		}
 		tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("status",false);
			status.put("reason","Error happend");
			status.put("originalErrorMsg", e.getMessage());
		} finally{
			   if(session !=null &&session.isOpen()){
				   session.close();
			   }
			   }
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject getEmployeeLeaveListByYear() {
		System.out.println("Inside dao1");
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
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, 3);
	    int year = c.get(Calendar.YEAR);
	    int month = c.get(Calendar.MONTH);  
	    int day = 1;
	    month = month-3;
	    c.set(year, month, day);
	    int numOfDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
	    System.out.println("First Day of month: " + c.getTime());
	    Date from = c.getTime();
	    System.out.println("FROM DATE="+from);
	    c.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth-1);
	    System.out.println("Last Day of month: " + c.getTime());
	    System.out.println("get next year");
	    month = month+11;
	    c.set(year, month, day);
	     numOfDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
	    System.out.println("First Day of month: " + c.getTime());
	    c.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth-2);
	    System.out.println("Last Day of month: " + c.getTime());
	    Date to = c.getTime();
	    System.out.println("TO DATE="+to);
		ArrayList<Object> listOfDetails = new ArrayList<Object>();
		Session session = null;			
		Transaction tr=null;	
		try {
			 session = this.sf.getCurrentSession();			
			 tr=session.beginTransaction();	
    		Query query = session.createQuery("FROM  EmployeeLeaveTransaction WHERE creationDate >= :searchA AND creationDate <= :searchB ");
    		List<EmployeeLeaveTransaction> leaveTransaction = query.setParameter("searchA", from).setParameter("searchB", to).list();
    		Integer empid = leaveTransaction.get(0).getEmployee_id();
    		if (leaveTransaction!=null && !leaveTransaction.isEmpty()) {
    			HashMap<String, Object> leaveRequest = new HashMap<>();
    			leaveRequest.put("leaveTransaction", leaveTransaction);
    			 Query query2 = session.createQuery("From Financialyear WHERE fromyear=:searchA");
			        List<Financialyear> financialyears = query2.setParameter("searchA", financialFromYear).list();
	    		Integer fid = financialyears.get(0).getFinancialyearid();
			        Query query3 = session.createQuery("FROM Leavedetails WHERE financialyear_id=:searchA");
			        List<Leavedetails> leavedetails = query3.setParameter("searchA", fid).list();
		        leaveRequest.put("leavedata", leavedetails);
		        Query query4 = session.createQuery("FROM EmployeeFiscalYearLeaveDetails");
		        List<EmployeeFiscalYearLeaveDetails> employeeFiscalYearLeaveDetails = query4.list();
		        leaveRequest.put("employeeFiscalYear", employeeFiscalYearLeaveDetails);
		       //Query query5 = se
		        listOfDetails.add(leaveRequest);
		        status.put("result", listOfDetails);
	  } else {
		status.put("result",new ArrayList<EmployeeLeaveTransaction>());
	 }		
		} catch (Exception e) {
			e.printStackTrace();
			status.put("status",false);
			status.put("reason","Error happend");
			status.put("originalErrorMsg", e.getMessage());
		} finally{
			   if(session !=null &&session.isOpen()){
				   session.close();
			   }
			   }
		return status;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getEmployeeFiscalyearDetailByEmpId(JSONObject employeeid) {
		JSONObject status = new JSONObject();
		status.put("status",true);
		Integer empid = (Integer)employeeid.get("employee_id");
		Calendar cc = Calendar.getInstance();					
		System.out.println("Current Date : "+cc.getTime().toGMTString());			
		int currentYear = cc.get(Calendar.YEAR);		
		int currentMonth = cc.get(Calendar.MONTH);			
		int financialFromYear = 0;		
		if(currentMonth<=2){
			financialFromYear = currentYear - 1;		
		}else{
			financialFromYear = currentYear;		
		}	
		Session session = null;			
		Transaction tr=null;	
		try {
			 session = this.sf.getCurrentSession();			
			 tr=session.beginTransaction();	
			 Query query2 = session.createQuery("From Financialyear WHERE fromyear=:searchA ");
		       List<Financialyear> financialyears = query2.setParameter("searchA", financialFromYear).list();		     
		       Integer finid = financialyears.get(0).getFinancialyearid();
		        Query query3 = session.createQuery("FROM EmployeeFiscalYearLeaveDetails WHERE financialyear_id=:searchA AND employee_id=:searchB");
		        List<EmployeeFiscalYearLeaveDetails> leavedetails = query3.setParameter("searchA", finid).setParameter("searchB", empid).list();
		if (leavedetails!=null && !leavedetails.isEmpty()) {
			status.put("result", leavedetails);
		} else {
			status.put("result",new ArrayList<Staff>());
		}
		        tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("status",false);
			status.put("reason","Error happend");
			status.put("originalErrorMsg", e.getMessage());
			
		} finally{
			   if(session !=null &&session.isOpen()){
				   session.close();
			   }
			   }
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject getFiscalyearDetailByEmpId(JSONObject fiscaldetail) {
		JSONObject status = new JSONObject();
		status.put("status",true);
		Session session = sf.openSession();			
		Transaction tr=session.beginTransaction();	
		Integer tomonth = (Integer)fiscaldetail.get("To_month");
		int toyr = tomonth-1900;Date from_date = null;
		return status;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject approveLeaverequestByAdmin(JSONObject approvaldata) {
		JSONObject status = new JSONObject();
		status.put("status",true);
		Calendar cc = Calendar.getInstance();					
		System.out.println("Current Date : "+cc.getTime().toGMTString());			
		int currentYear = cc.get(Calendar.YEAR);		
		int currentMonth = cc.get(Calendar.MONTH);	
		System.out.println("Year="+currentYear);
		System.out.println("Month="+currentMonth);
		int financialFromYear = 0;		
		if(currentMonth<=2){
			financialFromYear = currentYear - 1;
			System.out.println("FinancialFromYear"+financialFromYear);
		}else{
			financialFromYear = currentYear;
			System.out.println("FinancialFromYear"+financialFromYear);
		}	
		Integer eid = (Integer)approvaldata.get("employee_id");
		Integer lcfid = (Integer)approvaldata.get("leaveconfig_id");
		Integer id = (Integer)approvaldata.get("id");
		Integer did = (Integer)approvaldata.get("numberofdays");
		Integer fid = (Integer)approvaldata.get("financialyear_id");
	
		ArrayList<Object> listOfDetailss = new ArrayList<Object>();
		Session session = null;			
		Transaction tr=null;	
		try {
			 session = this.sf.getCurrentSession();			
			 tr=session.beginTransaction();	
			Query query = session.createQuery("FROM  EmployeeFiscalYearLeaveDetails WHERE employee_id=:searchA AND financialyear_id=:searchB AND leaveConfiguration_Id=:searchC");
			List<EmployeeFiscalYearLeaveDetails> fiscalYearLeaveDetails=query.setParameter("searchA", eid).setParameter("searchB", fid).setParameter("searchC", lcfid).list();	
			if(fiscalYearLeaveDetails!=null && !fiscalYearLeaveDetails.isEmpty()){
				HashMap<String, Object> leaveRequestData = new HashMap<>();				
				leaveRequestData.put("leavedatabyid",fiscalYearLeaveDetails);
			 Query query2 = session.createQuery("From Financialyear WHERE fromyear=:searchA ");
		       List<Financialyear> financialyears = query2.setParameter("searchA", financialFromYear).list();	     
		       Integer finid = financialyears.get(0).getFinancialyearid();
		        Query query3 = session.createQuery("FROM Leavedetails WHERE financialyear_id=:searchA AND id=:searchB");
		        List<Leavedetails> leavedetails = query3.setParameter("searchA", finid).setParameter("searchB", lcfid).list();
		        leaveRequestData.put("leavedetails", leavedetails);
		       listOfDetailss.add(leaveRequestData);
		       status.put("result", listOfDetailss);
			}else {
			status.put("result",new ArrayList<Staff>());
		}
		tr.commit();		
		} catch (Exception e) {
			e.printStackTrace();
			status.put("status",false);
			status.put("reason","Error happend");
			status.put("originalErrorMsg", e.getMessage());		
		} finally{
			   if(session !=null &&session.isOpen()){
				   session.close();
			   }
			   }
		return status;
	}
	//********Admin Approve  Employee leave request and update********//
	@SuppressWarnings("unchecked")
	public JSONObject approveEmployeeLeaveByAdmin(JSONObject getapprove)  {
		System.out.println("Inside dao1");
		JSONObject status = new JSONObject();
		status.put("status",true);				
		String leave_status = getapprove.get("status").toString(); 		
		Integer empid = (Integer)getapprove.get("employee_id");
		Integer lcfid = (Integer)getapprove.get("leaveconfig_id");
		Integer id = (Integer)getapprove.get("id");
		Integer day = (Integer)getapprove.get("numberofdays");				
		if(leave_status.equals("approve"))
		{
			// approve Logic
			    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				System.out.println("Apply day="+day);
				Integer fid = (Integer)getapprove.get("financialyear_id");
				System.out.println("="+empid);				
				System.out.println("="+lcfid);				
				Calendar cc = Calendar.getInstance();		
				cc.add(Calendar.MONTH,6);								
				int currentYear = cc.get(Calendar.YEAR);		
				int currentMonth = cc.get(Calendar.MONTH);			
				int FinancialFromYear = 0;			
				if(currentMonth<=2){
					FinancialFromYear = currentYear - 1;
				}else{
					FinancialFromYear = currentYear;
				}					
				Session session = null;			
				Transaction tr=null;	
				try {
					 session = this.sf.getCurrentSession();			
					 tr=session.beginTransaction();	
					//Date createdate =sdf.parse(getapprove.get("creationdate").toString());
					System.out.println("Inside dao1 try11");
					Query qur = session.createQuery("FROM EmployeeLeaveTransaction WHERE id=:searchA");
				List<EmployeeLeaveTransaction> leaveTransaction= qur.setParameter("searchA", id).list();
				Query qury = session.createQuery("FROM EmployeeFiscalYearLeaveDetails WHERE employee_id=:searchA AND financialyear_id=:searchB AND leaveConfiguration_Id=:searchC");
				List<EmployeeFiscalYearLeaveDetails> employeeFiscal = qury.setParameter("searchA", empid).setParameter("searchB", fid).setParameter("searchC", lcfid).list();
				System.out.println("after execute query");
				System.out.println("Inside dao1 try33");
			for (EmployeeLeaveTransaction employeeLeaveTransaction : leaveTransaction) {			
				Integer pid=employeeLeaveTransaction.getId();
				String statuss ="approved";
				Date dt =Calendar.getInstance().getTime();
				employeeLeaveTransaction.setId(pid);
				employeeLeaveTransaction.setStatus(statuss);
				employeeLeaveTransaction.setActionDate(Calendar.getInstance().getTime().toString());
				System.out.println("Inside merge 1EmployeeLeaveTransaction");
				session.merge(employeeLeaveTransaction);			
			}
			for (EmployeeFiscalYearLeaveDetails employeeFiscalYearLeaveDetails : employeeFiscal) {
				Integer fisid = employeeFiscalYearLeaveDetails.getId();
				Integer emppid = employeeFiscalYearLeaveDetails.getEmployee_id();
				Integer days = employeeFiscalYearLeaveDetails.getRemainingdays();
				System.out.println("DBDays="+days);
				days = days-day;
				System.out.println("Updated days="+days);
				employeeFiscalYearLeaveDetails.setId(fisid);
				employeeFiscalYearLeaveDetails.setEmployee_id(emppid);
				employeeFiscalYearLeaveDetails.setLeaveConfiguration_Id(lcfid);
				employeeFiscalYearLeaveDetails.setRemainingdays(days);
				System.out.println("Inside merge EmployeeFiscalYearLeaveDetails ");
				session.merge(employeeFiscalYearLeaveDetails);	
			}
				tr.commit();
				session.close();
				
				if (leaveTransaction != null && !leaveTransaction.isEmpty()) {
					status.put("result",leaveTransaction.get(0));	
				} else {
					status.put("result",new ArrayList<Staff>());
				}							
				}catch (Exception e) {
					status.put("status",false);
					status.put("reason","Error happend");
					status.put("originalErrorMsg", e.getMessage());
					e.printStackTrace();
				}
		}	
		else if(leave_status.equals("reject"))
		{
			// reject logic			
			Session session = sf.openSession();			
			Transaction tr=session.beginTransaction();				
			Query qur = session.createQuery("FROM EmployeeLeaveTransaction WHERE id=:searchA");
			List<EmployeeLeaveTransaction> leaveTransaction= qur.setParameter("searchA", id).list();
			
			for (EmployeeLeaveTransaction employeeLeaveTransaction : leaveTransaction) {				
				Integer pid=employeeLeaveTransaction.getId();
				employeeLeaveTransaction.setId(pid);
				employeeLeaveTransaction.setStatus(leave_status);
				employeeLeaveTransaction.setActionDate(Calendar.getInstance().getTime().toString());
				session.merge(employeeLeaveTransaction);
				tr.commit();
				session.close();
				status.put("message", "Leave request rejected");
			}
		}
		else
		{
			System.err.println("Unknown leave status by admin. It should be either approve or reject. But Found "+leave_status);
			status.put("status",false);
			status.put("reason","Unknown leave status by admin. It should be either approve or reject. But Found "+leave_status);
		}		
		return status;
	}
	
	//**********NEW TYPE LEAVE Request APPROVE By ADMIN START*********////
	
		@SuppressWarnings("unchecked")
		public JSONObject approveLeaverequestByEmpByAdmin(JSONObject approveleave)  {
			System.out.println("Inside dao1");
			System.out.println("Approval Inside dao1");	
			JSONObject status = new JSONObject();
			status.put("status",true);
			SimpleDateFormat sddf = new SimpleDateFormat("dd-MM-yyyy");
			String leave_status = approveleave.get("status").toString(); 		
			Integer empid = (Integer)approveleave.get("employee_id");
			Integer lcfid = (Integer)approveleave.get("leaveConfigurationId");
			Integer id = (Integer)approveleave.get("id");
			Integer day = (Integer)approveleave.get("totaldays");	
			System.out.println("Requested day="+day);
			Integer finid = (Integer)approveleave.get("financialyearid");
			Date empfrom =null;Date empto = null;
			String leavetype =null;
			ArrayList<Date> halfyearly = new ArrayList<Date>();
			ArrayList<Date> quarterly = new ArrayList<Date>();
			ArrayList<Date> monthly = new ArrayList<Date>();
			Integer fisid = 0;Integer emppid =0;Integer days =0; Integer lctid = 0;Integer tid = 0;
			Date dbfrom =null;Date dbto =null;
			List<EmployeeFiscalYearLeaveDetails> employeeFiscal = null;
			List<EmployeeLeaveTransaction> leaveTransaction=null;
			 List<Leavedetails> leave =null;
			try {
				 empfrom = sddf.parse(approveleave.get("FromDate").toString());
				 empto = sddf.parse(approveleave.get("ToDate").toString());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			if(leave_status.equals("approve"))
			{// approve Log   
				System.out.println("##$$Inside approve##$$=");
				    //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					System.out.println("Apply day="+day);
					//Integer fid = (Integer)approveleave.get("financialyearid");
					System.out.println("="+empid);				
					System.out.println("="+lcfid);				
					Calendar cc = Calendar.getInstance();		
					cc.add(Calendar.MONTH,6);								
					Session session = null;			
					Transaction tr=null;	
					try {
						System.out.println("##$$Inside approve try##$$=");
						 session = this.sf.getCurrentSession();			
						 tr=session.beginTransaction();		
						System.out.println("Inside dao1 try11");
			           Query query = session.createQuery("FROM Leavedetails WHERE financialyear_id=:searchA AND id=:searchB");		
			           leave = query.setParameter("searchA", finid).setParameter("searchB", lcfid).list();
			         leavetype = leave.get(0).getLeave_days_type(); 	
			         System.out.println("Admin approve  Employe lave types="+leavetype);
						Query qur = session.createQuery("FROM EmployeeLeaveTransaction WHERE id=:searchA");
					 leaveTransaction= qur.setParameter("searchA", id).list();
				 tid = leaveTransaction.get(0).getId();
				 if(tid ==null){
					 status.put("status", false);
					 status.put("Reason", "Employee  leave request not found");
					 return status;
				 }
					System.out.println("LETR ID="+tid);
					
					Query qury = session.createQuery("FROM EmployeeFiscalYearLeaveDetails WHERE employee_id=:searchA AND financialyear_id=:searchB AND leaveConfiguration_Id=:searchC");
					 employeeFiscal = qury.setParameter("searchA", empid).setParameter("searchB", finid).setParameter("searchC", lcfid).list();
					
					 System.out.println("EmployeeFiscal SIZE ***="+ employeeFiscal.size());
					 Integer ltid = 0;
					lctid = employeeFiscal.get(0).getRemainingdays();
					System.out.println("GET Db Reamaning DAYS="+lctid);
					for (EmployeeFiscalYearLeaveDetails employeeFiscalYearLeaveDetails : employeeFiscal) {
						 fisid = employeeFiscalYearLeaveDetails.getId();
						 emppid = employeeFiscalYearLeaveDetails.getEmployee_id();
						 days = employeeFiscalYearLeaveDetails.getRemainingdays();
						 ltid = employeeFiscalYearLeaveDetails.getLeaveConfiguration_Id();
						 dbfrom = employeeFiscalYearLeaveDetails.getFromDate();
						 dbto=employeeFiscalYearLeaveDetails.getToDate();
								halfyearly.add(dbfrom);halfyearly.add(dbto);
								quarterly.add(dbfrom);quarterly.add(dbto);
								monthly.add(dbfrom);monthly.add(dbto);
					}
				
					tr.commit();
					
					}catch (Exception e) {
						status.put("status",false);
						status.put("reason","Error happend");
						status.put("originalErrorMsg", e.getMessage());
						e.printStackTrace();
					}/*finally{
						if(session!=null &&session.isOpen()){
							session.close();
						}
					}*/
					
				if(leavetype.equals("yearly")){
					if((empfrom.after(dbfrom))&&(empto.before(dbto))){
						
						Session ses =null;
						Transaction trns =null;
						try {
							ses = this.sf.getCurrentSession();
							 trns = ses.beginTransaction();
							for (EmployeeLeaveTransaction employeeLeaveTransaction : leaveTransaction) {							
								Integer pid=employeeLeaveTransaction.getId();
								String statuss ="approved";								
								employeeLeaveTransaction.setId(pid);
								employeeLeaveTransaction.setStatus(statuss);
								employeeLeaveTransaction.setActionDate(Calendar.getInstance().getTime().toString());
								ses.merge(employeeLeaveTransaction);
								
							}
							for (EmployeeFiscalYearLeaveDetails efld : employeeFiscal) {							
								System.out.println("DBDays="+lctid);
								lctid = lctid-day;	
								efld.setId(fisid);
								efld.setEmployee_id(emppid);
								efld.setLeaveConfiguration_Id(lcfid);
								efld.setRemainingdays(lctid);						
								ses.merge(efld);	
							}
							trns.commit();
						} catch (Exception e) {	
							e.printStackTrace();
						}finally{
						     if(ses!=null&&ses.isOpen()){
						    	 ses.close();
						     }
						}
						
					}						
				}
                if(leavetype.equals("halfyearly")){//start  
                	
                    int ficalsize = employeeFiscal.size();                  
         		     EmployeeFiscalYearLeaveDetails matchedDetails = null;      		             		         		          		              		     
       		    	 for(int j=0;j<ficalsize;j++){
       		    		matchedDetails = employeeFiscal.get(j);       		    		 
       		    		Session sessionh = null;
    					Transaction trans = null;   					     					
    					if( (matchedDetails.getFromDate().compareTo(empto) == -1 || matchedDetails.getFromDate().compareTo(empto) == 0) &&  (matchedDetails.getToDate().compareTo(empto) == 1 || matchedDetails.getToDate().compareTo(empto) == 0) )
    					{
       		    			System.out.println("Enter exact matched  halfyearly");   
       		    			System.out.println("Exact matched  halfyearly Id="+matchedDetails.getId());
       		    			System.out.println("Before update  total days=="+matchedDetails.getRemainingdays());
                    		try {
                    			sessionh =this.sf.getCurrentSession();
                    					trans =sessionh.beginTransaction();
    							for (EmployeeLeaveTransaction employeeLeaveTransaction : leaveTransaction) {   								
    								Integer pid=employeeLeaveTransaction.getId();
    								String statuss ="approved";								
    								employeeLeaveTransaction.setId(pid);
    								employeeLeaveTransaction.setStatus(statuss);
    								employeeLeaveTransaction.setActionDate(Calendar.getInstance().getTime().toString());
    								sessionh.merge(employeeLeaveTransaction); 
    								
    							}	
    							
    							Integer eid =matchedDetails.getEmployee_id();
    							Integer dayy = matchedDetails.getRemainingdays();   							
    							dayy = dayy-day;    							
    							matchedDetails.setEmployee_id(eid);
    							matchedDetails.setRemainingdays(dayy);
    							matchedDetails.setLeaveConfiguration_Id(lcfid);
    							sessionh.merge(matchedDetails);
    							   	
    							status.put("result", "Leave request approved");
    							trans.commit();                   		
                    		} catch (Exception e) {
                    			e.printStackTrace();
    						}finally{
    							if(sessionh!=null&&sessionh.isOpen()){
        							sessionh.close();
        						}
    						}                  		 
       		    		 }      		    	
       		    	 }      		    	    		    	      		        		   
				}//Halfyearly End
                if(leavetype.equals("quarterly")){              
                	  int ficalsize = employeeFiscal.size();                   
          		     EmployeeFiscalYearLeaveDetails matchedDetails = null;      		              		 
          		   for(int j=0;j<ficalsize;j++){
      		    		matchedDetails = employeeFiscal.get(j);     		    		      		    		
      		    		Session sessionq = null;
   					Transaction trans = null;   					
   					if( (matchedDetails.getFromDate().compareTo(empto) == -1 || matchedDetails.getFromDate().compareTo(empto) == 0) &&  (matchedDetails.getToDate().compareTo(empto) == 1 || matchedDetails.getToDate().compareTo(empto) == 0) )
					{   						
   						System.out.println("EFISID="+matchedDetails.getId());
                		try {
                			sessionq =this.sf.getCurrentSession();
                					trans =sessionq.beginTransaction();
							for (EmployeeLeaveTransaction employeeLeaveTransaction : leaveTransaction) {						
								Integer pid=employeeLeaveTransaction.getId();
								String statuss ="approved";								
								employeeLeaveTransaction.setId(pid);
								employeeLeaveTransaction.setStatus(statuss);
								employeeLeaveTransaction.setActionDate(Calendar.getInstance().getTime().toString());
								sessionq.merge(employeeLeaveTransaction);						
							}	
							Integer eid =matchedDetails.getEmployee_id();
							Integer dayy = matchedDetails.getRemainingdays();						
							dayy = dayy-day;							
							matchedDetails.setEmployee_id(eid);
							matchedDetails.setRemainingdays(dayy);
							matchedDetails.setLeaveConfiguration_Id(lcfid);
							sessionq.merge(matchedDetails);	
							status.put("result", "Leave request approved");
							trans.commit();                   		
                		} catch (Exception e) {
                			e.printStackTrace();
						}finally{
							if(sessionq!=null&&sessionq.isOpen()){
    							sessionq.close();
    						}
						}  
					}
          		   }        		   
                	//Minimise code end                                                                                        
                	         
          		   }
               
                if(leavetype.equals("monthly")){
                	//Date apr = monthly.get(0);Date aprend = monthly.get(1);               	        	
                	Date endd = findEndDate(empto);     		      	       
      		       int ficalsizee = employeeFiscal.size();
      		     EmployeeFiscalYearLeaveDetails matchedDetailss = null;    		           		      		     
      		       for(int i=0;i<ficalsizee;i++)
      		       {      		    	   
      		    	 matchedDetailss = employeeFiscal.get(i);
      		    	
      		    	 if((matchedDetailss.getToDate().before(endd))||(matchedDetailss.getToDate().compareTo(endd)==0))	    		 
      		    	 {         		    		  
      		    		 Session sessionm = null;
            		    	Transaction transm = null;
           		    	   // Open session and update leave transaction
           		    	   System.out.println("Founded "+matchedDetailss.getLeaveConfiguration_Id()+" Id : "+matchedDetailss.getId());
           		    	   try {							
           		    		 sessionm = this.sf.getCurrentSession();
							 transm = sessionm.beginTransaction();
							for (EmployeeLeaveTransaction employeeLeaveTransaction : leaveTransaction) {
								
								Integer pid=employeeLeaveTransaction.getId();
								String statuss ="approved";								
								employeeLeaveTransaction.setId(pid);
								employeeLeaveTransaction.setStatus(statuss);
								employeeLeaveTransaction.setActionDate(Calendar.getInstance().getTime().toString());
								sessionm.merge(employeeLeaveTransaction);
								
							}
							/*Integer da = matchedDetailss.getRemainingdays();
							da=da-days;*/
							Integer elfid = matchedDetailss.getId();
							Integer eid =matchedDetailss.getEmployee_id();
							Integer dayy = matchedDetailss.getRemainingdays();
							dayy = dayy-day;
							matchedDetailss.setEmployee_id(eid);
							matchedDetailss.setRemainingdays(dayy);
							matchedDetailss.setLeaveConfiguration_Id(lcfid);
							sessionm.merge(matchedDetailss);
							
							status.put("result", "Leave request approved");							
							transm.commit();
						} catch (Exception e) {
							status.put("status",false);
							status.put("reason","Error happend");
							status.put("originalErrorMsg", e.getMessage());
							e.printStackTrace();
						}finally{
							if(sessionm!=null&&sessionm.isOpen()){
								sessionm.close();
							}
						}
      		    		 break;	
      		    	 }     		    	 
      		       }    		       
      		       if(matchedDetailss != null) {     		         		    	   
      		       } else {
      		    	   System.out.println("Something went wrong");
      		       }      		                                  	
                }				
				if (leaveTransaction != null && !leaveTransaction.isEmpty()) {
						status.put("result",leaveTransaction.get(0));	
					} else {
						status.put("result",new ArrayList<Staff>());
					}											
			}	
			else if(leave_status.equals("reject")){
				// reject logic			
				Session session = null;			
				Transaction tr=null;	
				try{
					session=this.sf.getCurrentSession();
					tr = session.beginTransaction();
				Query qur = session.createQuery("FROM EmployeeLeaveTransaction WHERE id=:searchA");
				List<EmployeeLeaveTransaction> leaveTransactionn= qur.setParameter("searchA", id).list();			
				for (EmployeeLeaveTransaction employeeLeaveTransaction : leaveTransactionn) {				
					Integer pid=employeeLeaveTransaction.getId();
					employeeLeaveTransaction.setId(pid);
					employeeLeaveTransaction.setStatus(leave_status);
					employeeLeaveTransaction.setActionDate(Calendar.getInstance().getTime().toString());
					session.merge(employeeLeaveTransaction);
					tr.commit();					
					status.put("message", "Leave request rejected");
				}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					if(session!=null&&session.isOpen()){
						session.close();
					}
				}
			}
			else
			{
				System.err.println("Unknown leave status by admin. It should be either approve or reject. But Found "+leave_status);
				status.put("status",false);
				status.put("reason","Unknown leave status by admin. It should be either approve or reject. But Found "+leave_status);
			}		
			return status;
		}

	private Date findEndDate(Date empto) {
		// End Date 
		//*******
		Calendar end = Calendar.getInstance();
		end.setTime(empto);
		int ed = end.getActualMaximum(Calendar.DATE);
  			  end.set(Calendar.DATE, ed);
  		       Date endd = end.getTime();
		return endd;
	}	
		
		//**********NEW TYPE LEAVE Request APPROVE By ADMIN END*********////
	////
	@SuppressWarnings("unchecked")
	public JSONObject getEmployeeById(JSONObject employeeid) {
		JSONObject status = new JSONObject();
		status.put("status",true);		
		Integer empid = (Integer)employeeid.get("employee_id");
		Session session = null;			
		Transaction tr=null;	
		try{
			session=this.sf.getCurrentSession();
			tr = session.beginTransaction();
			Query query = session.createQuery("FROM Staff us WHERE us.user_id=:searchA");
			List<Staff> user = query.setParameter("searchA", empid).list();		
			if (user!=null && !user.isEmpty()) {
				status.put("result",user.get(0));
			} else {
				status.put("result",new ArrayList<Staff>());
			}
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("status",false);
			status.put("reason","Error happend");
			status.put("originalErrorMsg", e.getMessage());			
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject getLeavedetailsByFid() {
		JSONObject status = new JSONObject();
		status.put("status",true);
		Integer finanacialYearId = getCurrentFinancialYearId();
	
		Session session = null;			
		Transaction tr=null;
		 List<Financialyear> financialyears = null; 
		try{
			session=this.sf.getCurrentSession();
			tr = session.beginTransaction();
			 Query query2 = session.createQuery("From Financialyear WHERE financialyearid=:searchA ");
		     financialyears = query2.setParameter("searchA", finanacialYearId).list();	
		     if (financialyears!=null && !financialyears.isEmpty()) {				
		    	 Integer finid = financialyears.get(0).getFinancialyearid();
			        Query query3 = session.createQuery("FROM Leavedetails WHERE financialyear_id=:searchA");
			        List<Leavedetails> leavedetails = query3.setParameter("searchA", finid).list();
			        tr.commit();	
			       if (leavedetails!=null && !leavedetails.isEmpty()) {
			    	   status.put("status",leavedetails);
				} else {
					//status.put("status",new ArrayList<Leavedetails>());
					status.put("Reason","Admin still not configured Leave details");
					status.put("status",false);
				}
			} else {
				status.put("Reason","Admin still not configured Leave details");
				status.put("status",false);				
			}		      		        		
		} catch (Exception e) {
			e.printStackTrace();
			status.put("status",false);
			status.put("reason","Error happend");
			status.put("originalErrorMsg", e.getMessage());			
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
		return status;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getParticularLeavedetailsByLid(JSONObject leaveconfig) {
		JSONObject status = new JSONObject();
		status.put("status",true);
		Calendar cc = Calendar.getInstance();					
					
		int currentYear = cc.get(Calendar.YEAR);		
		int currentMonth = cc.get(Calendar.MONTH);	
		Integer leconfid=(Integer)leaveconfig.get("leaveconfig_id");
		Integer empid=(Integer)leaveconfig.get("employee_id");
		int financialFromYear = 0;	
		if(currentMonth<=2){
			financialFromYear = currentYear - 1;
			
		}else{
			financialFromYear = currentYear;
			
		}
		Session session = null;			
		Transaction tr=null;	
		try{
			session=this.sf.getCurrentSession();
			tr = session.beginTransaction();
			 Query query2 = session.createQuery("From Financialyear WHERE fromyear=:searchA ");
		       List<Financialyear> financialyears = query2.setParameter("searchA", financialFromYear).list();		     
		       Integer finid = financialyears.get(0).getFinancialyearid();
		       Query query = session.createQuery("FROM EmployeeFiscalYearLeaveDetails WHERE financialyear_id=:searchA AND employee_id=:searchB AND leaveConfiguration_Id=:searchC");
		       List<EmployeeFiscalYearLeaveDetails> fiscalYearLeaveDetail = query.setParameter("searchA", finid).setParameter("searchB", empid).setParameter("searchC", leconfid).list();
		       /* Query query3 = session.createQuery("FROM Leavedetails WHERE financialyear_id=:searchA AND id=:searchB");
		        List<Leavedetails> leavedetails = query3.setParameter("searchA", finid).setParameter("searchB", leconfid).list();*/
		       if (fiscalYearLeaveDetail!=null && !fiscalYearLeaveDetail.isEmpty()) {
		    	   status.put("status",fiscalYearLeaveDetail);
			} else {
				status.put("status",new ArrayList<EmployeeFiscalYearLeaveDetails>());
			}
		        tr.commit();			
		} catch (Exception e) {
			e.printStackTrace();
			status.put("status",false);
			status.put("reason","Error happend");
			status.put("originalErrorMsg", e.getMessage());			
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}		
		return status;
	}	
	 /////******Bind All Data1 Start******/////
	@SuppressWarnings("unchecked")
		public JSONObject getAllLeaveRequestByAdmin(){
		JSONObject status = new JSONObject();
		status.put("status", true);
		Integer financialYearId = getCurrentFinancialYearId();	
		HashMap<String, Object> resultDatas = new HashMap<String, Object>();
		List<EmployeeLeaveTransaction> value = null;
		value = getData();
		HashSet<Integer> employe = new HashSet<Integer>();
		HashSet<Integer> leave = new HashSet<Integer>();
		if (value != null) {
			ObjectMapper om = new ObjectMapper();
			om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
			ArrayList<Object> object = om.convertValue(value, ArrayList.class);
			System.out.println("SIZE=" + object);
			int objectSize = object.size();
			for (int i = 0; i < objectSize; i++) {
				System.out.println("Convert object to java pojo EmployeeLeaveTransaction");
				HashMap<String, Object> data = om.convertValue(object.get(i),new TypeReference<HashMap<String, Object>>() {});
				employe.add((Integer) data.get("employee_id"));
				leave.add((Integer) data.get("leaveConfigurationId"));
				object.set(i, data);
			}
			Iterator<Integer> eid = (Iterator<Integer>) employe.iterator();
			while (eid.hasNext()) {
				Integer integer = (Integer) eid.next();
				System.out.println("EMP Id=" + integer);
			}
			Session session = null;
			Transaction tr = null;
			List<Staff> users = null;
			try {
				session = this.sf.getCurrentSession();
				tr = session.beginTransaction();
				Criteria crt = session.createCriteria(Staff.class);
				crt.add(Property.forName("user_id").in(employe.toArray()));
				users = crt.list();
				
				String json = jsonViewObjectMapper.writeValueAsString(com.monitorjbl.json.JsonView.with(users).onClass(
								Staff.class,com.monitorjbl.json.Match.match().exclude("clients")));
				ArrayList<Staff> convertedValue = objectMapper.readValue(json,new TypeReference<ArrayList<Staff>>() {});
				users = convertedValue;

				Query query = session.createQuery("From Financialyear WHERE financialyearid=:searchA");
				List<Financialyear> financialyears = query.setParameter("searchA", financialYearId).list();
				Integer fid = financialyears.get(0).getFinancialyearid();
				Query query3 = session
						.createQuery("FROM Leavedetails WHERE financialyear_id=:searchA");
				List<Leavedetails> leavedetails = query3.setParameter("searchA", fid).list();
				Criteria criteria = session.createCriteria(EmployeeFiscalYearLeaveDetails.class);
				criteria.add(Property.forName("employee_id").in(employe.toArray()));
				criteria.add(Property.forName("leaveConfiguration_Id").in(leave.toArray()));
				criteria.add(Restrictions.eq("financialyear_id", fid));
				List<EmployeeFiscalYearLeaveDetails> fiscal = criteria.list();
				System.out.println("fiscal.size() Size=" + fiscal.size());
				// New Code
				System.out.println("New Code");
				int size = object.size();
				int users_size = users.size();
				int leave_details_size = leavedetails.size();
				int fisal_size = fiscal.size();
				HashMap<String, Object> leaveTransaction = null;
				for (int i = 0; i < size; i++) {
					leaveTransaction = (HashMap<String, Object>) object.get(i);
					int emppid = (Integer) leaveTransaction.get("employee_id");
					int leavecofid = (Integer) leaveTransaction.get("leaveConfigurationId");
					for (Integer j = 0; j < users_size; j++) {
						Staff user_detail = users.get(j);
						if (emppid == user_detail.getStaffId()) {
							leaveTransaction.put("employeeinfo", user_detail);
							break;
						}
					}
					for (Integer k = 0; k < leave_details_size; k++) {
						Leavedetails leavedata = leavedetails.get(k);
						if (leavecofid == leavedata.getId()) {
							leaveTransaction.put("LeaveData", leavedata);
							break;
						}
					}
					for (Integer l = 0; l < fisal_size; l++) {
						EmployeeFiscalYearLeaveDetails fiscalData = fiscal.get(l);
						if (emppid == fiscalData.getEmployee_id()
								&& leavecofid == fiscalData.getLeaveConfiguration_Id()) {
							leaveTransaction.put("FiscalData", fiscalData);
							break;
						}
					}
				}
				System.out.println("New Code2");
				resultDatas.put("result", leaveTransaction);
				status.put("result", resultDatas);
				status.put("result", object);

				System.out.println("New Code3");
				tr.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
		}else{//Admin STILL not configure Financial year table
			System.err.println("Employee not yet to apply leave request.Leaverequest table is empty");
			status.put("reason", "Employee not yet to apply leave request. Leaverequest table is empty");
			status.put("status", false);
			return status;//
		}
		return status;
		}		
		 /////******Bind All Data1 End******/////
		
	////**Only ALL pendingdata START***////
	@SuppressWarnings("unchecked")
		public JSONObject getAllPendingLeaveRequestByAdmin() {
			System.out.println("Inside dao1");
			JSONObject status = new JSONObject();
			status.put("status",true);
			Session session = null; Transaction td = null;	
			Integer financialId = getCurrentFinancialYearId();
			
				HashMap<String, Object> resultDatas = new HashMap<String, Object>();
				List<EmployeeLeaveTransaction> value = null;				
				value = getPendingData();		
				HashSet<Integer> employe = new HashSet<Integer>();
				HashSet<Integer> leave = new HashSet<Integer>();
				int empid =0;
				int leaveid = 0;
			try {							
				if(value!=null&&!value.isEmpty()){
					  ObjectMapper om = new ObjectMapper();           
				    om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				    ArrayList object = om.convertValue(value, ArrayList.class);
				    System.out.println("SIZE="+object);         				   
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
				   List<Staff> users = null;
					try {
						System.out.println("Inside dao12");
						 session = this.sf.getCurrentSession();				
							td = session.beginTransaction();	
						 Criteria crt = session.createCriteria(Staff.class);
						    crt.add(Property.forName("user_id").in(employe.toArray()));
						     users =  crt.list();							     
						     String json = jsonViewObjectMapper.writeValueAsString(com.monitorjbl.json.JsonView.with(users).onClass(Staff.class, 
										com.monitorjbl.json.Match.match().exclude("clients")));
							 ArrayList<Staff> convertedValue = objectMapper.readValue(json,new TypeReference<ArrayList<Staff>>() {});
							 users = convertedValue;						     				     
					   Query query = session.createQuery("From Financialyear WHERE 	financialyearid=:searchA");
				        List<Financialyear> financialyears = query.setParameter("searchA", financialId).list();
				Integer fid = financialyears.get(0).getFinancialyearid();
				 Query query3 = session.createQuery("FROM Leavedetails WHERE financialyear_id=:searchA");
					List<Leavedetails>	leavedetails = query3.setParameter("searchA", fid).list();				
					Criteria criteria = session.createCriteria(EmployeeFiscalYearLeaveDetails.class);			
					criteria.add(Property.forName("employee_id").in(employe.toArray()));
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
					System.out.println("Inside dao33 Merge datas");
					for(int i=0;i<size;i++){
						 leaveTransaction =(Map<String,Object>)object.get(i);	
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
				
					td.commit();
					} catch (Exception e) {
						status.put("status",false);
						status.put("reason","Error happend");
						status.put("originalErrorMsg", e.getMessage());
						e.printStackTrace();
					}finally{
						if(session!=null&&session.isOpen()){
							session.close();
						}
					}
				}else{				
					System.err.println("EmployeeLeaveRequest table is empty");				
					status.put("reason","Employee not yet to request Leave.So EmployeeLeaveRequest table is empty");
					status.put("status",false);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return status;
		}
		////**Only ALL pendingdata END***////
		////**Only pendingdata START***////
	@SuppressWarnings("unchecked")
		public JSONObject getAllPendingRequestByEidByAdmin(JSONObject employe) {
			System.out.println("");
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
			Session session = null;
			Transaction td = null;
				
			HashMap<String, Object> resultDatas = new HashMap<String, Object>();
			List<EmployeeLeaveTransaction> value = null;
			System.out.println("");
			Integer epmid = (Integer)employe.get("employee_id");
			
			//value=getEmployeePendingData(employe);
			value = getEmployeePendingData(epmid);//call method		
			
			HashSet<Integer> employee = new HashSet<Integer>();
			HashSet<Integer> leave = new HashSet<Integer>();
			int empid =0;
			int leaveid = 0;
			 ArrayList object = null;
			if(value!=null &&value.size()>0){
				
				  ObjectMapper om = new ObjectMapper();           
	          om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	          try{
	        	  object = om.convertValue(value, ArrayList.class);
		          System.out.println("SIZE="+object);	       
	          }catch(NullPointerException npe){
	        	  npe.printStackTrace();
	          }
	                
	         
	          int objectSize = object.size();
			     for(int i=0;i<objectSize;i++)
			     {
			    	 System.out.println("Convert object to java pojo EmployeeLeaveTransaction");
			    	  HashMap<String, Object> data = om.convertValue( object.get(i) , new TypeReference<HashMap<String, Object>>() {});
			    	  
			    	  for (Entry<String, Object> entry : data.entrySet()) {
			    		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			    		}
			    	  employee.add((Integer)data.get("employee_id"));
			    	leave.add((Integer)data.get("leaveConfigurationId"));
			    	//
			    	empid  = (Integer)data.get("employee_id");
			    	leaveid = (Integer)data.get("leaveConfigurationId");
			    	//
			    	
			    	object.set(i, data);			    	
			     }
			     
	          
	          Iterator<Integer> eid = (Iterator<Integer>)employee.iterator();
	         while (eid.hasNext()) {
					Integer integer = (Integer) eid.next();
					System.out.println("EMP Id="+integer);					
				}
	            
	         
				System.out.println("Inside dao1 admin11 merge"); 
				 List<Staff> users = null;
				try {
					session = this.sf.getCurrentSession();
					td = session.beginTransaction();
					 Query querya = session.createQuery("FROM Staff WHERE user_id=:searchA");
			          users = querya.setParameter("searchA", empid).list(); 
	//		         
			          String json = jsonViewObjectMapper.writeValueAsString(com.monitorjbl.json.JsonView.with(users).onClass(Staff.class, 
								com.monitorjbl.json.Match.match().exclude("clients")));
					 ArrayList<Staff> convertedValue = objectMapper.readValue(json,new TypeReference<ArrayList<Staff>>() {});
					 users = convertedValue;
	//		          
				   Query query = session.createQuery("From Financialyear WHERE fromyear=:searchA");
			        List<Financialyear> financialyears = query.setParameter("searchA", financialFromYear).list();
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
				status.put("Reason", "Employee not yet to apply leave reqest!.So it is empty");
				System.err.println("Employee not yet to apply leave reqest!.So it is empty");
			}
			return status;
		}
	//**Only pendingdataEND***//
	
	@SuppressWarnings("deprecation")
	private Date getDateObjectByYearMonth(Integer year,Integer Month){		
		return new Date(year-1900,Month,1);
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getCurrentFinancialYear(){
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		
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
			if(session!= null && session.isOpen() )
				session.close();
		}
		return result;
	}
	
	
	/*@SuppressWarnings("unchecked")
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
		}
		return financialYearId;
	}*/
	@SuppressWarnings({ "unchecked", "resource", "deprecation" })
	public JSONObject getFinancialData(JSONObject inputDetails){
		
		System.out.println(getCurrentFinancialYear());
		
		JSONObject result = new JSONObject();
		result.put("status",true);	
		Session session = null;			
		try {		
			/**
			 * 1. Prepare input data
			 * 2. Check all constraints on input data (like between 11 months)
			 * 3. Get All Db Financial year details
			 * 4. Apply all logics on db financial year data & input data 
			 * 
			 */
			// Preparing Input data
			Date in_fromDate = getDateObjectByYearMonth((Integer)inputDetails.get("fromyear"),(Integer)inputDetails.get("frommonth"));		
			
			Date in_toDate = getDateObjectByYearMonth((Integer)inputDetails.get("toyear"),(Integer)inputDetails.get("tomonth"));
			
			Calendar cal_in_fromDate = Calendar.getInstance();
			Calendar cal_in_toDate = Calendar.getInstance();
			cal_in_fromDate.setTime(in_fromDate);
			cal_in_toDate.setTime(in_toDate);
			
			int numOfDaysInMonth = cal_in_toDate.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			cal_in_toDate.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth-1);
			
			
			int monthDifference =   (cal_in_toDate.get(Calendar.YEAR) - cal_in_fromDate.get(Calendar.YEAR)) * 12 +
				 ( cal_in_toDate.get(Calendar.MONTH)- cal_in_fromDate.get(Calendar.MONTH)) + 
				 (cal_in_toDate.get(Calendar.DAY_OF_MONTH) >= cal_in_fromDate.get(Calendar.DAY_OF_MONTH)? 0: -1); 
			
			//** Check the Month difference between fromdate and to date must be 11 
			if(monthDifference != 11)
			{
				System.err.println("Diffrenece between from year and to year months is 11.But front end value month range exceed 12 months");
				result.put("status",false);
				result.put("Reason", "Diffrenece between from year and to year months is 11.But front end value month range exceed 12 months");				
				return result;
			}
			
			// Get All Db finanical year details
			session = this.sf.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM Financialyear");			
			ArrayList<Financialyear> financialYearDetails = (ArrayList<Financialyear>) query.list();
			
			if(!financialYearDetails.isEmpty())
			{
				// 1. Overlap 
				// 2. Current Year : 2017 Give year : 2019-2020
				// 3. Either we have to check 2016-2017, 2017-2018
				
				// Checking for Overlap
				for (Financialyear financialyear : financialYearDetails) {					
						
					// (StartA < = EndB && EndA >= StartB)
					
					
					/**
					 * 
					 * Assume start1 and start2 are the two starting date objects of the two ranges and end1 and end2 the ending dates.
					 * 
					 * if(end1.before(start2)||start1.after(end2))
						    //they do not overlap
						else
						    //they overlap
					 
					 * Start1 = cal_in_fromDate.getTime()
					 * Start2 = cal_in_toDate.getTime()
					 * 
					 * 
					 * end1 = financialyear.getFinancialYear_From()
					 * end2 = financialyear.getFinancialYear_To()
					 * 
					 */
					
					/*if(
							financialyear.getFinancialYear_From().before(cal_in_fromDate.getTime()) ||
							cal_in_fromDate.getTime().after(financialyear.getFinancialYear_To())
							
					)
					{
						System.err.println("they do not overlap");
					}
					else
					{
						System.err.println("they overlap");
					}
					
					
					if(
					   financialyear.getFinancialYear_From().before(cal_in_toDate.getTime()) &&
					   cal_in_fromDate.getTime().after(financialyear.getFinancialYear_To())
					)
					{
						System.out.println("overlapped");
					}
					else
					{
						System.out.println("not overlapped");
					}	*/
					
					if(financialyear.getFinancialYear_From().after(cal_in_fromDate.getTime()))
					{
						System.err.println("Creating financial year before the db year");
						
						if(
								financialyear.getFinancialYear_From().after(cal_in_fromDate.getTime()) &&
								financialyear.getFinancialYear_From().after(cal_in_toDate.getTime())
								
						 )
						{
							System.err.println("not overlapped");
						}
						else
						{
							System.err.println("overlapped");
							result.put("status",false);
							result.put("Reason", "Overlapped");				
							return result;
							
						}	
					}
					else if (financialyear.getFinancialYear_From().before(cal_in_fromDate.getTime()))
					{
						System.err.println("Creating financial year after the db year");
						if(
								financialyear.getFinancialYear_To().before(cal_in_fromDate.getTime()) &&
								financialyear.getFinancialYear_To().before(cal_in_toDate.getTime())
								
						 )
						{
							System.err.println("not overlapped");
						}
						else
						{
							System.err.println("overlapped");
							result.put("status",false);
							result.put("Reason", "Overlapped");				
							return result;
							
						}	
					}
					else if(financialyear.getFinancialYear_From().equals(cal_in_fromDate.getTime()))
					{
						System.err.println("db & creating new year are equal");
						result.put("status",false);
						result.put("Reason", "Overlapped");				
						return result;						
					}	
				}
				
			}
			else
				System.err.println("Empty record in Financial Year Table");
			
			// Insert Database				
			Financialyear financialyear2 = new Financialyear();
			financialyear2.setFinancialYear_From(in_fromDate);
			financialyear2.setFinancialYear_To(cal_in_toDate.getTime());
			financialyear2.setFromyear(in_fromDate.getYear()+1900);
			financialyear2.setToyear(cal_in_toDate.get(Calendar.YEAR));
			
			System.out.println("Financial year details are addded end");
			
			session.save(financialyear2);
		
			
			session.getTransaction().commit();     
			
			
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("reason","error happend");
			result.put("error message",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session!= null && session.isOpen() )
				session.close();
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject  sample(JSONObject financialyear) {		
		System.out.println("After sample private method");
		JSONObject status = new JSONObject();
		status.put("status",true);
		Session sessiona = null;			
		Transaction tra=null;	
		Integer frommonth = (Integer)financialyear.get("frommonth");
		Integer fromyear = (Integer)financialyear.get("fromyear");
		Integer tomonth = (Integer)financialyear.get("tomonth");
		Integer toyear = (Integer)financialyear.get("toyear");	
		
		int frmyear = fromyear-1900;int toyr = toyear-1900;
		Date dbfrom_date = null;Date dbto_date = null;
		Date from_date = null;Date to_date = null;
		Date fromdate = new Date(frmyear,frommonth,1);			
		Date todate = new Date(toyr,tomonth,1);	
		System.out.println("From date="+fromdate);
		System.out.println("To date="+todate);
		Calendar frm = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		frm.setTime(fromdate);to.setTime(todate);
		System.out.println("Calender From Date="+frm.getTime());
		
		int numOfDaysInMonth = to.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		to.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth-1);
		
		System.out.println("Calender Last Day of month: " + to.getTime());
		int d =0;
	
			d=   (to.get(Calendar.YEAR) - frm.get(Calendar.YEAR)) * 12 +
				       (to.get(Calendar.MONTH)- frm.get(Calendar.MONTH)) + 
				       (to.get(Calendar.DAY_OF_MONTH) >= frm.get(Calendar.DAY_OF_MONTH)? 0: -1); 
				System.out.println(" Number of Months difference To==From=****" + d); 
				List<Financialyear> financialyearr =null;
				if (d == 11) {
        	  System.out.println("If part is executed");
        	  status.put("status",true);
        	  Calendar calendar = Calendar.getInstance();
				 calendar.setTime(todate);		
				 int mnth = calendar.get(Calendar.MONTH);		
				 int numOfDaysInMonthh = calendar.getActualMaximum(Calendar.DATE);			 
				 calendar.add(Calendar.DAY_OF_MONTH, numOfDaysInMonthh-1);
				 int endmonth = calendar.get(Calendar.MONTH);
				 int dbfromyeear =0;	int dbtoyeear = 0;		
				    Date fntodate =null;
				    		fntodate = calendar.getTime();
				    	System.out.println("##fntodate####="+fntodate);
        	  try {
        		  sessiona = this.sf.getCurrentSession();
	    			tra = sessiona.beginTransaction();
	    			Query query = sessiona.createQuery("FROM Financialyear WHERE fromyear=:searchA AND toyear=:searchB");
	              financialyearr = query.setParameter("searchA", fromyear).setParameter("searchB", toyear).list();	          
                System.out.println("SIZE+"+financialyearr.size());
	              System.out.println("If part is executed after try");
        	 
        	  System.out.println("If part is executed outside try");
        	  for (Financialyear financialyears : financialyearr) {
					 dbfromyeear = financialyears.getFromyear();
					 dbtoyeear = financialyears.getToyear();	
					 dbfrom_date=financialyears.getFinancialYear_From();
					 dbto_date= financialyears.getFinancialYear_To();
				}
        	  System.out.println("If part is executed outside try after for each");
        	  Calendar dbfromd1 =Calendar.getInstance();
        	  Calendar dbtod2 =Calendar.getInstance();
        	  dbfromd1.setTime(dbfrom_date);
        	  dbtod2.setTime(dbto_date);
        	  System.out.println("Calender DB From Date="+dbfromd1.getTime());
      		System.out.println(" Calender DB TO Date="+dbtod2.getTime());
      		 System.out.println("Enter into first time save");
      	
      		  System.out.println("Enter into first time save");
      		
					Financialyear financialyear2 = new Financialyear();
					financialyear2.setFinancialYear_From(fromdate);
					financialyear2.setFinancialYear_To(fntodate);
					financialyear2.setFromyear(fromyear);
					financialyear2.setToyear(toyear);
					System.out.println("Financial year details are addded end");
			
					tra.commit();
					
					System.out.println("Session closed");
				 } catch (Exception e) {
						status.put("status",false);
						status.put("reason","Error happend");
						status.put("originalErrorMsg", e.getMessage());
						e.printStackTrace();
					}finally{
						if(sessiona!= null && sessiona.isOpen() )
							sessiona.close();
					}
      	 // }
      		
        	 /* if(financialyearr!=null&& !financialyearr.isEmpty()){
        		  System.out.println("First if part condition");
        		  if((frm.compareTo(dbfromd1)==0)&&(to.compareTo(dbtod2)==0)){
        			 
						System.out.println("Enter into  overlap section");
						status.put("status", false);		
        		  }
        		  else if(frm.before(dbtod2)){
        			  System.out.println("Enter into second  overlap section");
						status.put("status", false);		
        		  }       		 
        	  }*/
        	 /* else if(financialyearr!=null&& !financialyearr.isEmpty()){
        		  System.out.println("First if part condition");
        		  if((frm.compareTo(dbfromd1)==0)&&(to.compareTo(dbtod2)==0)){
        			 
						System.out.println("Enter into  overlap section");
						status.put("status", false);		
        		  }
        		  else if(frm.before(dbtod2)){
        			  System.out.println("Enter into second  overlap section");
						status.put("status", false);		
        		  }       		 
        	  }*/
        	  /*else*/ 
        	 /* if ((dbfromyeear!=fromyear)&&(dbtoyeear!=toyear)){
					Financialyear financialyear2 = new Financialyear();
					financialyear2.setFinancialYear_From(fromdate);
					financialyear2.setFinancialYear_To(fntodate);
					financialyear2.setFromyear(fromyear);
					financialyear2.setToyear(toyear);
					System.out.println("Financial year details are addded end");
					status.put("result", true);
					session.save(financialyear2);
					tr.commit();
			}*/
        	  /* else if() {

			}*/
			/*else{
				System.out.println("From year To year values are already available");
				String msg = "Already available";
				status.put("status", msg);		
			}*/
        	 
		} else {
			System.err.println("Diffrenece between from year and to year months is 11.But front end value month range exceed 12 months");
			System.out.println("Else part is executed");
			status.put("Reason", "Diffrenece between from year and to year months is 11.But front end value month range exceed 12 months");
			status.put("status",false);
		}
				
				return status;
	}
		
		
		//
	
	@SuppressWarnings("unchecked")
	public JSONObject deleteEmployeeLeaveRequestById(JSONObject employeeLeaveRequestid) {
		JSONObject status = new JSONObject();
		status.put("status",true);
		Session session = null;Transaction trn =null;
		Integer id = (Integer)employeeLeaveRequestid.get("leaveRequest_Id");
		try {
			session = sf.openSession();
			trn = session.beginTransaction();							
		EmployeeLeaveTransaction leaveTransaction = (EmployeeLeaveTransaction)session.get(EmployeeLeaveTransaction.class, id);	
		session.delete(leaveTransaction);	
		trn.commit();		
		} catch (Exception e) {
			status.put("status",false);
			status.put("reason","Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		   finally{
    			if(session!=null&&session.isOpen()){
    				session.close();
    			}
    		}
		return status;
	}
		
		//GET AND CONFIGURE HOLIDAYS START///
		@SuppressWarnings("unchecked")
		public JSONObject setYearlyHolidays(JSONObject holidays) {
			System.out.println("Enter getYearlyHolidays DAO");
			JSONObject status = new JSONObject();
			status.put("status",true);
			Integer financialId = getCurrentFinancialYearId();
			System.out.println("FINANCIAL Year Id="+financialId);
			ObjectMapper omp = new ObjectMapper();
			   omp.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			   Holidays	holidayss = omp.convertValue(holidays, Holidays.class);
			   holidayss.setFinancialyr_id(financialId);
			
			Session session = null;Transaction trn =null;
			
			try {
				session = sf.openSession();
				trn = session.beginTransaction();							
				session.save(holidayss);
				trn.commit();
			
			} catch (Exception e) {
				status.put("status",false);
				status.put("reason","Error happend");
				status.put("originalErrorMsg", e.getMessage());
				e.printStackTrace();
			}
			   finally{
	    			if(session!=null&&session.isOpen()){
	    				session.close();
	    			}
	    		}
			return status;
		}
		@SuppressWarnings("unchecked")
		public JSONObject setWeekoffdays(JSONObject weekoff) {
			System.out.println("Inside Admin weekof days DAO ###");
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
			Session ses = null;Transaction trn =null;
			List<Financialyear> financial = null;
			Integer fid =0;
			try {
				ses = sf.openSession();
				trn = ses.beginTransaction();			
				Query query = ses.createQuery("FROM Financialyear WHERE formyear=:searchA");
				financial = query.setParameter("searchA", financialFromYear).list();
				fid = financial.get(0).getFinancialyearid();
				trn.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(ses!= null && ses.isOpen() )
					ses.close();
			}
			Session session = sf.openSession();			
			Transaction tr=session.beginTransaction();	
			ObjectMapper omp = new ObjectMapper();
			   omp.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			 
			   Weekoff	weekof = omp.convertValue(weekoff, Weekoff.class);
			   weekof.setFinancialyearid(fid);
			   try {
				   System.out.println("Enter weekof days DAO try block");
				session.save(weekof);
				tr.commit();
				status.put("status", true);
				System.out.println("Admin Save weekof days details end");
			} catch (Exception e) {
				status.put("status",false);
				status.put("reason","Error happend");
				status.put("originalErrorMsg", e.getMessage());
				e.printStackTrace();
			}
			   finally{
					if(session!= null && session.isOpen() )
						session.close();
				}
			return status;
		}
		
		@SuppressWarnings("unchecked")
		public JSONObject deleteLeavedetailsByFid(JSONObject leaveid) {
			JSONObject status = new JSONObject();
			status.put("status", true);		
			Session session =null;
			Transaction tr = null;
		Integer leavedataid = (Integer)leaveid.get("leave_id");
		Integer financialId = (Integer)leaveid.get("financialyear_id");
		List<Leavedetails> leavedetails=null;
		Leavedetails leavedetails2 = null;
			try {
				session = this.sf.getCurrentSession();
				tr =session.beginTransaction(); 
				Query query = session.createQuery("FROM Leavedetails WHERE id=:searchA AND financialyear_id=:searchB");
				leavedetails = query.setParameter("searchA", leavedataid).setParameter("searchB", financialId).list();	
				
			if (leavedetails!=null&&!leavedetails.isEmpty()) {
				leavedetails2 = leavedetails.get(0);
				session.delete(leavedetails2);
				tr.commit();
				status.put("status", true);		
			} else {
				System.err.println("Leavedetails table is empty");
				status.put("status", false);
				status.put("reason", "Leavedetails table is empty");
			}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				status.put("reason", "Error found");
				status.put("originalErrorMsg", e.getMessage());
				status.put("status", false);		
			}finally{
				if(session!=null&&session.isOpen()){
					session.close();
				}
			}
			return status;
		}
		
		@SuppressWarnings("unchecked")
		public JSONObject deleteFinancialyearById(JSONObject financialyearid) {
			JSONObject status = new JSONObject();
			status.put("status",true);
			Session session = null;			
			Transaction transaction=null;	
			try {
				session = this.sf.openSession();
				transaction = session.beginTransaction();
				Financialyear financialyear = (Financialyear)session.get(Financialyear.class,(Integer)financialyearid.get("financialyearid"));
				if (financialyear!=null) {
					session.delete(financialyear);
					transaction.commit();
					status.put("status",true);
				} else {
					System.err.println("Financialyear table is empty");
					status.put("status", false);
					status.put("reason", "Financialyear table is empty");
				}
				
			} catch (Exception e) {
				status.put("status", false);
				status.put("reason", "Error Happened");
				status.put("originalErrorMsg", e.getMessage());
				e.printStackTrace();
			}finally{
				if(session!=null&&session.isOpen()){
					session.close();
				}
			}
			return status;
		}
		@SuppressWarnings("unchecked")
		public JSONObject deleteHolidayById(JSONObject holidayid) {
			JSONObject status = new JSONObject();
			status.put("status",true);
			Session session = null;			
			Transaction transaction=null;				
			try {
				session = this.sf.openSession();
				transaction = session.beginTransaction();
				Holidays holidays = (Holidays)session.get(Holidays.class,(Integer)holidayid.get("holidayId"));
				if (holidays!=null) {
					session.delete(holidays);
					transaction.commit();
					status.put("status",true);
				} else {
					System.err.println("Holiday table is empty");
					status.put("status", false);
					status.put("reason", "Holiday table is empty");
				}
				
			} catch (Exception e) {
				status.put("status", false);
				status.put("reason", "Error Happened");
				status.put("originalErrorMsg", e.getMessage());
				e.printStackTrace();
			}finally{
				if(session!=null&&session.isOpen()){
					session.close();
				}
			}
			return status;
		}
		//GET AND CONFIGURE HOLIDAYS END///
		//ADMIN GET AND Leave balance  START///
		@SuppressWarnings("unchecked")
		public JSONObject getLeaveRemainingDays(JSONObject employee) {
			
			JSONObject status = new JSONObject();
			status.put("status",true);
			SimpleDateFormat sdf =  new SimpleDateFormat("dd-MM-yyyy");
			Session session = null;			
			Transaction transaction=null;	
			Date empfrom =null;
			Date empto = null;
			Integer eid = (Integer)employee.get("employee_id");
			Integer fid = (Integer)employee.get("financialyearid");
			Integer lcid = (Integer)employee.get("leaveconfigid");
			try {
				 empfrom = sdf.parse(employee.get("From_Date").toString());
				 empto = sdf.parse(employee.get("To_Date").toString());
				 System.out.println("E Apply  from date="+empfrom);
				 System.out.println("E Apply  to date="+empto);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			List<Leavedetails> leave = null;
			String leaveType = "";
			try {
				session = this.sf.getCurrentSession();
				transaction = session.beginTransaction();
				Query query = session.createQuery("FROM Leavedetails WHERE id=:searchA AND financialyear_id=:searchB");
				leave = query.setParameter("searchA", lcid).setParameter("searchB", fid).list();
			transaction.commit();
			if(leave==null ||leave.isEmpty()){
				status.put("Reason", "Admin still not configure Leavetypes.So FiscalLeaveDetails table is empty");
				status.put("status", false);
				return status;
			}
				if(leave!=null && !leave.isEmpty()){
				leaveType = leave.get(0).getLeave_days_type();
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				if(session!=null&&session.isOpen()){
					session.close();
				}
			}
			Session sessiona=null;Transaction transactiona=null;
			List<EmployeeFiscalYearLeaveDetails> fiscaldata=null;
			try {
				sessiona = this.sf.getCurrentSession();
				transactiona = sessiona.beginTransaction();
				Query query = sessiona.createQuery("FROM EmployeeFiscalYearLeaveDetails where employee_id=:searchA  AND  financialyear_id=:searchB AND leaveConfiguration_Id=:searchC");		
				fiscaldata =  query.setParameter("searchA", eid).setParameter("searchB", fid).setParameter("searchC", lcid).list();
				transactiona.commit();
				if(fiscaldata!=null && !fiscaldata.isEmpty()){
				 System.out.println("==SIZE=="+fiscaldata.size());
				 for (EmployeeFiscalYearLeaveDetails employeeFiscalYear : fiscaldata) {
					    System.out.println("#ParticularROWId##="+employeeFiscalYear.getId());
						System.out.println("#####From date####="+employeeFiscalYear.getFromDate());
						System.out.println("###Todate#########="+employeeFiscalYear.getToDate());
				}			 
			 }
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(sessiona!=null&&sessiona.isOpen()){
					sessiona.close();
				}
			}
			List<EmployeeFiscalYearLeaveDetails> fiscal=null;
			if (leaveType.equals("yearly")) {
				Session session2 =null;Transaction transaction2 = null;
				
				try {
					System.out.println("####GET  EmployeeFiscalYearLeaveDetails values##### START");
					session2 = sf.openSession();
					transaction2 = session2.beginTransaction();
					Query query = session2.createQuery("FROM EmployeeFiscalYearLeaveDetails WHERE employee_id=:searchA AND financialyear_id=:searchB AND leaveConfiguration_Id=:searchC");
					fiscal = query.setParameter("searchA", eid).setParameter("searchB", fid).setParameter("searchC", lcid).list();
					transaction2.commit();
					if(fiscal!=null && !fiscal.isEmpty()){
						status.put("RemainingDays", fiscal);
					}
					System.out.println("####GET  EmployeeFiscalYearLeaveDetails values##### END");
				} catch (Exception e) {
					status.put("status",false);
					status.put("reason","Error happend");
					status.put("originalErrorMsg", e.getMessage());
					e.printStackTrace();
				}finally{
					if(session2!=null&&session2.isOpen()){
						session2.close();
					}
				}
				
			}if(leaveType.equals("halfyearly")){
				System.out.println("Enter Halfyearly");
				Date dbfrom =null; Date dbto = null;
				Session sessionh =null;Transaction transactionh = null;
				int sizeh = fiscaldata.size();	
				Integer pkid =0;Integer lcdid =0;		
				EmployeeFiscalYearLeaveDetails efyld = null;
				if(fiscaldata != null && !fiscaldata.isEmpty()){		
					for(int j=0;j<sizeh;j++){
						 efyld = fiscaldata.get(j);
						 if( (efyld.getFromDate().compareTo(empto) == -1 || efyld.getFromDate().compareTo(empto) == 0)&&(efyld.getToDate().compareTo(empto) == 1||efyld.getToDate().compareTo(empto) == 0) )
		 					{							
								 pkid = efyld.getId();
								 lcdid= efyld.getId();
								
								try {
									System.out.println("####GET  EmployeeFiscalYearLeaveDetails values##### START");
									sessionh = sf.openSession();
									transactionh = sessionh.beginTransaction();
									Query query1 = sessionh.createQuery("FROM EmployeeFiscalYearLeaveDetails WHERE employee_id=:searchA AND financialyear_id=:searchB AND leaveConfiguration_Id=:searchC AND id=:searchD");
		     						fiscal=query1.setParameter("searchA", eid).setParameter("searchB", fid).setParameter("searchC", lcid).setParameter("searchD", efyld.getId()).list();
		     						transactionh.commit();
		     						if(fiscal!=null && !fiscal.isEmpty()){
		     							status.put("RemainingDays", fiscal);
		     						}
		     						System.out.println("####GET  EmployeeFiscalYearLeaveDetails values##### END");
								} catch (Exception e) {
									status.put("status",false);
									status.put("reason","Error happend");
									status.put("originalErrorMsg", e.getMessage());
									e.printStackTrace();
								}finally{
									if(sessionh!=null&&sessionh.isOpen()){
										sessionh.close();
									}
								}
		 					}//If END
					}//For end									
				}//fiscal data end								
			}//end halfyearly
			if(leaveType.equals("quarterly")){
				System.out.println("Enter Halfyearly");				
				Session sessionq =null;Transaction transactionq = null;
				int sizeh = fiscaldata.size();	
				Integer pkid =0;Integer lcdid =0;		
				EmployeeFiscalYearLeaveDetails efyld = null;
				if(fiscaldata != null && !fiscaldata.isEmpty()){	
					for(int j=0;j<sizeh;j++){
						 efyld = fiscaldata.get(j);
						 if( (efyld.getFromDate().compareTo(empto) == -1 || efyld.getFromDate().compareTo(empto) == 0)&&(efyld.getToDate().compareTo(empto) == 1||efyld.getToDate().compareTo(empto) == 0) )
		 					{
							 pkid = efyld.getId();
							 lcdid= efyld.getId();
							
							try {
								System.out.println("####GET  EmployeeFiscalYearLeaveDetails  correct values##### START");
								sessionq = sf.openSession();
								transactionq = sessionq.beginTransaction();
								Query query1 = sessionq.createQuery("FROM EmployeeFiscalYearLeaveDetails WHERE employee_id=:searchA AND financialyear_id=:searchB AND leaveConfiguration_Id=:searchC AND id=:searchD");
	     						fiscal=query1.setParameter("searchA", eid).setParameter("searchB", fid).setParameter("searchC", lcid).setParameter("searchD", efyld.getId()).list();
	     						transactionq.commit();
	     						if(fiscal!=null && !fiscal.isEmpty()){
	     							status.put("RemainingDays", fiscal);
	     						}
	     						System.out.println("####GET  EmployeeFiscalYearLeaveDetails correct  values##### END");
							} catch (Exception e) {
								status.put("status",false);
								status.put("reason","Error happend");
								status.put("originalErrorMsg", e.getMessage());
								e.printStackTrace();
							}finally{
								if(sessionq!=null&&sessionq.isOpen()){
									sessionq.close();
								}
							}
		 					}//correct match end
					}//for loop end
				}//fiscal end
			}//quarterly end
			if(leaveType.equals("monthly")){
				System.out.println("ENTER MONTHLY Leave Request");										
				Integer pkdid = 0;Integer alloweddayss =0;
				Long reqq=0l;
				 Date dbmfrom = null; Date dbmto = null;
              	System.out.println("Apply date range matched");
					int sizeh = fiscaldata.size();
					System.out.println("Monthly size="+sizeh);
					if(fiscaldata != null && !fiscaldata.isEmpty()){//Fiscal start
						Date endd = findEndDate(empto);
		      		       System.out.println("Request month end="+endd);      		       
		      		       int ficalsizee = fiscaldata.size();
		      		     EmployeeFiscalYearLeaveDetails matchedDetailss = null;    		     
		      		     System.out.println("Size :"+ficalsizee);
		      		   for(int i=0;i<ficalsizee;i++){//Forloop fiscal size start
		      			 matchedDetailss = fiscaldata.get(i);
		      			 System.out.println("Matched employeeFiscal Remaining days###="+matchedDetailss.getRemainingdays());						
							if(matchedDetailss.getToDate().compareTo(endd)==0){//Exact match period start
								Integer pkid = matchedDetailss.getId();
								System.out.println("***EXACT MONTH ID***="+pkid);
								   fiscal=getAllowedDays(eid,fid,lcid,pkid);
								   Integer  remainingDays = fiscal.get(0).getRemainingdays();
								   if(fiscal!=null && !fiscal.isEmpty()){
		     							status.put("RemainingDays", fiscal);
		     							System.out.println("####GET  EmployeeFiscalYearLeaveDetails correct  values##### END");
		     						}
		     						
								
							}//Exact match period end
		      		   }//Forloop fiscal size end
					}//Fiscal end
			}
			else {

			}//leave type not matched
			
			return status;
		}
		@SuppressWarnings("unchecked")
		public JSONObject getFiscalLeaveDetailsByEmpIdAdmin(JSONObject fiscaldata) {
			System.out.println("getEmployee FiscalLeaveDetailsByEmpId ADMIN  DAO");
			JSONObject status = new JSONObject();
			status.put("status",true);
			Integer financialYearId=getCurrentFinancialYearId();
		
			Session session = null;
			Transaction td = null;
			Integer epmid = (Integer)fiscaldata.get("employee_id");
			try {						
				HashMap<String, Object> resultDatas = new HashMap<String, Object>();
				List<EmployeeFiscalYearLeaveDetails> fiscalData = null;
				fiscalData=getAllLeaveremainingByEidAdmin(epmid);				
				HashSet<Integer> employe = new HashSet<Integer>();
				HashSet<Integer> leave = new HashSet<Integer>();
				int empid =0;
				int leaveid = 0;
				
				//if(fiscalData.size()>0){//
				if(fiscalData != null ){
					  ObjectMapper om = new ObjectMapper();           
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
				      System.out.println("SIZE="+object);				      
				      System.out.println("Employee Size EmployeeFiscalLeaveremaining data="+employe.size());
				      Iterator<Integer> eid = (Iterator<Integer>)employe.iterator();
				     while (eid.hasNext()) {
							Integer integer = (Integer) eid.next();										
						}
				      try {						
						session = this.sf.getCurrentSession();
						td = session.beginTransaction();
						 Query query = session.createQuery("From Financialyear WHERE financialyearid=:searchA");
					        List<Financialyear> financialyears = query.setParameter("searchA", financialYearId).list();
					     Integer fid = financialyears.get(0).getFinancialyearid();
					     Query query3 = session.createQuery("FROM Leavedetails WHERE financialyear_id=:searchA");
							List<Leavedetails>	leavedetails = query3.setParameter("searchA", fid).list();
							int size = object.size();						
							int leave_details_size = leavedetails.size();	
							//bind value
							Map<String,Object> remainingdays=null;
							for (int i = 0; i <size; i++) {
								remainingdays=(Map<String,Object>)object.get(i); //bind Fiscal leave details
								int leavecofid =(Integer)remainingdays.get("leaveConfiguration_Id"); 
								for(Integer k=0;k< leave_details_size;k++){									
									Leavedetails  leavedata = leavedetails.get(k);
									if(leavecofid== leavedata.getId()){									
										remainingdays.put("LeaveData", leavedata);//Bind Matched leave data
										System.out.println("LeaveData"+remainingdays.get("LeaveData"));
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
		
		@SuppressWarnings("unchecked")
		public JSONObject getFiscalyearDetailByEmpIdYear(JSONObject fiscaldata) {
			JSONObject status = new JSONObject();
			status.put("status",true);
			System.out.println("getFiscalyearDetailByEmpIdYear with Finanacial year DAO");
			Integer epmid = (Integer)fiscaldata.get("employee_id");
			//financialyear_id
			Integer fid = (Integer)fiscaldata.get("financialyear_id");
			List<EmployeeFiscalYearLeaveDetails> fiscalData = null;
			fiscalData=getAllLeaveremainingByEidYear(epmid,fid);
			HashMap<String, Object> resultDatas = new HashMap<String, Object>();
			Session session = null;			
			Transaction td=null;	
			try{
				System.out.println("getFiscalyearDetailByEmpIdYear with Finanacial year DAO try");
				HashSet<Integer> employe = new HashSet<Integer>();
				HashSet<Integer> leave = new HashSet<Integer>();
				int empid =0;
				int leaveid = 0;
				if(fiscalData!=null&&!fiscalData.isEmpty()&&fiscalData.size()>0){
					  ObjectMapper om = new ObjectMapper();           
				      om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				      ArrayList object = om.convertValue(fiscalData, ArrayList.class);   		     
				      int objectSize = object.size();
				     for(int i=0;i<objectSize;i++)
				     {
				    	 System.out.println("Convert object to java pojo EmployeeLeaveTransaction");
				    	  HashMap<String, Object> data = om.convertValue( object.get(i) , new TypeReference<HashMap<String, Object>>() {});
				    	  
				    	  for (Entry<String, Object> entry : data.entrySet()) {
				    		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
				    		}
				    	  employe.add((Integer)data.get("employee_id"));
				    	leave.add((Integer)data.get("leaveConfiguration_Id"));
				    	//
				    	empid  = (Integer)data.get("employee_id");
				    	leaveid = (Integer)data.get("leaveConfiguration_Id");
				    	//
				    	
				    	object.set(i, data);			    	
				     }
				     
				    
				      System.out.println("SIZE="+object);
				      
				      System.out.println("Employee Size EmployeeFiscalLeaveremaining data="+employe.size());
				      Iterator<Integer> eid = (Iterator<Integer>)employe.iterator();
				     while (eid.hasNext()) {
							Integer integer = (Integer) eid.next();
							System.out.println("EMP Id="+integer);
							
						}
				      try {
						System.out.println("Inside try");

						session = this.sf.getCurrentSession();
						td = session.beginTransaction();
						/* Query query = session.createQuery("From Financialyear WHERE fromyear=:searchA");
					        List<Financialyear> financialyears = query.setParameter("searchA", financialFromYear).list();
					     Integer fid = financialyears.get(0).getFinancialyearid();*/
					     Query query3 = session.createQuery("FROM Leavedetails WHERE financialyear_id=:searchA");
							List<Leavedetails>	leavedetails = query3.setParameter("searchA", fid).list();
							int size = object.size();
							
							int leave_details_size = leavedetails.size();	
							//bind value
							Map<String,Object> remainingdays=null;
							for (int i = 0; i <size; i++) {
								remainingdays=(Map<String,Object>)object.get(i); //bind Fiscal leave details
								int leavecofid =(Integer)remainingdays.get("leaveConfiguration_Id"); 
								for(Integer k=0;k< leave_details_size;k++){
									
									Leavedetails  leavedata = leavedetails.get(k);
									if(leavecofid== leavedata.getId()){									
										remainingdays.put("LeaveData", leavedata);//Bind Matched leave data
										System.out.println("LeaveData"+remainingdays.get("LeaveData"));
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
					status.put("reason","Admin still not configured Leave details types!.So there is no leave remaining days for this year.");
				System.err.println("Admin still not configured Leave details types!.So there is no leave remaining days for this year.");
				}
				
			} catch (Exception e) {		
				e.printStackTrace();
				status.put("status", false);
				status.put("reason", "Error happened");
				status.put("originalErrorMsg", e.getMessage());
			} 
			return status;
		}
		@SuppressWarnings("unchecked")
		public JSONObject deleteFiscalyearDetailByEmpIdYear(JSONObject fiscaldataid) {
			JSONObject status = new JSONObject();
			status.put("status", true);
			Session session = null;	
			Transaction tr=null;
			Integer leaveid = (Integer)fiscaldataid.get("leaveConfiguration_Id");
			Integer financialid = (Integer)fiscaldataid.get("financialyear_id");
			List<EmployeeFiscalYearLeaveDetails> fiscalYearLeaveDetails=null;			
			try {	
				 session = this.sf.openSession();	
				 tr=session.beginTransaction();				
				Query  query2 = session.createQuery("FROM EmployeeFiscalYearLeaveDetails WHERE financialyear_id=:searchA AND leaveConfiguration_Id=:searchB");
				fiscalYearLeaveDetails = query2.setParameter("searchA", financialid).setParameter("searchB",leaveid).list();
				EmployeeFiscalYearLeaveDetails yearLeaveDetails =null;
				if (fiscalYearLeaveDetails!=null&&!fiscalYearLeaveDetails.isEmpty()) {
					yearLeaveDetails = fiscalYearLeaveDetails.get(0);
					session.delete(yearLeaveDetails);
					tr.commit();
					 status.put("status", true);
				} else {
					System.err.println("EmployeeFiscalYearLeaveDetails table value is empty");
					status.put("status", false);
					status.put("reason", "EmployeeFiscalYearLeaveDetails table value is empty");
				} 
				tr.commit();
				 status.put("status", true);
			} catch (Exception e) {
				e.printStackTrace();
				status.put("status", false);
				status.put("reason", "Error happened");
				status.put("originalErrorMsg", e.getMessage());
			}finally{
				if(session!=null&&session.isOpen()){
					session.close();
				}
			}		
			return status;
		}
		
	public int getHoliday(JSONObject leaveDeatils){
		System.out.println("Inside holiday calculation");
		String fromDate = leaveDeatils.get("From_Date").toString();
		//dd-MM-yyyy
		String fromDateParts[] = fromDate.split("-");
		int totalDays = Integer.parseInt(leaveDeatils.get("numberofdays").toString());
		int totalHolidays = 0;
		Calendar cal = new GregorianCalendar(converInt(fromDateParts[2]), converInt(fromDateParts[1])-1,converInt(fromDateParts[0]));		
		for (int i = 0; i < totalDays; i++) {
			System.out.println("Date : "+cal.getTime()+" Day : "+cal.get(Calendar.DAY_OF_WEEK));
			int date = cal.get(cal.DAY_OF_WEEK);
			if(date == 1)
				totalHolidays = totalHolidays+1;			
			cal.add(Calendar.DAY_OF_WEEK, 1);			
		}	
		
		System.out.println("Total holiday ="+totalHolidays);
		return totalHolidays;
	}		
	public int converInt(String num){
		return Integer.parseInt(num);
	}
	@SuppressWarnings("unchecked")
	 public  List<EmployeeLeaveTransaction> getData(){
		System.out.println("call sp method");
		Session session = null;
		Transaction tr = null;
		Integer financialId = getCurrentFinancialYearId();		
		List<EmployeeLeaveTransaction> employeeLeaveTransaction = null;
		List<Financialyear> financialyears =null;Integer fid = 0;
		try {
			session = this.sf.getCurrentSession();
			tr = session.beginTransaction();
			Query query = session.createQuery("From Financialyear WHERE financialyearid=:searchA");
			financialyears = query.setParameter("searchA",financialId).list();
			if (financialyears!=null&&!financialyears.isEmpty()) {
				 fid = financialyears.get(0).getFinancialyearid();
					Query query1 = session.createQuery("FROM EmployeeLeaveTransaction WHERE financialyearid=:searchA");
					employeeLeaveTransaction = query1.setParameter("searchA", fid).list();			
					tr.commit();
			} else {
				System.err.println("Admin still not configure Financial year EmployeeLeaveTransaction is empty ");

			}			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		if(employeeLeaveTransaction != null
				&& !employeeLeaveTransaction.isEmpty()) {
			return employeeLeaveTransaction;
		} else {
			return null;
		}
	   }
	 @SuppressWarnings("unchecked")
	 public  List<EmployeeLeaveTransaction> getPendingData(){
		 System.out.println("call sp method");
		 
		 	Session session = null;
			Transaction tr = null;		  
			Integer financialId = getCurrentFinancialYearId();		
			
			List<EmployeeLeaveTransaction> employeeLeaveTransactions =null;
			List<Financialyear> financialyears =null;
			try {
				session = this.sf.getCurrentSession();
				tr = session.beginTransaction();
				 Query query = session.createQuery("From Financialyear WHERE financialyearid=:searchA");
			       financialyears = query.setParameter("searchA", financialId).list();
			       if (financialyears!=null && !financialyears.isEmpty()) {
			    	   Integer fid = financialyears.get(0).getFinancialyearid();			    
						Query query1 = session.createQuery("FROM EmployeeLeaveTransaction WHERE financialyearid=:searchA AND status=:searchB");
						employeeLeaveTransactions =query1.setParameter("searchA", fid).setParameter("searchB", new String("pending")).list();
						System.out.println("Return value");
						tr.commit();
				} else {
					System.err.println("Admin not yet to configure Leave types details.EmployeeLeaveTransaction table is empty.");
				}
			    
			} catch (Exception ex) {
				ex.printStackTrace();				
			}finally{
				if(session!=null&&session.isOpen()){
					session.close();
				}
			}
				if (employeeLeaveTransactions!=null&&!employeeLeaveTransactions.isEmpty()) {
					return employeeLeaveTransactions;				
				} else {
					return null;
				}		    
	 }
	 //pendingdata for particular employee separate method//
	 public   List<EmployeeLeaveTransaction> getEmployeePendingData(Integer empid){
		
		 Session session = null;			
			Transaction tr=null;	
			Calendar cc = Calendar.getInstance();									
			int currentYear = cc.get(Calendar.YEAR);		
			int currentMonth = cc.get(Calendar.MONTH);				
			int financialFromYear = 0;
			String status = "pending";
			if(currentMonth<=2){
				financialFromYear = currentYear - 1;			
			}else{
				financialFromYear = currentYear;		
			}	
			List<EmployeeLeaveTransaction> employeeLeaveTransaction =null;
			try {
				session = this.sf.getCurrentSession();
				tr = session.beginTransaction();
				 Query query = session.createQuery("From Financialyear WHERE fromyear=:searchA");
			        List<Financialyear> financialyears = query.setParameter("searchA", financialFromYear).list();
			     Integer fid = financialyears.get(0).getFinancialyearid();
			    
				Query query1 = session.createQuery("FROM EmployeeLeaveTransaction WHERE status=:searchA AND employee_id=:searchB AND financialyearid=:searchC");
				employeeLeaveTransaction =query1.setParameter("searchA", status).setParameter("searchB", empid).setParameter("searchC", fid).list();
				System.out.println("Return pending  value");
				tr.commit();
			} catch (NullPointerException ex) {
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
	 private ArrayList<Object> calculateLeaveDuration(Date from,Date to, int monthstoadd){		 
         ArrayList<Object> dateIntervals = new ArrayList<Object>();		 
		 try {
			 Calendar calendar = Calendar.getInstance();
			 calendar.setTime(from);			 			 
			 // TODO Financial year should be 12 or it is won't work
			 int loopCount = 12/monthstoadd;		 
			 for(int i=0;i<loopCount;i++) {
				HashMap<Object, Object> dateInterval = new HashMap<Object, Object>();					
				Calendar from_date= Calendar.getInstance();
				from_date.setTime(from);				
				Calendar to_date = Calendar.getInstance();
				to_date.setTime(from);
				to_date.add(Calendar.MONTH, monthstoadd);
				to_date.add(Calendar.DAY_OF_MONTH,-1);
				dateInterval.put(from_date, to_date);
				dateIntervals.add(dateInterval);				
				Calendar set_from_date = Calendar.getInstance();
				set_from_date.setTime(to_date.getTime());
				set_from_date.add(Calendar.DAY_OF_MONTH,1);			
				from = set_from_date.getTime();			
			 }	 			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return dateIntervals;
	 }
	 private EmployeeFiscalYearLeaveDetails getParticularFiscalLDetails(Date empto, List<EmployeeFiscalYearLeaveDetails> emploeefiscal) {
			EmployeeFiscalYearLeaveDetails singleFYLD = null;
			boolean isMatched = false;
			int totalPeriods = emploeefiscal.size();
			for(int i=0;i<totalPeriods;i++)
			{
				 singleFYLD = emploeefiscal.get(i);
				 if( (singleFYLD.getFromDate().compareTo(empto) == -1 || singleFYLD.getFromDate().compareTo(empto) == 0)&&(singleFYLD.getToDate().compareTo(empto) == 1||singleFYLD.getToDate().compareTo(empto) == 0) )
				{
					 isMatched=true;
					 break;
				}                	
				
			}
			if(isMatched)
			  return singleFYLD;
			else		  
			return null;
		}
	 
	 public List<EmployeeFiscalYearLeaveDetails> getAllowedDays(Integer eid,Integer fid,Integer levid,Integer pkid){
	    	Session session = null;Transaction trans = null;	    	
	    	Integer alloewddays = 0;
	    	 List<EmployeeFiscalYearLeaveDetails> dt =null;
	    	 Date dbfsfromm =null; Date dbfctom  =null;
	    	try {	    		
				 session=this.sf.getCurrentSession();
				 trans =session.beginTransaction();
					Query query1 = session.createQuery("FROM EmployeeFiscalYearLeaveDetails WHERE employee_id=:searchA AND financialyear_id=:searchB AND leaveConfiguration_Id=:searchC AND id=:searchD");
						dt=query1.setParameter("searchA", eid).setParameter("searchB", fid).setParameter("searchC", levid).setParameter("searchD", pkid).list();
					
						trans.commit();
						
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(session!=null&&session.isOpen()){
					session.close();
				}
			}
	    	if (dt!=null && !dt.isEmpty()) {
	    		System.out.println("Value returened");
				return dt;
			} else {
				return null;
			}    	
	    }
	
		private static int differenceInMonths(Date d1, Date d2) {
		    Calendar c1 = Calendar.getInstance();
		    c1.setTime(d1);
		    Calendar c2 = Calendar.getInstance();
		    c2.setTime(d2);
		    int diff = 0;
		    if (c2.after(c1)) {
		    	diff++;
		        while (c2.after(c1)) {
		            c1.add(Calendar.MONTH, 1);
		            if (c2.after(c1)) {
		                diff++;
		            }
		        }
		    } else if (c2.before(c1)) {
		        while (c2.before(c1)) {
		            c1.add(Calendar.MONTH, -1);
		            if (c1.before(c2)) {
		                diff--;
		            }
		        }
		    }
		    return diff;
		}
		
		 @SuppressWarnings("unchecked")
			public  List<EmployeeFiscalYearLeaveDetails> getAllLeaveremainingByEidYear(Integer eid,Integer fid){
				 System.out.println("call sp method");	
				Session session = null;
					Transaction tr = null;	  			
				
					List<EmployeeFiscalYearLeaveDetails> employeeFiscalYearLeaveDetails=null;
					try {
						System.out.println("Inside try");
						session = this.sf.getCurrentSession();
						tr = session.beginTransaction();
						/* Query query = session.createQuery("From Financialyear WHERE fromyear=:searchA");
					        List<Financialyear> financialyears = query.setParameter("searchA", financialFromYear).list();
					     Integer fid = financialyears.get(0).getFinancialyearid();*/
					     Query query2 = session.createQuery("FROM EmployeeFiscalYearLeaveDetails WHERE financialyear_id=:searchA AND employee_id=:searchB");
					     employeeFiscalYearLeaveDetails=query2.setParameter("searchA", fid).setParameter("searchB", eid).list();
					     tr.commit();
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
			public  List<EmployeeFiscalYearLeaveDetails> getAllLeaveremainingByEidAdmin(Integer eid){
				 System.out.println("call sp method");	
				Session session = null;
					Transaction tr = null;	 
					Integer financialId = getCurrentFinancialYearId();		
					List<EmployeeFiscalYearLeaveDetails> employeeFiscalYearLeaveDetails=null;
					try {
						System.out.println("Inside try");
						session = this.sf.getCurrentSession();
						tr = session.beginTransaction();
						 Query query = session.createQuery("From Financialyear WHERE financialyearid=:searchA");
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
