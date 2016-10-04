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
import com.hospital.model.EMedicalReport;
import com.hospital.model.InPatient;
import com.hospital.model.Patient;
import com.hospital.model.Doctor;

/**
 * @author Krishna
 *
 */
@Repository
public class EMedicalReportDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class EMedicalReportDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addEMedicalReport(JSONObject eMedicalReport) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		EMedicalReport appoint = om.convertValue(eMedicalReport, EMedicalReport.class);
		// Load Doctor
		Doctor doctorDetails = session.load(Doctor.class, (Integer) eMedicalReport.get("doctor_id"));
		appoint.setDoctor(doctorDetails);
		// Load Patient
		Patient patientDetails = session.load(Patient.class, (Integer) eMedicalReport.get("patient_id"));
		appoint.setPatient(patientDetails);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.merge(appoint);
			transaction.commit();
			System.out.println("Save eMedicalReports");
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

	public JSONObject listEMedicalReport() {
		System.out.println("Inside Dao1EMedicalReport");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<EMedicalReport> eMedicalReportList = null;
		try {
			Query query = session.createQuery("FROM EMedicalReport");
			eMedicalReportList = query.list();
			status.put("EMedicalReport", eMedicalReportList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

	public JSONObject updateEMedicalReport(JSONObject eMedicalReport) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			EMedicalReport eMedicalReportDetails = session.load(EMedicalReport.class,
					(Integer) eMedicalReport.get("eMedicalReportId"));
			session.update(eMedicalReportDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public EMedicalReport getEMedicalReport(Integer eMedicalReportId) {
		EMedicalReport eMedicalReport = null;
		try {
			session.beginTransaction();
			session.get(EMedicalReport.class, eMedicalReportId);
			eMedicalReport = (EMedicalReport) session.get(EMedicalReport.class, eMedicalReportId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (eMedicalReport != null) {
			return eMedicalReport;
		} else {
			return null;
		}
	}

	public JSONObject deleteEMedicalReport(JSONObject eMedicalReportId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			EMedicalReport eMedicalReportDetails = session.load(EMedicalReport.class,
					(Integer) eMedicalReportId.get("eMedicalReportId"));
			session.delete(eMedicalReportDetails);
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
	 * List EMR by patient ID
	 * @param patientId
	 * @return
	 */
	public JSONObject listByPatientId(JSONObject patientId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<EMedicalReport> eMedicalReportList = null;
		try {
			Query query = session.createQuery("FROM EMedicalReport WHERE patient_id = :id");
			query.setParameter("id", (Integer) patientId.get("patient_id"));
			eMedicalReportList = query.list();
			status.put("EMedicalReport", eMedicalReportList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

}
