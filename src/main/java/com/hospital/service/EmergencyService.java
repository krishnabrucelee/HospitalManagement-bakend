/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface EmergencyService {

	/**
	 * Create Emergency.
	 * 
	 * @param emergency
	 * @return emergency
	 */
	public JSONObject addEmergency(JSONObject emergency);

	/**
	 * List Emergency.
	 * 
	 * @param emergency
	 * @return emergency
	 */
	public JSONObject listEmergency();

	/**
	 * Update Emergency.
	 * 
	 * @param emergency
	 * @return emergency
	 */
	public JSONObject updateEmergency(JSONObject emergency);

	/**
	 * Delete Emergency.
	 * 
	 * @param emergency
	 * @return emergency
	 */
	public JSONObject deleteEmergency(JSONObject emergencyId);

}
