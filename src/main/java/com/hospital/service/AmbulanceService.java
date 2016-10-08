/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface AmbulanceService {

	/**
	 * Create Ambulance.
	 * 
	 * @param ambulance
	 * @return ambulance
	 */
	public JSONObject addAmbulance(JSONObject ambulance);

	/**
	 * List Ambulance.
	 * 
	 * @param ambulance
	 * @return ambulance
	 */
	public JSONObject listAmbulance();

	/**
	 * Update Ambulance.
	 * 
	 * @param ambulance
	 * @return ambulance
	 */
	public JSONObject updateAmbulance(JSONObject ambulance);

	/**
	 * Delete Ambulance.
	 * 
	 * @param ambulance
	 * @return ambulance
	 */
	public JSONObject deleteAmbulance(JSONObject ambulanceId);

}
