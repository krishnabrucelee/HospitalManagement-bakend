/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface DonorService {

	/**
	 * Create Donor.
	 * 
	 * @param donor
	 * @return donor
	 */
	public JSONObject addDonor(JSONObject donor);

	/**
	 * List Donor.
	 * 
	 * @param donor
	 * @return donor
	 */
	public JSONObject listDonor();

	/**
	 * Update Donor.
	 * 
	 * @param donor
	 * @return donor
	 */
	public JSONObject updateDonor(JSONObject donor);

	/**
	 * Delete Donor.
	 * 
	 * @param donor
	 * @return donor
	 */
	public JSONObject deleteDonor(JSONObject donorId);

}
