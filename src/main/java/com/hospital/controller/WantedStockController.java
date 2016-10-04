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

import com.hospital.service.WantedStockService;

/**
 * @author Krishna
 *
 */
@Controller
public class WantedStockController {

	/**
	 * WantedStock Service.
	 */
	@Autowired
	private WantedStockService wantedStockService;

	/**
	 * Create WantedStock.
	 * 
	 * @param wantedStock
	 * @return wantedStock
	 */
	@RequestMapping(value = "/addWantedStock", method = RequestMethod.POST)
	public @ResponseBody JSONObject addWantedStock(@RequestBody JSONObject wantedStock) {
		return wantedStockService.addWantedStock(wantedStock);
	}

	/**
	 * List wantedStock.
	 * 
	 * @param wantedStock
	 * @return wantedStock
	 */
	@RequestMapping(value = "/listWantedStock")
	public @ResponseBody JSONObject listWantedStock() {
		return wantedStockService.listWantedStock();
	}
	
	/**
	 * Update WantedStock.
	 * 
	 * @param wantedStock
	 * @return wantedStock
	 */
	@RequestMapping(value = "/updateWantedStock")
	public @ResponseBody JSONObject updateWantedStock(@RequestBody JSONObject wantedStock) {
		return wantedStockService.updateWantedStock(wantedStock);
	}

	/**
	 * Delete wantedStock.
	 * 
	 * @param wantedStock
	 * @return wantedStock
	 */
	@RequestMapping(value = "/deleteWantedStock", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteWantedStock(@RequestBody JSONObject wantedStockId) {
		return wantedStockService.deleteWantedStock(wantedStockId);
	}
}
