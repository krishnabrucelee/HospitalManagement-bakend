/**
 * 
 */
package com.hospital.payroll.service;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.payroll.dao.StaffPayrollDao;
import com.hospital.payroll.serviceImpl.IStaffPayrollService;

/**
 * @author admin
 *
 */
@Service
public class StaffPayrollServiceImpl implements IStaffPayrollService {

	@Autowired
	private StaffPayrollDao payrollDao;
	
	@Override
	public JSONObject createSalaryComponents(JSONObject salaryDetails) {
		return payrollDao.createSalaryComponents(salaryDetails);
	}

	@Override
	public JSONObject createSalaryConfigurationForStaff(JSONObject salary_staff_details) {
		return payrollDao.createSalaryConfigurationForStaff(salary_staff_details);
	}

	@Override
	public JSONObject createBonusAndIncentiveForStaff(JSONObject bonus_incentive_details) {
		return payrollDao.createBonusAndIncentiveForStaff(bonus_incentive_details);
	}

	@Override
	public JSONObject addHikeForStaff(JSONObject hikeDetails) {
		return payrollDao.addHikeForStaff(hikeDetails);
	}

	@Override
	public JSONObject getAllBounsAndIncentivesForStaff(JSONObject staffDetail) {
		return payrollDao.getAllBounsAndIncentivesForStaff(staffDetail);
	}

	@Override
	public JSONObject getAllHikesForStaff(JSONObject staffDetail) {
		return payrollDao.getAllHikesForStaff(staffDetail);
	}

	@Override
	public JSONObject getPaySlipForMonth(JSONObject staffDetail) {
		return payrollDao.getPaySlipForMonth(staffDetail);
	}

	@Override
	public JSONObject generatePaySlip(JSONObject staffDetails) {
		return payrollDao.generatePaySlip(staffDetails);
	}

	@Override
	public JSONObject generatePayrollForMonth(JSONObject payrollMonthDetails) {
		return payrollDao.generatePayrollForMonth(payrollMonthDetails);
	}

}
