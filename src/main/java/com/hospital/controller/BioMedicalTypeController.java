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

import com.hospital.service.BioMedicalTypeService;

/**
 * @author Krishna
 *
 */
@Controller
public class BioMedicalTypeController {

	/**
	 * BioMedicalType Service.
	 */
	@Autowired
	private BioMedicalTypeService bioMedicalTypeService;

	/**
	 * Create BioMedicalType.
	 * 
	 * @param bioMedicalType
	 * @return bioMedicalType
	 */
	@RequestMapping(value = "/addBioMedicalType", method = RequestMethod.POST)
	public @ResponseBody JSONObject addBioMedicalType(@RequestBody JSONObject bioMedicalType) {
		return bioMedicalTypeService.addBioMedicalType(bioMedicalType);
	}

	/**
	 * List bioMedicalType.
	 * 
	 * @param bioMedicalType
	 * @return bioMedicalType
	 */
	@RequestMapping(value = "/listBioMedicalType")
	public @ResponseBody JSONObject listBioMedicalType() {
		return bioMedicalTypeService.listBioMedicalType();
	}
	
	/**
	 * Update BioMedicalType.
	 * 
	 * @param bioMedicalType
	 * @return bioMedicalType
	 */
	@RequestMapping(value = "/updateBioMedicalType")
	public @ResponseBody JSONObject updateBioMedicalType(@RequestBody JSONObject bioMedicalType) {
		return bioMedicalTypeService.updateBioMedicalType(bioMedicalType);
	}

	/**
	 * Delete bioMedicalType.
	 * 
	 * @param bioMedicalType
	 * @return bioMedicalType
	 */
	@RequestMapping(value = "/deleteBioMedicalType", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteBioMedicalType(@RequestBody JSONObject bioMedicalTypeId) {
		return bioMedicalTypeService.deleteBioMedicalType(bioMedicalTypeId);
	}
}
