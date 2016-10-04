/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface StockLedgerService {

	/**
	 * Create StockLedger.
	 * 
	 * @param stockLedger
	 * @return stockLedger
	 */
	public JSONObject addStockLedger(JSONObject stockLedger);

	/**
	 * List StockLedger.
	 * 
	 * @param stockLedger
	 * @return stockLedger
	 */
	public JSONObject listStockLedger();

	/**
	 * Update StockLedger.
	 * 
	 * @param stockLedger
	 * @return stockLedger
	 */
	public JSONObject updateStockLedger(JSONObject stockLedger);

	/**
	 * Delete StockLedger.
	 * 
	 * @param stockLedger
	 * @return stockLedger
	 */
	public JSONObject deleteStockLedger(JSONObject stockLedgerId);

}
