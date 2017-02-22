/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface WaitingListService {

	/**
	 * Create WaitingList.
	 * 
	 * @param waitingList
	 * @return waitingList
	 */
	public JSONObject addWaitingList(JSONObject waitingList);

	/**
	 * List WaitingList.
	 * 
	 * @param waitingList
	 * @return waitingList
	 */
	public JSONObject listWaitingList();

	/**
	 * Update WaitingList.
	 * 
	 * @param waitingList
	 * @return waitingList
	 */
	public JSONObject updateWaitingList(JSONObject waitingList);

	/**
	 * Delete WaitingList.
	 * 
	 * @param waitingList
	 * @return waitingList
	 */
	public JSONObject deleteWaitingList(JSONObject waitingListId);

}
