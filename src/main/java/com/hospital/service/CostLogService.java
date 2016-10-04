/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface CostLogService {

	/**
	 * Create CostLog.
	 * 
	 * @param costLog
	 * @return costLog
	 */
	public JSONObject addCostLog(JSONObject costLog);

	/**
	 * List CostLog.
	 * 
	 * @param costLog
	 * @return costLog
	 */
	public JSONObject listCostLog();

	/**
	 * Update CostLog.
	 * 
	 * @param costLog
	 * @return costLog
	 */
	public JSONObject updateCostLog(JSONObject costLog);

	/**
	 * Delete CostLog.
	 * 
	 * @param costLog
	 * @return costLog
	 */
	public JSONObject deleteCostLog(JSONObject costLogId);

}
