package com.hospital.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.service.PatientService;

/**
 * Patient Controller.
 * 
 * @author Krishna
 *
 */
@Controller
public class PatientController {
	
	/**
	 * Patient Service.
	 */
	@Autowired
	private PatientService patientService;

	/**
	 * Create Patient.
	 * 
	 * @param patient
	 * @return patient
	 */
	@RequestMapping(value = "/addPatient", method = RequestMethod.POST)
	public @ResponseBody JSONObject addPatient(@RequestBody JSONObject patient) {
		return patientService.addPatient(patient);
	}

	/**
	 * List Patient.
	 * 
	 * @param patient
	 * @return patient
	 */
	@RequestMapping(value = "/listPatient")
	public @ResponseBody JSONObject listPatient() {
		return patientService.listPatient();
	}

	/**
	 * Update Patient.
	 * 
	 * @param patient
	 * @return patient
	 */
	@RequestMapping(value = "/updatePatient")
	public @ResponseBody JSONObject updatePatient(@RequestBody JSONObject patient) {
		return patientService.updatePatient(patient);
	}

	/**
	 * Delete Patient.
	 * 
	 * @param patient
	 * @return patient
	 */
	@RequestMapping(value = "/deletePatient", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject delete(@RequestBody JSONObject patientId) {
		return patientService.deletePatient(patientId);
	}
	
	@RequestMapping(value = "/getPatientDetailsById", method = RequestMethod.POST)
	public @ResponseBody JSONObject getPatientDetailsById(@RequestBody JSONObject patient) {
	
		System.out.println(patient);
		return patientService.getPatientDetailsById(patient);
	}
}
