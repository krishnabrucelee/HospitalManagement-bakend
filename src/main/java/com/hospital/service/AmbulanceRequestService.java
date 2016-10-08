/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface AmbulanceRequestService {

	/**
	 * Create AmbulanceRequest.
	 * 
	 * @param ambulanceRequest
	 * @return ambulanceRequest
	 */
	public JSONObject addAmbulanceRequest(JSONObject ambulanceRequest);

	/**
	 * List AmbulanceRequest.
	 * 
	 * @param ambulanceRequest
	 * @return ambulanceRequest
	 */
	public JSONObject listAmbulanceRequest();

	/**
	 * Update AmbulanceRequest.
	 * 
	 * @param ambulanceRequest
	 * @return ambulanceRequest
	 */
	public JSONObject updateAmbulanceRequest(JSONObject ambulanceRequest);

	/**
	 * Delete AmbulanceRequest.
	 * 
	 * @param ambulanceRequest
	 * @return ambulanceRequest
	 */
	public JSONObject deleteAmbulanceRequest(JSONObject ambulanceRequestId);

}
