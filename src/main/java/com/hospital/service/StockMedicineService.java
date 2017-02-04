/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface StockMedicineService {

	/**
	 * Create StockMedicine.
	 * 
	 * @param stockMedicine
	 * @return stockMedicine
	 */
	public JSONObject addStockMedicine(JSONObject stockMedicine);

	/**
	 * List StockMedicine.
	 * 
	 * @param stockMedicine
	 * @return stockMedicine
	 */
	public JSONObject listStockMedicine();

	/**
	 * Update StockMedicine.
	 * 
	 * @param stockMedicine
	 * @return stockMedicine
	 */
	public JSONObject updateStockMedicine(JSONObject stockMedicine);

	/**
	 * Delete StockMedicine.
	 * 
	 * @param stockMedicine
	 * @return stockMedicine
	 */
	public JSONObject deleteStockMedicine(JSONObject stockMedicineId);

	/**
	 * @param stockMedicine
	 * @return
	 */
	public JSONObject adjustStockMedicine(JSONObject stockMedicine);

	/**
	 * @param stockMedicineId
	 * @return
	 */
	public JSONObject getStockMedicineById(JSONObject stockMedicineId);

}
