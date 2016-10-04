/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface WardService {

	/**
	 * Create Ward.
	 * 
	 * @param Ward
	 * @return Ward
	 */
	public JSONObject addWard(JSONObject Ward);

	/**
	 * List Ward.
	 * 
	 * @param Ward
	 * @return Ward
	 */
	public JSONObject listWard();

	/**
	 * Update Ward.
	 * 
	 * @param Ward
	 * @return Ward
	 */
	public JSONObject updateWard(JSONObject Ward);

	/**
	 * Delete Ward.
	 * 
	 * @param Ward
	 * @return Ward
	 */
	public JSONObject deleteWard(JSONObject WardId);

}
