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
import com.hospital.model.Laboratory;
import com.hospital.model.Patient;
import com.hospital.model.Doctor;
import com.hospital.model.InPatient;
import com.hospital.model.Laboratory;

/**
 * @author Krishna
 *
 */
@Repository
public class LaboratoryDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class LaboratoryDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addLaboratory(JSONObject laboratory) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Laboratory appoint = om.convertValue(laboratory, Laboratory.class);

		// Load Patient
		Patient patientDetails = session.load(Patient.class, (Integer) laboratory.get("patient_id"));
		appoint.setPatient(patientDetails);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.merge(appoint);
			transaction.commit();
			System.out.println("Save laboratorys");
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

	public JSONObject listLaboratory() {
		System.out.println("Inside Dao1Laboratory");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Laboratory> laboratoryList = null;
		try {
			Query query = session.createQuery("FROM Laboratory");
			laboratoryList = query.list();
			status.put("Laboratory", laboratoryList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

	public JSONObject updateLaboratory(JSONObject laboratory) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Laboratory laboratoryDetails = session.load(Laboratory.class,
					(Integer) laboratory.get("laboratoryId"));
			session.update(laboratoryDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public Laboratory getLaboratory(Integer laboratoryId) {
		Laboratory laboratory = null;
		try {
			session.beginTransaction();
			session.get(Laboratory.class, laboratoryId);
			laboratory = (Laboratory) session.get(Laboratory.class, laboratoryId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (laboratory != null) {
			return laboratory;
		} else {
			return null;
		}
	}

	public JSONObject deleteLaboratory(JSONObject laboratoryId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Laboratory laboratoryDetails = session.load(Laboratory.class,
					(Integer) laboratoryId.get("laboratoryId"));
			session.delete(laboratoryDetails);
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
	 * @param patientId
	 * @return
	 */
	public JSONObject listByPatientId(JSONObject patientId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Laboratory> laboratoryList = null;
		try {
			Query query = session.createQuery("FROM Laboratory WHERE patient_id = :id");
			query.setParameter("id", (Integer) patientId.get("patient_id"));
			laboratoryList = query.list();
			status.put("Laboratory", laboratoryList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}
}
