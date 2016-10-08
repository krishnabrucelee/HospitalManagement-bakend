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

import com.hospital.service.CssdService;

/**
 * @author Krishna
 *
 */
@Controller
public class CssdController {

	/**
	 * Cssd Service.
	 */
	@Autowired
	private CssdService cssdService;

	/**
	 * Create Cssd.
	 * 
	 * @param cssd
	 * @return cssd
	 */
	@RequestMapping(value = "/addCssd", method = RequestMethod.POST)
	public @ResponseBody JSONObject addCssd(@RequestBody JSONObject cssd) {
		return cssdService.addCssd(cssd);
	}

	/**
	 * List cssd.
	 * 
	 * @param cssd
	 * @return cssd
	 */
	@RequestMapping(value = "/listCssd")
	public @ResponseBody JSONObject listCssd() {
		return cssdService.listCssd();
	}
	
	/**
	 * Update Cssd.
	 * 
	 * @param cssd
	 * @return cssd
	 */
	@RequestMapping(value = "/updateCssd")
	public @ResponseBody JSONObject updateCssd(@RequestBody JSONObject cssd) {
		return cssdService.updateCssd(cssd);
	}

	/**
	 * Delete cssd.
	 * 
	 * @param cssd
	 * @return cssd
	 */
	@RequestMapping(value = "/deleteCssd", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteCssd(@RequestBody JSONObject cssdId) {
		return cssdService.deleteCssd(cssdId);
	}
}
