/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface PatientBillingService {

	/**
	 * Create PatientBilling.
	 * 
	 * @param patientBilling
	 * @return patientBilling
	 */
	public JSONObject addPatientBilling(JSONObject patientBilling);

	/**
	 * List PatientBilling.
	 * 
	 * @param patientBilling
	 * @return patientBilling
	 */
	public JSONObject listPatientBilling();

	/**
	 * Update PatientBilling.
	 * 
	 * @param patientBilling
	 * @return patientBilling
	 */
	public JSONObject updatePatientBilling(JSONObject patientBilling);

	/**
	 * Delete PatientBilling.
	 * 
	 * @param patientBilling
	 * @return patientBilling
	 */
	public JSONObject deletePatientBilling(JSONObject patientBillingId);

}
