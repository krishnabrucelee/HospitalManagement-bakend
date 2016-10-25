/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

import com.hospital.model.Pharmacist;

/**
 * @author Krishna
 *
 */
public interface PharmacistService {

	/**
	 * Create Pharmacist.
	 * 
	 * @param pharmacist
	 * @return pharmacist
	 */
	public JSONObject addPharmacist(JSONObject pharmacist);

	/**
	 * List Pharmacist.
	 * 
	 * @param pharmacist
	 * @return pharmacist
	 */
	public JSONObject listPharmacist();

	/**
	 * Update Pharmacist.
	 * 
	 * @param pharmacist
	 * @return pharmacist
	 */
	public JSONObject updatePharmacist(JSONObject pharmacist);

	/**
	 * Delete Pharmacist.
	 * 
	 * @param pharmacist
	 * @return pharmacist
	 */
	public JSONObject deletePharmacist(JSONObject pharmacistId);

	/**
	 * @param pharmacist
	 */
	public Pharmacist addPharmacistFromStaff(Pharmacist pharmacist);

}
