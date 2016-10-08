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
import com.hospital.model.Diet;
import com.hospital.model.Doctor;
import com.hospital.model.EMedicalReport;
import com.hospital.model.Patient;
import com.hospital.model.Department;
import com.hospital.util.DateUtil;

/**
 * @author Krishna
 *
 */
@Repository
public class DietDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class DietDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addDiet(JSONObject diet) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Diet appoint = om.convertValue(diet, Diet.class);
		// Load Doctor
		Doctor doctorDetails = session.load(Doctor.class, (Integer) diet.get("doctor_id"));
		appoint.setDoctor(doctorDetails);
		// Load Patient
		Patient patientDetails = session.load(Patient.class, (Integer) diet.get("patient_id"));
		appoint.setPatient(patientDetails);
		try {
			System.out.println("Inside Dao11 PATIENT");
			session.merge(appoint);
			transaction.commit();
			System.out.println("Save diets");
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

	public JSONObject listDiet() {
		System.out.println("Inside Dao1Diet");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Diet> dietList = null;
		try {
			Query query = session.createQuery("FROM Diet");
			dietList = query.list();
			status.put("Diet", dietList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

	public JSONObject updateDiet(JSONObject diet) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Diet dietDetails = session.load(Diet.class,
					(Integer) diet.get("dietId"));
			session.update(dietDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public Diet getDiet(Integer dietId) {
		Diet diet = null;
		try {
			session.beginTransaction();
			session.get(Diet.class, dietId);
			diet = (Diet) session.get(Diet.class, dietId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (diet != null) {
			return diet;
		} else {
			return null;
		}
	}

	public JSONObject deleteDiet(JSONObject dietId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Diet dietDetails = session.load(Diet.class,
					(Integer) dietId.get("dietId"));
			session.delete(dietDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	/**
	 * list diet by Patient Id.
	 * 
	 * @param diet
	 * @return diet
	 */
	public JSONObject listByPatientId(JSONObject patientId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Diet> dietList = null;
		try {
			Query query = session.createQuery("FROM Diet WHERE patient_id = :id");
			query.setParameter("id", (Integer) patientId.get("patient_id"));
			dietList = query.list();
			status.put("Diet", dietList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

}
