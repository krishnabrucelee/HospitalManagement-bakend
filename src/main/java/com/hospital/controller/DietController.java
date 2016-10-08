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

import com.hospital.service.DietService;

/**
 * @author Krishna
 *
 */
@Controller
public class DietController {

	/**
	 * Diet Service.
	 */
	@Autowired
	private DietService dietService;

	/**
	 * Create Diet.
	 * 
	 * @param diet
	 * @return diet
	 */
	@RequestMapping(value = "/addDiet", method = RequestMethod.POST)
	public @ResponseBody JSONObject addDiet(@RequestBody JSONObject diet) {
		return dietService.addDiet(diet);
	}

	/**
	 * List diet.
	 * 
	 * @param diet
	 * @return diet
	 */
	@RequestMapping(value = "/listDiet")
	public @ResponseBody JSONObject listDiet() {
		return dietService.listDiet();
	}
	
	/**
	 * Update Diet.
	 * 
	 * @param diet
	 * @return diet
	 */
	@RequestMapping(value = "/updateDiet")
	public @ResponseBody JSONObject updateDiet(@RequestBody JSONObject diet) {
		return dietService.updateDiet(diet);
	}

	/**
	 * Delete diet.
	 * 
	 * @param diet
	 * @return diet
	 */
	@RequestMapping(value = "/deleteDiet", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteDiet(@RequestBody JSONObject dietId) {
		return dietService.deleteDiet(dietId);
	}
	
	/**
	 * list diet by Patient Id.
	 * 
	 * @param diet
	 * @return diet
	 */
	@RequestMapping(value = "/listDietByPatientId")
	public @ResponseBody JSONObject listByPatientId(@RequestBody JSONObject patientId) {
		return dietService.listByPatientId(patientId);
	}
}
