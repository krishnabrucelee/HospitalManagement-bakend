/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface PurchaseBillingService {

	/**
	 * Create PurchaseBilling.
	 * 
	 * @param purchaseBilling
	 * @return purchaseBilling
	 */
	public JSONObject addPurchaseBilling(JSONObject purchaseBilling);

	/**
	 * List PurchaseBilling.
	 * 
	 * @param purchaseBilling
	 * @return purchaseBilling
	 */
	public JSONObject listPurchaseBilling();

	/**
	 * Update PurchaseBilling.
	 * 
	 * @param purchaseBilling
	 * @return purchaseBilling
	 */
	public JSONObject updatePurchaseBilling(JSONObject purchaseBilling);

	/**
	 * Delete PurchaseBilling.
	 * 
	 * @param purchaseBilling
	 * @return purchaseBilling
	 */
	public JSONObject deletePurchaseBilling(JSONObject purchaseBillingId);

	/**
	 * @param patient
	 * @return
	 */
	public JSONObject getPurchaseBilling(JSONObject patient);

}
