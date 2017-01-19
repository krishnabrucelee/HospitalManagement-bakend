/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

import com.hospital.model.Doctor;

/**
 * @author Krishna
 *
 */
public interface DoctorService {

	/**
	 * Create Doctor.
	 * 
	 * @param doctor
	 * @return doctor
	 */
	public JSONObject addDoctor(JSONObject doctor);

	/**
	 * List Doctor.
	 * 
	 * @param doctor
	 * @return doctor
	 */
	public JSONObject listDoctor();

	/**
	 * Update Doctor.
	 * 
	 * @param doctor
	 * @return doctor
	 */
	public JSONObject updateDoctor(JSONObject doctor);

	/**
	 * Delete Doctor.
	 * 
	 * @param doctor
	 * @return doctor
	 */
	public JSONObject deleteDoctor(JSONObject doctorId);

	/**
	 * @param doctor
	 * @return
	 */
	public Doctor addDoctorFromStaff(Doctor doctor);

	/**
	 * @param doctorEmail
	 * @return
	 */
	public JSONObject getDoctorByEmail(JSONObject doctorEmail);
}
