/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface CostService {

	/**
	 * Create Cost.
	 * 
	 * @param cost
	 * @return cost
	 */
	public JSONObject addCost(JSONObject cost);

	/**
	 * List Cost.
	 * 
	 * @param cost
	 * @return cost
	 */
	public JSONObject listCost();

	/**
	 * Update Cost.
	 * 
	 * @param cost
	 * @return cost
	 */
	public JSONObject updateCost(JSONObject cost);

	/**
	 * Delete Cost.
	 * 
	 * @param cost
	 * @return cost
	 */
	public JSONObject deleteCost(JSONObject costId);

}
