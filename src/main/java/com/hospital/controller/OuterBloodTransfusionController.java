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

import com.hospital.service.OuterBloodTransfusionService;

/**
 * @author Krishna
 *
 */
@Controller
public class OuterBloodTransfusionController {

	/**
	 * OuterBloodTransfusion Service.
	 */
	@Autowired
	private OuterBloodTransfusionService outerBloodTransService;

	/**
	 * Create OuterBloodTransfusion.
	 * 
	 * @param outerBloodTrans
	 * @return outerBloodTrans
	 */
	@RequestMapping(value = "/addOuterBloodTransfusion", method = RequestMethod.POST)
	public @ResponseBody JSONObject addOuterBloodTransfusion(@RequestBody JSONObject outerBloodTrans) {
		return outerBloodTransService.addOuterBloodTransfusion(outerBloodTrans);
	}

	/**
	 * List outerBloodTrans.
	 * 
	 * @param outerBloodTrans
	 * @return outerBloodTrans
	 */
	@RequestMapping(value = "/listOuterBloodTransfusion")
	public @ResponseBody JSONObject listOuterBloodTransfusion() {
		return outerBloodTransService.listOuterBloodTransfusion();
	}
	
	/**
	 * Update OuterBloodTransfusion.
	 * 
	 * @param outerBloodTrans
	 * @return outerBloodTrans
	 */
	@RequestMapping(value = "/updateOuterBloodTransfusion")
	public @ResponseBody JSONObject updateOuterBloodTransfusion(@RequestBody JSONObject outerBloodTrans) {
		return outerBloodTransService.updateOuterBloodTransfusion(outerBloodTrans);
	}

	/**
	 * Delete outerBloodTrans.
	 * 
	 * @param outerBloodTrans
	 * @return outerBloodTrans
	 */
	@RequestMapping(value = "/deleteOuterBloodTransfusion", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteOuterBloodTransfusion(@RequestBody JSONObject outerBloodTransId) {
		return outerBloodTransService.deleteOuterBloodTransfusion(outerBloodTransId);
	}
}
