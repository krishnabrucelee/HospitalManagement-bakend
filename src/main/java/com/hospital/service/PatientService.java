package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * 
 * @author Krishna
 *
 */
public interface PatientService {

	/**
	 * Create Patient.
	 * 
	 * @param patient
	 * @return patient
	 */
	public JSONObject addPatient(JSONObject patient);

	/**
	 * List Patient.
	 * 
	 * @param patient
	 * @return patient
	 */
	public JSONObject listPatient();

	/**
	 * Update Patient.
	 * 
	 * @param patient
	 * @return patient
	 */
	public JSONObject updatePatient(JSONObject patient);

	/**
	 * Delete Patient.
	 * 
	 * @param patient
	 * @return patient
	 */
	public JSONObject deletePatient(JSONObject patientId);

	/**
	 * @param patient
	 * @return
	 */
	public JSONObject getPatientDetailsById(JSONObject patient);
}
