/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

import com.hospital.model.Doctor;
import com.hospital.model.Nurse;

/**
 * @author Krishna
 *
 */
public interface NurseService {

	/**
	 * Create Nurse.
	 * 
	 * @param nurse
	 * @return nurse
	 */
	public JSONObject addNurse(JSONObject nurse);

	/**
	 * List Nurse.
	 * 
	 * @param nurse
	 * @return nurse
	 */
	public JSONObject listNurse();

	/**
	 * Update Nurse.
	 * 
	 * @param nurse
	 * @return nurse
	 */
	public JSONObject updateNurse(JSONObject nurse);

	/**
	 * Delete Nurse.
	 * 
	 * @param nurse
	 * @return nurse
	 */
	public JSONObject deleteNurse(JSONObject nurseId);

	/**
	 * @param nurse
	 */
	public Nurse addNurseFromStaff(Nurse nurse);

}
