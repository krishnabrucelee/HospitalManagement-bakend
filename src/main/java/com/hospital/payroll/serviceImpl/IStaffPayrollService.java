/**
 * 
 */
package com.hospital.payroll.serviceImpl;

import org.json.simple.JSONObject;

/**
 * @author admin
 *
 */
public interface IStaffPayrollService {

	JSONObject createSalaryComponents(JSONObject salaryDetails);

	JSONObject createSalaryConfigurationForStaff(JSONObject salary_staff_details);
	
	JSONObject createBonusAndIncentiveForStaff(JSONObject bonus_incentive_details);
	
	JSONObject addHikeForStaff(JSONObject hikeDetails);
	
	JSONObject getAllBounsAndIncentivesForStaff(JSONObject staffDetail);
	
	JSONObject getAllHikesForStaff(JSONObject staffDetail);
	
	JSONObject getPaySlipForMonth(JSONObject staffDetail);
	
	JSONObject generatePaySlip(JSONObject staffDetails);
	
	JSONObject generatePayrollForMonth(JSONObject payrollMonthDetails);
}
