/**
 * 
 */
package com.hospital.payroll.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.payroll.serviceImpl.IStaffPayrollService;

/**
 * @author admin
 *
 */
@Controller
public class StaffPayrollController {

	@Autowired
	private IStaffPayrollService staffPayrollService;
	
	@RequestMapping(value="/createSalaryComponents",method=RequestMethod.POST)
	public @ResponseBody JSONObject createSalaryComponents(@RequestBody JSONObject salaryDetails) {
		return staffPayrollService.createSalaryComponents(salaryDetails);
	}

	@RequestMapping(value="/createSalaryConfigurationForStaff",method=RequestMethod.POST)
	public @ResponseBody JSONObject createSalaryConfigurationForStaff(@RequestBody JSONObject salary_staff_details) {
		return staffPayrollService.createSalaryConfigurationForStaff(salary_staff_details);
	}

	@RequestMapping(value="/createBonusAndIncentiveForStaff",method=RequestMethod.POST)
	public @ResponseBody JSONObject createBonusAndIncentiveForStaff(@RequestBody JSONObject bonus_incentive_details) {
		return staffPayrollService.createBonusAndIncentiveForStaff(bonus_incentive_details);
	}
	
	@RequestMapping(value="/addHikeForStaff",method=RequestMethod.POST)
	public @ResponseBody JSONObject addHikeForStaff(@RequestBody JSONObject hikeDetails) {
		return staffPayrollService.addHikeForStaff(hikeDetails);
	}

	@RequestMapping(value="/getAllBounsAndIncentivesForStaff",method=RequestMethod.POST)
	public @ResponseBody JSONObject getAllBounsAndIncentivesForStaff(@RequestBody JSONObject staffDetail) {
		return staffPayrollService.getAllBounsAndIncentivesForStaff(staffDetail);
	}

	@RequestMapping(value="/getAllHikesForStaff",method=RequestMethod.POST)
	public @ResponseBody JSONObject getAllHikesForStaff(@RequestBody JSONObject staffDetail) {
		return staffPayrollService.getAllHikesForStaff(staffDetail);
	}
	
	@RequestMapping(value="/getPaySlipForMonth",method=RequestMethod.POST)
	public @ResponseBody JSONObject getPaySlipForMonth(@RequestBody JSONObject staffDetail) {
		return staffPayrollService.getPaySlipForMonth(staffDetail);
	}

	@RequestMapping(value="/generatePaySlip",method=RequestMethod.POST)
	public @ResponseBody JSONObject generatePaySlip(@RequestBody JSONObject staffDetails) {
		return staffPayrollService.generatePaySlip(staffDetails);
	}

	@RequestMapping(value="/generatePayrollForMonth",method=RequestMethod.POST)
	public @ResponseBody JSONObject generatePayrollForMonth(@RequestBody JSONObject payrollMonthDetails) {
		return staffPayrollService.generatePayrollForMonth(payrollMonthDetails);
	}
}
