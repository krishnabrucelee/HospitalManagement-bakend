package com.hospital.leave.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.joda.time.Days;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.hospital.model.Staff;
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
public class RequestLeaveEmployeeDao {
	
	@Autowired
	SessionFactory sf;
	
	@Autowired
	@Qualifier("jsonViewObjectMapper")
	private ObjectMapper jsonViewObjectMapper;
	
	@Autowired
	@Qualifier("om")
	private ObjectMapper objectMapper;
	
	@SuppressWarnings("unchecked")
	public JSONObject employeeApplyLeave(JSONObject employeerequestLeave){
		JSONObject result = new JSONObject();
		Session session =  null;
		LocalDate fromDate = LocalDate.parse(employeerequestLeave.get("From_Date").toString(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));       
        LocalDate toDate = LocalDate.parse(employeerequestLeave.get("To_Date").toString(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));    
        Date applyFromDate = Date.from(fromDate.atStartOfDay(ZoneId.of("UTC")).toInstant());        
        Date applytoFromDate = Date.from(toDate.atStartOfDay(ZoneId.of("UTC")).toInstant());
        System.out.println("From Date="+applyFromDate);
        System.out.println("To Date="+applytoFromDate);  
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date apply_fromDate = null;Date apply_todate =null;
		try {	
			
			session = this.sf.getCurrentSession();
			session.beginTransaction();
			
			// 1. Apply date not before current date end
			
			Date today = new Date();
			Date now = formatter.parse(formatter.format(today));
			apply_fromDate = formatter.parse((String)employeerequestLeave.get("From_Date"));
			 apply_todate = formatter.parse((String)employeerequestLeave.get("To_Date"));
			System.out.println("Employee apply  TO date="+apply_todate);
			if( now.compareTo(apply_fromDate) > 0 )
			{
				result.put("status",false);
				result.put("reason", "apply after current day or same day");	
				return result;
			}	
			//A.To check applied date within accounting year range
			System.out.println("Enter into before get getFianacialYear()");
			Financialyear financialyear =getFianacialYear((Integer)employeerequestLeave.get("financialyearid"));
			
			if(financialyear ==null){
				result.put("status", false);
				result.put("reason", "Admin still not configure FianacialYear & Leavetypes datails.So you are not apply leave");
				return result;
			}
			
		 if(apply_todate.compareTo(financialyear.getFinancialYear_To()) > 0){
				result.put("status", false);
				result.put("reason", "Applied date exceed Accounting year");
				return result;
			}/*else{
				
			}*/
			// Apply date not before current date end	
			 
			 List<Leavedetails> leavedetails = null;
			 
			 Query querya = session.createQuery("FROM Leavedetails WHERE financialyear_id=:searchA");
			 leavedetails = querya.setParameter("searchA",(Integer)employeerequestLeave.get("financialyearid")).list();
			 if(leavedetails==null || leavedetails.isEmpty() ){
				 result.put("status", false);
					result.put("reason", "Admin still not configure  Leavedetails.So you are not apply leave.");
					return result; 
			 }
			 
			 // 2. Check employee already applied for day begin
			// 2.1. Get Employee Leave Transaction table using financial yearid and employee id
			
			
			
			List<EmployeeLeaveTransaction> employeeLeaveTransactions = null;
			
			Query query = session.createQuery("FROM EmployeeLeaveTransaction WHERE employee_id=:searchA AND  financialyearid=:searchB");
			employeeLeaveTransactions = query.setParameter("searchA",(int)employeerequestLeave.get("employee_id"))
					.setParameter("searchB", (int)employeerequestLeave.get("financialyearid"))
					.list();	
			if(employeeLeaveTransactions == null || employeeLeaveTransactions.isEmpty())
			{
				System.err.println("No record found in employeeleavetranaction. So won't check applied date already exists or not.");
			}
			else
			{
				
				
				//
				boolean isAlreadyApplied = isOverlapDates(						
						formatter.parse(formatter.format(employeeLeaveTransactions.get(0).getFromDate())),
						formatter.parse(formatter.format(employeeLeaveTransactions.get(0).getToDate())),
						applyFromDate,
						applytoFromDate
						);
				
				
				System.out.println("isAlreadyApplied : "+isAlreadyApplied);
			
             	if(isAlreadyApplied){
             		System.err.println("Employee Already Applied this day");
                 	result.put("status",false);
    				result.put("reason", "Value already exist check applied date range");
    				return result;
             	}
			}
			// Check employee already applied for day end
			
			// 3. Check apply no.of days & remaining days with EmployeeFicalYearDetails begin
			
			List<EmployeeFiscalYearLeaveDetails> employeeLeaveDetails = null;
			
			query = session.createQuery("FROM EmployeeFiscalYearLeaveDetails where employee_id=:searchA  AND  financialyear_id=:searchB AND leaveConfiguration_Id=:searchC");
					query
					.setParameter("searchA",(int)employeerequestLeave.get("employee_id"))
					.setParameter("searchB", (int)employeerequestLeave.get("financialyearid"))
					.setParameter("searchC", (int)employeerequestLeave.get("leaveconfigid"));
					
			employeeLeaveDetails = query.list();	
			
			// Check apply no.of days & remaining days with EmployeeFicalYearDetails end
			
			// 4. Check Employee Leave Apply date is Within Range or Overlapped with db ranges begin
			
			
			JSONObject rangeResult = matchTwoRange(employeeLeaveDetails,
					applyFromDate,
					applytoFromDate);
			System.out.println("JSONObject rangeResult="+rangeResult);
			/*if(!(boolean)rangeResult.get("matched")){
				result.put("status",false);
				result.put("reason", "Apply Date is Not matched with current finanical year");
				return result;
			}	*/		
			// Check Employee Leave Apply date is Within Range or Overlapped with db ranges end
			
			// 5. Find out holidays & week offs and minus the total number of applied days begin
			
			ArrayList<Map<String, Object>> filterResults =  (ArrayList<Map<String, Object>>) rangeResult.get("filterResult");
			System.out.println("FilterResults="+filterResults);
			for (Map<String, Object> filterResult : filterResults) {
				int reduceHoliday = getHoliday(filterResult);
				
				EmployeeFiscalYearLeaveDetails FYLeaveDetails = (EmployeeFiscalYearLeaveDetails) filterResult.get("fiscalYearLeaveBalance");
				
				Long appliedDays = getAppliedDays((int)employeerequestLeave.get("employee_id"),
						        (int)employeerequestLeave.get("financialyearid"),
						        (int)employeerequestLeave.get("leaveconfigid"),						        
						        FYLeaveDetails.getFromDate(),
						        FYLeaveDetails.getToDate()
						       );				
			
				long totalApplyday = (int)filterResult.get("numberofdays")-reduceHoliday;
				long allApplydays = totalApplyday;			
				if(appliedDays != null){					
					allApplydays = totalApplyday+appliedDays;
				}				
				int allowedday = FYLeaveDetails.getRemainingdays() ;			
				if(allApplydays > allowedday)
				{	
					result.put("status",false);
					result.put("reason","You have only "+ allowedday +" days remaining. But you are trying to apply "+allApplydays+" (after holiday weakoff and already applied days deduction) days");					
					return result;
				}				
				if(allowedday == 0)
				{
					result.put("status",false);
					result.put("reason","You had done all leave request in this period");
    				System.out.println("You had done all leave request in this period");
    				return result;
				}
				
				
				// Save EmployeeLeaveTransaction
				EmployeeLeaveTransaction elt = new EmployeeLeaveTransaction();
				elt.setCreationDate(new Date());
				elt.setEmployee_id((int)employeerequestLeave.get("employee_id"));
				elt.setFinancialyearid((int)employeerequestLeave.get("financialyearid"));
				elt.setFromDate((Date)filterResult.get("From_Date"));
				elt.setToDate((Date)filterResult.get("To_Date"));
				elt.setLeaveConfigurationId((int)employeerequestLeave.get("leaveconfigid"));
				elt.setReason((String)employeerequestLeave.get("Reason"));
				elt.setStatus("pending");
				elt.setTotaldays((int)totalApplyday);	
				System.out.println("EmployeeLeaveTransaction="+elt);
				session.save(elt);				
				System.out.println(" Your current and previous+appliedDays= "+allApplydays);
				result.put("status",true);
				
				result.put("result", "Leave applied success Your total apply days"+allApplydays +"");
			}
			
			session.beginTransaction().commit();
			
			return result;
		
			// Find out holidays & week offs and minus the total number of applied days end
			
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("reason", "error happend");
			result.put("message", e.getMessage());
			e.printStackTrace();
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return result;
	}
	
	
	@SuppressWarnings({ "unchecked", "unused" })
	public JSONObject requestLeaveEmployee(JSONObject emprequestLeave) {
		System.out.println("Inside employee request leave");
		JSONObject status = new JSONObject();
		status.put("status","reson");
		SimpleDateFormat sddf = new SimpleDateFormat("dd-MM-yyyy");
		List<EmployeeFiscalYearLeaveDetails> fiscaldata = null;
		Integer financialId = getCurrentFinancialYearId();
	
			List<Financialyear> finanacial =null;Integer finid=0;Integer fyear=0;
			
			/*try {
				sessionf = this.sf.getCurrentSession();
				transactionf = sessionf.beginTransaction();
			Query query = sessionf.createQuery("FROM Financialyear WHERE fromyear=:searchA");	
			finanacial = query.setParameter("searchA", financialFromYear).list();
			fyear = finanacial.get(0).getFromyear();		
			finid = finanacial.get(0).getFinancialyearid();		
			transactionf.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				if(sessionf!=null&&sessionf.isOpen()){
					sessionf.close();
				}
			}*/
			
			Session session = this.sf.openSession();
			Transaction tr = session.beginTransaction();
			Integer eid = (Integer)emprequestLeave.get("employee_id");
			Integer fid = (Integer)emprequestLeave.get("financialyearid");
			Integer lcid = (Integer)emprequestLeave.get("leaveconfigid");			
			Integer empdays = (Integer)emprequestLeave.get("numberofdays");			
			String reason = emprequestLeave.get("Reason").toString();
			Date empfrom =null;
			Date empto = null; Long exceedapply=0l;Long applydays =0l;
			
			try {
				 empfrom = sddf.parse(emprequestLeave.get("From_Date").toString());
				 empto = sddf.parse(emprequestLeave.get("To_Date").toString());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			List<Date> nummonth = new ArrayList<Date>();
			Integer totalleave = 0;
			Integer leaveRemaingDays = 0;		
			Integer lid =0;
			String leavetype = "";	
			 List<Leavedetails> lds = null;		
			ArrayList<Date> halfyearly = new ArrayList<Date>();
			ArrayList<Date> quarterly = new ArrayList<Date>();
			ArrayList<Date> monthly = new ArrayList<Date>();
			Date dbfrom =null;
			Date dbto =null;
			List< EmployeeFiscalYearLeaveDetails>  emploeefiscal = null;
			List<EmployeeFiscalYearLeaveDetails> range = null;
			List<EmployeeLeaveTransaction>exist=null;
			Date dbfeomdatee=null; Date dbtodatee=null;
			Date dbfromdatec=null; Date dbtodatecon=null;Integer rid=0;
			 ArrayList<HashMap<Object,Object>> allLeaveConfigForFY = new ArrayList<HashMap<Object,Object>>();
			Integer totalHolydays = 0;Integer totalApplyday = 0;
			/*totalHolydays=	getHoliday(emprequestLeave);
			totalApplyday = empdays-totalHolydays;*/

			Session session2=null;Transaction transaction=null;
			//ArrayList<HashMap<String,LocalDate>> allLeaveTransactions = new ArrayList<>();
			 try {
					session2 = this.sf.getCurrentSession();
					transaction = session2.beginTransaction();
					
					Query queryf = session2.createQuery("FROM Financialyear WHERE 	financialyearid=:searchA");	
					finanacial = queryf.setParameter("searchA", financialId).list();
					
					if (finanacial!=null && !finanacial.isEmpty()&&finanacial.size()>0 ) {
						fyear = finanacial.get(0).getFromyear();		
						finid = finanacial.get(0).getFinancialyearid();		
						/*Query query = session2.createQuery("FROM EmployeeLeaveTransaction WHERE employee_id=:searchA AND  financialyearid=:searchB AND status=:searchC");
						exist = query.setParameter("searchA", eid).setParameter("searchB", finid).setParameter("searchC", new String("pending")).list();					*/
						Query query = session2.createQuery("FROM EmployeeLeaveTransaction WHERE employee_id=:searchA AND  financialyearid=:searchB");
						exist = query.setParameter("searchA", eid).setParameter("searchB", finid).list();					
						transaction.commit();
					} else {
						System.err.println("Admin still not yet to configure Financial year");
                    	System.err.println("Admin still not yet to configure Leave types");
                    	status.put("status",false);
                		status.put("Reason", "Admin still not yet to configure Leave types & Financial year");
                		
                		return status;
					}
										
				} catch (NullPointerException e) {
					e.printStackTrace();
				}if(session2!=null&&session2.isOpen()){
					session2.close();
				}								
				// Check apply date is exist or not
               
                 if (exist!=null&&!exist.isEmpty()) {
					System.out.println("Enter into Already applied block");
                	System.out.println("Check apply date range");
                	boolean  result = false;
                   result=	CheckDate(exist, emprequestLeave.get("From_Date").toString());
                	if(result){
                		System.out.println("Enter into Already applied block");
                    	System.out.println("Check apply date range");
                    	status.put("status",false);
                		status.put("Reason", "Value already exist check applied date range");
                		
                		return status;
                	}
                	
				}	
                
              
               
				Session sessiona=null;Transaction transactiona=null;
				 Date dbfromyr = null; Date dbtoyr=null;
				try {
					sessiona = this.sf.getCurrentSession();
					transactiona = sessiona.beginTransaction();
					Query querya = session.createQuery("FROM Leavedetails WHERE id=:searchA AND financialyear_id=:searchB");
					 lds = querya.setParameter("searchA", lcid).setParameter("searchB", financialId).list();				
					if(lds!=null){
						leavetype = lds.get(0).getLeave_days_type();
					}		
					System.out.println("Leave type="+leavetype);
					Query query = session.createQuery("FROM EmployeeFiscalYearLeaveDetails where employee_id=:searchA  AND  financialyear_id=:searchB AND leaveConfiguration_Id=:searchC");		
					  emploeefiscal =  query.setParameter("searchA", eid).setParameter("searchB", financialId).setParameter("searchC", lcid).list();
					 rid = emploeefiscal.get(0).getRemainingdays();
					  for (EmployeeFiscalYearLeaveDetails employeeFiscalYearLeaveDetails : emploeefiscal) {
							leaveRemaingDays = employeeFiscalYearLeaveDetails.getRemainingdays();
							lid = employeeFiscalYearLeaveDetails.getLeaveConfiguration_Id();
							dbfrom = employeeFiscalYearLeaveDetails.getFromDate();
							dbto = employeeFiscalYearLeaveDetails.getToDate();
							
								halfyearly.add(dbfrom);halfyearly.add(dbto);									
						}
						for (EmployeeFiscalYearLeaveDetails employeeFiscalYearLeaveDetailss : emploeefiscal) {
							leaveRemaingDays = employeeFiscalYearLeaveDetailss.getRemainingdays();
							lid = employeeFiscalYearLeaveDetailss.getLeaveConfiguration_Id();
							dbfrom = employeeFiscalYearLeaveDetailss.getFromDate();
							dbto = employeeFiscalYearLeaveDetailss.getToDate();
							quarterly.add(dbfrom);quarterly.add(dbto);	
							
						}
						for (EmployeeFiscalYearLeaveDetails employeeFiscalYearLeaveDetailss : emploeefiscal) {
							leaveRemaingDays = employeeFiscalYearLeaveDetailss.getRemainingdays();
							lid = employeeFiscalYearLeaveDetailss.getLeaveConfiguration_Id();
							dbfrom = employeeFiscalYearLeaveDetailss.getFromDate();
							dbto = employeeFiscalYearLeaveDetailss.getToDate();
							monthly.add(dbfrom);monthly.add(dbto);				
						}
						transactiona.commit();
				} catch (Exception e) {
						e.printStackTrace();
				}finally{
					if(sessiona!=null&&sessiona.isOpen()){
						sessiona.close();
					}
				}				
				 totalleave = getHoliday(emprequestLeave);
			
				empdays=empdays-totalleave;
				totalHolydays=	getHoliday(emprequestLeave);
				totalApplyday = empdays-totalHolydays;
				
					if(leavetype.equals("yearly")){
						System.out.println("Enter yearly leave type");
						range = getFiscalDateRange( eid, finid, lcid);
						if (match(range,empfrom,empto)){
							System.out.println("***MATCH if condition SUCCESS***");
							if(emploeefiscal != null && !emploeefiscal.isEmpty()){	
								//List<EmployeeFiscalYearLeaveDetails> fiscaldata = null;
								Session s = null;
								Transaction transaction2 = null;
								
								try {
									System.out.println("***MATCH if condition inside try***");
									s = this.sf.getCurrentSession();
									transaction2 = s.beginTransaction();
									Query query = s.createQuery("FROM EmployeeFiscalYearLeaveDetails WHERE employee_id=:searchA AND  financialyear_id=:searchB AND leaveConfiguration_Id=:searchC");
									fiscaldata = query.setParameter("searchA", eid).setParameter("searchB", finid).setParameter("searchC", lcid).list();
								transaction2.commit();
								
								} catch (Exception e) {
									e.printStackTrace();
								}finally{
									if(s!=null&&s.isOpen()){
										s.close();
									}
								}
								
	        					//fiscaldata = getAllowedDays(eid,fid,lcid,rid);	//Get remaining days
								Session sessionap = null;Transaction transactionap =null;
						    	Long totalapplyDays = 0l;
						    	try {
						    		System.out.println("***MATCH if condition CRITERIA query***");
									sessionap = this.sf.getCurrentSession();				
									transactionap = sessionap.beginTransaction();
									Criteria crtth = sessionap.createCriteria(EmployeeLeaveTransaction.class)
												.add(Restrictions.eq("employee_id",eid))
												.add(Restrictions.eq("financialyearid",finid))
												.add(Restrictions.eq("leaveConfigurationId",lcid))
												.add(Restrictions.eq("status", new String("pending")))
												.add(Restrictions.ge("FromDate", fiscaldata.get(0).getFromDate()))
												.add(Restrictions.le("ToDate", fiscaldata.get(0).getToDate()))
												.setProjection(Projections.sum("totaldays"));
									totalapplyDays = (Long)crtth.uniqueResult();
									System.out.println("***MATCH if condition CRITERIA query END***");
									//System.out.println(" Employee Private method="+totalapplyDays);
									transactionap.commit();
									
								} catch (Exception e) {
										e.printStackTrace();
								}finally{
									if(sessionap!=null&& sessionap.isOpen()){
										sessionap.close();
									}
								}
								//Long totalapplyDays = getAppliedDays(eid, fid, lcid,dbfromyr,dbtoyr);
								int allowedday = fiscaldata.get(0).getRemainingdays();
								
								if(totalapplyDays == null){
		                			System.out.println("Empty Leave Transaction. Start New One");                			
		                			try {
		                				System.out.println("Employee not applied for particular leave");
		                				
										return saveEmployeeLeaveTransction(emprequestLeave,status, session, tr, eid, finid, lcid, reason);
									} catch (ParseException e) {
										e.printStackTrace();
									}  
		                		} else{
		                			if (allowedday>totalapplyDays) {
		                				System.out.println("########Already applied  again insert****");		                			
		                				Long applydayTotal =0l;
		                				applydayTotal=totalapplyDays;
		                				applydayTotal=applydayTotal+(long)totalApplyday;
		                				if(allowedday >= applydayTotal){
		                					
		                					 try {
		                						 System.out.println("########Enter into SECOND TIME REQUEST SAVE  ****");
												return saveEmployeeLeaveTransction(emprequestLeave,status, session, tr, eid, finid, lcid, reason);
											} catch (ParseException e) {
												e.printStackTrace();
											}  
		                				}else{
		                					long capplydays =totalapplyDays;
		                					capplydays=capplydays+(long)totalApplyday;		         					
		                					status.put("reason","Already applied day"+totalapplyDays);
		                					return status;
		                				}									
									} else {
										status.put("status",false);
		                				status.put("reason","You had done all leave request in this period");
		                				System.out.println("You had done all leave request in this period");
		                				return status;
									}		                			
		                		}//Check and save second time end
							}else{
								status.put("status",false);
		                		status.put("reason", "Cannot find particular fiscal year leave details");
		                		System.err.println("Cannot find particular fiscal year leave details");
		                		return status;
							}//fiscal end
						}else{
							 status.put("Information", "Date must be within the range");
						}
						
					}
					
					if(leavetype.equals("halfyearly")){
						System.out.println("Inside halfyearly  leave request start ");		
						range = getFiscalDateRange( eid, finid, lcid);
						
						if (match(range,empfrom,empto)){
							System.out.println("Apply date range matched");	
		                	// Remaining Days                	            	
		                	// Get Particular EmployeeFiscalYearLeaveDetails Remaining Days
		                	EmployeeFiscalYearLeaveDetails FYLeaveDetails = getParticularFiscalLDetails(empto, emploeefiscal);         
		                	if(FYLeaveDetails != null){
		                		Long totalapplyDays = getAppliedDays(eid, finid, lcid, FYLeaveDetails.getFromDate(), FYLeaveDetails.getToDate());
		                		if(totalapplyDays == null){
		                			System.out.println("Empty Leave Transaction. Start New One");                			
		                			try {
		                				System.out.println("Particular halfyearly="+FYLeaveDetails.getId());
		                				System.out.println("Employee not applied for particular leave");
		                				
										return saveEmployeeLeaveTransction(emprequestLeave,status, session, tr, eid, finid, lcid, reason);
									} catch (ParseException e) {
										e.printStackTrace();
									}  
		                		}else{
		                			int allowedday = FYLeaveDetails.getRemainingdays() ;
		                			if (allowedday>totalapplyDays) {
		                				
		                				System.out.println("########Already applied  again insert****");
		                				//totalApplyday = totalApplyday+totalapplyDays;
		                				Long applydayTotal =0l;
		                				applydayTotal=totalapplyDays;
		                				applydayTotal=applydayTotal+(long)totalApplyday;
		                				if(allowedday >= applydayTotal){
		                				
		                					 try {
		                						 System.out.println("########Enter into SECOND TIME REQUEST SAVE  ****");
												return saveEmployeeLeaveTransction(emprequestLeave,status, session, tr, eid, finid, lcid, reason);
											} catch (ParseException e) {
												e.printStackTrace();
											}  
		                				}else{
		                					long capplydays =totalapplyDays;
		                					capplydays=capplydays+(long)totalApplyday;
		                					
		                					status.put("reason","Already applied day"+totalapplyDays);
		                					return status;
		                				}
										
									} else {
										status.put("status",false);
		                				status.put("reason","You had done all leave request in this period");
		                				System.out.println("You had done all leave request in this period");
		                				return status;
									}
		                		}//SECOND time leave request check and save end//
		                	
		                	}else{
		                		status.put("status",false);
		                		status.put("reason", "Cannot find particular fiscal year leave details");
		                		System.err.println("Cannot find particular fiscal year leave details");
		                		return status;
		                	}
						
						}else{
							System.err.println("Date must be within the range");
							 status.put("Information", "Date must be within the range");
						}//Match else end
						
					}//HALF END
					if(leavetype.equals("quarterly")){//QUAT START
						System.out.println("Inside quarterly  leave request start ");								
						range = getFiscalDateRange( eid, finid, lcid);						
						// Check Apply Date is with in the Range of EmployeeFiscalYearLeaveDetails.
		                if(match(range,empfrom,empto)){//Match start
		                	System.out.println("Apply date range matched");			                              	            	
		                	// Get Particular EmployeeFiscalYearLeaveDetails Remaining Days
		                	EmployeeFiscalYearLeaveDetails FYLeaveDetails = getParticularFiscalLDetails(empto, emploeefiscal);    
		                	System.out.println("Particular quarterly="+FYLeaveDetails.getId());
		                	if(FYLeaveDetails != null){		                		
		                		// Get total applied days from leaveTransaction only for pending status
		                		Long totalapplyDays = getAppliedDays(eid, finid, lcid, FYLeaveDetails.getFromDate(), FYLeaveDetails.getToDate());		                		
		                		if(totalapplyDays == null){
		                			System.out.println("Empty Leave Transaction. Start New One");                			
		                			try {
		                				System.out.println("Particular quarterly="+FYLeaveDetails.getId());
		                			
										return saveEmployeeLeaveTransction(emprequestLeave,status, session, tr, eid, finid, lcid, reason);
									} catch (ParseException e) {
										e.printStackTrace();
									}  
		                		} else{
		                			int remaningDays = FYLeaveDetails.getRemainingdays() ;
		                			if(remaningDays > totalapplyDays )//if(remaningDays > totalapplyDays )
		                			{
		                				
		                				Long applydayTotal =0l;
		                				applydayTotal=totalapplyDays;
		                				applydayTotal=applydayTotal+(long)totalApplyday;
		                				if(remaningDays >= applydayTotal)
		                				{
		                					 try {
		                						 System.out.println("########Enter into SECOND TIME REQUEST SAVE  ****");
												return saveEmployeeLeaveTransction(emprequestLeave,status, session, tr, eid, finid, lcid, reason);
											} catch (ParseException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}  
		                				}else{
		                					status.put("status",false);
		                					System.err.println("You have only "+remaningDays +" these days remining but you are trying to apply "+emprequestLeave.get("numberofdays")+" these days");
		                					status.put("Reason", "Your current and previous applied days more than your remaining days");
		                					return status;
		                				}		                				
		                			}else{
		                				status.put("status",false);
		                				status.put("reason","You had done all leave request in this period");
		                				System.out.println("You had done all leave request in this period");
		                				return status;
		                			}
		                		}//SECOND TIMME END		                		
		                	}else{
		                		status.put("status",false);
		                		status.put("reason", "Cannot find particular fiscal year leave details");
		                		System.err.println("Cannot find particular fiscal year leave details");
		                		return status;
		                	}		                	
		                }
		                else{
		                	System.err.println("Range Not Matched");
		                	status.put("status",false);
		                	status.put("reason", "Range Not Matched");
		                	return status;
		                }
						
					}//QUART END
					if(leavetype.equals("monthly")){//Monthly START
						System.out.println("ENTER MONTHLY Leave Request");										
						Integer pkdid = 0;Integer alloweddayss =0;
						Long reqq=0l;
						Calendar calendar =Calendar.getInstance();
						calendar.setTime(empfrom);
						System.out.println("$$$Applied date$$$==="+calendar.getTime());
						calendar.set(Calendar.DAY_OF_MONTH,1);
						System.out.println("$$$MONTH FIRST DATE$$$==="+calendar.getTime());
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date dfrom = calendar.getTime();
						//Date d3 = sdf.parse(dfrom.for)
		                 range = getFiscalDateRange( eid, finid, lcid);	                 	         
		                 if(endMonthMatch(range,empfrom,empto)){
		                	 System.out.println("Ranged matched");
		                	 Date dbmfrom = null; Date dbmto = null;		                 
		 					int sizeh = emploeefiscal.size();
		 					System.out.println("Monthly size="+sizeh);
		 					if(emploeefiscal != null && !emploeefiscal.isEmpty()){//Fiscal Start
		 						Date endd = findEndDate(empto);
				      		       System.out.println("Request month end="+endd);      		       
				      		       int ficalsizee = emploeefiscal.size();
				      		     EmployeeFiscalYearLeaveDetails matchedDetailss = null;    		     
				      		     System.out.println("SIZE= :"+ficalsizee);
				      		   for(int i=0;i<ficalsizee;i++){//Start Size			      			
				      			   matchedDetailss = emploeefiscal.get(i);				      			
				      			 System.out.println("Matched employeeFiscal ID="+matchedDetailss.getRemainingdays());
									System.out.println("Matched employeeFiscal ID="+matchedDetailss.getId());
									System.out.println("###Before check applied date first month##="+dfrom);
									System.out.println("DB FROM DATE="+matchedDetailss.getFromDate());
									if((matchedDetailss.getFromDate().compareTo(dfrom)==0)&&(matchedDetailss.getToDate().compareTo(endd)==0)){ //Exact match period
										System.out.println("After enter exact match month first and last date");
										Long totalapplyDays = getAppliedDays(eid,fid,lcid,matchedDetailss.getFromDate(),matchedDetailss.getToDate());
									System.out.println("Particular applied days="+totalapplyDays);
									Integer pkid = matchedDetailss.getId();
									System.out.println("***EXACT MONTH ID***="+pkid);
									 List<EmployeeFiscalYearLeaveDetails>  fiscal=getAllowedDays(eid,finid,lcid,pkid);
									Integer  remainingDays = fiscal.get(0).getRemainingdays();
									if(totalapplyDays == null){
										System.out.println("Empty Leave Transaction. Start New One");                			
			                			try {
			                				System.out.println("Particular monthly="+pkid);
			                				
											return saveEmployeeLeaveTransction(emprequestLeave,status, session, tr, eid, finid, lcid, reason);
										} catch (ParseException e) {
											e.printStackTrace();
										}  
									}else{
										
			                			if(remainingDays > totalapplyDays ){ //if(remaningDays > totalapplyDays )			                			
			                				
			                				Long applydayTotal =0l;
			                				applydayTotal=totalapplyDays;
			                				applydayTotal=applydayTotal+(long)totalApplyday;			              		
			                				if(remainingDays >= applydayTotal)
			                				{
			                					 try {
			                					
													return saveEmployeeLeaveTransction(emprequestLeave,status, session, tr, eid, finid, lcid, reason);
												} catch (ParseException e) {
													e.printStackTrace();
												}  
			                				}else{
			                					status.put("status",false);
			                					
			                					status.put("Reason", "Your current and previous applied days more than your remaining days");
			                					return status;
			                				}		                				
			                			}else{
			                				status.put("status",false);
			                				status.put("reason","You had done all leave request in this period");
			                				System.out.println("You had done all leave request in this period");
			                				return status;
			                			}
									}//SECON TIME END									
									}//Exact match period end	
									else{
										status.put("reason", "Range Not Matched");
									}
				      		   }//End Size
		 					}//Fiscal END
		                 }else{
		                	 System.err.println("Range Not Matched");
			                	status.put("status",false);
			                	status.put("reason", "Range Not Matched");
			                	return status;
		                 }
					}//Monthly END		
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject getEmployeeFiscalyearleaveBalance(JSONObject employeedata) {
		return null;
	}
	//######*****///
	@SuppressWarnings("unchecked")
	public JSONObject employeeAllPARViewStatus(JSONObject leaveRequestStatus) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		System.out.println("Enter into employeeAllPARViewStatus DAO");
		Integer employid = (Integer)leaveRequestStatus.get("employee_id");
		Integer financialYearId = getCurrentFinancialYearId();	
		System.out.println("Enter into after get financial id");
		HashMap<String, Object> resultDatas = new HashMap<String, Object>();
		List<EmployeeLeaveTransaction> value = null;
		value = getAllPARData(employid);
		System.out.println("Enter into after get ALL pending  APP REJ data="+value);
		HashSet<Integer> employe = new HashSet<Integer>();
		HashSet<Integer> leave = new HashSet<Integer>();
		if (value != null) {
			System.out.println("Enter into value not null");
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
				System.out.println("Enter intoJSON VIEW try");
				session = this.sf.getCurrentSession();
				tr = session.beginTransaction();
				Criteria crt = session.createCriteria(Staff.class);
				crt.add(Property.forName("staffId").in(employe.toArray()));
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
				System.out.println("Enter into all value return");
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

	@SuppressWarnings("unchecked")
	public JSONObject employeeViewPendingStatus(JSONObject leaveRequestpendingStatus) {
		return null;
	}

	@SuppressWarnings("unchecked")
	public JSONObject employeeViewApprovedStatus(JSONObject leaveRequestApprovedStatus) {
		return null;
	}

	@SuppressWarnings("unchecked")
	public JSONObject employeeCurrentMonthLeaveRequestStatus(JSONObject currentMonthLeaveRequestStatus) {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getemployeeCurrentRangeLeaveRequest(JSONObject currentRangeLeaveRequest) {
		return null;
	}
	//////////
	@SuppressWarnings("unchecked")//SAVE EMPLOYEE
	private JSONObject saveEmployeeLeaveTransction(JSONObject emprequestLeave,JSONObject status, Session session, Transaction tr, Integer eid,
			Integer fid, Integer lcid, String reason) throws ParseException {
		System.out.println("*********EmployeeLeaveTransaction  insert value START**********");
		EmployeeLeaveTransaction elt = new EmployeeLeaveTransaction();
		Session sessions = null;
		Transaction transaction =null;
		
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
			sessions = this.sf.getCurrentSession();
			transaction = sessions.beginTransaction();
			sessions.save(elt);		
			status.put("status",true);
			transaction.commit();	
		} catch (Exception e) {
			status.put("status",false);
			status.put("reason","error happend");
			status.put("error",e.getMessage());
			
		}if(sessions!=null&&sessions.isOpen()){
			sessions.close();
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
	@SuppressWarnings("unchecked")
	public int getHoliday(Map emprequestLeave){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S");
		Date dbdt = null;
		System.out.println("Inside holiday calculation");
		/*String fromDate = emprequestLeave.get("From_Date").toString();
		
		String toDate = emprequestLeave.get("To_Date").toString();
		Date applydate =null;Date applytodate =null; List<Date> dt = null;
		
		
		try {
			 applydate = sdf.parse(emprequestLeave.get("From_Date").toString());
			 applytodate = sdf.parse(emprequestLeave.get("To_Date").toString());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}*/
		Session sess = null;
		Transaction trns = null;
		Integer fid = getCurrentFinancialYearId();
		
			Date dbdate =null;
			int ndvalue=0;
		//dd-MM-yyyy
		
		int totalDays = (int)emprequestLeave.get("numberofdays");
		int totalHolidays = 0;
		int weekoof = 0;
		//From date is set toCaledar date
		Calendar cal = Calendar.getInstance();
		cal.setTime((Date)emprequestLeave.get("From_Date"));
		List<Holidays> apply = null;
			List<Integer> week = null;	List<Integer> hlolidy = null;
		List<Holidays> weekooff = null;List<Holidays> holidayss = null;
		String leavetype=null;String wekof=null;String publicholyday=null;int weeknvale=0;int holynvalue=0;
	
		List<Weekoff> weekof = null;		
		try {
			sess = this.sf.getCurrentSession();
			sess.beginTransaction();
			/*Query query = sess.createQuery("FROM Financialyear WHERE  fromyear=:searchA");
			 financeyear = query.setParameter("searchA", financialFromYear).list();	*/
			 // if (financeyear!=null&&!financeyear.isEmpty()) {
			 if (fid!=0) {
				 //Integer fidd = financeyear.get(0).getFinancialyearid();
					//Query queryw = sess.createQuery("FROM Weekoff WHERE financialyr_id=:searchA AND");			
					Query query2 = sess.createQuery("FROM Holidays WHERE financialyr_id=:searchA AND leave_type=:searchB");
					weekooff = query2.setParameter("searchA", fid).setParameter("searchB", new String("weekoff")).list();				
					 Query query3 = sess.createQuery("FROM Holidays WHERE financialyr_id=:searchA AND leave_type=:searchB");
					 holidayss = query3.setParameter("searchA", fid).setParameter("searchB", new String("public holiday")).list();			
					 for (Holidays holiday : holidayss) {
							holynvalue=holiday.getDay_value();
							dbdt=holiday.getDaate();		
						}
					
				for (int i = 0; i < totalDays; i++) {//  checkHolidays					
					int date = cal.get(cal.DAY_OF_WEEK);			
					if(checkweekOffs(weekooff,date) || checkHolidays(holidayss, cal.getTime())){
						totalHolidays++;
					}			
					cal.add(Calendar.DAY_OF_WEEK, 1);//add one date to current date						
				}	
				System.out.println("Total  weekoff + holidays TOTAL ="+totalHolidays);					
			} else {
				System.err.println("Admin still not configured Holidays Details");

			}
			/*Integer fidd = financeyear.get(0).getFinancialyearid();
			System.out.println("Financialyear ID="+fidd);
			//Query queryw = sess.createQuery("FROM Weekoff WHERE financialyr_id=:searchA AND");			
			Query query2 = sess.createQuery("FROM Holidays WHERE financialyr_id=:searchA AND leave_type=:searchB");
			weekooff = query2.setParameter("searchA", fidd).setParameter("searchB", new String("weekoff")).list();				
			 Query query3 = sess.createQuery("FROM Holidays WHERE financialyr_id=:searchA AND leave_type=:searchB");
			 holidayss = query3.setParameter("searchA", fidd).setParameter("searchB", new String("public holiday")).list();			
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
			trns.commit();*/
		} catch (Exception e) {	
			e.printStackTrace();
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
			session=this.sf.getCurrentSession();
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
	@SuppressWarnings("unchecked")
	public List<Date> getNumberofMonths(Integer eid,Integer fid,Integer lid){
		List<Date> nummonth = new ArrayList<Date>();
		Session session=null;
		Transaction tr =null;
		List< EmployeeFiscalYearLeaveDetails>  emploeefiscal = null;
		Date dbfrom =null;
		Date dbto =null;
		try {
			session=this.sf.getCurrentSession();
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
  		      
		return endd;
	}
	@SuppressWarnings("unchecked")
	public List<EmployeeFiscalYearLeaveDetails> getFiscalDateRange(Integer empid,Integer finid,Integer lecid){
		
		List<EmployeeFiscalYearLeaveDetails> fiscalDate=null;
		Session sessionf = null;
		Transaction tran = null;
		
		try {
			sessionf = this.sf.getCurrentSession();
			tran = sessionf.beginTransaction();
			Query query = sessionf.createQuery("FROM EmployeeFiscalYearLeaveDetails WHERE employee_id=:searchA AND financialyear_id=:searchB AND leaveConfiguration_Id=:searchC");
		fiscalDate = query.setParameter("searchA", empid).setParameter("searchB", finid).setParameter("searchC", lecid).list();
		tran.commit();
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
	            //( dbFromDate.before(FormfromDate) || dbFromDate.equals(FormfromDate)//( dbToDate.after(FormtoDate) || dbToDate.equals(FormtoDate) )
	            Date FormtoDate =ToDate;
	          
if((((dbFromDate.before(FormfromDate))||(dbFromDate.compareTo(FormfromDate)==0 ))||((dbFromDate.before(FormfromDate))||(dbToDate.compareTo(FormfromDate)==0 )))&&((dbToDate.after(FormtoDate))||(dbToDate.compareTo(FormtoDate)==0))){
	 System.out.println("Matched");
     matched = true;
     break;
	
	        }	           	                     
	        }     
	        if(!matched){
	        	 matched = false;
	          
	        }
	        return matched;
	    }
	 
	 @SuppressWarnings("unchecked")
	public JSONObject matchTwoRange(List<EmployeeFiscalYearLeaveDetails> leaveTypeConfigs,Date FromDate,Date ToDate) {   
		 
	        int size = leaveTypeConfigs.size(); 
	      
	        boolean matched = false;
	        EmployeeFiscalYearLeaveDetails leaveType = null;
	        
	        JSONObject result = new JSONObject();
	        ArrayList<Map<String,Object>> dbObject = null;
		    
	        
	        boolean isRangeExceed = false;
	        
	        for(int i=0;i<size;i++)
	        {
	        	leaveType = leaveTypeConfigs.get(i);        	
	        	
	        	boolean isFromDateValid = (FromDate.compareTo(leaveType.getFromDate()) >= 0) 
	        										&& 
	        							  (FromDate.compareTo(leaveType.getToDate()) <=0 );
	        	
	        	boolean isToDateValid = (ToDate.compareTo(leaveType.getFromDate()) >= 0) 
	        			                            &&
	        			                (ToDate.compareTo(leaveType.getToDate()) <= 0);	        	
	        	
	        	if(isFromDateValid && isToDateValid)
	        	{
	        		matched = true;
	        		dbObject =  new ArrayList<Map<String, Object>>();
		        	System.err.println("With in the Range");	
		        	Map<String,Object> firstHalf = new HashMap<String, Object>();	       
		         	firstHalf.put("From_Date",FromDate );
		         	firstHalf.put("To_Date",ToDate );
		         	firstHalf.put("numberofdays",Days.daysBetween(
		         	           new org.joda.time.LocalDate(FromDate.getTime()), 
		         	           new org.joda.time.LocalDate(ToDate.getTime())).getDays()+1);
		         	firstHalf.put("fiscalYearLeaveBalance",leaveType);
		         	dbObject.add(firstHalf);
	        		break;
	        	}
	        	else if(isFromDateValid && !isToDateValid){
	        		isRangeExceed = true;
	        		matched = true;
	        		System.err.println("Range exceed Apply two range values");	        		
	        		break;
	        	}
	        	else
	        	{
	        		System.err.println("not match");
	        	}	
	        	
	        }
	        
	        result.put("matched", matched);
	        
	        if(matched && !isRangeExceed)
	        {	 
	        	result.put("filterResult",dbObject );
	        	return result;
	        }	        	
	        else if(!matched){
	        	System.err.println("Apply Date Exceed Accounting year");
	        	return result;
	        }
	        
	        //** Prepare Leave Object which is going to be insert to db **//
	        
	        dbObject =  new ArrayList<Map<String, Object>>();
	        
	        Map<String,Object> firstHalf = new HashMap<String, Object>();	       
        	firstHalf.put("From_Date",FromDate );
        	firstHalf.put("To_Date",leaveType.getToDate() );
        	firstHalf.put("numberofdays",Days.daysBetween(
        	           new org.joda.time.LocalDate(FromDate.getTime()), 
        	           new org.joda.time.LocalDate(leaveType.getToDate().getTime())).getDays()+1);
        	
        	
        	
        	Calendar Cal_toDate = Calendar.getInstance();
        	Cal_toDate.setTime(leaveType.getToDate());
        	Cal_toDate.add(Calendar.DATE,1);
        	
        	 Map<String,Object> secondHalf = new HashMap<String, Object>();
        	 secondHalf.put("From_Date",Cal_toDate.getTime() );
        	 secondHalf.put("To_Date",ToDate );
        	 secondHalf.put("numberofdays",Days.daysBetween(
         	           new org.joda.time.LocalDate(Cal_toDate.getTime() ), 
         	           new org.joda.time.LocalDate(ToDate.getTime())).getDays()+1);
        	
        	 
        	 dbObject.add(firstHalf);
        	 dbObject.add(secondHalf);
        	 
        	 // Find and Assign Employee Leave Transaction for particular Range
        	addEmployeeLeaveTransaction(leaveTypeConfigs,dbObject);
        	
        	System.out.println(dbObject);
        	
        	result.put("filterResult",dbObject );
	   
	        return result;
	    }
	 
	 private void addEmployeeLeaveTransaction(List<EmployeeFiscalYearLeaveDetails> leaveTypeConfigs,ArrayList<Map<String, Object>> matchedRanges){
		 
		 int size = leaveTypeConfigs.size();        
	     
		 EmployeeFiscalYearLeaveDetails leaveType = null;
	     
	     for (Map<String, Object> range : matchedRanges) {
			Date FromDate = (Date) range.get("From_Date");
			Date ToDate = (Date) range.get("To_Date");
			
			for(int i=0;i<size;i++)
	        {
	        	leaveType = leaveTypeConfigs.get(i);        	
	        	
	        	
	        	boolean isFromDateValid = (FromDate.compareTo(leaveType.getFromDate()) >= 0) 
	        										&& 
	        							  (FromDate.compareTo(leaveType.getToDate()) <=0 );
	        	
	        	boolean isToDateValid = (ToDate.compareTo(leaveType.getFromDate()) >= 0) 
	        			                            &&
	        			                (ToDate.compareTo(leaveType.getToDate()) <= 0);	        	
	        	
	        	if(isFromDateValid && isToDateValid)
	        	{
	        	  range.put("fiscalYearLeaveBalance", leaveType);
	        	  break;
	        	}
	        	
	        }
			
	    	 
		}
}
	 
	 public boolean monthMatch(List<EmployeeFiscalYearLeaveDetails> leaveTypeConfigs,Date FromDate,Date ToDate) {   
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
	            //( dbFromDate.before(FormfromDate) || dbFromDate.equals(FormfromDate)//( dbToDate.after(FormtoDate) || dbToDate.equals(FormtoDate) )
	            if( ( dbFromDate.equals(FormfromDate) || dbFromDate.after(FormfromDate) ) && ( dbToDate.after(FormtoDate) || dbToDate.equals(FormtoDate) ) )
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
	 public boolean endMonthMatch(List<EmployeeFiscalYearLeaveDetails> leaveTypeConfigs,Date FromDate,Date ToDate) {   
	        int size = leaveTypeConfigs.size();        
	        boolean matched = false;      
	        for(int i=0;i<size;i++){
	           EmployeeFiscalYearLeaveDetails leaveType = leaveTypeConfigs.get(i);
	            
	            Date dbFromDate = leaveType.getFromDate();
	            		
	            Date dbToDate = leaveType.getToDate();
	            
	            Date FormfromDate = FromDate;
	            
	            Date FormtoDate =ToDate;	          
	            //( dbFromDate.before(FormfromDate) || dbFromDate.equals(FormfromDate)//( dbToDate.after(FormtoDate) || dbToDate.equals(FormtoDate) )
	            if( ( ((dbFromDate.before(FormfromDate))||(dbToDate.compareTo(FormfromDate)==0))||(dbToDate.compareTo(FormfromDate)==0 || dbToDate.compareTo(FormtoDate)==0) ) /*&& ( dbToDate.after(FormtoDate) || dbToDate.equals(FormtoDate) )*/ )
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
	    @SuppressWarnings("unchecked")
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
				return dt;
			} else {
				return null;
			}    	
	    }
	    public Long getAppliedDays(Integer eid,Integer fid,Integer levid,Date from,Date to){
	    	Session session = null;
	    	Long applieddays = 0l;
	    	try {
				session = this.sf.getCurrentSession();				
				session.beginTransaction();
				Criteria crtth = session.createCriteria(EmployeeLeaveTransaction.class)
							.add(Restrictions.eq("employee_id",eid))
							.add(Restrictions.eq("financialyearid",fid))
							.add(Restrictions.eq("leaveConfigurationId",levid))
							.add(Restrictions.eq("status", new String("pending")))
							.add(Restrictions.ge("FromDate", from))
							.add(Restrictions.le("ToDate", to))
							.setProjection(Projections.sum("totaldays"));
				applieddays = (Long)crtth.uniqueResult();				
				System.out.println(" Employee Applied dates="+applieddays);
			} catch (Exception e) {
					e.printStackTrace();					
			}
	    	
	    	return applieddays;
	    }
	    
	    public static boolean CheckDate(List<EmployeeLeaveTransaction> exist,String EmpFDate)
	    {
	        int size = exist.size();	        
	        System.out.println("Applying From Date : "+EmpFDate);     	  
	        LocalDate RequestFromDate = LocalDate.parse(EmpFDate,DateTimeFormatter.ofPattern("dd-MM-yyyy"));	
	        System.out.println("RequestFromDate : "+RequestFromDate);
	        for(int i=0;i<size;i++){
	        	EmployeeLeaveTransaction LeaveTransaction = exist.get(i);
	        	
	        	System.out.println("From Date : "+LeaveTransaction.getFromDate());
	        	
	        	System.out.println("To Date : "+LeaveTransaction.getToDate());
	        	
	        	LocalDate FromDate = LeaveTransaction.getFromDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	            
	            Period p = Period.between(FromDate, LeaveTransaction.getToDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	            
	            int diffDays = p.getDays();
	            
	            System.out.println(" diffDays "+diffDays);            
	            for( int j=0;j<=diffDays;j++){
	            	System.out.println("FromDate.plusDays(j) : "+FromDate.plusDays(j));
	            	System.err.println("FromDate.plusDays(j) : "+FromDate.plusDays(j));
	            	 System.out.println("CHECK ALREADY Applying From Date :"); 
	                     if(FromDate.plusDays(j).isEqual(RequestFromDate)){
	                         System.err.println("Condition Matched : true");
	                         return true;                                             
	                    }
	            }            
	        }        	        
	        return false;
	    }
	    
	    public static boolean checkAlreadyApplyDate(List<EmployeeLeaveTransaction> exist,String EmpFDate,Integer number)
	    {
	        int size = exist.size();	        
	        System.out.println("Applying From Date : "+EmpFDate);     	  
	        LocalDate RequestFromDate = LocalDate.parse(EmpFDate,DateTimeFormatter.ofPattern("dd-MM-yyyy"));	
	        System.out.println("RequestFromDate : "+RequestFromDate);
	        for(int i=0;i<size;i++){
	        	EmployeeLeaveTransaction LeaveTransaction = exist.get(i);
	        	
	        	System.out.println("From Date : "+LeaveTransaction.getFromDate());
	        	
	        	System.out.println("To Date : "+LeaveTransaction.getToDate());
	        	
	        	LocalDate FromDate = LeaveTransaction.getFromDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	            
	            Period p = Period.between(FromDate, LeaveTransaction.getToDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	            
	            int diffDays = p.getDays();
	            
	            System.out.println(" diffDays "+diffDays);            
	            for( int j=0;j<=diffDays;j++){
	            	System.out.println("FromDate.plusDays(j) : "+FromDate.plusDays(j));
	            	System.err.println("FromDate.plusDays(j) : "+FromDate.plusDays(j));
	            	 System.err.println("CHECK ALREADY Applying From Date :"); 
	                     if(FromDate.plusDays(j).isEqual(RequestFromDate)){
	                         System.err.println("Condition Matched : true");
	                         return true;                                             
	                    }
	            }            
	        }        	        
	        return false;
	    }
	 
	    //
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
			}/*finally{
				if(session!=null&&session.isOpen()){
					session.close();
				}
			}*/
			return financialYearId;
		}
	    
	   /**
	    * 
	    * @param StartA ==> Db Date == 9
	    * @param StartB ==> Db Date == 10
	    * @param EndA ==> Apply Date == 08
	    * @param EndB ==> Apply Date == 11
	    * @return
	    */
	    private boolean isOverlapDates(Date StartA,Date StartB,Date EndA,Date EndB)
	    {
	    	/**
	    	 * SA: 10-1-2017 to SB: 11-1-2017
	    	 * EA: 09-1-2017     EB:11-1-2017  	    	
	    	 */
	    	
	    
	    	
	    	int endAComparision = EndA.compareTo(StartA);
	    	
	    		
	    	if(endAComparision == 0)
	    	{
	    		// both are equal
	    		System.out.println("Both Are equal");
	    		return true;
	    	}
	    	else if(endAComparision>0)
	    	{
	    		// Staff applying Date is after db end Date
	    		if(EndA.compareTo(StartB) > 0)	    		
	    			return false;	    		
	    		else	    			
	    			return true;
	    	}
	    	else if (endAComparision<0)
	    	{
	    		// Staff applying Date is before db end Date
	    		System.out.println("Applying Date is less than dbstart date");	    		
	    		
	    		if(EndB.compareTo(StartA) < 0)	    		
	    			return false;	    		
	    		else	    			
	    			return true;	    		
	    		
	    	}
	    	System.err.println("Default return executed in isOverlapDates method and true");
	    	return true;
	    	
	    }
	    @SuppressWarnings("unchecked")
	    public Financialyear  getFianacialYear(Integer financialId){
	    	Financialyear financialyear = null;
	    	Session session = null;
	    	List<Financialyear> financial =null;
	    	try {
				session = this.sf.getCurrentSession();
				Query query = session.createQuery("FROM Financialyear WHERE financialyearid=:searchA");
				financial = query.setParameter("searchA", financialId).list();
				if (financial!=null && !financial.isEmpty()) {
					financialyear = financial.get(0);
				} else {
					System.err.println("Admin still not configure Financialyear");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	return financialyear;
	    }
	    
	    @SuppressWarnings("unchecked")
		 public  List<EmployeeLeaveTransaction> getAllPARData(Integer employeId){
	    	Session session = null;
			Transaction tr = null;
	    	Integer financialId = getCurrentFinancialYearId();
	    	System.out.println("Enter into after get = getCurrentFinancialYearId();=");
			List<EmployeeLeaveTransaction> employeeLeaveTransaction = null;
			List<Financialyear> financialyears =null;Integer fid = 0;
			try {
				session = this.sf.getCurrentSession();
				tr = session.beginTransaction();
				Query query = session.createQuery("From Financialyear WHERE financialyearid=:searchA");
				financialyears = query.setParameter("searchA",financialId).list();
				if (financialyears!=null&&!financialyears.isEmpty()) {
					 fid = financialyears.get(0).getFinancialyearid();
						Query query1 = session.createQuery("FROM EmployeeLeaveTransaction WHERE financialyearid=:searchA AND employee_id=:searchB");
						employeeLeaveTransaction = query1.setParameter("searchA", fid).setParameter("searchB", employeId).list();			
						tr.commit();
				} else {
					System.err.println("Admin still not configure Financial year. So EmployeeLeaveTransaction is empty ");

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
  }
	

//}
