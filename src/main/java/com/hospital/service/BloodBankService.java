/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

import com.hospital.model.BloodBank;

/**
 * @author Krishna
 *
 */
public interface BloodBankService {

	/**
	 * Create BloodBank.
	 * 
	 * @param bloodBank
	 * @return bloodBank
	 */
	public JSONObject addBloodBank(JSONObject bloodBank);

	/**
	 * List BloodBank.
	 * 
	 * @param bloodBank
	 * @return bloodBank
	 */
	public JSONObject listBloodBank();

	/**
	 * Update BloodBank.
	 * 
	 * @param bloodBank
	 * @return bloodBank
	 */
	public JSONObject updateBloodBank(JSONObject bloodBank);

	/**
	 * Delete BloodBank.
	 * 
	 * @param bloodBank
	 * @return bloodBank
	 */
	public JSONObject deleteBloodBank(JSONObject bloodBankId);

	/**
	 * @param bloodBank
	 */
	public BloodBank updateBloodBankById(BloodBank bloodBank);

	/**
	 * @param bloodBankId
	 */
	public Integer deleteBloodBankById(Integer bloodBankId);

}
