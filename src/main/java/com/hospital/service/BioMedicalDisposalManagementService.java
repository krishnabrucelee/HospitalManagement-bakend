/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface BioMedicalDisposalManagementService {

	/**
	 * Create BioMedicalDisposalManagement.
	 * 
	 * @param bioMedicalDisposalManagement
	 * @return bioMedicalDisposalManagement
	 */
	public JSONObject addBioMedicalDisposalManagement(JSONObject bioMedicalDisposalManagement);

	/**
	 * List BioMedicalDisposalManagement.
	 * 
	 * @param bioMedicalDisposalManagement
	 * @return bioMedicalDisposalManagement
	 */
	public JSONObject listBioMedicalDisposalManagement();

	/**
	 * Update BioMedicalDisposalManagement.
	 * 
	 * @param bioMedicalDisposalManagement
	 * @return bioMedicalDisposalManagement
	 */
	public JSONObject updateBioMedicalDisposalManagement(JSONObject bioMedicalDisposalManagement);

	/**
	 * Delete BioMedicalDisposalManagement.
	 * 
	 * @param bioMedicalDisposalManagement
	 * @return bioMedicalDisposalManagement
	 */
	public JSONObject deleteBioMedicalDisposalManagement(JSONObject bioMedicalDisposalManagementId);

}
