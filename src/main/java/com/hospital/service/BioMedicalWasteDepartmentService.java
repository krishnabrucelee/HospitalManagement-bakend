/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface BioMedicalWasteDepartmentService {

	/**
	 * Create BioMedicalWasteDepartment.
	 * 
	 * @param BioMedicalWasteDepartment
	 * @return BioMedicalWasteDepartment
	 */
	public JSONObject addBioMedicalWasteDepartment(JSONObject BioMedicalWasteDepartment);

	/**
	 * List BioMedicalWasteDepartment.
	 * 
	 * @param BioMedicalWasteDepartment
	 * @return BioMedicalWasteDepartment
	 */
	public JSONObject listBioMedicalWasteDepartment();

	/**
	 * Update BioMedicalWasteDepartment.
	 * 
	 * @param BioMedicalWasteDepartment
	 * @return BioMedicalWasteDepartment
	 */
	public JSONObject updateBioMedicalWasteDepartment(JSONObject BioMedicalWasteDepartment);

	/**
	 * Delete BioMedicalWasteDepartment.
	 * 
	 * @param BioMedicalWasteDepartment
	 * @return BioMedicalWasteDepartment
	 */
	public JSONObject deleteBioMedicalWasteDepartment(JSONObject BioMedicalWasteDepartmentId);

}
