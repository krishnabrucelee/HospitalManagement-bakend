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

import com.hospital.service.TransferDoctorDetailsService;

/**
 * @author Krishna
 *
 */
@Controller
public class TransferDoctorDetailsController {

	/**
	 * TransferDoctorDetails Service.
	 */
	@Autowired
	private TransferDoctorDetailsService transferDoctorDetailsService;

	/**
	 * Create TransferDoctorDetails.
	 * 
	 * @param transferDoctorDetails
	 * @return transferDoctorDetails
	 */
	@RequestMapping(value = "/addTransferDoctorDetails", method = RequestMethod.POST)
	public @ResponseBody JSONObject addTransferDoctorDetails(@RequestBody JSONObject transferDoctorDetails) {
		return transferDoctorDetailsService.addTransferDoctorDetails(transferDoctorDetails);
	}

	/**
	 * List transferDoctorDetails.
	 * 
	 * @param transferDoctorDetails
	 * @return transferDoctorDetails
	 */
	@RequestMapping(value = "/listTransferDoctorDetails")
	public @ResponseBody JSONObject listTransferDoctorDetails() {
		return transferDoctorDetailsService.listTransferDoctorDetails();
	}
	
	/**
	 * Update TransferDoctorDetails.
	 * 
	 * @param transferDoctorDetails
	 * @return transferDoctorDetails
	 */
	@RequestMapping(value = "/updateTransferDoctorDetails")
	public @ResponseBody JSONObject updateTransferDoctorDetails(@RequestBody JSONObject transferDoctorDetails) {
		return transferDoctorDetailsService.updateTransferDoctorDetails(transferDoctorDetails);
	}

	/**
	 * Delete transferDoctorDetails.
	 * 
	 * @param transferDoctorDetails
	 * @return transferDoctorDetails
	 */
	@RequestMapping(value = "/deleteTransferDoctorDetails", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteTransferDoctorDetails(@RequestBody JSONObject transferDoctorDetailsId) {
		return transferDoctorDetailsService.deleteTransferDoctorDetails(transferDoctorDetailsId);
	}
}
