/**
 * 
 */
package com.hospital.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.PharmacyMedicine;
import com.hospital.model.Doctor;
import com.hospital.model.InPatient;
import com.hospital.model.MedicineList;
import com.hospital.model.Patient;
import com.hospital.model.PharmacyMedicine;

/**
 * @author Krishna
 *
 */
@Repository
public class PharmacyMedicineDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class PharmacyMedicineDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addPharmacyMedicine(JSONObject pharmacyMedicine) throws JsonParseException, JsonMappingException, IOException, JSONException {
		JSONObject status = new JSONObject();
		status.put("status", true);
		System.out.println("JSON" + pharmacyMedicine);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		PharmacyMedicine appoint = om.convertValue(pharmacyMedicine, PharmacyMedicine.class);
		
		// Load Patient
		Patient patientDetails = session.load(Patient.class, (Integer) pharmacyMedicine.get("patient_id"));
		appoint.setPatient(patientDetails);
		
		try {
			System.out.println("Inside Dao11 PATIENT");
			session.merge(appoint);
			transaction.commit();
			System.out.println("Save pharmacyMedicines");
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

	public JSONObject listPharmacyMedicine() {
		System.out.println("Inside Dao1PharmacyMedicine");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<PharmacyMedicine> pharmacyMedicineList = null;
		try {
			Query query = session.createQuery("FROM PharmacyMedicine");
			pharmacyMedicineList = query.list();
			status.put("PharmacyMedicine", pharmacyMedicineList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

	public JSONObject updatePharmacyMedicine(JSONObject pharmacyMedicine) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			PharmacyMedicine pharmacyMedicineDetails = session.load(PharmacyMedicine.class,
					(Integer) pharmacyMedicine.get("pharmacyMedicineId"));
			session.update(pharmacyMedicineDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public PharmacyMedicine getPharmacyMedicine(Integer pharmacyMedicineId) {
		PharmacyMedicine pharmacyMedicine = null;
		try {
			session.beginTransaction();
			session.get(PharmacyMedicine.class, pharmacyMedicineId);
			pharmacyMedicine = (PharmacyMedicine) session.get(PharmacyMedicine.class, pharmacyMedicineId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pharmacyMedicine != null) {
			return pharmacyMedicine;
		} else {
			return null;
		}
	}

	public JSONObject deletePharmacyMedicine(JSONObject pharmacyMedicineId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			PharmacyMedicine pharmacyMedicineDetails = session.load(PharmacyMedicine.class,
					(Integer) pharmacyMedicineId.get("pharmacyMedicineId"));
			session.delete(pharmacyMedicineDetails);
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
		List<PharmacyMedicine> pharmacyMedicineList = null;
		try {
			Query query = session.createQuery("FROM PharmacyMedicine WHERE patient_id = :id");
			query.setParameter("id", (Integer) patientId.get("patient_id"));
			pharmacyMedicineList = query.list();
			status.put("PharmacyMedicine", pharmacyMedicineList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

}
