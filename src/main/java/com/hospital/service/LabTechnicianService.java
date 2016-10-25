/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

import com.hospital.model.LabTechnician;

/**
 * @author Krishna
 *
 */
public interface LabTechnicianService {

	/**
	 * Create LabTechnician.
	 * 
	 * @param labTechnician
	 * @return labTechnician
	 */
	public JSONObject addLabTechnician(JSONObject labTechnician);

	/**
	 * List LabTechnician.
	 * 
	 * @param labTechnician
	 * @return labTechnician
	 */
	public JSONObject listLabTechnician();

	/**
	 * Update LabTechnician.
	 * 
	 * @param labTechnician
	 * @return labTechnician
	 */
	public JSONObject updateLabTechnician(JSONObject labTechnician);

	/**
	 * Delete LabTechnician.
	 * 
	 * @param labTechnician
	 * @return labTechnician
	 */
	public JSONObject deleteLabTechnician(JSONObject labTechnicianId);

	/**
	 * @param labTech
	 * @return
	 */
	public LabTechnician addLabTechnicianFromStaff(LabTechnician labTech);

}
