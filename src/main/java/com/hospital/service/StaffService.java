/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface StaffService {

	/**
	 * Create Staff.
	 * 
	 * @param staff
	 * @return staff
	 */
	public JSONObject addStaff(JSONObject staff);

	/**
	 * List Staff.
	 * 
	 * @param staff
	 * @return staff
	 */
	public JSONObject listStaff();

	/**
	 * Update Staff.
	 * 
	 * @param staff
	 * @return staff
	 */
	public JSONObject updateStaff(JSONObject staff);

	/**
	 * Delete Staff.
	 * 
	 * @param staff
	 * @return staff
	 */
	public JSONObject deleteStaff(JSONObject staffId);

}
