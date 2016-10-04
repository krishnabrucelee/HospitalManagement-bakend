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

import com.hospital.service.InPatientService;

/**
 * @author Krishna
 *
 */
@Controller
public class InPatientController {

	/**
	 * InPatient Service.
	 */
	@Autowired
	private InPatientService inPatientService;

	/**
	 * Create InPatient.
	 * 
	 * @param inPatient
	 * @return inPatient
	 */
	@RequestMapping(value = "/addInPatient", method = RequestMethod.POST)
	public @ResponseBody JSONObject addInPatient(@RequestBody JSONObject inPatient) {
		return inPatientService.addInPatient(inPatient);
	}

	/**
	 * List inPatient.
	 * 
	 * @param inPatient
	 * @return inPatient
	 */
	@RequestMapping(value = "/listInPatient")
	public @ResponseBody JSONObject listInPatient() {
		return inPatientService.listInPatient();
	}
	
	/**
	 * Update InPatient.
	 * 
	 * @param inPatient
	 * @return inPatient
	 */
	@RequestMapping(value = "/updateInPatient")
	public @ResponseBody JSONObject updateInPatient(@RequestBody JSONObject inPatient) {
		return inPatientService.updateInPatient(inPatient);
	}

	/**
	 * Delete inPatient.
	 * 
	 * @param inPatient
	 * @return inPatient
	 */
	@RequestMapping(value = "/deleteInPatient", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteInPatient(@RequestBody JSONObject inPatientId) {
		return inPatientService.deleteInPatient(inPatientId);
	}
}
