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

import com.hospital.service.LaboratoryService;

/**
 * @author Krishna
 *
 */
@Controller
public class LaboratoryController {

	/**
	 * Laboratory Service.
	 */
	@Autowired
	private LaboratoryService laboratoryService;

	/**
	 * Create Laboratory.
	 * 
	 * @param laboratory
	 * @return laboratory
	 */
	@RequestMapping(value = "/addLaboratory", method = RequestMethod.POST)
	public @ResponseBody JSONObject addLaboratory(@RequestBody JSONObject laboratory) {
		return laboratoryService.addLaboratory(laboratory);
	}

	/**
	 * List laboratory.
	 * 
	 * @param laboratory
	 * @return laboratory
	 */
	@RequestMapping(value = "/listLaboratory")
	public @ResponseBody JSONObject listLaboratory() {
		return laboratoryService.listLaboratory();
	}
	
	/**
	 * Update Laboratory.
	 * 
	 * @param laboratory
	 * @return laboratory
	 */
	@RequestMapping(value = "/updateLaboratory")
	public @ResponseBody JSONObject updateLaboratory(@RequestBody JSONObject laboratory) {
		return laboratoryService.updateLaboratory(laboratory);
	}

	/**
	 * Delete laboratory.
	 * 
	 * @param laboratory
	 * @return laboratory
	 */
	@RequestMapping(value = "/deleteLaboratory", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteLaboratory(@RequestBody JSONObject laboratoryId) {
		return laboratoryService.deleteLaboratory(laboratoryId);
	}
	
	/**
	 * list EMR by Patient Id.
	 * 
	 * @param eMedicalReport
	 * @return eMedicalReport
	 */
	@RequestMapping(value = "/listLabByPatientId")
	public @ResponseBody JSONObject listByPatientId(@RequestBody JSONObject patientId) {
		return laboratoryService.listByPatientId(patientId);
	}
}
