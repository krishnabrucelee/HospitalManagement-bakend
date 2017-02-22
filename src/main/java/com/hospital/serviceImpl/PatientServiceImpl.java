package com.hospital.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospital.dao.PatientDao;
import com.hospital.service.EMedicalReportService;
import com.hospital.service.PatientService;

/**
 * Patient Service Implementation.
 * 
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

	@Autowired
	private EMedicalReportService eMedicalReportService;

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

	@Override
	public JSONObject getPatientDetailsById(JSONObject patient) {

		if (patient.get("view").equals("emrView")) {
			JSONObject eMedical = eMedicalReportService.listByPatientId(patient);
			List<Object> list = (List<Object>) eMedical.get("EMedicalReport");
			if(!list.isEmpty()) {
				return eMedical;
			} else {
				return patientdao.getPatientDetailsById(patient);
			}
		} else {
			return patientdao.getPatientDetailsById(patient);
		}
	}

	@Override
	public JSONObject addDischargeSummary(JSONObject patient) {
		return patientdao.addDischargeSummary(patient);
	}

}
