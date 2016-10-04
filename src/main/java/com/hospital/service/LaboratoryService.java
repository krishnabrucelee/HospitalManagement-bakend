/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface LaboratoryService {

	/**
	 * Create Laboratory.
	 * 
	 * @param laboratory
	 * @return laboratory
	 */
	public JSONObject addLaboratory(JSONObject laboratory);

	/**
	 * List Laboratory.
	 * 
	 * @param laboratory
	 * @return laboratory
	 */
	public JSONObject listLaboratory();

	/**
	 * Update Laboratory.
	 * 
	 * @param laboratory
	 * @return laboratory
	 */
	public JSONObject updateLaboratory(JSONObject laboratory);

	/**
	 * Delete Laboratory.
	 * 
	 * @param laboratory
	 * @return laboratory
	 */
	public JSONObject deleteLaboratory(JSONObject laboratoryId);

	/**
	 * @param patientId
	 * @return
	 */
	public JSONObject listByPatientId(JSONObject patientId);

}
