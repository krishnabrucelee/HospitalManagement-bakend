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
import com.hospital.model.BloodRequest;
import com.hospital.model.Department;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;

/**
 * @author Krishna
 *
 */
@Repository
public class BloodRequestDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class BloodRequestDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addBloodRequest(JSONObject bloodRequest) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		BloodRequest appoint = om.convertValue(bloodRequest, BloodRequest.class);
		// Load Patient
		Patient patientDetails = session.load(Patient.class, (Integer) bloodRequest.get("patient_id"));
		appoint.setPatient(patientDetails);
		// Load Department
		Department departmentDetails = session.load(Department.class, (Integer) bloodRequest.get("department_id"));
		appoint.setDepartment(departmentDetails);
		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save bloodRequests");
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

	public JSONObject listBloodRequest() {
		System.out.println("Inside Dao1BloodRequest");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<BloodRequest> bloodRequestList = null;
		try {
			Query query = session.createQuery("FROM BloodRequest");
			bloodRequestList = query.list();
			status.put("BloodRequest", bloodRequestList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return status;
	}

	public JSONObject updateBloodRequest(JSONObject bloodRequest) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			BloodRequest bloodRequestDetails = session.load(BloodRequest.class,
					(Integer) bloodRequest.get("bloodRequestId"));
			session.update(bloodRequestDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return status;
	}

	public BloodRequest getBloodRequest(Integer bloodRequestId) {
		BloodRequest bloodRequest = null;
		try {
			session.beginTransaction();
			session.get(BloodRequest.class, bloodRequestId);
			bloodRequest = (BloodRequest) session.get(BloodRequest.class, bloodRequestId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		if (bloodRequest != null) {
			return bloodRequest;
		} else {
			return null;
		}
	}

	public JSONObject deleteBloodRequest(JSONObject bloodRequestId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			BloodRequest bloodRequestDetails = session.load(BloodRequest.class,
					(Integer) bloodRequestId.get("bloodRequestId"));
			session.delete(bloodRequestDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return status;
	}

}
