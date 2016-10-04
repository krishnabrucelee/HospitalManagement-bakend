/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface BloodReturnService {

	/**
	 * Create BloodReturn.
	 * 
	 * @param appointment
	 * @return appointment
	 */
	public JSONObject addBloodReturn(JSONObject appointment);

	/**
	 * List BloodReturn.
	 * 
	 * @param appointment
	 * @return appointment
	 */
	public JSONObject listBloodReturn();

	/**
	 * Update BloodReturn.
	 * 
	 * @param appointment
	 * @return appointment
	 */
	public JSONObject updateBloodReturn(JSONObject appointment);

	/**
	 * Delete BloodReturn.
	 * 
	 * @param appointment
	 * @return appointment
	 */
	public JSONObject deleteBloodReturn(JSONObject appointmentId);

}
