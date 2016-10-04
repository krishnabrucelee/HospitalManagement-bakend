/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface AppointmentService {

	/**
	 * Create Appointment.
	 * 
	 * @param appointment
	 * @return appointment
	 */
	public JSONObject addAppointment(JSONObject appointment);

	/**
	 * List Appointment.
	 * 
	 * @param appointment
	 * @return appointment
	 */
	public JSONObject listAppointment();

	/**
	 * Update Appointment.
	 * 
	 * @param appointment
	 * @return appointment
	 */
	public JSONObject updateAppointment(JSONObject appointment);

	/**
	 * Delete Appointment.
	 * 
	 * @param appointment
	 * @return appointment
	 */
	public JSONObject deleteAppointment(JSONObject appointmentId);

}
