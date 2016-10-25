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

import com.hospital.service.LabTechnicianService;

/**
 * @author Krishna
 *
 */
@Controller
public class LabTechnicianController {

	/**
	 * LabTechnician Service.
	 */
	@Autowired
	private LabTechnicianService labTechnicianService;

	/**
	 * Create LabTechnician.
	 * 
	 * @param labTechnician
	 * @return labTechnician
	 */
	@RequestMapping(value = "/addLabTechnician", method = RequestMethod.POST)
	public @ResponseBody JSONObject addLabTechnician(@RequestBody JSONObject labTechnician) {
		return labTechnicianService.addLabTechnician(labTechnician);
	}

	/**
	 * List labTechnician.
	 * 
	 * @param labTechnician
	 * @return labTechnician
	 */
	@RequestMapping(value = "/listLabTechnician")
	public @ResponseBody JSONObject listLabTechnician() {
		return labTechnicianService.listLabTechnician();
	}
	
	/**
	 * Update LabTechnician.
	 * 
	 * @param labTechnician
	 * @return labTechnician
	 */
	@RequestMapping(value = "/updateLabTechnician")
	public @ResponseBody JSONObject updateLabTechnician(@RequestBody JSONObject labTechnician) {
		return labTechnicianService.updateLabTechnician(labTechnician);
	}

	/**
	 * Delete labTechnician.
	 * 
	 * @param labTechnician
	 * @return labTechnician
	 */
	@RequestMapping(value = "/deleteLabTechnician", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteLabTechnician(@RequestBody JSONObject labTechnicianId) {
		return labTechnicianService.deleteLabTechnician(labTechnicianId);
	}
}
