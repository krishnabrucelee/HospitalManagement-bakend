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
import com.hospital.model.Ambulance;
import com.hospital.model.AmbulanceRequest;
import com.hospital.model.Doctor;
import com.hospital.model.Driver;
import com.hospital.model.Patient;

/**
 * @author Krishna
 *
 */
@Repository
public class AmbulanceRequestDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class AmbulanceRequestDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addAmbulanceRequest(JSONObject ambulanceRequest) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		AmbulanceRequest appoint = om.convertValue(ambulanceRequest, AmbulanceRequest.class);
		// Load Doctor
		Doctor doctorDetails = session.load(Doctor.class, (Integer) ambulanceRequest.get("doctor_id"));
		appoint.setDoctor(doctorDetails);
		// Load Ambulance
		Ambulance ambulanceDetails = session.load(Ambulance.class, (Integer) ambulanceRequest.get("ambulance_id"));
		appoint.setAmbulance(ambulanceDetails);
		// Load Driver
		Driver driverDetails = session.load(Driver.class, (Integer) ambulanceRequest.get("ambulance_id"));
		appoint.setDriver(driverDetails);
		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save ambulanceRequests");
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

	public JSONObject listAmbulanceRequest() {
		System.out.println("Inside Dao1AmbulanceRequest");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<AmbulanceRequest> ambulanceRequestList = null;
		try {
			Query query = session.createQuery("FROM AmbulanceRequest");
			ambulanceRequestList = query.list();
			status.put("AmbulanceRequest", ambulanceRequestList);
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

	public JSONObject updateAmbulanceRequest(JSONObject ambulanceRequest) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			AmbulanceRequest ambulanceRequestDetails = session.load(AmbulanceRequest.class,
					(Integer) ambulanceRequest.get("ambulanceRequestId"));
			session.update(ambulanceRequestDetails);
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

	public AmbulanceRequest getAmbulanceRequest(Integer ambulanceRequestId) {
		AmbulanceRequest ambulanceRequest = null;
		try {
			session.beginTransaction();
			session.get(AmbulanceRequest.class, ambulanceRequestId);
			ambulanceRequest = (AmbulanceRequest) session.get(AmbulanceRequest.class, ambulanceRequestId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ambulanceRequest != null) {
			return ambulanceRequest;
		} else {
			return null;
		}
	}

	public JSONObject deleteAmbulanceRequest(JSONObject ambulanceRequestId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			AmbulanceRequest ambulanceRequestDetails = session.load(AmbulanceRequest.class,
					(Integer) ambulanceRequestId.get("ambulanceRequestId"));
			session.delete(ambulanceRequestDetails);
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
