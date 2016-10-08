/**
 * 
 */
package com.hospital.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.service.DriverService;

/**
 * @author Krishna
 *
 */
@Controller
public class DriverController {

	/**
	 * Driver Service.
	 */
	@Autowired
	private DriverService driverService;

	/**
	 * Create Driver.
	 * 
	 * @param driver
	 * @return driver
	 */
	@RequestMapping(value = "/addDriver", method = RequestMethod.POST)
	public @ResponseBody JSONObject addDriver(@RequestBody JSONObject driver) {
		return driverService.addDriver(driver);
	}

	/**
	 * List driver.
	 * 
	 * @param driver
	 * @return driver
	 */
	@RequestMapping(value = "/listDriver")
	public @ResponseBody JSONObject listDriver() {
		return driverService.listDriver();
	}
	
	/**
	 * Update Driver.
	 * 
	 * @param driver
	 * @return driver
	 */
	@RequestMapping(value = "/updateDriver")
	public @ResponseBody JSONObject updateDriver(@RequestBody JSONObject driver) {
		return driverService.updateDriver(driver);
	}

	/**
	 * Delete driver.
	 * 
	 * @param driver
	 * @return driver
	 */
	@RequestMapping(value = "/deleteDriver", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteDriver(@RequestBody JSONObject driverId) {
		return driverService.deleteDriver(driverId);
	}
}
