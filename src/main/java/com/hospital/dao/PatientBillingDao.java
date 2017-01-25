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
import com.hospital.model.PatientBilling;
import com.hospital.model.Department;
import com.hospital.model.Doctor;
import com.hospital.model.LaboratoryBilling;
import com.hospital.model.Patient;

/**
 * @author Krishna
 *
 */
@Repository
public class PatientBillingDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class PatientBillingDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addPatientBilling(JSONObject patientBilling) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		PatientBilling appoint = om.convertValue(patientBilling, PatientBilling.class);
		// Load Department
		Department departmentDetails = session.load(Department.class, (Integer) patientBilling.get("department_id"));
		appoint.setDepartment(departmentDetails);
		// Load Patient
		Patient patientDetails = session.load(Patient.class, (Integer) patientBilling.get("patient_id"));
		appoint.setPatient(patientDetails);
		// Load LaboratoryBilling
		LaboratoryBilling laboratoryBillingDetails = session.load(LaboratoryBilling.class, (Integer) patientBilling.get("laboratory_billing_id"));
		appoint.setLaboratoryBilling(laboratoryBillingDetails);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save patientBillings");
			status.put("success", "User details saved");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}

	public JSONObject listPatientBilling() {
		System.out.println("Inside Dao1PatientBilling");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<PatientBilling> patientBillingList = null;
		try {
			Query query = session.createQuery("FROM PatientBilling");
			patientBillingList = query.list();
			status.put("PatientBilling", patientBillingList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}

	public JSONObject updatePatientBilling(JSONObject patientBilling) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			PatientBilling patientBillingDetails = session.load(PatientBilling.class,
					(Integer) patientBilling.get("patientBillingId"));
			session.update(patientBillingDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}

	public PatientBilling getPatientBilling(Integer patientBillingId) {
		PatientBilling patientBilling = null;
		try {
			session.beginTransaction();
			session.get(PatientBilling.class, patientBillingId);
			patientBilling = (PatientBilling) session.get(PatientBilling.class, patientBillingId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (patientBilling != null) {
			return patientBilling;
		} else {
			return null;
		}
	}

	public JSONObject deletePatientBilling(JSONObject patientBillingId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			PatientBilling patientBillingDetails = session.load(PatientBilling.class,
					(Integer) patientBillingId.get("patientBillingId"));
			session.delete(patientBillingDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}

}
