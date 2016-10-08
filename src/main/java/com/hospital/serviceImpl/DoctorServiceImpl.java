/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.DoctorDao;
import com.hospital.model.Doctor;
import com.hospital.service.DoctorService;

/**
 * @author Krishna
 *
 */
@Service
public class DoctorServiceImpl implements DoctorService {

	/**
	 * Doctor dao
	 */
	@Autowired
	DoctorDao doctordao;
	
	@Override
	public JSONObject addDoctor(JSONObject doctor) {
		return doctordao.addDoctor(doctor);
	}

	@Override
	public JSONObject listDoctor() {
		return doctordao.listDoctor();
	}

	@Override
	public JSONObject updateDoctor(JSONObject doctor) {
		return doctordao.updateDoctor(doctor);
	}

	@Override
	public JSONObject deleteDoctor(JSONObject doctorId) {
		return doctordao.deleteDoctor(doctorId);
	}
	
	@Override
	public Doctor addDoctorFromStaff(Doctor doctor) {
		return doctordao.addDoctorFromStaff(doctor);
	}

}
