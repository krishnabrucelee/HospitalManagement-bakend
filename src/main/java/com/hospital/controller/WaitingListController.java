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

import com.hospital.service.WaitingListService;

/**
 * @author Krishna
 *
 */
@Controller
public class WaitingListController {

	/**
	 * WaitingList Service.
	 */
	@Autowired
	private WaitingListService waitingListService;

	/**
	 * Create WaitingList.
	 * 
	 * @param waitingList
	 * @return waitingList
	 */
	@RequestMapping(value = "/addWaitingList", method = RequestMethod.POST)
	public @ResponseBody JSONObject addWaitingList(@RequestBody JSONObject waitingList) {
		return waitingListService.addWaitingList(waitingList);
	}

	/**
	 * List waitingList.
	 * 
	 * @param waitingList
	 * @return waitingList
	 */
	@RequestMapping(value = "/listWaitingList")
	public @ResponseBody JSONObject listWaitingList() {
		return waitingListService.listWaitingList();
	}
	
	/**
	 * Update WaitingList.
	 * 
	 * @param waitingList
	 * @return waitingList
	 */
	@RequestMapping(value = "/updateWaitingList")
	public @ResponseBody JSONObject updateWaitingList(@RequestBody JSONObject waitingList) {
		return waitingListService.updateWaitingList(waitingList);
	}

	/**
	 * Delete waitingList.
	 * 
	 * @param waitingList
	 * @return waitingList
	 */
	@RequestMapping(value = "/deleteWaitingList", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteWaitingList(@RequestBody JSONObject waitingListId) {
		return waitingListService.deleteWaitingList(waitingListId);
	}
}
