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

import com.hospital.service.BioMedicalWasteService;

/**
 * @author Krishna
 *
 */
@Controller
public class BioMedicalWasteController {

	/**
	 * BioMedicalWaste Service.
	 */
	@Autowired
	private BioMedicalWasteService bioMedicalWasteService;

	/**
	 * Create BioMedicalWaste.
	 * 
	 * @param bioMedicalWaste
	 * @return bioMedicalWaste
	 */
	@RequestMapping(value = "/addBioMedicalWaste", method = RequestMethod.POST)
	public @ResponseBody JSONObject addBioMedicalWaste(@RequestBody JSONObject bioMedicalWaste) {
		return bioMedicalWasteService.addBioMedicalWaste(bioMedicalWaste);
	}

	/**
	 * List bioMedicalWaste.
	 * 
	 * @param bioMedicalWaste
	 * @return bioMedicalWaste
	 */
	@RequestMapping(value = "/listBioMedicalWaste")
	public @ResponseBody JSONObject listBioMedicalWaste() {
		return bioMedicalWasteService.listBioMedicalWaste();
	}
	
	/**
	 * Update BioMedicalWaste.
	 * 
	 * @param bioMedicalWaste
	 * @return bioMedicalWaste
	 */
	@RequestMapping(value = "/updateBioMedicalWaste")
	public @ResponseBody JSONObject updateBioMedicalWaste(@RequestBody JSONObject bioMedicalWaste) {
		return bioMedicalWasteService.updateBioMedicalWaste(bioMedicalWaste);
	}

	/**
	 * Delete bioMedicalWaste.
	 * 
	 * @param bioMedicalWaste
	 * @return bioMedicalWaste
	 */
	@RequestMapping(value = "/deleteBioMedicalWaste", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteBioMedicalWaste(@RequestBody JSONObject bioMedicalWasteId) {
		return bioMedicalWasteService.deleteBioMedicalWaste(bioMedicalWasteId);
	}
}
