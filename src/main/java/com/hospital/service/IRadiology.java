package com.hospital.service;
import org.json.simple.JSONObject;

/**
 * @author admin
 *
 */
public interface IRadiology {
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
	 * Save  Patient radiology request.
	 * 
	 * @param radiology
	 *
	 */
	public JSONObject savePatientRequestRadiologyTest(JSONObject parientradiology);
	/**
	 * List Patient radiology request.
	 * 
	 * 
	 * @return Patient radiology request
	 */
	public JSONObject listPatientRequestRadiologyTest();

	/**
	 * Update Patient radiology request.
	 * 
	 * @param Patient Radiology request
	 *
	 */
	public JSONObject updatePatientRequestRadiologyTest(JSONObject radiologyupdate);

	/**
	 * Delete Patient radiology request.
	 * 
	 *@param Radiology request 
	 *
	 */
	public JSONObject deletePatientRequestRadiologyTest(JSONObject radiology);
	/**
	 * Get Patient radiology request.
	 * 
	 *@param Radiology request 
	 *@return Patient radiology request one row
	 */
	public JSONObject getPatientRequestRadiologyTesById(JSONObject radiologyrequestid);
	
	/**
	 * Create Radiology report.
	 * 
	 * @param radiologyreport
	 *
	 */
	public JSONObject saveRadiologyTestReport(JSONObject radiologyreport);
	/**
	 * List Radiology report.
	 * 
	 * 
	 * @return Radiologyreport
	 */
	public JSONObject listRadiologyTestReport();

	/**
	 * Update Radiology test Name.
	 * 
	 * @param Radiology reportupdate
	 * @return Radiology
	 */
	public JSONObject updateRadiologyTestReport(JSONObject reportupdate);

	/**
	 * Delete Radiology.
	 * 
	 *@param Radiology reportdelete
	 * 
	 */
	public JSONObject deleteRadiologyTestReport(JSONObject reportdelete);
	/**
	 * Get Patient radiology request.
	 * 
	 *@param Radiology report 
	 *@return Patient radiology report one row
	 */
	public JSONObject getRadiologyTestReportById(JSONObject radiologyreportid);
}
