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

import com.hospital.service.InterBloodTransfusionService;

/**
 * @author Krishna
 *
 */
@Controller
public class InterBloodTransfusionController {

	/**
	 * InterBloodTransfusion Service.
	 */
	@Autowired
	private InterBloodTransfusionService interBloodTransService;

	/**
	 * Create InterBloodTransfusion.
	 * 
	 * @param interBloodTrans
	 * @return interBloodTrans
	 */
	@RequestMapping(value = "/addInterBloodTransfusion", method = RequestMethod.POST)
	public @ResponseBody JSONObject addInterBloodTransfusion(@RequestBody JSONObject interBloodTrans) {
		return interBloodTransService.addInterBloodTransfusion(interBloodTrans);
	}

	/**
	 * List interBloodTrans.
	 * 
	 * @param interBloodTrans
	 * @return interBloodTrans
	 */
	@RequestMapping(value = "/listInterBloodTransfusion")
	public @ResponseBody JSONObject listInterBloodTransfusion() {
		return interBloodTransService.listInterBloodTransfusion();
	}
	
	/**
	 * Update InterBloodTransfusion.
	 * 
	 * @param interBloodTrans
	 * @return interBloodTrans
	 */
	@RequestMapping(value = "/updateInterBloodTransfusion")
	public @ResponseBody JSONObject updateInterBloodTransfusion(@RequestBody JSONObject interBloodTrans) {
		return interBloodTransService.updateInterBloodTransfusion(interBloodTrans);
	}

	/**
	 * Delete interBloodTrans.
	 * 
	 * @param interBloodTrans
	 * @return interBloodTrans
	 */
	@RequestMapping(value = "/deleteInterBloodTransfusion", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteInterBloodTransfusion(@RequestBody JSONObject interBloodTransId) {
		return interBloodTransService.deleteInterBloodTransfusion(interBloodTransId);
	}
}
