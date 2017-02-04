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

import com.hospital.service.StockMedicineService;

/**
 * @author Krishna
 *
 */
@Controller
public class StockMedicineController {

	/**
	 * StockMedicine Service.
	 */
	@Autowired
	private StockMedicineService stockMedicineService;

	/**
	 * Create StockMedicine.
	 * 
	 * @param stockMedicine
	 * @return stockMedicine
	 */
	@RequestMapping(value = "/addStockMedicine", method = RequestMethod.POST)
	public @ResponseBody JSONObject addStockMedicine(@RequestBody JSONObject stockMedicine) {
		return stockMedicineService.addStockMedicine(stockMedicine);
	}

	/**
	 * List stockMedicine.
	 * 
	 * @param stockMedicine
	 * @return stockMedicine
	 */
	@RequestMapping(value = "/listStockMedicine")
	public @ResponseBody JSONObject listStockMedicine() {
		return stockMedicineService.listStockMedicine();
	}
	
	/**
	 * Update StockMedicine.
	 * 
	 * @param stockMedicine
	 * @return stockMedicine
	 */
	@RequestMapping(value = "/updateStockMedicine")
	public @ResponseBody JSONObject updateStockMedicine(@RequestBody JSONObject stockMedicine) {
		return stockMedicineService.updateStockMedicine(stockMedicine);
	}

	/**
	 * Delete stockMedicine.
	 * 
	 * @param stockMedicine
	 * @return stockMedicine
	 */
	@RequestMapping(value = "/deleteStockMedicine", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteStockMedicine(@RequestBody JSONObject stockMedicineId) {
		return stockMedicineService.deleteStockMedicine(stockMedicineId);
	}
	
	/**
	 * Create StockMedicine.
	 * 
	 * @param stockMedicine
	 * @return stockMedicine
	 */
	@RequestMapping(value = "/adjustStockMedicine", method = RequestMethod.POST)
	public @ResponseBody JSONObject adjustStockMedicine(@RequestBody JSONObject stockMedicine) {
		return stockMedicineService.adjustStockMedicine(stockMedicine);
	}
	
	@RequestMapping(value = "/getStockMedicineById", method = RequestMethod.POST)
	public @ResponseBody JSONObject getStockMedicineById(@RequestBody JSONObject stockMedicineId) {
		return stockMedicineService.getStockMedicineById(stockMedicineId);
	}
}
