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

	public JSONObject getDoctorAvaliablities(JSONObject doctor);
	
	public JSONObject getDoctorAppointmentForCurrentDate(JSONObject doctorDetails);
	
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
