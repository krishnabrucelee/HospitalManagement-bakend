/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospital.dao.AppointmentDao;
import com.hospital.service.AppointmentService;

/**
 * @author Krishna
 *
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {

	/**
	 * Appointment dao
	 */
	@Autowired
	AppointmentDao appointmentdao;
	
	@Override
	public JSONObject addAppointment(JSONObject appointment) {
		return appointmentdao.addAppointment(appointment);
	}

	@Override
	public JSONObject getDoctorAvaliablities(JSONObject doctor) {
		return appointmentdao.getDoctorAvaliablities(doctor);
	}
	
	@Override
	public JSONObject listAppointment() {
		return appointmentdao.listAppointment();
	}

	@Override
	public JSONObject updateAppointment(JSONObject appointment) {
		return appointmentdao.updateAppointment(appointment);
	}

	@Override
	public JSONObject deleteAppointment(JSONObject appointmentId) {
		return appointmentdao.deleteAppointment(appointmentId);
	}


}
