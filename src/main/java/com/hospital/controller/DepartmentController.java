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

import com.hospital.service.DepartmentService;

/**
 * @author Krishna
 *
 */
@Controller
public class DepartmentController {

	/**
	 * Department Service.
	 */
	@Autowired
	private DepartmentService departmentService;

	/**
	 * Create Department.
	 * 
	 * @param department
	 * @return department
	 */
	@RequestMapping(value = "/addDepartment", method = RequestMethod.POST)
	public @ResponseBody JSONObject addDepartment(@RequestBody JSONObject department) {
		return departmentService.addDepartment(department);
	}

	/**
	 * List department.
	 * 
	 * @param department
	 * @return department
	 */
	@RequestMapping(value = "/listDepartment")
	public @ResponseBody JSONObject listDepartment() {
		return departmentService.listDepartment();
	}
	
	/**
	 * Update Department.
	 * 
	 * @param department
	 * @return department
	 */
	@RequestMapping(value = "/updateDepartment")
	public @ResponseBody JSONObject updateDepartment(@RequestBody JSONObject department) {
		return departmentService.updateDepartment(department);
	}

	/**
	 * Delete department.
	 * 
	 * @param department
	 * @return department
	 */
	@RequestMapping(value = "/deleteDepartment", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteDepartment(@RequestBody JSONObject departmentId) {
		return departmentService.deleteDepartment(departmentId);
	}
}
