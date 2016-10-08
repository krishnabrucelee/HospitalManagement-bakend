/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface DietService {

	/**
	 * Create Diet.
	 * 
	 * @param diet
	 * @return diet
	 */
	public JSONObject addDiet(JSONObject diet);

	/**
	 * List Diet.
	 * 
	 * @param diet
	 * @return diet
	 */
	public JSONObject listDiet();

	/**
	 * Update Diet.
	 * 
	 * @param diet
	 * @return diet
	 */
	public JSONObject updateDiet(JSONObject diet);

	/**
	 * Delete Diet.
	 * 
	 * @param diet
	 * @return diet
	 */
	public JSONObject deleteDiet(JSONObject dietId);

	/**
	 * list diet by Patient Id.
	 * 
	 * @param diet
	 * @return diet
	 */
	public JSONObject listByPatientId(JSONObject patientId);

}
