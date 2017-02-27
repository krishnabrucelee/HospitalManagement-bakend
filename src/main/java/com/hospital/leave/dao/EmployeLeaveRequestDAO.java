package com.hospital.leave.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hospital.leave.model.Applyremaining;
import com.hospital.leave.model.EmployeeFiscalYearLeaveDetails;
import com.hospital.leave.model.EmployeeLeaveTransaction;
import com.hospital.leave.model.Financialyear;
import com.hospital.leave.model.Holidays;
import com.hospital.leave.model.Leavedetails;

@Repository
public class EmployeLeaveRequestDAO {
	
	@Autowired
	SessionFactory sf;
	
	
	@SuppressWarnings("unchecked")
	private JSONObject saveEmployeeLeaveTransction(JSONObject emprequestLeave,JSONObject status, Session session, Transaction tr, Integer eid,
			Integer fid, Integer lcid, String reason) throws ParseException {
		System.out.println("quarterly type leave EmployeeLeaveTransaction  insert value END");
		EmployeeLeaveTransaction elt = new EmployeeLeaveTransaction();
		elt.setEmployee_id(eid);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");				
		elt.setFromDate(sdf.parse(emprequestLeave.get("From_Date").toString()));
		elt.setToDate(sdf.parse(emprequestLeave.get("To_Date").toString()));
		elt.setCreationDate(Calendar.getInstance().getTime());
		elt.setStatus("pending");
		int holy = getHoliday(emprequestLeave);
		int totdays = (Integer)emprequestLeave.get("numberofdays");
		System.out.println("Find totdays="+totdays);
		System.out.println("Find leave days="+holy);
		totdays = totdays-holy;
		elt.setTotaldays((Integer)emprequestLeave.get("numberofdays")-getHoliday(emprequestLeave));
		elt.setReason(reason);
		
		elt.setLeaveConfigurationId(lcid);elt.setFinancialyearid(fid);
		try {
			session.save(elt);
			System.out.println("quarterly type leave EmployeeLeaveTransaction  insert value END");
			status.put("status",true);
			tr.commit();	
		} catch (Exception e) {
			status.put("status",false);
			status.put("reason","error happend");
			status.put("error",e.getMessage());
			
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
		return status;
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
	//
	public int getHoliday(JSONObject emprequestLeave){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println("Inside holiday calculation");
		String fromDate = emprequestLeave.get("From_Date").toString();
		String toDate = emprequestLeave.get("To_Date").toString();
		Date applydate =null;Date applytodate =null; List<Date> dt = null;
		Date dbdt = null;
		int [] wnval = new int[1];int [] hlynv=new int[6];
		try {
			 applydate = sdf.parse(emprequestLeave.get("From_Date").toString());
			 applytodate = sdf.parse(emprequestLeave.get("To_Date").toString());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Session sess = sf.openSession();
		Transaction trns = sess.beginTransaction();
		 Calendar cc = Calendar.getInstance();								
			int currentYear = cc.get(Calendar.YEAR);		
			int currentMonth = cc.get(Calendar.MONTH);			
			int financialFromYear = 0;		
			if(currentMonth<=2){
				financialFromYear = currentYear - 1;				
			}else{
				financialFromYear = currentYear;	
			}	
			System.out.println("Financial from year="+financialFromYear);
			try {
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			Date dbdate =null;
			int ndvalue=0;
		//dd-MM-yyyy
		String fromDateParts[] = fromDate.split("-");
		int totalDays = Integer.parseInt(emprequestLeave.get("numberofdays").toString());
		int totalHolidays = 0;
		int weekoof = 0;
		Calendar cal = new GregorianCalendar(converInt(fromDateParts[2]), converInt(fromDateParts[1])-1,converInt(fromDateParts[0]));				
		List<Holidays> apply = null;
		ArrayList<Integer> listofHolidays = null;	List<Integer> week = null;	List<Integer> hlolidy = null;
		List<Holidays> weekooff = null;List<Holidays> holidayss = null;
		String leavetype=null;String wekof=null;String publicholyday=null;int weeknvale=0;int holynvalue=0;
		int wk1=0;int wk2 =0;int wk=0;
		try {
			Query query = sess.createQuery("FROM Financialyear WHERE  fromyear=:searchA");
			List<Financialyear> financeyear = query.setParameter("searchA", financialFromYear).list();
			Integer fidd = financeyear.get(0).getFinancialyearid();
			System.out.println("Financialyear ID="+fidd);
			Query query2 = sess.createQuery("FROM Holidays WHERE financialyr_id=:searchA AND leave_type=:searchB");
			weekooff = query2.setParameter("searchA", fidd).setParameter("searchB", new String("weekoff")).list();
			 System.out.println("weekooff size="+weekooff.size());
			 System.out.println("weekooff ="+weekooff);			
			 Query query3 = sess.createQuery("FROM Holidays WHERE financialyr_id=:searchA AND leave_type=:searchB");
			 holidayss = query3.setParameter("searchA", fidd).setParameter("searchB", new String("public holiday")).list();
			System.out.println("Holiday size="+holidayss.size());
			System.out.println("Holiday size="+holidayss);
			 for (Holidays holiday : holidayss) {
					holynvalue=holiday.getDay_value();
					dbdt=holiday.getDaate();		
				}
			System.out.println("DBDATE=="+dt);
	     	
		for (int i = 0; i < totalDays; i++) {//  checkHolidays					
			int date = cal.get(cal.DAY_OF_WEEK);			
			if(checkweekOffs(weekooff,date) || checkHolidays(holidayss, cal.getTime())){
				totalHolidays++;
			}			
			cal.add(Calendar.DAY_OF_WEEK, 1);						
		}	
		System.out.println("Total  weekoff + holidays TOTAL ="+totalHolidays);
			trns.commit();
		} catch (Exception e) {	
			e.printStackTrace();
		}
		finally{
			if(sess.isOpen()){
				sess.close();
			}
		}	
		return totalHolidays;
	}	
		
	private boolean checkweekOffs(List<Holidays> weekOffs,int NumericDate)
	{
		for (Holidays holidays : weekOffs) {
			if(holidays.getDay_value().equals(NumericDate))
			{
				return true;				
			}
		}
		return false;
	}
	
	private boolean checkHolidays(List<Holidays> holidays,Date holidayDate)
	{
		for (Holidays holiday : holidays) {
		    if(holiday.getDaate().equals(holidayDate))
		    {
			 return true;
		    }
		}
		return false;			
	}				
	
	public int converInt(String num){
		return Integer.parseInt(num);
	}
	
	private boolean checkAppliedDays(List<EmployeeLeaveTransaction> list,Date applydate){
		for (EmployeeLeaveTransaction employeeLeaveTransaction : list) {
			if(employeeLeaveTransaction.getFromDate().equals(applydate)){
				return true;
			}
		}
		return false;
	}
	 @SuppressWarnings("unchecked")
	public JSONObject getEmployeeAllPendingdata(JSONObject emppending) {
		JSONObject status = new JSONObject();
		status.put("status",true);
		Integer eid = (Integer)emppending.get("employee_id");
		Integer fid = (Integer)emppending.get("financialyearid");
		Integer lcid = (Integer)emppending.get("leaveconfigid");
		Session session=null;
		Transaction tr =null;
		List< EmployeeFiscalYearLeaveDetails>  emploeefiscal = null;
		List< EmployeeLeaveTransaction>  elaveTransaction = null;
		ArrayList<Object> listOfDetailss = new ArrayList<Object>();
		HashMap<String, Object> leaveDatahistory = new HashMap<>();
		HashMap<String, Object> totaldays = new HashMap<>();
		Long applydays =0l;
		try {
			session=sf.openSession();
			tr = session.beginTransaction();
			Criteria crt = session.createCriteria(EmployeeLeaveTransaction.class)
					.add(Restrictions.eq("employee_id",eid))
					.add(Restrictions.eq("financialyearid",fid))
					.add(Restrictions.eq("leaveConfigurationId",lcid))
					.addOrder(Order.desc("id"));
			elaveTransaction =crt.list();
			leaveDatahistory.put("LeaveRequest", elaveTransaction);
			Query query1 = session.createQuery("FROM EmployeeFiscalYearLeaveDetails WHERE employee_id=:searchA AND financialyear_id=:searchB AND leaveConfiguration_Id=:searchC");
			emploeefiscal=query1.setParameter("searchA", eid).setParameter("searchB", fid).setParameter("searchC", lcid).list();
			leaveDatahistory.put("emploeefiscal", emploeefiscal);
		
		Criteria crtt = session.createCriteria(EmployeeLeaveTransaction.class)
				.add(Restrictions.eq("employee_id",eid))
				.add(Restrictions.eq("financialyearid",fid))
				.add(Restrictions.eq("leaveConfigurationId",lcid))
				.setProjection(Projections.sum("totaldays"));
		applydays = (Long)crtt.uniqueResult();	
		totaldays.put("Total", applydays);
		leaveDatahistory.put("ApplyDays", totaldays);
		listOfDetailss.add(leaveDatahistory);
		status.put("result", listOfDetailss);
		System.out.println("Emplyee request total days="+applydays);
		tr.commit();session.close();
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
	public List<Date> getNumberofMonths(Integer eid,Integer fid,Integer lid){
		List<Date> nummonth = new ArrayList<Date>();
		Session session=null;
		Transaction tr =null;
		List< EmployeeFiscalYearLeaveDetails>  emploeefiscal = null;
		Date dbfrom =null;
		Date dbto =null;
		try {
			session=sf.openSession();
			tr = session.beginTransaction();
			Query query = session.createQuery("FROM EmployeeFiscalYearLeaveDetails where employee_id=:searchA  AND  financialyear_id=:searchB AND leaveConfiguration_Id=:searchC");		
			 emploeefiscal =  query.setParameter("searchA", eid).setParameter("searchB", fid).setParameter("searchC", lid).list();
		} catch (Exception e) {
				e.printStackTrace();
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
		if (emploeefiscal !=null && !emploeefiscal.isEmpty() ) {
			for (EmployeeFiscalYearLeaveDetails employeeFiscalYearLeaveDetails : emploeefiscal) {
				dbfrom = employeeFiscalYearLeaveDetails.getFromDate();
				dbto = employeeFiscalYearLeaveDetails.getToDate();
				nummonth.add(dbfrom);nummonth.add(dbto);
				return nummonth;
			}		
	}
		return nummonth;	
   }
	private Date findEndDate(Date empto) {
		// End Date 
		//*******
		Calendar end = Calendar.getInstance();
		end.setTime(empto);
		int ed = end.getActualMaximum(Calendar.DATE);
  			  end.set(Calendar.DATE, ed);
  		       Date endd = end.getTime();
  		       System.out.println("*****Employee applied End date*****="+endd);
		return endd;
	}	
	public List<EmployeeFiscalYearLeaveDetails> getFiscalDateRange(Integer empid,Integer finid,Integer lecid){
		System.out.println("Inside getFiscalDateRange");
		List<EmployeeFiscalYearLeaveDetails> fiscalDate=null;
		Session sessionf = sf.openSession();
		Transaction tran = sessionf.beginTransaction();
		try {
			Query query = sessionf.createQuery("FROM EmployeeFiscalYearLeaveDetails WHERE employee_id=:searchA AND financialyear_id=:searchB AND leaveConfiguration_Id=:searchC");
		fiscalDate = query.setParameter("searchA", empid).setParameter("searchB", finid).setParameter("searchC", lecid).list();
		    for (EmployeeFiscalYearLeaveDetails employeeFiscalYearLeaveDetails : fiscalDate) {
		    	System.out.println("EmployeeFiscalYearLeaveDetails START");
				System.out.println("DB FROM Date="+employeeFiscalYearLeaveDetails.getFromDate());
				System.out.println("DB FROM Date=="+employeeFiscalYearLeaveDetails.getToDate());
				System.out.println("EmployeeFiscalYearLeaveDetails END");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(sessionf!=null&&sessionf.isOpen()){
				sessionf.close();
			}
		}
		if (fiscalDate!=null &&!fiscalDate.isEmpty()) {
			return fiscalDate;
		} else {
			return null;
		}	
	}
	
	 public boolean match(List<EmployeeFiscalYearLeaveDetails> leaveTypeConfigs,Date FromDate,Date ToDate) {   
	        int size = leaveTypeConfigs.size();        
	        boolean matched = false;      
	        for(int i=0;i<size;i++){
	           EmployeeFiscalYearLeaveDetails leaveType = leaveTypeConfigs.get(i);
	            
	            Date dbFromDate = leaveType.getFromDate();
	            		
	            Date dbToDate = leaveType.getToDate();
	            
	            Date FormfromDate = FromDate;
	            
	            Date FormtoDate =ToDate;
	            System.out.println("DB From date="+dbFromDate);
	            System.out.println("EmpFrom date="+FormfromDate);
	            System.out.println("DB TTOO date="+dbToDate);
	            System.out.println("EmpTTOO date="+FormtoDate);
	            
	            if( ( dbFromDate.before(FormfromDate) || dbFromDate.equals(FormfromDate) ) && ( dbToDate.after(FormtoDate) || dbToDate.equals(FormtoDate) ) )
	            {
	                System.out.println("Matched");
	                matched = true;
	                break;
	            }                     
	        }     
	        if(!matched){
	        	 matched = false;
	            System.out.println("Status date must be within the range");
	        }
	        return matched;
	    }
	    public static Date convertDate(String Date) {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	        try {
	            return  sdf.parse(Date);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return null;             
	    }
	    public List<EmployeeFiscalYearLeaveDetails> getAllowedDays(Integer eid,Integer fid,Integer levid,Integer pkid){
	    	Session session = null;Transaction trans = null;	    	
	    	Integer alloewddays = 0;
	    	 List<EmployeeFiscalYearLeaveDetails> dt =null;
	    	 Date dbfsfromm =null; Date dbfctom  =null;
	    	try {	    		
				 session=sf.openSession();
				 trans =session.beginTransaction();
					Query query1 = session.createQuery("FROM EmployeeFiscalYearLeaveDetails WHERE employee_id=:searchA AND financialyear_id=:searchB AND leaveConfiguration_Id=:searchC AND id=:searchD");
						dt=query1.setParameter("searchA", eid).setParameter("searchB", fid).setParameter("searchC", levid).setParameter("searchD", pkid).list();
						alloewddays=dt.get(0).getRemainingdays();
						System.out.println(" Particular quarterly FIND ID="+dt.get(0).getId());
						System.out.println(" FISCAL QUARTER PART Allowed days="+alloewddays);
						
						for (EmployeeFiscalYearLeaveDetails employeeFiscalYearLeaveDetails : dt) {
							dbfsfromm = employeeFiscalYearLeaveDetails.getFromDate();
							dbfctom = employeeFiscalYearLeaveDetails.getToDate();
							alloewddays = employeeFiscalYearLeaveDetails.getRemainingdays();
						    System.out.println("SECOND START");
							System.out.println("ID="+employeeFiscalYearLeaveDetails.getId());
						System.out.println("EID="+employeeFiscalYearLeaveDetails.getEmployee_id());
						System.out.println("FID="+employeeFiscalYearLeaveDetails.getFinancialyear_id());
						System.out.println("Remaining days="+employeeFiscalYearLeaveDetails.getRemainingdays());
						System.out.println("FROM date="+employeeFiscalYearLeaveDetails.getFromDate());
						System.out.println("To date="+employeeFiscalYearLeaveDetails.getToDate());
						  System.out.println("SECOND END ");
					}
						trans.commit();
						session.close();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}finally{
				if(session!=null&&session.isOpen()){
					session.close();
				}
			}
	    	if (dt!=null && !dt.isEmpty()) {
				return dt;
			} else {
				return null;
			}    	
	    }
	    public Long getAppliedDays(Integer eid,Integer fid,Integer levid,Date from,Date to){
	    	Session session = null;Transaction transaction =null;
	    	Long applieddays = 0l;
	    	try {
				session = sf.openSession();				
				transaction = session.beginTransaction();
				Criteria crtth = session.createCriteria(EmployeeLeaveTransaction.class)
							.add(Restrictions.eq("employee_id",eid))
							.add(Restrictions.eq("financialyearid",fid))
							.add(Restrictions.eq("leaveConfigurationId",levid))
							.add(Restrictions.eq("status", new String("pending")))
							.add(Restrictions.ge("FromDate", from))
							.add(Restrictions.le("ToDate", to))
							.setProjection(Projections.sum("totaldays"));
				applieddays = (Long)crtth.uniqueResult();	
				System.out.println(" Employee Private method="+applieddays);
			} catch (Exception e) {
					e.printStackTrace();
			}finally{
				if(session!=null&&session.isOpen()){
					session.close();
				}
			}	    	
	    	return applieddays;
	    }
  }
