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
import com.hospital.model.LaboratoryBilling;
import com.hospital.model.Cost;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;

/**
 * @author Krishna
 *
 */
@Repository
public class LaboratoryBillingDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class LaboratoryBillingDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addLaboratoryBilling(JSONObject laboratoryBilling) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		LaboratoryBilling appoint = om.convertValue(laboratoryBilling, LaboratoryBilling.class);
		// Load Doctor
		Cost costDetails = session.load(Cost.class, (Integer) laboratoryBilling.get("cost_id"));
		appoint.setCost(costDetails);
		// Load Patient
		Patient patientDetails = session.load(Patient.class, (Integer) laboratoryBilling.get("patient_id"));
		appoint.setPatient(patientDetails);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save laboratoryBillings");
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

	public JSONObject listLaboratoryBilling() {
		System.out.println("Inside Dao1LaboratoryBilling");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<LaboratoryBilling> laboratoryBillingList = null;
		try {
			Query query = session.createQuery("FROM LaboratoryBilling");
			laboratoryBillingList = query.list();
			status.put("LaboratoryBilling", laboratoryBillingList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

	public JSONObject updateLaboratoryBilling(JSONObject laboratoryBilling) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			LaboratoryBilling laboratoryBillingDetails = session.load(LaboratoryBilling.class,
					(Integer) laboratoryBilling.get("laboratoryBillingId"));
			session.update(laboratoryBillingDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public LaboratoryBilling getLaboratoryBilling(Integer laboratoryBillingId) {
		LaboratoryBilling laboratoryBilling = null;
		try {
			session.beginTransaction();
			session.get(LaboratoryBilling.class, laboratoryBillingId);
			laboratoryBilling = (LaboratoryBilling) session.get(LaboratoryBilling.class, laboratoryBillingId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (laboratoryBilling != null) {
			return laboratoryBilling;
		} else {
			return null;
		}
	}

	public JSONObject deleteLaboratoryBilling(JSONObject laboratoryBillingId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			LaboratoryBilling laboratoryBillingDetails = session.load(LaboratoryBilling.class,
					(Integer) laboratoryBillingId.get("laboratoryBillingId"));
			session.delete(laboratoryBillingDetails);
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
