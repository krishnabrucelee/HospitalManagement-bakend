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
import com.hospital.model.TransferDoctorDetails;
import com.hospital.model.Doctor;
import com.hospital.model.InPatient;
import com.hospital.model.Patient;

/**
 * @author Krishna
 *
 */
@Repository
public class TransferDoctorDetailsDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class TransferDoctorDetailsDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addTransferDoctorDetails(JSONObject transferDoctorDetails) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		TransferDoctorDetails appoint = om.convertValue(transferDoctorDetails, TransferDoctorDetails.class);
		// Load Doctor
		Doctor doctorDetails = session.load(Doctor.class, (Integer) transferDoctorDetails.get("doctor_id"));
		appoint.setDoctor(doctorDetails);
		// Load Patient
		Patient patientDetails = session.load(Patient.class, (Integer) transferDoctorDetails.get("patient_id"));
		appoint.setPatient(patientDetails);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save transferDoctorDetailss");
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

	public JSONObject listTransferDoctorDetails() {
		System.out.println("Inside Dao1TransferDoctorDetails");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<TransferDoctorDetails> transferDoctorDetailsList = null;
		try {
			Query query = session.createQuery("FROM TransferDoctorDetails");
			transferDoctorDetailsList = query.list();
			status.put("TransferDoctorDetails", transferDoctorDetailsList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

	public JSONObject updateTransferDoctorDetails(JSONObject transferDoctorDetails) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			TransferDoctorDetails transferDoctorDetailsDetails = session.load(TransferDoctorDetails.class,
					(Integer) transferDoctorDetails.get("transferDoctorId"));
			session.update(transferDoctorDetailsDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public TransferDoctorDetails getTransferDoctorDetails(Integer transferDoctorDetailsId) {
		TransferDoctorDetails transferDoctorDetails = null;
		try {
			session.beginTransaction();
			session.get(TransferDoctorDetails.class, transferDoctorDetailsId);
			transferDoctorDetails = (TransferDoctorDetails) session.get(TransferDoctorDetails.class, transferDoctorDetailsId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (transferDoctorDetails != null) {
			return transferDoctorDetails;
		} else {
			return null;
		}
	}

	public JSONObject deleteTransferDoctorDetails(JSONObject transferDoctorDetailsId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			TransferDoctorDetails transferDoctorDetailsDetails = session.load(TransferDoctorDetails.class,
					(Integer) transferDoctorDetailsId.get("transferDoctorId"));
			session.delete(transferDoctorDetailsDetails);
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
