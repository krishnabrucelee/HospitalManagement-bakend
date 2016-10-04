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

import com.hospital.service.WardService;

/**
 * @author Krishna
 *
 */
@Controller
public class WardController {

	/**
	 * Ward Service.
	 */
	@Autowired
	private WardService WardService;

	/**
	 * Create Ward.
	 * 
	 * @param Ward
	 * @return Ward
	 */
	@RequestMapping(value = "/addWard", method = RequestMethod.POST)
	public @ResponseBody JSONObject addWard(@RequestBody JSONObject Ward) {
		return WardService.addWard(Ward);
	}

	/**
	 * List Ward.
	 * 
	 * @param Ward
	 * @return Ward
	 */
	@RequestMapping(value = "/listWard")
	public @ResponseBody JSONObject listWard() {
		return WardService.listWard();
	}
	
	/**
	 * Update Ward.
	 * 
	 * @param Ward
	 * @return Ward
	 */
	@RequestMapping(value = "/updateWard")
	public @ResponseBody JSONObject updateWard(@RequestBody JSONObject Ward) {
		return WardService.updateWard(Ward);
	}

	/**
	 * Delete Ward.
	 * 
	 * @param Ward
	 * @return Ward
	 */
	@RequestMapping(value = "/deleteWard", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteWard(@RequestBody JSONObject WardId) {
		return WardService.deleteWard(WardId);
	}
}
