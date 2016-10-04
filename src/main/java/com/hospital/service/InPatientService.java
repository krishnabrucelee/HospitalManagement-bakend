/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface InPatientService {

	/**
	 * Create InPatient.
	 * 
	 * @param inPatient
	 * @return inPatient
	 */
	public JSONObject addInPatient(JSONObject inPatient);

	/**
	 * List InPatient.
	 * 
	 * @param inPatient
	 * @return inPatient
	 */
	public JSONObject listInPatient();

	/**
	 * Update InPatient.
	 * 
	 * @param inPatient
	 * @return inPatient
	 */
	public JSONObject updateInPatient(JSONObject inPatient);

	/**
	 * Delete InPatient.
	 * 
	 * @param inPatient
	 * @return inPatient
	 */
	public JSONObject deleteInPatient(JSONObject inPatientId);



}
