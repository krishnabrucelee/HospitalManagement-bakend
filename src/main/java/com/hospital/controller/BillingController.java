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

import com.hospital.service.BillingService;

/**
 * @author Krishna
 *
 */
@Controller
public class BillingController {

	/**
	 * Billing Service.
	 */
	@Autowired
	private BillingService billingService;

	/**
	 * Create Billing.
	 * 
	 * @param billing
	 * @return billing
	 */
	@RequestMapping(value = "/addBilling", method = RequestMethod.POST)
	public @ResponseBody JSONObject addBilling(@RequestBody JSONObject billing) {
		return billingService.addBilling(billing);
	}

	/**
	 * List billingRequest.
	 * 
	 * @param billingRequest
	 * @return billingRequest
	 */
	@RequestMapping(value = "/listBilling")
	public @ResponseBody JSONObject listBilling() {
		return billingService.listBilling();
	}
	
	/**
	 * Update Billing.
	 * 
	 * @param billing
	 * @return billing
	 */
	@RequestMapping(value = "/updateBilling")
	public @ResponseBody JSONObject updateBilling(@RequestBody JSONObject billing) {
		return billingService.updateBilling(billing);
	}

	/**
	 * Delete billing.
	 * 
	 * @param billing
	 * @return billing
	 */
	@RequestMapping(value = "/deleteBilling", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteBilling(@RequestBody JSONObject billingId) {
		return billingService.deleteBilling(billingId);
	}
}
