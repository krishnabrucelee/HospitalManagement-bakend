/**
 * 
 */
package com.hospital.payroll.dao;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Staff;
import com.hospital.payroll.model.EarnedSalaryConfigDetails;
import com.hospital.payroll.model.Incentive_BonusManagement;
import com.hospital.payroll.model.PayrollEntries;
import com.hospital.payroll.model.SalaryComponents;
import com.hospital.payroll.model.StaffHikeDetails;
import com.hospital.payroll.model.StaffSalaryConfigDetails;
import com.hospital.payroll.model.StaffSalaryConfigs;
import com.hospital.payroll.model.StaffSalaryPayoutDetails;
import com.monitorjbl.json.JsonView;
import com.monitorjbl.json.Match;

/**
 * @author admin
 *
 */
@Repository
public class StaffPayrollDao {
	
	@Autowired
	private SessionFactory sessionFactory;
		
	private HashMap<String,Object> payRollProcessDetails = new HashMap<String, Object>();
	
	@Autowired
	@Qualifier("jsonViewObjectMapper")
	private ObjectMapper jsonViewObjectMapper;
	
	public StaffPayrollDao(){
		// isLockAvailable
		payRollProcessDetails.put("isLockAvailable", true);
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject createSalaryComponents(JSONObject salaryDetails) {
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		try {
			
			SalaryComponents components = jsonViewObjectMapper.convertValue(salaryDetails, SalaryComponents.class);			
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();			
			session.save(components);			
			session.getTransaction().commit();
			
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("error",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session!= null && session.isOpen())
				session.close();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getAllSalaryComponents() {
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		List<SalaryComponents> listSalaryConfig=null;	
		try {						
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();			
		    listSalaryConfig=session.createQuery("FROM SalaryComponents").list();	
			session.getTransaction().commit();
			if (listSalaryConfig!=null &&!listSalaryConfig.isEmpty()) {
			    result.put("SalaryConfig", listSalaryConfig);
			} else {
				result.put("status",false);
				result.put("reason","Admin still not configure values");
			}
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("error",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session!= null && session.isOpen())
				session.close();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject createSalaryConfigurationForStaff(JSONObject salary_staff_details) {
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		try {
			
			// Enable Payroll Process lock			
			boolean payRollProcessingLock = (boolean) payRollProcessDetails.get("isLockAvailable");	
			
			if(!payRollProcessingLock)
			{
				result.put("reason","you cann't run payroll process now because "+payRollProcessDetails.get("processname")+" process is running. Please try after some time");
				result.put("status",false);
				return result;
			}
			
			// Accquire payrollProcessLock lock
			payRollProcessDetails.put("isLockAvailable",false);
			payRollProcessDetails.put("processname","create salary configuration");	
			
			//1. Load Staff Details			
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			
			Staff staffdetails = session.get(Staff.class,(int)salary_staff_details.get("staffId"));
			
			if(staffdetails == null)
			{
				result.put("status",false);
				result.put("message","please check staffId is not found");
				return result;
			}
			
			StaffSalaryConfigs staffSalaryConfigs = jsonViewObjectMapper.convertValue(salary_staff_details, StaffSalaryConfigs.class);
			
			// For The First Time we set isCurrent flag is true
			staffSalaryConfigs.setCurrent(true);			
			staffSalaryConfigs.setStaff(staffdetails);
			
						
			session.save(staffSalaryConfigs);
			
			session.getTransaction().commit();
			
			// Accquire payrollProcessLock lock
			payRollProcessDetails.put("isLockAvailable",true);
			payRollProcessDetails.remove("processname");	
						
			
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("error",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session!= null && session.isOpen())
				session.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject createBonusAndIncentiveForStaff(JSONObject bonus_incentive_details) {
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		try {
			
			// Enable Payroll Process lock			
			boolean payRollProcessingLock = (boolean) payRollProcessDetails.get("isLockAvailable");	
			
			if(!payRollProcessingLock)
			{
				result.put("reason","you cann't run payroll process now because "+payRollProcessDetails.get("processname")+" process is running. Please try after some time");
				result.put("status",false);
				return result;
			}
			
			// Accquire payrollProcessLock lock
			payRollProcessDetails.put("isLockAvailable",false);
			payRollProcessDetails.put("processname","createBonusAndIncentiveForStaff");	
			
			//1. Load Staff Details			
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			
			Staff staffdetails = session.get(Staff.class,(int)bonus_incentive_details.get("staffId"));
			
			if(staffdetails == null)
			{
				result.put("status",false);
				result.put("message","please check staffId is not found");
				return result;
			}
			
			Incentive_BonusManagement incentive = jsonViewObjectMapper.convertValue(bonus_incentive_details, Incentive_BonusManagement.class);
			
			// For The First Time we set isCurrent flag is true
			incentive.setPayed(false);
			incentive.setStaff(staffdetails);
						
			session.save(incentive);
			
			session.getTransaction().commit();
			
			// Release payrollProcessLock lock
			payRollProcessDetails.put("isLockAvailable",true);
			payRollProcessDetails.remove("processname");	

		} catch (Exception e) {
			result.put("status",false);
			result.put("error",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session!= null && session.isOpen())
				session.close();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject addHikeForStaff(JSONObject hikeDetails) {
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		try {
			
			// 1. Load Existing Salary Configuration
			// 2. Prepare new Salary Components based on hikeDetails (Percentage and amount)
			// 3. Save new Salary Configuration and change isCurrent falg to false in old salary configuration			
			
			// Acquire Lock
			boolean payRollProcessingLock = (boolean) payRollProcessDetails.get("isLockAvailable");			
			if(!payRollProcessingLock)
			{
				result.put("message","isLockAvailable lock required for this process");
				result.put("status",false);
				return result;
			}			
			// Accquire payrollProcessLock lock
			payRollProcessDetails.put("isLockAvailable",false);
			payRollProcessDetails.put("processname","add Hike");	
			
			
			session = sessionFactory.getCurrentSession();			
			session.beginTransaction();
			
			// 1. Load Staff Salary Config Details			
			Staff staff = new Staff();
			staff.setStaffId((int)hikeDetails.get("staffId"));
			
			Criteria cri = session.createCriteria(StaffSalaryConfigs.class);
			cri.add(Restrictions.and(
					  Restrictions.eq("staff",staff),
					  Restrictions.eq("isCurrent", true)
					));
			List<StaffSalaryConfigs> salaryConfig = cri.list();
			if(salaryConfig.isEmpty())
			{
				result.put("status",false);
				result.put("message","default salary config not found. Please check staff id or insert salary config details");
				return result;
			}
			int size = salaryConfig.size();
			if(size > 1)
			{
				result.put("status",false);
				result.put("message","Something goes wrong. For a staff only one salary configuration need. But founded is "+size);
				return result;
			}
			
			StaffSalaryConfigs configs = salaryConfig.get(0);			
			configs.setCurrent(false);
			
			// For Lazy Loading
			ArrayList<LinkedHashMap<String,Object>> currentSalaryConfigDetails = jsonViewObjectMapper.convertValue(configs.getSalaryConfigDetails(), new TypeReference<ArrayList<LinkedHashMap<String,Object>>>() {});		
			
			session.update(configs);		
						
			// 2. Prepare new Salary Components based on hikeDetails			
			currentSalaryConfigDetails.stream().forEach((v)->{
				int percentage = (int) v.get("percentage");
				int amount = ((int)hikeDetails.get("amount") * percentage )/ 100 ;			
				v.put("amount",amount);		
				v.remove("staff_salary_config_details_id");
			});
			
			// 3.Save new Salary Configuration & Hike Details		
			
			Staff newStaffDetails = session.get(Staff.class,(int)hikeDetails.get("staffId"));
			
			List<StaffSalaryConfigDetails> newSalaryConfigDetails = jsonViewObjectMapper.convertValue(currentSalaryConfigDetails, new TypeReference<ArrayList<StaffSalaryConfigDetails>>() {});			
			
			StaffSalaryConfigs staffSalaryConfig = new StaffSalaryConfigs();
			staffSalaryConfig.setCurrent(true);
			staffSalaryConfig.setStaff(newStaffDetails);
			staffSalaryConfig.setPerMonthAmount((int) hikeDetails.get("amount"));
			staffSalaryConfig.setSalaryConfigDetails(newSalaryConfigDetails);
			
			StaffHikeDetails staffHikeDetails = jsonViewObjectMapper.convertValue(hikeDetails, StaffHikeDetails.class);			
			staffHikeDetails.setCreationDate(new Date());
			staffHikeDetails.setStaff(newStaffDetails);
			
			session.save(staffSalaryConfig);
			session.save(staffHikeDetails);
			
			session.getTransaction().commit();
			
			// Release Lock
			// Accquire payrollProcessLock lock
			payRollProcessDetails.put("isLockAvailable",true);
			payRollProcessDetails.remove("processname");	
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("error",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session!= null && session.isOpen())
				session.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getAllBounsAndIncentivesForStaff(JSONObject staffDetail) {
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		try {
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("error",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session!= null && session.isOpen())
				session.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getAllHikesForStaff(JSONObject staffDetail) {
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		try {
			
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("error",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session!= null && session.isOpen())
				session.close();
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getPaySlipForMonth(JSONObject staffDetail) {
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		try {
			
			// 1. Load Staff Salary Config Details
			// 2. Load Actual Earned Salary Config Details
			
			// Enable Payroll Process lock			
			boolean payRollProcessingLock = (boolean) payRollProcessDetails.get("isLockAvailable");	
			
			if(!payRollProcessingLock)
			{
				result.put("reason","you cann't run payroll process now because "+payRollProcessDetails.get("processname")+" process is running. Please try after some time");
				result.put("status",false);
				return result;
			}
			
			// Accquire payrollProcessLock lock
			payRollProcessDetails.put("isLockAvailable",false);
			payRollProcessDetails.put("processname","payroll");	
			
			session = sessionFactory.getCurrentSession();			
			session.beginTransaction();
			
			// 1. Load Staff Salary Config Details			
			Staff staff = new Staff();
			staff.setStaffId((int)staffDetail.get("staffId"));
			
			Criteria cri = session.createCriteria(StaffSalaryConfigs.class);
			cri.add(Restrictions.and(
					  Restrictions.eq("staff",staff),
					  Restrictions.eq("isCurrent", true)
					));
			List<StaffSalaryConfigs> salaryConfig = cri.list();
			if(salaryConfig.isEmpty())
			{
				result.put("status",false);
				result.put("message","default salary config not found. Please check staff id or insert salary config details");
				return result;
			}
			int size = salaryConfig.size();
			if(size > 1)
			{
				result.put("status",false);
				result.put("message","Something goes wrong. For a staff only one salary configuration need. But founded is "+size);
				return result;
			}	
			
			LocalDate date = LocalDate.parse(staffDetail.get("salaryDate").toString(),DateTimeFormatter.ofPattern("dd-MM-yyyy"));			
			date = date.with(TemporalAdjusters.firstDayOfMonth());
			
			Date firsDay_date_Obj = Date.from(date.atStartOfDay(ZoneId.of("UTC")).toInstant());
					
			cri = session.createCriteria(StaffSalaryPayoutDetails.class); 
			cri.add(Restrictions.and(
					  Restrictions.eq("staff",staff),
					  Restrictions.eq("fromDate", firsDay_date_Obj)
					));		
			
			StaffSalaryPayoutDetails staffSalaryPayoutDetails = (StaffSalaryPayoutDetails) cri.uniqueResult();
			
			if(staffSalaryPayoutDetails == null)
			{
				result.put("status",false);
				result.put("message","PaySlip not generated for this "+staffDetail.get("salaryDate").toString()+" month");
				return result;
			}
			
			String json = jsonViewObjectMapper.writeValueAsString(JsonView.with(salaryConfig.get(0))
					.onClass(StaffSalaryConfigs.class, Match.match().exclude("staff"))
					);
			String earnedJson = jsonViewObjectMapper.writeValueAsString(JsonView.with(staffSalaryPayoutDetails)
					.onClass(StaffSalaryPayoutDetails.class, Match.match().include("staff"))
					);
			result.put("defaultSalaryConfig",jsonViewObjectMapper.readValue(json,new TypeReference<HashMap<String,Object>>(){}));
			result.put("earnedSalaryConfig",jsonViewObjectMapper.readValue(earnedJson,new TypeReference<HashMap<String,Object>>(){}));
			
			
			// release payrollProcessLock lock
			payRollProcessDetails.put("isLockAvailable",true);
			payRollProcessDetails.remove("processname");	
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("error",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session!= null && session.isOpen())
				session.close();
		}
		return result;
	}
	
	private ArrayList<LinkedHashMap<String,Object>> assignSalaryAmountToComponents(ArrayList<LinkedHashMap<String,Object>> listOfComponents,int salaryAmount){
	
		
		listOfComponents.stream().forEach(config->{			
			int percentage = (int) config.get("percentage");
			int amount = (salaryAmount * percentage)/100;
			config.put("amount",amount);						
		});
		
		return listOfComponents;
		
	}
	
	/**
	 * 
	 * @param staffDetail (Date, StaffId)
	 * @return Number of days for LOP
	 */
	@SuppressWarnings("unchecked")
	private JSONObject staffAttendanceForMonth(JSONObject staffDetail){
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		try {
			
			// Perform Calculation on Leave Module
			
			// TODO remove static
			Random random = new Random();
			result.put("numberofdays",random.nextInt(5)+1);
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("error",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session!= null && session.isOpen())
				session.close();
		}
		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public JSONObject generatePaySlip(JSONObject staffDetails) {
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		try {
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("error",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session!= null && session.isOpen())
				session.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject generatePayrollForMonth(JSONObject payrollMonthDetails) {
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		
	    
		try {
			
			// 1. Check if payroll already existed for this month and year
			// 2. Acquire Payroll Process lock						
			// 3. Load All Staff StaffSalaryConfigs details
			// 4. Calculate Total Month Working Days
			// 5. Process Salary For Every Staff 
			// 6. Process payroll for month and year
			// 7. Save PayrollEntries
			// 8. Release Payroll Process lock	
			
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
            String month = payrollMonthDetails.get("month").toString();
			
			LocalDate payrollLocalDate = LocalDate.parse(month, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			
			Date payrollDate = Date.from(payrollLocalDate.atStartOfDay(ZoneId.of("UTC")).toInstant());
			// 1. Check if payroll already existed for this month and year
			Criteria cri = session.createCriteria(PayrollEntries.class);
			cri.add(Restrictions.eq("fromDate",payrollDate));
			
			PayrollEntries entries = (PayrollEntries) cri.uniqueResult();
			
			System.out.println("Entries : "+entries);
			
			if(entries != null)
			{
				result.put("reason","cann't generate payroll for "+month+" this month. Beacuse already generated");
				result.put("status",false);
				return result;
			}
			
			// 2. Enable Payroll Process lock
			
			boolean payRollProcessingLock = (boolean) payRollProcessDetails.get("isLockAvailable");	
			
			if(!payRollProcessingLock)
			{
				result.put("reason","you cann't run payroll process now because "+payRollProcessDetails.get("processname")+" process is running. Please try after some time");
				result.put("status",false);
				return result;
			}
			
			// Accquire payrollProcessLock lock
			payRollProcessDetails.put("isLockAvailable",false);
			payRollProcessDetails.put("processname","payroll");		
			 
			// 3. Load All Staff StaffSalaryConfigs details & 4. Load All Staff 
			
			List<StaffSalaryConfigs> staffSalaryConfigs = session.createQuery("From StaffSalaryConfigs").list();
			
			List<Staff> staffList = session.createQuery("From Staff").list();
			
			List<Integer> salaryStaffList = staffSalaryConfigs.stream().map((v)->v.getStaff().getStaffId()).collect(Collectors.toList());
			
			List<Integer> allStaffList = staffList.stream().map(v->v.getStaffId()).collect(Collectors.toList());
			
			allStaffList.removeAll(salaryStaffList);
			
			
			
			List<Staff> staffs= staffSalaryConfigs.stream().map(v->v.getStaff()).collect(Collectors.toList());
			
			cri = session.createCriteria(StaffSalaryConfigs.class);
			cri.add(Restrictions.and(
					  Restrictions.in("staff",staffs),
					  Restrictions.eq("isCurrent", true)
					));
			List<StaffSalaryConfigs> salaryConfig = cri.list();
			
			
			// 4. Calculate Total Month Working Days			
			LocalDate date = LocalDate.parse(payrollMonthDetails.get("month").toString(),DateTimeFormatter.ofPattern("dd-MM-yyyy"));			
			date = date.with(TemporalAdjusters.firstDayOfMonth());
			LocalDate lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());			
			Period period = Period.between(date, lastDayOfMonth);
			
			Date firsDay_date_Obj = Date.from(date.atStartOfDay(ZoneId.of("UTC")).toInstant());
			Date lastDay_date_Obj = Date.from(lastDayOfMonth.atStartOfDay(ZoneId.of("UTC")).toInstant());
			
			
			// Last Day exclusive so get days plus one
			// This variable holds total working days of a month
			int totalWorkingDays = period.getDays()+1;
						
			
			int totalStaffList = salaryConfig.size();
			
			// 5. Process Salary For Every Staff		
			JSONObject saffDetail = null;
			int batchSize = 1;
			for(int i=0;i<totalStaffList;i++)
			{
				StaffSalaryConfigs configs = salaryConfig.get(i);
				
				saffDetail = new JSONObject();
				
				// 1. Get Leave Details			
				JSONObject attendance = staffAttendanceForMonth(saffDetail);
				// This variable holds totalLeaveDays taken by staff
				int LOPDays = (int) attendance.get("numberofdays");				
				int actualWorkingDays = totalWorkingDays - LOPDays;
				
				
				// 2. Calculate Salary For a month
				int perDaySalaryAmount = (int) configs.getPerMonthAmount()/totalWorkingDays;				
				int earnedSalaryAmount = actualWorkingDays * perDaySalaryAmount;
				
				// 3. Get Incentive and Bonus Details if found add to salary and update ispayed flag to true				
				ArrayList<LinkedHashMap<String,Object>> salaryConfigDetails = jsonViewObjectMapper.convertValue(configs.getSalaryConfigDetails(), new TypeReference<ArrayList<LinkedHashMap<String,Object>>>() {});		
				ArrayList<LinkedHashMap<String,Object>> assignedSalaryConfigDetails  = assignSalaryAmountToComponents(salaryConfigDetails,earnedSalaryAmount);
				
				
				cri = session.createCriteria(Incentive_BonusManagement.class);
				cri.add(
						Restrictions.and(
									Restrictions.between("applicableDate",
									firsDay_date_Obj, 
									lastDay_date_Obj
									),
									Restrictions.eq("isPayed", false)
									)
				);		
				
				List<Incentive_BonusManagement> incentives = cri.list();
				
				int totalBonus =0;
				
				if(! incentives.isEmpty())
				{
					totalBonus = incentives.stream()
										.peek((v)->{
						v.setPayed(true);					
					}).mapToInt(in-> Integer.valueOf((int)in.getAmount())).sum();				
					
					earnedSalaryAmount = earnedSalaryAmount + (int)totalBonus;			
					
				}
				if(totalBonus != 0)
				{
					// Add Incentive and Bonus to Salary Component
					LinkedHashMap<String,Object> incentive_bonuse = new LinkedHashMap<String, Object>();
					incentive_bonuse.put("name", "bonus or incentive");
					incentive_bonuse.put("amount", (int)totalBonus);
					assignedSalaryConfigDetails.add(incentive_bonuse);
				}			
				List<EarnedSalaryConfigDetails> earnedSalaryConfigDetails = jsonViewObjectMapper.convertValue(assignedSalaryConfigDetails, new TypeReference<ArrayList<EarnedSalaryConfigDetails>>() {});
				
				
				// 4. Save Employee Payout Details
				StaffSalaryPayoutDetails salaryPayoutDetails = new StaffSalaryPayoutDetails();
				salaryPayoutDetails.setFromDate(firsDay_date_Obj);
				salaryPayoutDetails.setToDate(lastDay_date_Obj);
				salaryPayoutDetails.setPayed(true);
				salaryPayoutDetails.setStaff(configs.getStaff());
				salaryPayoutDetails.setTotalAmout(earnedSalaryAmount);
				salaryPayoutDetails.setConfigDetails(earnedSalaryConfigDetails);				
				session.save(salaryPayoutDetails);
				
				if(batchSize == 50)
    			{
    				// Update batches for Every 50 object
    				session.flush();
    				session.clear();
    			}
    			else
    				batchSize++;
				
			}
			
			// 7. Save PayrollEntries
			
			PayrollEntries payrollEntries = new PayrollEntries();
			payrollEntries.setFromDate(firsDay_date_Obj);
			payrollEntries.setToDate(lastDay_date_Obj);
			payrollEntries.setTotalDays(totalWorkingDays);
			payrollEntries.setCreationDate(new Date());			
			session.save(payrollEntries);		
			
			
			result.put("message","Successfully payroll created for this "+month);
			
			session.getTransaction().commit();
			
			// 8. Release Payroll Process lock	
			payRollProcessDetails.put("isLockAvailable",true);
			payRollProcessDetails.remove("processname");		
						
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("error",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session!= null && session.isOpen())
				session.close();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject listSalaryPaySlipForMonth() {
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;		
		try {
			
			// 1. Load Staff Salary Config Details
						// 2. Load Actual Earned Salary Config Details
						
						// Enable Payroll Process lock			
						/*boolean payRollProcessingLock = (boolean) payRollProcessDetails.get("isLockAvailable");	
						
						if(!payRollProcessingLock)
						{
							result.put("reason","you cann't run payroll process now because "+payRollProcessDetails.get("processname")+" process is running. Please try after some time");
							result.put("status",false);
							return result;
						}
						
						// Accquire payrollProcessLock lock
						payRollProcessDetails.put("isLockAvailable",false);
						payRollProcessDetails.put("processname","payroll");	*/
						
						session = sessionFactory.getCurrentSession();			
						session.beginTransaction();
						
						
						Criteria  cri = session.createCriteria(StaffSalaryPayoutDetails.class); 
						List<StaffSalaryPayoutDetails> staffSalaryPayoutDetails = (List<StaffSalaryPayoutDetails>) cri.list();
						
						if(staffSalaryPayoutDetails == null)
						{
							result.put("status",false);//"+staffDetail.get("salaryDate").toString()+"
							result.put("message","PaySlip not generated for this  month");
							return result;
						}
						
						
						String earnedJson = jsonViewObjectMapper.writeValueAsString(JsonView.with(staffSalaryPayoutDetails)
								.onClass(StaffSalaryPayoutDetails.class, Match.match().exclude("staff"))
								);
						
						result.put("earnedSalaryConfigAll",jsonViewObjectMapper.readValue(earnedJson,new TypeReference<HashMap<String,Object>>(){}));
						
						
						// release payrollProcessLock lock
						payRollProcessDetails.put("isLockAvailable",true);
						payRollProcessDetails.remove("processname");	
						
						
		} catch (Exception e) {
			result.put("status",false);
			result.put("error",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session!= null && session.isOpen())
				session.close();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject listAllSalaryPaySlipForMonth() {
		JSONObject result = new JSONObject();
		Session  session =null;
		List<StaffSalaryPayoutDetails> details = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM StaffSalaryPayoutDetails");
			details = query.list();
			session.getTransaction().commit();
			if(details == null){
				result.put("status",false);//"+staffDetail.get("salaryDate").toString()+"
				result.put("message","PaySlip not generated for this  month");
				return result;
			}
			String earnedJson = jsonViewObjectMapper.writeValueAsString(JsonView.with(details)
					.onClass(StaffSalaryPayoutDetails.class, Match.match().exclude("staff"))
					);
			//ArrayList<User> convertedValue = objectMapper.readValue(json,new TypeReference<ArrayList<User>>() {});
			result.put("StaffSalaryList", earnedJson);
		} catch (Exception e) {
			result.put("status",false);
			result.put("error",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session!= null && session.isOpen())
				session.close();
		}
		return result;
	}
	
	/*@SuppressWarnings("unchecked")
	public JSONObject getPaySlipForMonth(JSONObject staffDetail) {
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		try {
			
			// 1. Load Staff Salary Config Details
			// 2. Calculate Total Month Working Days
			// 3. Get Leave Details (like LOP) from Leave Database
			// 4. Calculate Salary according to actual staff working days which is coming from finger print machine report			
			// 5. To check is there any bonus and incentive exists for current month
			
			
			session = sessionFactory.getCurrentSession();			
			session.beginTransaction();
			
			// 1. Load Staff Salary Config Details			
			Staff staff = new Staff();
			staff.setStaffId((int)staffDetail.get("staffId"));
			
			Criteria cri = session.createCriteria(StaffSalaryConfigs.class);
			cri.add(Restrictions.and(
					  Restrictions.eq("staff",staff),
					  Restrictions.eq("isCurrent", true)
					));
			List<StaffSalaryConfigs> salaryConfig = cri.list();
			if(salaryConfig.isEmpty())
			{
				result.put("status",false);
				result.put("message","default salary config not found. Please check staff id or insert salary config details");
				return result;
			}
			int size = salaryConfig.size();
			if(size > 1)
			{
				result.put("status",false);
				result.put("message","Something goes wrong. For a staff only one salary configuration need. But founded is "+size);
				return result;
			}
			
			
			// 2. Calculate Total Month Working Days			
			LocalDate date = LocalDate.parse(staffDetail.get("salaryDate").toString(),DateTimeFormatter.ofPattern("dd-MM-yyyy"));			
			date = date.with(TemporalAdjusters.firstDayOfMonth());
			LocalDate lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());			
			Period period = Period.between(date, lastDayOfMonth);
			
			// Last Day exclusive so get days plus one
			// This variable holds total working days of a month
			int totalWorkingDays = period.getDays()+1;
						
			
			// 3. Get Leave Details			
			JSONObject attendance = staffAttendanceForMonth(staffDetail);
			
			if(!(boolean)attendance.get("status"))
			{
				System.err.println("Error happened while calulation leave modules");
				return attendance;
			}
			
			// This variable holds totalLeaveDays taken by staff
			int LOPDays = (int) attendance.get("numberofdays");
			
			int actualWorkingDays = totalWorkingDays - LOPDays;
			
			
			// 4. Calculate Salary For a month
			
			StaffSalaryConfigs staffSalaryConfigDetails = salaryConfig.get(0);
			
			int perDaySalaryAmount = (int) staffSalaryConfigDetails.getPerMonthAmount()/totalWorkingDays;
			
			int earnedSalaryAmount = actualWorkingDays * perDaySalaryAmount;
			
			// 5. Get Incentive and Bonus Details if found add to salary and update ispayed flag to true
			
			ArrayList<LinkedHashMap<String,Object>> salaryConfigDetails = jsonViewObjectMapper.convertValue(staffSalaryConfigDetails.getSalaryConfigDetails(), new TypeReference<ArrayList<LinkedHashMap<String,Object>>>() {});		
			ArrayList<LinkedHashMap<String,Object>> assignedSalaryConfigDetails  = assignSalaryAmountToComponents(salaryConfigDetails,earnedSalaryAmount);
			
			
			Date firsDay_date_Obj = Date.from(date.atStartOfDay(ZoneId.of("UTC")).toInstant());
			Date lastDay_date_Obj = Date.from(lastDayOfMonth.atStartOfDay(ZoneId.of("UTC")).toInstant());
			
			cri = session.createCriteria(Incentive_BonusManagement.class);
			cri.add(
					Restrictions.and(
								Restrictions.between("applicableDate",
								firsDay_date_Obj, 
								lastDay_date_Obj
								),
								Restrictions.eq("isPayed", false)
								)
			);		
			
			List<Incentive_BonusManagement> incentives = cri.list();
			
			int totalBonus =0;
			
			if(! incentives.isEmpty())
			{
				totalBonus = incentives.stream()
									.peek((v)->{
					v.setPayed(true);					
				}).mapToInt(in-> Integer.valueOf((int)in.getAmount())).sum();				
				
				earnedSalaryAmount = earnedSalaryAmount + (int)totalBonus;			
				
			}
			if(totalBonus != 0)
			{
				// Add Incentive and Bonus to Salary Component
				LinkedHashMap<String,Object> incentive_bonuse = new LinkedHashMap<String, Object>();
				incentive_bonuse.put("name", "bonus or incentive");
				incentive_bonuse.put("amount", (int)totalBonus);
				assignedSalaryConfigDetails.add(incentive_bonuse);
			}			
			
			List<EarnedSalaryConfigDetails> earnedSalaryConfigDetails = jsonViewObjectMapper.convertValue(assignedSalaryConfigDetails, new TypeReference<ArrayList<EarnedSalaryConfigDetails>>() {});
			
			
			// Save Employee Payout Details
			StaffSalaryPayoutDetails salaryPayoutDetails = new StaffSalaryPayoutDetails();
			salaryPayoutDetails.setFromDate(firsDay_date_Obj);
			salaryPayoutDetails.setToDate(lastDay_date_Obj);
			salaryPayoutDetails.setPayed(true);
			salaryPayoutDetails.setStaff(staffSalaryConfigDetails.getStaff());
			salaryPayoutDetails.setTotalAmout(earnedSalaryAmount);
			salaryPayoutDetails.setConfigDetails(earnedSalaryConfigDetails);
			
			session.save(salaryPayoutDetails);
			
			session.getTransaction().commit();
			
			LinkedHashMap<String,Object> defaultDetails = new LinkedHashMap<String, Object>();
			defaultDetails.put("workingDays", totalWorkingDays);	
			defaultDetails.put("salaryAmount",staffSalaryConfigDetails.getPerMonthAmount());
			defaultDetails.put("salaryComponents", staffSalaryConfigDetails.getSalaryConfigDetails());
			
			
			LinkedHashMap<String,Object> earnedDetails = new LinkedHashMap<String, Object>();		
			earnedDetails.put("workingDays", actualWorkingDays);
			earnedDetails.put("salaryAmount",earnedSalaryAmount);
			earnedDetails.put("salaryComponents",assignedSalaryConfigDetails);
			
			result.put("defaultDetails",defaultDetails);
			result.put("earnedDetails",earnedDetails);
					
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("error",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session!= null && session.isOpen())
				session.close();
		}
		return result;
	}*/
}
