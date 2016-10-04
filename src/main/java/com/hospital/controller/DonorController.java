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

import com.hospital.service.DonorService;

/**
 * @author Krishna
 *
 */
@Controller
public class DonorController {

	/**
	 * Donor Service.
	 */
	@Autowired
	private DonorService donorService;

	/**
	 * Create Donor.
	 * 
	 * @param donor
	 * @return donor
	 */
	@RequestMapping(value = "/addDonor", method = RequestMethod.POST)
	public @ResponseBody JSONObject addDonor(@RequestBody JSONObject donor) {
		return donorService.addDonor(donor);
	}

	/**
	 * List donor.
	 * 
	 * @param donor
	 * @return donor
	 */
	@RequestMapping(value = "/listDonor")
	public @ResponseBody JSONObject listDonor() {
		return donorService.listDonor();
	}
	
	/**
	 * Update Donor.
	 * 
	 * @param donor
	 * @return donor
	 */
	@RequestMapping(value = "/updateDonor")
	public @ResponseBody JSONObject updateDonor(@RequestBody JSONObject donor) {
		return donorService.updateDonor(donor);
	}

	/**
	 * Delete donor.
	 * 	
	 * @param donor
	 * @return donor
	 */
	@RequestMapping(value = "/deleteDonor", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteDonor(@RequestBody JSONObject donorId) {
		return donorService.deleteDonor(donorId);
	}
}
