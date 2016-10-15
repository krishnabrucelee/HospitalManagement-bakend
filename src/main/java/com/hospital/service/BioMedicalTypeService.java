/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface BioMedicalTypeService {

	/**
	 * Create BioMedicalType.
	 * 
	 * @param bioMedicalType
	 * @return bioMedicalType
	 */
	public JSONObject addBioMedicalType(JSONObject bioMedicalType);

	/**
	 * List BioMedicalType.
	 * 
	 * @param bioMedicalType
	 * @return bioMedicalType
	 */
	public JSONObject listBioMedicalType();

	/**
	 * Update BioMedicalType.
	 * 
	 * @param bioMedicalType
	 * @return bioMedicalType
	 */
	public JSONObject updateBioMedicalType(JSONObject bioMedicalType);

	/**
	 * Delete BioMedicalType.
	 * 
	 * @param bioMedicalType
	 * @return bioMedicalType
	 */
	public JSONObject deleteBioMedicalType(JSONObject bioMedicalTypeId);

}
