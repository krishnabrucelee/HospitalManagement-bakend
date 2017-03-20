/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface PurchaseBillingTransactionService {

	/**
	 * Create PurchaseBillingTransaction.
	 * 
	 * @param purchaseBillingTransaction
	 * @return purchaseBillingTransaction
	 */
	public JSONObject addPurchaseBillingTransaction(JSONObject purchaseBillingTransaction);

	/**
	 * List PurchaseBillingTransaction.
	 * 
	 * @param purchaseBillingTransaction
	 * @return purchaseBillingTransaction
	 */
	public JSONObject listPurchaseBillingTransaction();

	/**
	 * Update PurchaseBillingTransaction.
	 * 
	 * @param purchaseBillingTransaction
	 * @return purchaseBillingTransaction
	 */
	public JSONObject updatePurchaseBillingTransaction(JSONObject purchaseBillingTransaction);

	/**
	 * Delete PurchaseBillingTransaction.
	 * 
	 * @param purchaseBillingTransaction
	 * @return purchaseBillingTransaction
	 */
	public JSONObject deletePurchaseBillingTransaction(JSONObject purchaseBillingTransactionId);

	/**
	 * @param purchaseBillingId
	 * @return
	 */
	public JSONObject listByTransactionIdAndLastDate(JSONObject purchaseBillingId);

}
