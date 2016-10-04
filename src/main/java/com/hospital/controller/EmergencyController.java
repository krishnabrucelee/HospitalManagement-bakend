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

import com.hospital.service.EmergencyService;

/**
 * @author Krishna
 *
 */
@Controller
public class EmergencyController {

	/**
	 * Emergency Service.
	 */
	@Autowired
	private EmergencyService emergencyService;

	/**
	 * Create Emergency.
	 * 
	 * @param emergency
	 * @return emergency
	 */
	@RequestMapping(value = "/addEmergency", method = RequestMethod.POST)
	public @ResponseBody JSONObject addEmergency(@RequestBody JSONObject emergency) {
		return emergencyService.addEmergency(emergency);
	}

	/**
	 * List emergency.
	 * 
	 * @param emergency
	 * @return emergency
	 */
	@RequestMapping(value = "/listEmergency")
	public @ResponseBody JSONObject listEmergency() {
		return emergencyService.listEmergency();
	}
	
	/**
	 * Update Emergency.
	 * 
	 * @param emergency
	 * @return emergency
	 */
	@RequestMapping(value = "/updateEmergency")
	public @ResponseBody JSONObject updateEmergency(@RequestBody JSONObject emergency) {
		return emergencyService.updateEmergency(emergency);
	}

	/**
	 * Delete emergency.
	 * 
	 * @param emergency
	 * @return emergency
	 */
	@RequestMapping(value = "/deleteEmergency", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteEmergency(@RequestBody JSONObject emergencyId) {
		return emergencyService.deleteEmergency(emergencyId);
	}
}
