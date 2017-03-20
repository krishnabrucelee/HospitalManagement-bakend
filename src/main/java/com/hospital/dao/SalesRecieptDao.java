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
import com.hospital.model.SalesReciept;

/**
 * @author Krishna
 *
 */
@Repository
public class SalesRecieptDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class SalesRecieptDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addSalesReciept(JSONObject salesReciept) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		SalesReciept appoint = om.convertValue(salesReciept, SalesReciept.class);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save salesReciepts");
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

	public JSONObject listSalesReciept() {
		System.out.println("Inside Dao1SalesReciept");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<SalesReciept> salesRecieptList = null;
		try {
			Query query = session.createQuery("FROM SalesReciept");
			salesRecieptList = query.list();
			status.put("SalesReciept", salesRecieptList);
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

	public JSONObject updateSalesReciept(JSONObject salesReciept) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			SalesReciept salesRecieptDetails = session.load(SalesReciept.class,
					(Integer) salesReciept.get("salesRecieptId"));
			session.update(salesRecieptDetails);
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

	public SalesReciept getSalesReciept(Integer salesRecieptId) {
		SalesReciept salesReciept = null;
		try {
			session.beginTransaction();
			session.get(SalesReciept.class, salesRecieptId);
			salesReciept = (SalesReciept) session.get(SalesReciept.class, salesRecieptId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (salesReciept != null) {
			return salesReciept;
		} else {
			return null;
		}
	}

	public JSONObject deleteSalesReciept(JSONObject salesRecieptId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			SalesReciept salesRecieptDetails = session.load(SalesReciept.class,
					(Integer) salesRecieptId.get("salesRecieptId"));
			session.delete(salesRecieptDetails);
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
