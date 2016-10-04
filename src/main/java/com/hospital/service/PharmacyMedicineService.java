/**
 * 
 */
package com.hospital.service;

import java.io.IOException;

import org.json.JSONException;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * @author Krishna
 *
 */
public interface PharmacyMedicineService {

	/**
	 * Create PharmacyMedicine.
	 * 
	 * @param pharmacyMedicine
	 * @return pharmacyMedicine
	 */
	public JSONObject addPharmacyMedicine(JSONObject pharmacyMedicine) throws JsonParseException, JsonMappingException, IOException, JSONException;

	/**
	 * List PharmacyMedicine.
	 * 
	 * @param pharmacyMedicine
	 * @return pharmacyMedicine
	 */
	public JSONObject listPharmacyMedicine();

	/**
	 * Update PharmacyMedicine.
	 * 
	 * @param pharmacyMedicine
	 * @return pharmacyMedicine
	 */
	public JSONObject updatePharmacyMedicine(JSONObject pharmacyMedicine);

	/**
	 * Delete PharmacyMedicine.
	 * 
	 * @param pharmacyMedicine
	 * @return pharmacyMedicine
	 */
	public JSONObject deletePharmacyMedicine(JSONObject pharmacyMedicineId);

	/**
	 * @param patientId
	 * @return
	 */
	public JSONObject listByPatientId(JSONObject patientId);

}
