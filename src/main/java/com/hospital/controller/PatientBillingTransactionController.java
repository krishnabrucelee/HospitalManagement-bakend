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

import com.hospital.service.PatientBillingTransactionService;

/**
 * @author Krishna
 *
 */
@Controller
public class PatientBillingTransactionController {

	/**
	 * PatientBillingTransaction Service.
	 */
	@Autowired
	private PatientBillingTransactionService patientBillingTransactionService;

	/**
	 * Create PatientBillingTransaction.
	 * 
	 * @param patientBillingTransaction
	 * @return patientBillingTransaction
	 */
	@RequestMapping(value = "/addPatientBillingTransaction", method = RequestMethod.POST)
	public @ResponseBody JSONObject addPatientBillingTransaction(@RequestBody JSONObject patientBillingTransaction) {
		return patientBillingTransactionService.addPatientBillingTransaction(patientBillingTransaction);
	}

	/**
	 * List patientBillingTransaction.
	 * 
	 * @param patientBillingTransaction
	 * @return patientBillingTransaction
	 */
	@RequestMapping(value = "/listPatientBillingTransaction")
	public @ResponseBody JSONObject listPatientBillingTransaction() {
		return patientBillingTransactionService.listPatientBillingTransaction();
	}
	
	/**
	 * Update PatientBillingTransaction.
	 * 
	 * @param patientBillingTransaction
	 * @return patientBillingTransaction
	 */
	@RequestMapping(value = "/updatePatientBillingTransaction")
	public @ResponseBody JSONObject updatePatientBillingTransaction(@RequestBody JSONObject patientBillingTransaction) {
		return patientBillingTransactionService.updatePatientBillingTransaction(patientBillingTransaction);
	}

	/**
	 * Delete patientBillingTransaction.
	 * 
	 * @param patientBillingTransaction
	 * @return patientBillingTransaction
	 */
	@RequestMapping(value = "/deletePatientBillingTransaction", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deletePatientBillingTransaction(@RequestBody JSONObject patientBillingTransactionId) {
		return patientBillingTransactionService.deletePatientBillingTransaction(patientBillingTransactionId);
	}
}
