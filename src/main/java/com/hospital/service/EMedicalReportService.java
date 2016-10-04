/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface EMedicalReportService {

	/**
	 * Create EMedicalReport.
	 * 
	 * @param eMedicalReport
	 * @return eMedicalReport
	 */
	public JSONObject addEMedicalReport(JSONObject eMedicalReport);

	/**
	 * List EMedicalReport.
	 * 
	 * @param eMedicalReport
	 * @return eMedicalReport
	 */
	public JSONObject listEMedicalReport();

	/**
	 * Update EMedicalReport.
	 * 
	 * @param eMedicalReport
	 * @return eMedicalReport
	 */
	public JSONObject updateEMedicalReport(JSONObject eMedicalReport);

	/**
	 * Delete EMedicalReport.
	 * 
	 * @param eMedicalReport
	 * @return eMedicalReport
	 */
	public JSONObject deleteEMedicalReport(JSONObject eMedicalReportId);

	/**
	 * list EMR by Patient Id.
	 * 
	 * @param patientId
	 * @return
	 */
	public JSONObject listByPatientId(JSONObject patientId);

}
