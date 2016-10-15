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

import com.hospital.service.BioMedicalDisposalManagementService;

/**
 * @author Krishna
 *
 */
@Controller
public class BioMedicalDisposalManagementController {

	/**
	 * BioMedicalDisposalManagement Service.
	 */
	@Autowired
	private BioMedicalDisposalManagementService bioMedicalDisposalManagementService;

	/**
	 * Create BioMedicalDisposalManagement.
	 * 
	 * @param bioMedicalDisposalManagement
	 * @return bioMedicalDisposalManagement
	 */
	@RequestMapping(value = "/addBioMedicalDisposalManagement", method = RequestMethod.POST)
	public @ResponseBody JSONObject addBioMedicalDisposalManagement(@RequestBody JSONObject bioMedicalDisposalManagement) {
		return bioMedicalDisposalManagementService.addBioMedicalDisposalManagement(bioMedicalDisposalManagement);
	}

	/**
	 * List bioMedicalDisposalManagement.
	 * 
	 * @param bioMedicalDisposalManagement
	 * @return bioMedicalDisposalManagement
	 */
	@RequestMapping(value = "/listBioMedicalDisposalManagement")
	public @ResponseBody JSONObject listBioMedicalDisposalManagement() {
		return bioMedicalDisposalManagementService.listBioMedicalDisposalManagement();
	}
	
	/**
	 * Update BioMedicalDisposalManagement.
	 * 
	 * @param bioMedicalDisposalManagement
	 * @return bioMedicalDisposalManagement
	 */
	@RequestMapping(value = "/updateBioMedicalDisposalManagement")
	public @ResponseBody JSONObject updateBioMedicalDisposalManagement(@RequestBody JSONObject bioMedicalDisposalManagement) {
		return bioMedicalDisposalManagementService.updateBioMedicalDisposalManagement(bioMedicalDisposalManagement);
	}

	/**
	 * Delete bioMedicalDisposalManagement.
	 * 
	 * @param bioMedicalDisposalManagement
	 * @return bioMedicalDisposalManagement
	 */
	@RequestMapping(value = "/deleteBioMedicalDisposalManagement", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteBioMedicalDisposalManagement(@RequestBody JSONObject bioMedicalDisposalManagementId) {
		return bioMedicalDisposalManagementService.deleteBioMedicalDisposalManagement(bioMedicalDisposalManagementId);
	}
}
