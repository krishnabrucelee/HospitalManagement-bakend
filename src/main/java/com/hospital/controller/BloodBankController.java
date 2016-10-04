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

import com.hospital.service.BloodBankService;

/**
 * @author Krishna
 *
 */
@Controller
public class BloodBankController {

	/**
	 * BloodBank Service.
	 */
	@Autowired
	private BloodBankService bloodBankService;

	/**
	 * Create BloodBank.
	 * 
	 * @param bloodBank
	 * @return bloodBank
	 */
	@RequestMapping(value = "/addBloodBank", method = RequestMethod.POST)
	public @ResponseBody JSONObject addBloodBank(@RequestBody JSONObject bloodBank) {
		return bloodBankService.addBloodBank(bloodBank);
	}

	/**
	 * List bloodBank.
	 * 
	 * @param bloodBank
	 * @return bloodBank
	 */
	@RequestMapping(value = "/listBloodBank")
	public @ResponseBody JSONObject listBloodBank() {
		return bloodBankService.listBloodBank();
	}
	
	/**
	 * Update BloodBank.
	 * 
	 * @param bloodBank
	 * @return bloodBank
	 */
	@RequestMapping(value = "/updateBloodBank")
	public @ResponseBody JSONObject updateBloodBank(@RequestBody JSONObject bloodBank) {
		return bloodBankService.updateBloodBank(bloodBank);
	}

	/**
	 * Delete bloodBank.
	 * 
	 * @param bloodBank
	 * @return bloodBank
	 */
	@RequestMapping(value = "/deleteBloodBank", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteBloodBank(@RequestBody JSONObject bloodBankId) {
		return bloodBankService.deleteBloodBank(bloodBankId);
	}
}
