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

import com.hospital.service.PurchaseBillingService;

/**
 * @author Krishna
 *
 */
@Controller
public class PurchaseBillingController {

	/**
	 * PurchaseBilling Service.
	 */
	@Autowired
	private PurchaseBillingService purchaseBillingService;

	/**
	 * Create PurchaseBilling.
	 * 
	 * @param purchaseBilling
	 * @return purchaseBilling
	 */
	@RequestMapping(value = "/addPurchaseBilling", method = RequestMethod.POST)
	public @ResponseBody JSONObject addPurchaseBilling(@RequestBody JSONObject purchaseBilling) {
		return purchaseBillingService.addPurchaseBilling(purchaseBilling);
	}

	/**
	 * List purchaseBilling.
	 * 
	 * @param purchaseBilling
	 * @return purchaseBilling
	 */
	@RequestMapping(value = "/listPurchaseBilling")
	public @ResponseBody JSONObject listPurchaseBilling() {
		return purchaseBillingService.listPurchaseBilling();
	}
	
	/**
	 * Update PurchaseBilling.
	 * 
	 * @param purchaseBilling
	 * @return purchaseBilling
	 */
	@RequestMapping(value = "/updatePurchaseBilling")
	public @ResponseBody JSONObject updatePurchaseBilling(@RequestBody JSONObject purchaseBilling) {
		return purchaseBillingService.updatePurchaseBilling(purchaseBilling);
	}

	/**
	 * Delete purchaseBilling.
	 * 
	 * @param purchaseBilling
	 * @return purchaseBilling
	 */
	@RequestMapping(value = "/deletePurchaseBilling", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deletePurchaseBilling(@RequestBody JSONObject purchaseBillingId) {
		return purchaseBillingService.deletePurchaseBilling(purchaseBillingId);
	}
}
