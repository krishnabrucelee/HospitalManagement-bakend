package com.hospital.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Patient;

/**
 * Patient Dao
 * 
 * @author Krishna
 *
 */
@Repository
public class PatientDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class PatientDao executed");
	}
	
	private Session session = null;
	private Transaction transaction = null;
	
	@SuppressWarnings("unchecked")
	public JSONObject addPatient(JSONObject patient) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Patient user = om.convertValue(patient, Patient.class);
		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(user);
			transaction.commit();
			System.out.println("Save patients");
			status.put("success", "User details saved");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return status;
	}

	public JSONObject listPatient() {
		System.out.println("Inside Dao1Patient");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Patient> patientList = null;
		try {
			Query query = session.createQuery("FROM Patient");
			patientList = query.list();
			status.put("Patient", patientList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

	public JSONObject updatePatient(JSONObject patient) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Patient patientDetails = session.load(Patient.class, (Integer) patient.get("patientId"));
			patientDetails.setPatientName((String) patient.get("patientName"));
			session.update(patientDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public Patient getPatient(Integer patientId) {
		Patient patient = null;
		try {
			session.beginTransaction();
			session.get(Patient.class, patientId);
			patient = (Patient) session.get(Patient.class, patientId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (patient != null) {
			return patient;
		} else {
			return null;
		}
	}

	public JSONObject deletePatient(JSONObject patientId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Patient patientDetails = session.load(Patient.class, (Integer) patientId.get("patient_id"));
			session.delete(patientDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
}
