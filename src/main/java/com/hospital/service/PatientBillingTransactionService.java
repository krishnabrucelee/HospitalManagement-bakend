/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Krishna
 *
 */
public interface PatientBillingTransactionService {

	/**
	 * Create PatientBillingTransaction.
	 * 
	 * @param patientBillingTransaction
	 * @return patientBillingTransaction
	 */
	public JSONObject addPatientBillingTransaction(JSONObject patientBillingTransaction);

	/**
	 * List PatientBillingTransaction.
	 * 
	 * @param patientBillingTransaction
	 * @return patientBillingTransaction
	 */
	public JSONObject listPatientBillingTransaction();

	/**
	 * Update PatientBillingTransaction.
	 * 
	 * @param patientBillingTransaction
	 * @return patientBillingTransaction
	 */
	public JSONObject updatePatientBillingTransaction(JSONObject patientBillingTransaction);

	/**
	 * Delete PatientBillingTransaction.
	 * 
	 * @param patientBillingTransaction
	 * @return patientBillingTransaction
	 */
	public JSONObject deletePatientBillingTransaction(JSONObject patientBillingTransactionId);

}
