package com.hospital.service;

import org.json.simple.JSONObject;

public interface RadiologyService {
	
	/**
	 * Create Radiology test Name.
	 * 
	 * @param radiology
	 *
	 */
	public JSONObject addRadiologyTestName(JSONObject radiology);
	/**
	 * List Radiology test Name.
	 * 
	 * @param Radiology
	 * @return Radiology
	 */
	public JSONObject listRadiologyTest();
	
	/**
	 * Get RadiologyTestNameById.
	 * 
	 *@param RadiologyTestNameById 
	 *@return  radiology test name one row
	 */
	public JSONObject getRadiologyTestNameById(JSONObject radiologyrequestid);
	/**
	 * Update Radiology test Name.
	 * 
	 * @param Radiology
	 * @return Radiology
	 */
	public JSONObject updateRadiologyTestName(JSONObject radiology);

	/**
	 * Delete Radiology.
	 * 
	 *@param Radiology
	 * @return Radiology
	 */
	public JSONObject deleteRadiologyTest(JSONObject radiology);
	
	
	
	/**
	 * List  Patient radiology test request.
	 * 
	 * @param radiology test request.
	 *
	 */
	public JSONObject listPatientRadiologyTest();

}
