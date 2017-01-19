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
import com.hospital.model.BioMedicalType;
import com.hospital.model.BioMedicalWaste;
import com.hospital.model.BioMedicalWasteDepartment;
import com.hospital.model.Department;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;

/**
 * @author Krishna
 *
 */
@Repository
public class BioMedicalWasteDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class BioMedicalWasteDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addBioMedicalWaste(JSONObject bioMedicalWaste) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		BioMedicalWaste appoint = om.convertValue(bioMedicalWaste, BioMedicalWaste.class);
		// Load Department
		Department departmentDetails = session.load(Department.class, (Integer) bioMedicalWaste.get("department_id"));
		appoint.setDepartment(departmentDetails);
		// Load BioMedicalType
		BioMedicalType bioMedicalTypeDetails = session.load(BioMedicalType.class, (Integer) bioMedicalWaste.get("bio_medical_type_id"));
		appoint.setBioMedicalType(bioMedicalTypeDetails);
		// Load BioMedicalWasteDepartment
		BioMedicalWasteDepartment bioMedicalWasteDepartmentDetails = session.load(BioMedicalWasteDepartment.class, (Integer) bioMedicalWaste.get("bio_medical_waste_department_id"));
		appoint.setBioMedicalWasteDepartment(bioMedicalWasteDepartmentDetails);
		

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.merge(appoint);
			transaction.commit();
			System.out.println("Save bioMedicalWastes");
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

	public JSONObject listBioMedicalWaste() {
		System.out.println("Inside Dao1BioMedicalWaste");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<BioMedicalWaste> bioMedicalWasteList = null;
		try {
			Query query = session.createQuery("FROM BioMedicalWaste");
			bioMedicalWasteList = query.list();
			status.put("BioMedicalWaste", bioMedicalWasteList);
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

	public JSONObject updateBioMedicalWaste(JSONObject bioMedicalWaste) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			BioMedicalWaste bioMedicalWasteDetails = session.load(BioMedicalWaste.class,
					(Integer) bioMedicalWaste.get("bioMedicalWasteId"));
			session.update(bioMedicalWasteDetails);
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

	public BioMedicalWaste getBioMedicalWaste(Integer bioMedicalWasteId) {
		BioMedicalWaste bioMedicalWaste = null;
		try {
			session.beginTransaction();
			session.get(BioMedicalWaste.class, bioMedicalWasteId);
			bioMedicalWaste = (BioMedicalWaste) session.get(BioMedicalWaste.class, bioMedicalWasteId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		if (bioMedicalWaste != null) {
			return bioMedicalWaste;
		} else {
			return null;
		}
	}

	public JSONObject deleteBioMedicalWaste(JSONObject bioMedicalWasteId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			BioMedicalWaste bioMedicalWasteDetails = session.load(BioMedicalWaste.class,
					(Integer) bioMedicalWasteId.get("bioMedicalWasteId"));
			session.delete(bioMedicalWasteDetails);
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
