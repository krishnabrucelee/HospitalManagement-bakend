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

/**
 * @author Krishna
 *
 */
@Repository
public class BioMedicalTypeDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class BioMedicalTypeDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addBioMedicalType(JSONObject bioMedicalType) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		BioMedicalType appoint = om.convertValue(bioMedicalType, BioMedicalType.class);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save bioMedicalTypes");
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

	public JSONObject listBioMedicalType() {
		System.out.println("Inside Dao1BioMedicalType");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<BioMedicalType> bioMedicalTypeList = null;
		try {
			Query query = session.createQuery("FROM BioMedicalType");
			bioMedicalTypeList = query.list();
			status.put("BioMedicalType", bioMedicalTypeList);
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

	public JSONObject updateBioMedicalType(JSONObject bioMedicalType) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			BioMedicalType bioMedicalTypeDetails = session.load(BioMedicalType.class,
					(Integer) bioMedicalType.get("bioMedicalTypeId"));
			session.update(bioMedicalTypeDetails);
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

	public BioMedicalType getBioMedicalType(Integer bioMedicalTypeId) {
		BioMedicalType bioMedicalType = null;
		try {
			session.beginTransaction();
			session.get(BioMedicalType.class, bioMedicalTypeId);
			bioMedicalType = (BioMedicalType) session.get(BioMedicalType.class, bioMedicalTypeId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (bioMedicalType != null) {
			return bioMedicalType;
		} else {
			return null;
		}
	}

	public JSONObject deleteBioMedicalType(JSONObject bioMedicalTypeId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			BioMedicalType bioMedicalTypeDetails = session.load(BioMedicalType.class,
					(Integer) bioMedicalTypeId.get("bioMedicalTypeId"));
			session.delete(bioMedicalTypeDetails);
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
