/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface BloodBankCampService {

	/**
	 * Create BloodBankCamp.
	 * 
	 * @param bloodBankCamp
	 * @return bloodBankCamp
	 */
	public JSONObject addBloodBankCamp(JSONObject bloodBankCamp);

	/**
	 * List BloodBankCamp.
	 * 
	 * @param bloodBankCamp
	 * @return bloodBankCamp
	 */
	public JSONObject listBloodBankCamp();

	/**
	 * Update BloodBankCamp.
	 * 
	 * @param bloodBankCamp
	 * @return bloodBankCamp
	 */
	public JSONObject updateBloodBankCamp(JSONObject bloodBankCamp);

	/**
	 * Delete BloodBankCamp.
	 * 
	 * @param bloodBankCamp
	 * @return bloodBankCamp
	 */
	public JSONObject deleteBloodBankCamp(JSONObject bloodBankCampId);

}
