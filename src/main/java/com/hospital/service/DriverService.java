/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

import com.hospital.model.Driver;

/**
 * @author Krishna
 *
 */
public interface DriverService {

	/**
	 * Create Driver.
	 * 
	 * @param driver
	 * @return driver
	 */
	public JSONObject addDriver(JSONObject driver);

	/**
	 * List Driver.
	 * 
	 * @param driver
	 * @return driver
	 */
	public JSONObject listDriver();

	/**
	 * Update Driver.
	 * 
	 * @param driver
	 * @return driver
	 */
	public JSONObject updateDriver(JSONObject driver);

	/**
	 * Delete Driver.
	 * 
	 * @param driver
	 * @return driver
	 */
	public JSONObject deleteDriver(JSONObject driverId);

	/**
	 * @param driver
	 * @return
	 */
	public Driver addDriverFromStaff(Driver driver);

}
