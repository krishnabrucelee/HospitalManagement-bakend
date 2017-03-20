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
import com.hospital.model.BloodBankCamp;

/**
 * @author Krishna
 *
 */
@Repository
public class BloodBankCampDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class BloodBankCampDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addBloodBankCamp(JSONObject bloodBankCamp) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		BloodBankCamp appoint = om.convertValue(bloodBankCamp, BloodBankCamp.class);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save bloodBankCamps");
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

	public JSONObject listBloodBankCamp() {
		System.out.println("Inside Dao1BloodBankCamp");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<BloodBankCamp> bloodBankCampList = null;
		try {
			Query query = session.createQuery("FROM BloodBankCamp");
			bloodBankCampList = query.list();
			status.put("BloodBankCamp", bloodBankCampList);
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

	public JSONObject updateBloodBankCamp(JSONObject bloodBankCamp) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			BloodBankCamp bloodBankCampDetails = session.load(BloodBankCamp.class,
					(Integer) bloodBankCamp.get("bloodBankCampId"));
			session.update(bloodBankCampDetails);
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

	public BloodBankCamp getBloodBankCamp(Integer bloodBankCampId) {
		BloodBankCamp bloodBankCamp = null;
		try {
			session.beginTransaction();
			session.get(BloodBankCamp.class, bloodBankCampId);
			bloodBankCamp = (BloodBankCamp) session.get(BloodBankCamp.class, bloodBankCampId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (bloodBankCamp != null) {
			return bloodBankCamp;
		} else {
			return null;
		}
	}

	public JSONObject deleteBloodBankCamp(JSONObject bloodBankCampId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			BloodBankCamp bloodBankCampDetails = session.load(BloodBankCamp.class,
					(Integer) bloodBankCampId.get("bloodBankCampId"));
			session.delete(bloodBankCampDetails);
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
