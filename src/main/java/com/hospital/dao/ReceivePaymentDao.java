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
import com.hospital.model.ReceivePayment;

/**
 * @author Krishna
 *
 */
@Repository
public class ReceivePaymentDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class ReceivePaymentDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addReceivePayment(JSONObject receivePayment) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		ReceivePayment appoint = om.convertValue(receivePayment, ReceivePayment.class);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save receivePayments");
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

	public JSONObject listReceivePayment() {
		System.out.println("Inside Dao1ReceivePayment");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<ReceivePayment> receivePaymentList = null;
		try {
			Query query = session.createQuery("FROM ReceivePayment");
			receivePaymentList = query.list();
			status.put("ReceivePayment", receivePaymentList);
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

	public JSONObject updateReceivePayment(JSONObject receivePayment) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			ReceivePayment receivePaymentDetails = session.load(ReceivePayment.class,
					(Integer) receivePayment.get("receivePaymentId"));
			session.update(receivePaymentDetails);
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

	public ReceivePayment getReceivePayment(Integer receivePaymentId) {
		ReceivePayment receivePayment = null;
		try {
			session.beginTransaction();
			session.get(ReceivePayment.class, receivePaymentId);
			receivePayment = (ReceivePayment) session.get(ReceivePayment.class, receivePaymentId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (receivePayment != null) {
			return receivePayment;
		} else {
			return null;
		}
	}

	public JSONObject deleteReceivePayment(JSONObject receivePaymentId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			ReceivePayment receivePaymentDetails = session.load(ReceivePayment.class,
					(Integer) receivePaymentId.get("receivePaymentId"));
			session.delete(receivePaymentDetails);
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
