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

import com.hospital.service.AmbulanceRequestService;

/**
 * @author Krishna
 *
 */
@Controller
public class AmbulanceRequestController {

	/**
	 * AmbulanceRequest Service.
	 */
	@Autowired
	private AmbulanceRequestService ambulanceRequestService;

	/**
	 * Create AmbulanceRequest.
	 * 
	 * @param ambulanceRequest
	 * @return ambulanceRequest
	 */
	@RequestMapping(value = "/addAmbulanceRequest", method = RequestMethod.POST)
	public @ResponseBody JSONObject addAmbulanceRequest(@RequestBody JSONObject ambulanceRequest) {
		return ambulanceRequestService.addAmbulanceRequest(ambulanceRequest);
	}

	/**
	 * List ambulanceRequest.
	 * 
	 * @param ambulanceRequest
	 * @return ambulanceRequest
	 */
	@RequestMapping(value = "/listAmbulanceRequest")
	public @ResponseBody JSONObject listAmbulanceRequest() {
		return ambulanceRequestService.listAmbulanceRequest();
	}
	
	/**
	 * Update AmbulanceRequest.
	 * 
	 * @param ambulanceRequest
	 * @return ambulanceRequest
	 */
	@RequestMapping(value = "/updateAmbulanceRequest")
	public @ResponseBody JSONObject updateAmbulanceRequest(@RequestBody JSONObject ambulanceRequest) {
		return ambulanceRequestService.updateAmbulanceRequest(ambulanceRequest);
	}

	/**
	 * Delete ambulanceRequest.
	 * 
	 * @param ambulanceRequest
	 * @return ambulanceRequest
	 */
	@RequestMapping(value = "/deleteAmbulanceRequest", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteAmbulanceRequest(@RequestBody JSONObject ambulanceRequestId) {
		return ambulanceRequestService.deleteAmbulanceRequest(ambulanceRequestId);
	}
}
