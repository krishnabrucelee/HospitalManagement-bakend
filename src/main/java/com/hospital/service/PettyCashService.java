/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface PettyCashService {

	/**
	 * Create PettyCash.
	 * 
	 * @param pettyCash
	 * @return pettyCash
	 */
	public JSONObject addPettyCash(JSONObject pettyCash);

	/**
	 * List PettyCash.
	 * 
	 * @param pettyCash
	 * @return pettyCash
	 */
	public JSONObject listPettyCash();

	/**
	 * Update PettyCash.
	 * 
	 * @param pettyCash
	 * @return pettyCash
	 */
	public JSONObject updatePettyCash(JSONObject pettyCash);

	/**
	 * Delete PettyCash.
	 * 
	 * @param pettyCash
	 * @return pettyCash
	 */
	public JSONObject deletePettyCash(JSONObject pettyCashId);

}
