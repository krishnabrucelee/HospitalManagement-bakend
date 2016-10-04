/**
 * 
 */
package com.hospital.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.service.AppointmentService;

/**
 * @author Krishna
 *
 */
@Controller
public class AppointmentController {

	/**
	 * Appointment Service.
	 */
	@Autowired
	private AppointmentService appointmentService;

	/**
	 * Create Appointment.
	 * 
	 * @param appointment
	 * @return appointment
	 */
	@RequestMapping(value = "/addAppointment", method = RequestMethod.POST)
	public @ResponseBody JSONObject addAppointment(@RequestBody JSONObject appointment) {
		return appointmentService.addAppointment(appointment);
	}

	/**
	 * List appointment.
	 * 
	 * @param appointment
	 * @return appointment
	 */
	@RequestMapping(value = "/listAppointment")
	public @ResponseBody JSONObject listAppointment() {
		return appointmentService.listAppointment();
	}
	
	/**
	 * Update Appointment.
	 * 
	 * @param appointment
	 * @return appointment
	 */
	@RequestMapping(value = "/updateAppointment")
	public @ResponseBody JSONObject updateAppointment(@RequestBody JSONObject appointment) {
		return appointmentService.updateAppointment(appointment);
	}

	/**
	 * Delete appointment.
	 * 
	 * @param appointment
	 * @return appointment
	 */
	@RequestMapping(value = "/deleteAppointment", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteAppointment(@RequestBody JSONObject appointmentId) {
		return appointmentService.deleteAppointment(appointmentId);
	}
}
