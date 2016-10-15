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

import com.hospital.service.BioMedicalWasteDepartmentService;

/**
 * @author Krishna
 *
 */
@Controller
public class BioMedicalWasteDepartmentController {

	/**
	 * BioMedicalWasteDepartment Service.
	 */
	@Autowired
	private BioMedicalWasteDepartmentService BioMedicalWasteDepartmentService;

	/**
	 * Create BioMedicalWasteDepartment.
	 * 
	 * @param BioMedicalWasteDepartment
	 * @return BioMedicalWasteDepartment
	 */
	@RequestMapping(value = "/addBioMedicalWasteDepartment", method = RequestMethod.POST)
	public @ResponseBody JSONObject addBioMedicalWasteDepartment(@RequestBody JSONObject BioMedicalWasteDepartment) {
		return BioMedicalWasteDepartmentService.addBioMedicalWasteDepartment(BioMedicalWasteDepartment);
	}

	/**
	 * List BioMedicalWasteDepartment.
	 * 
	 * @param BioMedicalWasteDepartment
	 * @return BioMedicalWasteDepartment
	 */
	@RequestMapping(value = "/listBioMedicalWasteDepartment")
	public @ResponseBody JSONObject listBioMedicalWasteDepartment() {
		return BioMedicalWasteDepartmentService.listBioMedicalWasteDepartment();
	}
	
	/**
	 * Update BioMedicalWasteDepartment.
	 * 
	 * @param BioMedicalWasteDepartment
	 * @return BioMedicalWasteDepartment
	 */
	@RequestMapping(value = "/updateBioMedicalWasteDepartment")
	public @ResponseBody JSONObject updateBioMedicalWasteDepartment(@RequestBody JSONObject BioMedicalWasteDepartment) {
		return BioMedicalWasteDepartmentService.updateBioMedicalWasteDepartment(BioMedicalWasteDepartment);
	}

	/**
	 * Delete BioMedicalWasteDepartment.
	 * 
	 * @param BioMedicalWasteDepartment
	 * @return BioMedicalWasteDepartment
	 */
	@RequestMapping(value = "/deleteBioMedicalWasteDepartment", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteBioMedicalWasteDepartment(@RequestBody JSONObject BioMedicalWasteDepartmentId) {
		return BioMedicalWasteDepartmentService.deleteBioMedicalWasteDepartment(BioMedicalWasteDepartmentId);
	}
}
