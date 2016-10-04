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

import com.hospital.service.PettyCashService;

/**
 * @author Krishna
 *
 */
@Controller
public class PettyCashController {

	/**
	 * PettyCash Service.
	 */
	@Autowired
	private PettyCashService pettyCashService;

	/**
	 * Create PettyCash.
	 * 
	 * @param pettyCash
	 * @return pettyCash
	 */
	@RequestMapping(value = "/addPettyCash", method = RequestMethod.POST)
	public @ResponseBody JSONObject addPettyCash(@RequestBody JSONObject pettyCash) {
		return pettyCashService.addPettyCash(pettyCash);
	}

	/**
	 * List pettyCash.
	 * 
	 * @param pettyCash
	 * @return pettyCash
	 */
	@RequestMapping(value = "/listPettyCash")
	public @ResponseBody JSONObject listPettyCash() {
		return pettyCashService.listPettyCash();
	}
	
	/**
	 * Update PettyCash.
	 * 
	 * @param pettyCash
	 * @return pettyCash
	 */
	@RequestMapping(value = "/updatePettyCash")
	public @ResponseBody JSONObject updatePettyCash(@RequestBody JSONObject pettyCash) {
		return pettyCashService.updatePettyCash(pettyCash);
	}

	/**
	 * Delete pettyCash.
	 * 
	 * @param pettyCash
	 * @return pettyCash
	 */
	@RequestMapping(value = "/deletePettyCash", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deletePettyCash(@RequestBody JSONObject pettyCashId) {
		return pettyCashService.deletePettyCash(pettyCashId);
	}
}
