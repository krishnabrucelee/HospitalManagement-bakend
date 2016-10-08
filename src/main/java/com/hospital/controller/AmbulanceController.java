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

import com.hospital.service.AmbulanceService;

/**
 * @author Krishna
 *
 */
@Controller
public class AmbulanceController {

	/**
	 * Ambulance Service.
	 */
	@Autowired
	private AmbulanceService ambulanceService;

	/**
	 * Create Ambulance.
	 * 
	 * @param ambulance
	 * @return ambulance
	 */
	@RequestMapping(value = "/addAmbulance", method = RequestMethod.POST)
	public @ResponseBody JSONObject addAmbulance(@RequestBody JSONObject ambulance) {
		return ambulanceService.addAmbulance(ambulance);
	}

	/**
	 * List ambulance.
	 * 
	 * @param ambulance
	 * @return ambulance
	 */
	@RequestMapping(value = "/listAmbulance")
	public @ResponseBody JSONObject listAmbulance() {
		return ambulanceService.listAmbulance();
	}
	
	/**
	 * Update Ambulance.
	 * 
	 * @param ambulance
	 * @return ambulance
	 */
	@RequestMapping(value = "/updateAmbulance")
	public @ResponseBody JSONObject updateAmbulance(@RequestBody JSONObject ambulance) {
		return ambulanceService.updateAmbulance(ambulance);
	}

	/**
	 * Delete ambulance.
	 * 
	 * @param ambulance
	 * @return ambulance
	 */
	@RequestMapping(value = "/deleteAmbulance", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteAmbulance(@RequestBody JSONObject ambulanceId) {
		return ambulanceService.deleteAmbulance(ambulanceId);
	}
}
