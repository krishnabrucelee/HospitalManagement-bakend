/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface WantedStockService {

	/**
	 * Create WantedStock.
	 * 
	 * @param wantedStock
	 * @return wantedStock
	 */
	public JSONObject addWantedStock(JSONObject wantedStock);

	/**
	 * List WantedStock.
	 * 
	 * @param wantedStock
	 * @return wantedStock
	 */
	public JSONObject listWantedStock();

	/**
	 * Update WantedStock.
	 * 
	 * @param wantedStock
	 * @return wantedStock
	 */
	public JSONObject updateWantedStock(JSONObject wantedStock);

	/**
	 * Delete WantedStock.
	 * 
	 * @param wantedStock
	 * @return wantedStock
	 */
	public JSONObject deleteWantedStock(JSONObject wantedStockId);

}
