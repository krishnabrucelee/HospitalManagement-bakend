/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface BloodRequestService {

	/**
	 * Create BloodRequest.
	 * 
	 * @param bloodRequest
	 * @return bloodRequest
	 */
	public JSONObject addBloodRequest(JSONObject bloodRequest);

	/**
	 * List BloodRequest.
	 * 
	 * @param bloodRequest
	 * @return bloodRequest
	 */
	public JSONObject listBloodRequest();

	/**
	 * Update BloodRequest.
	 * 
	 * @param bloodRequest
	 * @return bloodRequest
	 */
	public JSONObject updateBloodRequest(JSONObject bloodRequest);

	/**
	 * Delete BloodRequest.
	 * 
	 * @param bloodRequest
	 * @return bloodRequest
	 */
	public JSONObject deleteBloodRequest(JSONObject bloodRequestId);

}
