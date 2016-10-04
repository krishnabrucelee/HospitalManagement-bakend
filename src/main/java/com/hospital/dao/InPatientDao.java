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
import com.hospital.model.Doctor;
import com.hospital.model.InPatient;
import com.hospital.model.Ward;

/**
 * @author Krishna
 *
 */
@Repository
public class InPatientDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class InPatientDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addInPatient(JSONObject inPatient) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		InPatient user = om.convertValue(inPatient, InPatient.class);

		// Load Ward
		Ward inWardDetails = session.load(Ward.class, (Integer) inPatient.get("Ward_id"));
		user.setWard(inWardDetails);
				
		try {
			System.out.println("Inside Dao11 PATIENT");
			session.merge(user);
			transaction.commit();
			System.out.println("Save inPatients");
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

	public JSONObject listInPatient() {
		System.out.println("Inside Dao1InPatient");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<InPatient> inPatientList = null;
		try {
			Query query = session.createQuery("FROM InPatient");
			inPatientList = query.list();
			status.put("InPatient", inPatientList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

	public JSONObject updateInPatient(JSONObject inPatient) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			InPatient inPatientDetails = session.load(InPatient.class,
					(Integer) inPatient.get("in_patient_id"));
			session.update(inPatientDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public InPatient getInPatient(Integer inPatientId) {
		InPatient inPatient = null;
		try {
			session.beginTransaction();
			session.get(InPatient.class, inPatientId);
			inPatient = (InPatient) session.get(InPatient.class, inPatientId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (inPatient != null) {
			return inPatient;
		} else {
			return null;
		}
	}

	public JSONObject deleteInPatient(JSONObject inPatientId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			InPatient inPatientDetails = session.load(InPatient.class,
					(Integer) inPatientId.get("in_patient_id"));
			session.delete(inPatientDetails);
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
