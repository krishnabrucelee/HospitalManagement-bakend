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

import com.hospital.service.NurseService;

/**
 * @author Krishna
 *
 */
@Controller
public class NurseController {

	/**
	 * Nurse Service.
	 */
	@Autowired
	private NurseService nurseService;

	/**
	 * Create Nurse.
	 * 
	 * @param nurse
	 * @return nurse
	 */
	@RequestMapping(value = "/addNurse", method = RequestMethod.POST)
	public @ResponseBody JSONObject addNurse(@RequestBody JSONObject nurse) {
		return nurseService.addNurse(nurse);
	}

	/**
	 * List nurse.
	 * 
	 * @param nurse
	 * @return nurse
	 */
	@RequestMapping(value = "/listNurse")
	public @ResponseBody JSONObject listNurse() {
		return nurseService.listNurse();
	}
	
	/**
	 * Update Nurse.
	 * 
	 * @param nurse
	 * @return nurse
	 */
	@RequestMapping(value = "/updateNurse")
	public @ResponseBody JSONObject updateNurse(@RequestBody JSONObject nurse) {
		return nurseService.updateNurse(nurse);
	}

	/**
	 * Delete nurse.
	 * 
	 * @param nurse
	 * @return nurse
	 */
	@RequestMapping(value = "/deleteNurse", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteNurse(@RequestBody JSONObject nurseId) {
		return nurseService.deleteNurse(nurseId);
	}
	
	@RequestMapping(value = "/getNurseByEmail", method = RequestMethod.POST)
	public @ResponseBody JSONObject getNurseByEmail(@RequestBody JSONObject nurseEmail) {
		return nurseService.getNurseByEmail(nurseEmail);
	}
}
