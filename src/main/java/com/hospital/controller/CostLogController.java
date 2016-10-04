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

import com.hospital.service.CostLogService;

/**
 * @author Krishna
 *
 */
@Controller
public class CostLogController {

	/**
	 * CostLog Service.
	 */
	@Autowired
	private CostLogService costLogService;

	/**
	 * Create CostLog.
	 * 
	 * @param costLog
	 * @return costLog
	 */
	@RequestMapping(value = "/addCostLog", method = RequestMethod.POST)
	public @ResponseBody JSONObject addCostLog(@RequestBody JSONObject costLog) {
		return costLogService.addCostLog(costLog);
	}

	/**
	 * List costLog.
	 * 
	 * @param costLog
	 * @return costLog
	 */
	@RequestMapping(value = "/listCostLog")
	public @ResponseBody JSONObject listCostLog() {
		return costLogService.listCostLog();
	}
	
	/**
	 * Update CostLog.
	 * 
	 * @param costLog
	 * @return costLog
	 */
	@RequestMapping(value = "/updateCostLog")
	public @ResponseBody JSONObject updateCostLog(@RequestBody JSONObject costLog) {
		return costLogService.updateCostLog(costLog);
	}

	/**
	 * Delete costLog.
	 * 
	 * @param costLog
	 * @return costLog
	 */
	@RequestMapping(value = "/deleteCostLog", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteCostLog(@RequestBody JSONObject costLogId) {
		return costLogService.deleteCostLog(costLogId);
	}
}
