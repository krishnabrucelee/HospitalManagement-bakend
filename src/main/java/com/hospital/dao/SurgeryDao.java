/**
 * 
 */
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
import com.hospital.model.Surgery;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;

/**
 * @author Krishna
 *
 */
@Repository
public class SurgeryDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class SurgeryDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addSurgery(JSONObject surgery) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Surgery appoint = om.convertValue(surgery, Surgery.class);
		// Load Doctor
		Doctor doctorDetails = session.load(Doctor.class, (Integer) surgery.get("doctor_id"));
		appoint.setDoctor(doctorDetails);
		// Load Patient
		Patient patientDetails = session.load(Patient.class, (Integer) surgery.get("patient_id"));
		appoint.setPatient(patientDetails);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save surgerys");
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

	public JSONObject listSurgery() {
		System.out.println("Inside Dao1Surgery");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Surgery> surgeryList = null;
		try {
			Query query = session.createQuery("FROM Surgery");
			surgeryList = query.list();
			status.put("Surgery", surgeryList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

	public JSONObject updateSurgery(JSONObject surgery) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Surgery surgeryDetails = session.load(Surgery.class,
					(Integer) surgery.get("surgeryId"));
			session.update(surgeryDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public Surgery getSurgery(Integer surgeryId) {
		Surgery surgery = null;
		try {
			session.beginTransaction();
			session.get(Surgery.class, surgeryId);
			surgery = (Surgery) session.get(Surgery.class, surgeryId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (surgery != null) {
			return surgery;
		} else {
			return null;
		}
	}

	public JSONObject deleteSurgery(JSONObject surgeryId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Surgery surgeryDetails = session.load(Surgery.class,
					(Integer) surgeryId.get("surgeryId"));
			session.delete(surgeryDetails);
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
