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

import com.hospital.service.PurchaseBillingTransactionService;

/**
 * @author Krishna
 *
 */
@Controller
public class PurchaseBillingTransactionController {

	/**
	 * PurchaseBillingTransaction Service.
	 */
	@Autowired
	private PurchaseBillingTransactionService purchaseBillingTransactionService;

	/**
	 * Create PurchaseBillingTransaction.
	 * 
	 * @param purchaseBillingTransaction
	 * @return purchaseBillingTransaction
	 */
	@RequestMapping(value = "/addPurchaseBillingTransaction", method = RequestMethod.POST)
	public @ResponseBody JSONObject addPurchaseBillingTransaction(@RequestBody JSONObject purchaseBillingTransaction) {
		return purchaseBillingTransactionService.addPurchaseBillingTransaction(purchaseBillingTransaction);
	}

	/**
	 * List purchaseBillingTransactionRequest.
	 * 
	 * @param purchaseBillingTransactionRequest
	 * @return purchaseBillingTransactionRequest
	 */
	@RequestMapping(value = "/listPurchaseBillingTransaction")
	public @ResponseBody JSONObject listPurchaseBillingTransaction() {
		return purchaseBillingTransactionService.listPurchaseBillingTransaction();
	}
	
	/**
	 * Update PurchaseBillingTransaction.
	 * 
	 * @param purchaseBillingTransaction
	 * @return purchaseBillingTransaction
	 */
	@RequestMapping(value = "/updatePurchaseBillingTransaction")
	public @ResponseBody JSONObject updatePurchaseBillingTransaction(@RequestBody JSONObject purchaseBillingTransaction) {
		return purchaseBillingTransactionService.updatePurchaseBillingTransaction(purchaseBillingTransaction);
	}

	/**
	 * Delete purchaseBillingTransaction.
	 * 
	 * @param purchaseBillingTransaction
	 * @return purchaseBillingTransaction
	 */
	@RequestMapping(value = "/deletePurchaseBillingTransaction", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deletePurchaseBillingTransaction(@RequestBody JSONObject purchaseBillingTransactionId) {
		return purchaseBillingTransactionService.deletePurchaseBillingTransaction(purchaseBillingTransactionId);
	} 
	
	@RequestMapping(value = "/listByTransactionIdAndLastDate")
	public @ResponseBody JSONObject listByTransactionIdAndLastDate(@RequestBody JSONObject purchaseBillingId) {
		return purchaseBillingTransactionService.listByTransactionIdAndLastDate(purchaseBillingId);
	}
}
