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
import com.hospital.model.Ward;

/**
 * @author Krishna
 *
 */
@Repository
public class WardDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class WardDao executed");
	}
	
	private Session session = null;
	private Transaction transaction = null;
	
	@SuppressWarnings("unchecked")
	public JSONObject addWard(JSONObject Ward) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Ward user = om.convertValue(Ward, Ward.class);
		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(user);
			transaction.commit();
			System.out.println("Save Wards");
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

	public JSONObject listWard() {
		System.out.println("Inside Dao1Ward");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Ward> WardList = null;
		try {
			Query query = session.createQuery("FROM Ward");
			WardList = query.list();
			status.put("Ward", WardList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

	public JSONObject updateWard(JSONObject Ward) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Ward WardDetails = session.load(Ward.class, (Integer) Ward.get("WardId"));
			WardDetails.setWardNumber((Integer) Ward.get("WardNumber"));
			session.update(WardDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public Ward getWard(Integer WardId) {
		Ward Ward = null;
		try {
			session.beginTransaction();
			session.get(Ward.class, WardId);
			Ward = (Ward) session.get(Ward.class, WardId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (Ward != null) {
			return Ward;
		} else {
			return null;
		}
	}

	public JSONObject deleteWard(JSONObject WardId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Ward WardDetails = session.load(Ward.class, (Integer) WardId.get("WardId"));
			System.out.println("dd" + WardDetails.getWardId());
			session.delete(WardDetails);
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
