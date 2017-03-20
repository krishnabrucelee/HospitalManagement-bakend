/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface BillingService {

	/**
	 * Create Billing.
	 * 
	 * @param billing
	 * @return billing
	 */
	public JSONObject addBilling(JSONObject billing);

	/**
	 * List Billing.
	 * 
	 * @param billing
	 * @return billing
	 */
	public JSONObject listBilling();

	/**
	 * Update Billing.
	 * 
	 * @param billing
	 * @return billing
	 */
	public JSONObject updateBilling(JSONObject billing);

	/**
	 * Delete Billing.
	 * 
	 * @param billing
	 * @return billing
	 */
	public JSONObject deleteBilling(JSONObject billingId);

}
