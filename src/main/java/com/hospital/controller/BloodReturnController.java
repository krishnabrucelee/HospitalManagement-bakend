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

import com.hospital.service.BloodReturnService;

/**
 * @author Krishna
 *
 */
@Controller
public class BloodReturnController {

	/**
	 * BloodReturn Service.
	 */
	@Autowired
	private BloodReturnService bloodReturnService;

	/**
	 * Create BloodReturn.
	 * 
	 * @param bloodReturn
	 * @return bloodReturn
	 */
	@RequestMapping(value = "/addBloodReturn", method = RequestMethod.POST)
	public @ResponseBody JSONObject addBloodReturn(@RequestBody JSONObject bloodReturn) {
		return bloodReturnService.addBloodReturn(bloodReturn);
	}

	/**
	 * List bloodReturn.
	 * 
	 * @param bloodReturn
	 * @return bloodReturn
	 */
	@RequestMapping(value = "/listBloodReturn")
	public @ResponseBody JSONObject listBloodReturn() {
		return bloodReturnService.listBloodReturn();
	}
	
	/**
	 * Update BloodReturn.
	 * 
	 * @param bloodReturn
	 * @return bloodReturn
	 */
	@RequestMapping(value = "/updateBloodReturn")
	public @ResponseBody JSONObject updateBloodReturn(@RequestBody JSONObject bloodReturn) {
		return bloodReturnService.updateBloodReturn(bloodReturn);
	}

	/**
	 * Delete bloodReturn.
	 * 
	 * @param bloodReturn
	 * @return bloodReturn
	 */
	@RequestMapping(value = "/deleteBloodReturn", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteBloodReturn(@RequestBody JSONObject bloodReturnId) {
		return bloodReturnService.deleteBloodReturn(bloodReturnId);
	}
}
