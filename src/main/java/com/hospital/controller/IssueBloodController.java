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

import com.hospital.service.IssueBloodService;

/**
 * @author Krishna
 *
 */
@Controller
public class IssueBloodController {

	/**
	 * IssueBlood Service.
	 */
	@Autowired
	private IssueBloodService issueBloodService;

	/**
	 * Create IssueBlood.
	 * 
	 * @param issueBlood
	 * @return issueBlood
	 */
	@RequestMapping(value = "/addIssueBlood", method = RequestMethod.POST)
	public @ResponseBody JSONObject addIssueBlood(@RequestBody JSONObject issueBlood) {
		return issueBloodService.addIssueBlood(issueBlood);
	}

	/**
	 * List issueBlood.
	 * 
	 * @param issueBlood
	 * @return issueBlood
	 */
	@RequestMapping(value = "/listIssueBlood")
	public @ResponseBody JSONObject listIssueBlood() {
		return issueBloodService.listIssueBlood();
	}
	
	/**
	 * Update IssueBlood.
	 * 
	 * @param issueBlood
	 * @return issueBlood
	 */
	@RequestMapping(value = "/updateIssueBlood")
	public @ResponseBody JSONObject updateIssueBlood(@RequestBody JSONObject issueBlood) {
		return issueBloodService.updateIssueBlood(issueBlood);
	}

	/**
	 * Delete issueBlood.
	 * 
	 * @param issueBlood
	 * @return issueBlood
	 */
	@RequestMapping(value = "/deleteIssueBlood", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteIssueBlood(@RequestBody JSONObject issueBloodId) {
		return issueBloodService.deleteIssueBlood(issueBloodId);
	}
}
