/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface CssdService {

	/**
	 * Create Cssd.
	 * 
	 * @param cssd
	 * @return cssd
	 */
	public JSONObject addCssd(JSONObject cssd);

	/**
	 * List Cssd.
	 * 
	 * @param cssd
	 * @return cssd
	 */
	public JSONObject listCssd();

	/**
	 * Update Cssd.
	 * 
	 * @param cssd
	 * @return cssd
	 */
	public JSONObject updateCssd(JSONObject cssd);

	/**
	 * Delete Cssd.
	 * 
	 * @param cssd
	 * @return cssd
	 */
	public JSONObject deleteCssd(JSONObject cssdId);

}
