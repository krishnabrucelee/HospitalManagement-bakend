/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface BioMedicalWasteService {

	/**
	 * Create BioMedicalWaste.
	 * 
	 * @param bioMedicalWaste
	 * @return bioMedicalWaste
	 */
	public JSONObject addBioMedicalWaste(JSONObject bioMedicalWaste);

	/**
	 * List BioMedicalWaste.
	 * 
	 * @param bioMedicalWaste
	 * @return bioMedicalWaste
	 */
	public JSONObject listBioMedicalWaste();

	/**
	 * Update BioMedicalWaste.
	 * 
	 * @param bioMedicalWaste
	 * @return bioMedicalWaste
	 */
	public JSONObject updateBioMedicalWaste(JSONObject bioMedicalWaste);

	/**
	 * Delete BioMedicalWaste.
	 * 
	 * @param bioMedicalWaste
	 * @return bioMedicalWaste
	 */
	public JSONObject deleteBioMedicalWaste(JSONObject bioMedicalWasteId);

}
