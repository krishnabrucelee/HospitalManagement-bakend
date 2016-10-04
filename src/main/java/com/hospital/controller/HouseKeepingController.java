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

import com.hospital.service.HouseKeepingService;

/**
 * @author Krishna
 *
 */
@Controller
public class HouseKeepingController {

	/**
	 * HouseKeeping Service.
	 */
	@Autowired
	private HouseKeepingService houseKeepingService;

	/**
	 * Create HouseKeeping.
	 * 
	 * @param houseKeeping
	 * @return houseKeeping
	 */
	@RequestMapping(value = "/addHouseKeeping", method = RequestMethod.POST)
	public @ResponseBody JSONObject addHouseKeeping(@RequestBody JSONObject houseKeeping) {
		return houseKeepingService.addHouseKeeping(houseKeeping);
	}

	/**
	 * List houseKeeping.
	 * 
	 * @param houseKeeping
	 * @return houseKeeping
	 */
	@RequestMapping(value = "/listHouseKeeping")
	public @ResponseBody JSONObject listHouseKeeping() {
		return houseKeepingService.listHouseKeeping();
	}
	
	/**
	 * Update HouseKeeping.
	 * 
	 * @param houseKeeping
	 * @return houseKeeping
	 */
	@RequestMapping(value = "/updateHouseKeeping")
	public @ResponseBody JSONObject updateHouseKeeping(@RequestBody JSONObject houseKeeping) {
		return houseKeepingService.updateHouseKeeping(houseKeeping);
	}

	/**
	 * Delete houseKeeping.
	 * 
	 * @param houseKeeping
	 * @return houseKeeping
	 */
	@RequestMapping(value = "/deleteHouseKeeping", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteHouseKeeping(@RequestBody JSONObject houseKeepingId) {
		return houseKeepingService.deleteHouseKeeping(houseKeepingId);
	}
}
