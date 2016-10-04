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

import com.hospital.service.CostService;

/**
 * @author Krishna
 *
 */
@Controller
public class CostController {

	/**
	 * Cost Service.
	 */
	@Autowired
	private CostService costService;

	/**
	 * Create Cost.
	 * 
	 * @param cost
	 * @return cost
	 */
	@RequestMapping(value = "/addCost", method = RequestMethod.POST)
	public @ResponseBody JSONObject addCost(@RequestBody JSONObject cost) {
		return costService.addCost(cost);
	}

	/**
	 * List cost.
	 * 
	 * @param cost
	 * @return cost
	 */
	@RequestMapping(value = "/listCost")
	public @ResponseBody JSONObject listCost() {
		return costService.listCost();
	}
	
	/**
	 * Update Cost.
	 * 
	 * @param cost
	 * @return cost
	 */
	@RequestMapping(value = "/updateCost")
	public @ResponseBody JSONObject updateCost(@RequestBody JSONObject cost) {
		return costService.updateCost(cost);
	}

	/**
	 * Delete cost.
	 * 
	 * @param cost
	 * @return cost
	 */
	@RequestMapping(value = "/deleteCost", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteCost(@RequestBody JSONObject costId) {
		return costService.deleteCost(costId);
	}
}
