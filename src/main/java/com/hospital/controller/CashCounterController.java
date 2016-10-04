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

import com.hospital.service.CashCounterService;

/**
 * @author Krishna
 *
 */
@Controller
public class CashCounterController {

	/**
	 * CashCounter Service.
	 */
	@Autowired
	private CashCounterService cashCounterService;

	/**
	 * Create CashCounter.
	 * 
	 * @param cashCounter
	 * @return cashCounter
	 */
	@RequestMapping(value = "/addCashCounter", method = RequestMethod.POST)
	public @ResponseBody JSONObject addCashCounter(@RequestBody JSONObject cashCounter) {
		return cashCounterService.addCashCounter(cashCounter);
	}

	/**
	 * List cashCounter.
	 * 
	 * @param cashCounter
	 * @return cashCounter
	 */
	@RequestMapping(value = "/listCashCounter")
	public @ResponseBody JSONObject listCashCounter() {
		return cashCounterService.listCashCounter();
	}
	
	/**
	 * Update CashCounter.
	 * 
	 * @param cashCounter
	 * @return cashCounter
	 */
	@RequestMapping(value = "/updateCashCounter")
	public @ResponseBody JSONObject updateCashCounter(@RequestBody JSONObject cashCounter) {
		return cashCounterService.updateCashCounter(cashCounter);
	}

	/**
	 * Delete cashCounter.
	 * 
	 * @param cashCounter
	 * @return cashCounter
	 */
	@RequestMapping(value = "/deleteCashCounter", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteCashCounter(@RequestBody JSONObject cashCounterId) {
		return cashCounterService.deleteCashCounter(cashCounterId);
	}
}
