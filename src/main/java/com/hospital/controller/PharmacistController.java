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

import com.hospital.service.PharmacistService;

/**
 * @author Krishna
 *
 */
@Controller
public class PharmacistController {

	/**
	 * Pharmacist Service.
	 */
	@Autowired
	private PharmacistService pharmacistService;

	/**
	 * Create Pharmacist.
	 * 
	 * @param pharmacist
	 * @return pharmacist
	 */
	@RequestMapping(value = "/addPharmacist", method = RequestMethod.POST)
	public @ResponseBody JSONObject addPharmacist(@RequestBody JSONObject pharmacist) {
		return pharmacistService.addPharmacist(pharmacist);
	}

	/**
	 * List pharmacist.
	 * 
	 * @param pharmacist
	 * @return pharmacist
	 */
	@RequestMapping(value = "/listPharmacist")
	public @ResponseBody JSONObject listPharmacist() {
		return pharmacistService.listPharmacist();
	}
	
	/**
	 * Update Pharmacist.
	 * 
	 * @param pharmacist
	 * @return pharmacist
	 */
	@RequestMapping(value = "/updatePharmacist")
	public @ResponseBody JSONObject updatePharmacist(@RequestBody JSONObject pharmacist) {
		return pharmacistService.updatePharmacist(pharmacist);
	}

	/**
	 * Delete pharmacist.
	 * 
	 * @param pharmacist
	 * @return pharmacist
	 */
	@RequestMapping(value = "/deletePharmacist", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deletePharmacist(@RequestBody JSONObject pharmacistId) {
		return pharmacistService.deletePharmacist(pharmacistId);
	}
}
