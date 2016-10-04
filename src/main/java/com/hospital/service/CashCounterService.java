/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface CashCounterService {

	/**
	 * Create CashCounter.
	 * 
	 * @param cashCounter
	 * @return cashCounter
	 */
	public JSONObject addCashCounter(JSONObject cashCounter);

	/**
	 * List CashCounter.
	 * 
	 * @param cashCounter
	 * @return cashCounter
	 */
	public JSONObject listCashCounter();

	/**
	 * Update CashCounter.
	 * 
	 * @param cashCounter
	 * @return cashCounter
	 */
	public JSONObject updateCashCounter(JSONObject cashCounter);

	/**
	 * Delete CashCounter.
	 * 
	 * @param cashCounter
	 * @return cashCounter
	 */
	public JSONObject deleteCashCounter(JSONObject cashCounterId);

}
