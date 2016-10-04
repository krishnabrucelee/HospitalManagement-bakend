/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface PaymentService {

	/**
	 * Create Payment.
	 * 
	 * @param payment
	 * @return payment
	 */
	public JSONObject addPayment(JSONObject payment);

	/**
	 * List Payment.
	 * 
	 * @param payment
	 * @return payment
	 */
	public JSONObject listPayment();

	/**
	 * Update Payment.
	 * 
	 * @param payment
	 * @return payment
	 */
	public JSONObject updatePayment(JSONObject payment);

	/**
	 * Delete Payment.
	 * 
	 * @param payment
	 * @return payment
	 */
	public JSONObject deletePayment(JSONObject paymentId);

}
