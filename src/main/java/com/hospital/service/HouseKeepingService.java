/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

import com.hospital.model.HouseKeeping;

/**
 * @author Krishn
 *
 */
public interface HouseKeepingService {

	/**
	 * Create HouseKeeping.
	 * 
	 * @param houseKeeping
	 * @return houseKeeping
	 */
	public JSONObject addHouseKeeping(JSONObject houseKeeping);

	/**
	 * List HouseKeeping.
	 * 
	 * @param houseKeeping
	 * @return houseKeeping
	 */
	public JSONObject listHouseKeeping();

	/**
	 * Update HouseKeeping.
	 * 
	 * @param houseKeeping
	 * @return houseKeeping
	 */
	public JSONObject updateHouseKeeping(JSONObject houseKeeping);

	/**
	 * Delete HouseKeeping.
	 * 
	 * @param houseKeeping
	 * @return houseKeeping
	 */
	public JSONObject deleteHouseKeeping(JSONObject houseKeepingId);

	/**
	 * @param houseKeeping
	 * @return
	 */
	public HouseKeeping addHouseKeepingFromStaff(HouseKeeping houseKeeping);

}
