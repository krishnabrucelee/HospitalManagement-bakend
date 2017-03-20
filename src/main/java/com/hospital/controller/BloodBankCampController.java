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

import com.hospital.service.BloodBankCampService;

/**
 * @author Krishna
 *
 */
@Controller
public class BloodBankCampController {

	/**
	 * BloodBankCamp Service.
	 */
	@Autowired
	private BloodBankCampService bloodBankCampService;

	/**
	 * Create BloodBankCamp.
	 * 
	 * @param bloodBankCamp
	 * @return bloodBankCamp
	 */
	@RequestMapping(value = "/addBloodBankCamp", method = RequestMethod.POST)
	public @ResponseBody JSONObject addBloodBankCamp(@RequestBody JSONObject bloodBankCamp) {
		return bloodBankCampService.addBloodBankCamp(bloodBankCamp);
	}

	/**
	 * List bloodBankCamp.
	 * 
	 * @param bloodBankCamp
	 * @return bloodBankCamp
	 */
	@RequestMapping(value = "/listBloodBankCamp")
	public @ResponseBody JSONObject listBloodBankCamp() {
		return bloodBankCampService.listBloodBankCamp();
	}
	
	/**
	 * Update BloodBankCamp.
	 * 
	 * @param bloodBankCamp
	 * @return bloodBankCamp
	 */
	@RequestMapping(value = "/updateBloodBankCamp")
	public @ResponseBody JSONObject updateBloodBankCamp(@RequestBody JSONObject bloodBankCamp) {
		return bloodBankCampService.updateBloodBankCamp(bloodBankCamp);
	}

	/**
	 * Delete bloodBankCamp.
	 * 
	 * @param bloodBankCamp
	 * @return bloodBankCamp
	 */
	@RequestMapping(value = "/deleteBloodBankCamp", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteBloodBankCamp(@RequestBody JSONObject bloodBankCampId) {
		return bloodBankCampService.deleteBloodBankCamp(bloodBankCampId);
	}
}
