/**
 * 
 */
package com.hospital.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.service.StaffService;

/**
 * @author Krishna
 *
 */
@Controller
public class StaffController {

	/**
	 * Staff Service.
	 */
	@Autowired
	private StaffService staffService;

	/**
	 * Create Staff.
	 * 
	 * @param staff
	 * @return staff
	 */
	@RequestMapping(value = "/addStaff", method = RequestMethod.POST)
	public @ResponseBody JSONObject addStaff(@RequestBody JSONObject staff) {
		return staffService.addStaff(staff);
	}

	/**
	 * List staff.
	 * 
	 * @param staff
	 * @return staff
	 */
	@RequestMapping(value = "/listStaff")
	public @ResponseBody JSONObject listStaff() {
		return staffService.listStaff();
	}
	
	/**
	 * Update Staff.
	 * 
	 * @param staff
	 * @return staff
	 */
	@RequestMapping(value = "/updateStaff")
	public @ResponseBody JSONObject updateStaff(@RequestBody JSONObject staff) {
		return staffService.updateStaff(staff);
	}

	/**
	 * Delete staff.
	 * 
	 * @param staff
	 * @return staff
	 */
	@RequestMapping(value = "/deleteStaff", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteStaff(@RequestBody JSONObject staffId) {
		return staffService.deleteStaff(staffId);
	}
}
