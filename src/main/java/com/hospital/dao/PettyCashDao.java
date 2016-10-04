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
import com.hospital.model.PettyCash;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;

/**
 * @author Krishna
 *
 */
@Repository
public class PettyCashDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class PettyCashDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addPettyCash(JSONObject pettyCash) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		PettyCash appoint = om.convertValue(pettyCash, PettyCash.class);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save pettyCashs");
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

	public JSONObject listPettyCash() {
		System.out.println("Inside Dao1PettyCash");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<PettyCash> pettyCashList = null;
		try {
			Query query = session.createQuery("FROM PettyCash");
			pettyCashList = query.list();
			status.put("PettyCash", pettyCashList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

	public JSONObject updatePettyCash(JSONObject pettyCash) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			PettyCash pettyCashDetails = session.load(PettyCash.class,
					(Integer) pettyCash.get("pettyCashId"));
			session.update(pettyCashDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public PettyCash getPettyCash(Integer pettyCashId) {
		PettyCash pettyCash = null;
		try {
			session.beginTransaction();
			session.get(PettyCash.class, pettyCashId);
			pettyCash = (PettyCash) session.get(PettyCash.class, pettyCashId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pettyCash != null) {
			return pettyCash;
		} else {
			return null;
		}
	}

	public JSONObject deletePettyCash(JSONObject pettyCashId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			PettyCash pettyCashDetails = session.load(PettyCash.class,
					(Integer) pettyCashId.get("pettyCashId"));
			session.delete(pettyCashDetails);
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
