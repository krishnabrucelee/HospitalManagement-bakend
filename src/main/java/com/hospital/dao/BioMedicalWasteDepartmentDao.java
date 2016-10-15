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
import com.hospital.model.BioMedicalWasteDepartment;
import com.hospital.model.Department;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;

/**
 * @author Krishna
 *
 */
@Repository
public class BioMedicalWasteDepartmentDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class BioMedicalWasteDepartmentDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addBioMedicalWasteDepartment(JSONObject BioMedicalWasteDepartment) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		BioMedicalWasteDepartment appoint = om.convertValue(BioMedicalWasteDepartment,
				BioMedicalWasteDepartment.class);
		// Load Department
		Department departmentDetails = session.load(Department.class,
				(Integer) BioMedicalWasteDepartment.get("department_id"));
		appoint.setDepartment(departmentDetails);
		// Load BioMedicalType
		BioMedicalType bioMedicalTypeDetails = session.load(BioMedicalType.class,
				(Integer) BioMedicalWasteDepartment.get("bio_medical_type_id"));
		appoint.setBioMedicalType(bioMedicalTypeDetails);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save BioMedicalWasteDepartments");
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

	public JSONObject listBioMedicalWasteDepartment() {
		System.out.println("Inside Dao1BioMedicalWasteDepartment");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<BioMedicalWasteDepartment> BioMedicalWasteDepartmentList = null;
		try {
			Query query = session.createQuery("FROM BioMedicalWasteDepartment");
			BioMedicalWasteDepartmentList = query.list();
			status.put("BioMedicalWasteDepartment", BioMedicalWasteDepartmentList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

	public JSONObject updateBioMedicalWasteDepartment(JSONObject BioMedicalWasteDepartment) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			BioMedicalWasteDepartment BioMedicalWasteDepartmentDetails = session.load(
					BioMedicalWasteDepartment.class,
					(Integer) BioMedicalWasteDepartment.get("BioMedicalWasteDepartmentId"));
			session.update(BioMedicalWasteDepartmentDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public BioMedicalWasteDepartment getBioMedicalWasteDepartment(Integer BioMedicalWasteDepartmentId) {
		BioMedicalWasteDepartment bioMedicalWasteDepartment = null;
		try {
			session.beginTransaction();
			session.get(BioMedicalWasteDepartment.class, BioMedicalWasteDepartmentId);
			bioMedicalWasteDepartment = (BioMedicalWasteDepartment) session
					.get(BioMedicalWasteDepartment.class, BioMedicalWasteDepartmentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (bioMedicalWasteDepartment != null) {
			return bioMedicalWasteDepartment;
		} else {
			return null;
		}
	}

	public JSONObject deleteBioMedicalWasteDepartment(JSONObject BioMedicalWasteDepartmentId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			BioMedicalWasteDepartment BioMedicalWasteDepartmentDetails = session.load(
					BioMedicalWasteDepartment.class,
					(Integer) BioMedicalWasteDepartmentId.get("BioMedicalWasteDepartmentId"));
			session.delete(BioMedicalWasteDepartmentDetails);
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
