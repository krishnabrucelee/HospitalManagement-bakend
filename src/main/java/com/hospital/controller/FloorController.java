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

import com.hospital.service.FloorService;

/**
 * @author Krishna
 *
 */
@Controller
public class FloorController {

	/**
	 * Floor Service.
	 */
	@Autowired
	private FloorService floorService;

	/**
	 * Create Floor.
	 * 
	 * @param Floor
	 * @return Floor
	 */
	@RequestMapping(value = "/addFloor", method = RequestMethod.POST)
	public @ResponseBody JSONObject addFloor(@RequestBody JSONObject floor) {
		return floorService.addFloor(floor);
	}

	/**
	 * List Floor.
	 * 
	 * @param Floor
	 * @return Floor
	 */
	@RequestMapping(value = "/listFloor")
	public @ResponseBody JSONObject listFloor() {
		return floorService.listFloor();
	}
	
	/**
	 * Update Floor.
	 * 
	 * @param Floor
	 * @return Floor
	 */
	@RequestMapping(value = "/updateFloor")
	public @ResponseBody JSONObject updateFloor(@RequestBody JSONObject floor) {
		return floorService.updateFloor(floor);
	}

	/**
	 * Delete Floor.
	 * 
	 * @param Floor
	 * @return Floor
	 */
	@RequestMapping(value = "/deleteFloor", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteFloor(@RequestBody JSONObject floorId) {
		return floorService.deleteFloor(floorId);
	}
	
	@RequestMapping(value = "/listRoomByFilter", method = RequestMethod.POST)
	public @ResponseBody JSONObject listRoomByFilter(@RequestBody JSONObject floor) {
		return floorService.listRoomByFilter(floor);
	}
	
	@RequestMapping(value = "/getRoomDetailsByWardNumber", method = RequestMethod.POST)
	public @ResponseBody JSONObject getRoomDetailsByWardNumber(@RequestBody JSONObject floor) {
		return floorService.getRoomDetailsByWard(floor);
	}
	
}
