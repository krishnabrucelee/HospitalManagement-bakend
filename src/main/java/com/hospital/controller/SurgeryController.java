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

import com.hospital.service.SurgeryService;

/**
 * @author Krishna
 *
 */
@Controller
public class SurgeryController {

	/**
	 * Surgery Service.
	 */
	@Autowired
	private SurgeryService surgeryService;

	/**
	 * Create Surgery.
	 * 
	 * @param surgery
	 * @return surgery
	 */
	@RequestMapping(value = "/addSurgery", method = RequestMethod.POST)
	public @ResponseBody JSONObject addSurgery(@RequestBody JSONObject surgery) {
		return surgeryService.addSurgery(surgery);
	}

	/**
	 * List surgery.
	 * 
	 * @param surgery
	 * @return surgery
	 */
	@RequestMapping(value = "/listSurgery")
	public @ResponseBody JSONObject listSurgery() {
		return surgeryService.listSurgery();
	}
	
	/**
	 * Update Surgery.
	 * 
	 * @param surgery
	 * @return surgery
	 */
	@RequestMapping(value = "/updateSurgery")
	public @ResponseBody JSONObject updateSurgery(@RequestBody JSONObject surgery) {
		return surgeryService.updateSurgery(surgery);
	}

	/**
	 * Delete surgery.
	 * 
	 * @param surgery
	 * @return surgery
	 */
	@RequestMapping(value = "/deleteSurgery", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteSurgery(@RequestBody JSONObject surgeryId) {
		return surgeryService.deleteSurgery(surgeryId);
	}
}
