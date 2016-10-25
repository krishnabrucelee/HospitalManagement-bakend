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
import com.hospital.model.Pharmacist;

/**
 * @author Krishna
 *
 */
@Repository
public class PharmacistDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class PharmacistDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addPharmacist(JSONObject pharmacist) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Pharmacist appoint = om.convertValue(pharmacist, Pharmacist.class);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save pharmacists");
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

	public JSONObject listPharmacist() {
		System.out.println("Inside Dao1Pharmacist");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Pharmacist> pharmacistList = null;
		try {
			Query query = session.createQuery("FROM Pharmacist");
			pharmacistList = query.list();
			status.put("Pharmacist", pharmacistList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

	public JSONObject updatePharmacist(JSONObject pharmacist) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Pharmacist pharmacistDetails = session.load(Pharmacist.class,
					(Integer) pharmacist.get("pharmacistId"));
			session.update(pharmacistDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public Pharmacist getPharmacist(Integer pharmacistId) {
		Pharmacist pharmacist = null;
		try {
			session.beginTransaction();
			session.get(Pharmacist.class, pharmacistId);
			pharmacist = (Pharmacist) session.get(Pharmacist.class, pharmacistId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pharmacist != null) {
			return pharmacist;
		} else {
			return null;
		}
	}

	public JSONObject deletePharmacist(JSONObject pharmacistId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Pharmacist pharmacistDetails = session.load(Pharmacist.class,
					(Integer) pharmacistId.get("pharmacistId"));
			session.delete(pharmacistDetails);
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
	 * @param pharmacist
	 * @return
	 */
	public Pharmacist addPharmacistFromStaff(Pharmacist pharmacist) {
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(pharmacist);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pharmacist;
	}

}
