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

import com.hospital.service.StockLedgerService;

/**
 * @author Krishna
 *
 */
@Controller
public class StockLedgerController {

	/**
	 * StockLedger Service.
	 */
	@Autowired
	private StockLedgerService stockLedgerService;

	/**
	 * Create StockLedger.
	 * 
	 * @param stockLedger
	 * @return stockLedger
	 */
	@RequestMapping(value = "/addStockLedger", method = RequestMethod.POST)
	public @ResponseBody JSONObject addStockLedger(@RequestBody JSONObject stockLedger) {
		return stockLedgerService.addStockLedger(stockLedger);
	}

	/**
	 * List stockLedger.
	 * 
	 * @param stockLedger
	 * @return stockLedger
	 */
	@RequestMapping(value = "/listStockLedger")
	public @ResponseBody JSONObject listStockLedger() {
		return stockLedgerService.listStockLedger();
	}
	
	/**
	 * Update StockLedger.
	 * 
	 * @param stockLedger
	 * @return stockLedger
	 */
	@RequestMapping(value = "/updateStockLedger")
	public @ResponseBody JSONObject updateStockLedger(@RequestBody JSONObject stockLedger) {
		return stockLedgerService.updateStockLedger(stockLedger);
	}

	/**
	 * Delete stockLedger.
	 * 
	 * @param stockLedger
	 * @return stockLedger
	 */
	@RequestMapping(value = "/deleteStockLedger", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteStockLedger(@RequestBody JSONObject stockLedgerId) {
		return stockLedgerService.deleteStockLedger(stockLedgerId);
	}
}
