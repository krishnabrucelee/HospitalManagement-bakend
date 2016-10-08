package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospital.dao.PatientDao;
import com.hospital.service.PatientService;

/**
 * Patient Service Implementation.
 * @author Krishna
 *
 */
@Service
public class PatientServiceImpl implements PatientService {
	
	/**
	 * Patient dao
	 */
	@Autowired
	PatientDao patientdao;

	@Override
	public JSONObject addPatient(JSONObject patient) {
		return patientdao.addPatient(patient);
	}

	@Override
	public JSONObject listPatient() {
		return patientdao.listPatient();
	}
	
	@Override
	public JSONObject updatePatient(JSONObject patient) {
		return patientdao.updatePatient(patient);
	}

	@Override
	public JSONObject deletePatient(JSONObject patientId) {
		return patientdao.deletePatient(patientId);
	}
	
}
