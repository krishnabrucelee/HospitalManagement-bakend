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

import com.hospital.service.BloodRequestService;

/**
 * @author Krishna
 *
 */
@Controller
public class BloodRequestController {

	/**
	 * BloodRequest Service.
	 */
	@Autowired
	private BloodRequestService bloodRequestService;

	/**
	 * Create BloodRequest.
	 * 
	 * @param bloodRequest
	 * @return bloodRequest
	 */
	@RequestMapping(value = "/addBloodRequest", method = RequestMethod.POST)
	public @ResponseBody JSONObject addBloodRequest(@RequestBody JSONObject bloodRequest) {
		return bloodRequestService.addBloodRequest(bloodRequest);
	}

	/**
	 * List bloodRequest.
	 * 
	 * @param bloodRequest
	 * @return bloodRequest
	 */
	@RequestMapping(value = "/listBloodRequest")
	public @ResponseBody JSONObject listBloodRequest() {
		return bloodRequestService.listBloodRequest();
	}
	
	/**
	 * Update BloodRequest.
	 * 
	 * @param bloodRequest
	 * @return bloodRequest
	 */
	@RequestMapping(value = "/updateBloodRequest")
	public @ResponseBody JSONObject updateBloodRequest(@RequestBody JSONObject bloodRequest) {
		return bloodRequestService.updateBloodRequest(bloodRequest);
	}

	/**
	 * Delete bloodRequest.
	 * 
	 * @param bloodRequest
	 * @return bloodRequest
	 */
	@RequestMapping(value = "/deleteBloodRequest", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteBloodRequest(@RequestBody JSONObject bloodRequestId) {
		return bloodRequestService.deleteBloodRequest(bloodRequestId);
	}
}
