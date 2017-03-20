/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface ReceivePaymentService {

	/**
	 * Create ReceivePayment.
	 * 
	 * @param receivePayment
	 * @return receivePayment
	 */
	public JSONObject addReceivePayment(JSONObject receivePayment);

	/**
	 * List ReceivePayment.
	 * 
	 * @param receivePayment
	 * @return receivePayment
	 */
	public JSONObject listReceivePayment();

	/**
	 * Update ReceivePayment.
	 * 
	 * @param receivePayment
	 * @return receivePayment
	 */
	public JSONObject updateReceivePayment(JSONObject receivePayment);

	/**
	 * Delete ReceivePayment.
	 * 
	 * @param receivePayment
	 * @return receivePayment
	 */
	public JSONObject deleteReceivePayment(JSONObject receivePaymentId);

}
