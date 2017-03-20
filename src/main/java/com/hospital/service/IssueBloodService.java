/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

import com.hospital.model.IssueBlood;

/**
 * @author Krishna
 *
 */
public interface IssueBloodService {

	/**
	 * Create IssueBlood.
	 * 
	 * @param issueBlood
	 * @return issueBlood
	 */
	public JSONObject addIssueBlood(JSONObject issueBlood);

	/**
	 * List IssueBlood.
	 * 
	 * @param issueBlood
	 * @return issueBlood
	 */
	public JSONObject listIssueBlood();

	/**
	 * Update IssueBlood.
	 * 
	 * @param issueBlood
	 * @return issueBlood
	 */
	public JSONObject updateIssueBlood(JSONObject issueBlood);

	/**
	 * Delete IssueBlood.
	 * 
	 * @param issueBlood
	 * @return issueBlood
	 */
	public JSONObject deleteIssueBlood(JSONObject issueBloodId);

	/**
	 * @param issueBlood
	 */
	public IssueBlood updateIssueBloodById(IssueBlood issueBlood);

	/**
	 * @param issueBloodId
	 */
	public Integer deleteIssueBloodById(Integer issueBloodId);

}
