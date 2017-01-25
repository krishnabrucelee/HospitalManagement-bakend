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
import com.hospital.model.BioMedicalDisposalManagement;
import com.hospital.model.BioMedicalWasteDepartment;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import com.hospital.util.DateUtil;

/**
 * @author Krishna
 *
 */
@Repository
public class BioMedicalDisposalManagementDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class BioMedicalDisposalManagementDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addBioMedicalDisposalManagement(JSONObject bioMedicalDisposalManagement) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		BioMedicalDisposalManagement appoint = om.convertValue(bioMedicalDisposalManagement, BioMedicalDisposalManagement.class);
		// Load BioMedicalWasteDepartment
		BioMedicalWasteDepartment bioMedicalWasteDepartmentDetails = session.load(BioMedicalWasteDepartment.class, (Integer) bioMedicalDisposalManagement.get("bio_medical_waste_department_id"));
		appoint.setBioMedicalWasteDepartment(bioMedicalWasteDepartmentDetails);
		// TODO 
		//bioMedicalWasteDepartmentDetails.setDisposalDate(DateUtil.dateTimeUtil(bioMedicalDisposalManagement.get("disposalDate").toString()));
		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save bioMedicalDisposalManagements");
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

	public JSONObject listBioMedicalDisposalManagement() {
		System.out.println("Inside Dao1BioMedicalDisposalManagement");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<BioMedicalDisposalManagement> bioMedicalDisposalManagementList = null;
		try {
			Query query = session.createQuery("FROM BioMedicalDisposalManagement");
			bioMedicalDisposalManagementList = query.list();
			status.put("BioMedicalDisposalManagement", bioMedicalDisposalManagementList);
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

	public JSONObject updateBioMedicalDisposalManagement(JSONObject bioMedicalDisposalManagement) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			BioMedicalDisposalManagement bioMedicalDisposalManagementDetails = session.load(BioMedicalDisposalManagement.class,
					(Integer) bioMedicalDisposalManagement.get("bioMedicalDisposalManagementId"));
			session.update(bioMedicalDisposalManagementDetails);
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

	public BioMedicalDisposalManagement getBioMedicalDisposalManagement(Integer bioMedicalDisposalManagementId) {
		BioMedicalDisposalManagement bioMedicalDisposalManagement = null;
		try {
			session.beginTransaction();
			session.get(BioMedicalDisposalManagement.class, bioMedicalDisposalManagementId);
			bioMedicalDisposalManagement = (BioMedicalDisposalManagement) session.get(BioMedicalDisposalManagement.class, bioMedicalDisposalManagementId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (bioMedicalDisposalManagement != null) {
			return bioMedicalDisposalManagement;
		} else {
			return null;
		}
	}

	public JSONObject deleteBioMedicalDisposalManagement(JSONObject bioMedicalDisposalManagementId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			BioMedicalDisposalManagement bioMedicalDisposalManagementDetails = session.load(BioMedicalDisposalManagement.class,
					(Integer) bioMedicalDisposalManagementId.get("bioMedicalDisposalManagementId"));
			session.delete(bioMedicalDisposalManagementDetails);
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
