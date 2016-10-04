/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna0
 *
 */
public interface SurgeryService {

	/**
	 * Create Surgery.
	 * 
	 * @param surgery
	 * @return surgery
	 */
	public JSONObject addSurgery(JSONObject surgery);

	/**
	 * List Surgery.
	 * 
	 * @param surgery
	 * @return surgery
	 */
	public JSONObject listSurgery();

	/**
	 * Update Surgery.
	 * 
	 * @param surgery
	 * @return surgery
	 */
	public JSONObject updateSurgery(JSONObject surgery);

	/**
	 * Delete Surgery.
	 * 
	 * @param surgery
	 * @return surgery
	 */
	public JSONObject deleteSurgery(JSONObject surgeryId);

}
