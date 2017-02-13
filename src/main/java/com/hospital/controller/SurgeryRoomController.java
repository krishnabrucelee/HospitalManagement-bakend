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

import com.hospital.service.SurgeryRoomService;

/**
 * @author Krishna
 *
 */
@Controller
public class SurgeryRoomController {

	/**
	 * SurgeryRoom Service.
	 */
	@Autowired
	private SurgeryRoomService surgeryRoomService;

	/**
	 * Create SurgeryRoom.
	 * 
	 * @param surgeryRoom
	 * @return surgeryRoom
	 */
	@RequestMapping(value = "/addSurgeryRoom", method = RequestMethod.POST)
	public @ResponseBody JSONObject addSurgeryRoom(@RequestBody JSONObject surgeryRoom) {
		return surgeryRoomService.addSurgeryRoom(surgeryRoom);
	}

	/**
	 * List surgeryRoom.
	 * 
	 * @param surgeryRoom
	 * @return surgeryRoom
	 */
	@RequestMapping(value = "/listSurgeryRoom")
	public @ResponseBody JSONObject listSurgeryRoom() {
		return surgeryRoomService.listSurgeryRoom();
	}
	
	/**
	 * Update SurgeryRoom.
	 * 
	 * @param surgeryRoom
	 * @return surgeryRoom
	 */
	@RequestMapping(value = "/updateSurgeryRoom")
	public @ResponseBody JSONObject updateSurgeryRoom(@RequestBody JSONObject surgeryRoom) {
		return surgeryRoomService.updateSurgeryRoom(surgeryRoom);
	}

	/**
	 * Delete surgeryRoom.
	 * 
	 * @param surgeryRoom
	 * @return surgeryRoom
	 */
	@RequestMapping(value = "/deleteSurgeryRoom", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteSurgeryRoom(@RequestBody JSONObject surgeryRoomId) {
		return surgeryRoomService.deleteSurgeryRoom(surgeryRoomId);
	}
}
