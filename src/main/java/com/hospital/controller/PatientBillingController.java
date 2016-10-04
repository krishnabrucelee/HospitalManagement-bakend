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

import com.hospital.service.PatientBillingService;

/**
 * @author Krishna
 *
 */
@Controller
public class PatientBillingController {

	/**
	 * PatientBilling Service.
	 */
	@Autowired
	private PatientBillingService patientBillingService;

	/**
	 * Create PatientBilling.
	 * 
	 * @param patientBilling
	 * @return patientBilling
	 */
	@RequestMapping(value = "/addPatientBilling", method = RequestMethod.POST)
	public @ResponseBody JSONObject addPatientBilling(@RequestBody JSONObject patientBilling) {
		return patientBillingService.addPatientBilling(patientBilling);
	}

	/**
	 * List patientBilling.
	 * 
	 * @param patientBilling
	 * @return patientBilling
	 */
	@RequestMapping(value = "/listPatientBilling")
	public @ResponseBody JSONObject listPatientBilling() {
		return patientBillingService.listPatientBilling();
	}
	
	/**
	 * Update PatientBilling.
	 * 
	 * @param patientBilling
	 * @return patientBilling
	 */
	@RequestMapping(value = "/updatePatientBilling")
	public @ResponseBody JSONObject updatePatientBilling(@RequestBody JSONObject patientBilling) {
		return patientBillingService.updatePatientBilling(patientBilling);
	}

	/**
	 * Delete patientBilling.
	 * 
	 * @param patientBilling
	 * @return patientBilling
	 */
	@RequestMapping(value = "/deletePatientBilling", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deletePatientBilling(@RequestBody JSONObject patientBillingId) {
		return patientBillingService.deletePatientBilling(patientBillingId);
	}
}
