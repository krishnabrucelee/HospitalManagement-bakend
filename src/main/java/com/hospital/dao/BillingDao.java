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
import com.hospital.model.Billing;

/**
 * @author Krishna
 *
 */
@Repository
public class BillingDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class BillingDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addBilling(JSONObject billing) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Billing appoint = om.convertValue(billing, Billing.class);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save billings");
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

	public JSONObject listBilling() {
		System.out.println("Inside Dao1Billing");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Billing> billingList = null;
		try {
			Query query = session.createQuery("FROM Billing");
			billingList = query.list();
			status.put("Billing", billingList);
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

	public JSONObject updateBilling(JSONObject billing) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Billing billingDetails = session.load(Billing.class,
					(Integer) billing.get("billingId"));
			session.update(billingDetails);
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

	public Billing getBilling(Integer billingId) {
		Billing billing = null;
		try {
			session.beginTransaction();
			session.get(Billing.class, billingId);
			billing = (Billing) session.get(Billing.class, billingId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (billing != null) {
			return billing;
		} else {
			return null;
		}
	}

	public JSONObject deleteBilling(JSONObject billingId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Billing billingDetails = session.load(Billing.class,
					(Integer) billingId.get("billingId"));
			session.delete(billingDetails);
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
