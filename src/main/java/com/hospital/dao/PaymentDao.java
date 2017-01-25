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
import com.hospital.model.Payment;
import com.hospital.model.PettyCash;
import com.hospital.model.PurchaseBilling;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;

/**
 * @author Krishna
 *
 */
@Repository
public class PaymentDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class PaymentDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addPayment(JSONObject payment) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Payment appoint = om.convertValue(payment, Payment.class);
		// Load PurchaseBilling
		PurchaseBilling purchaseBillingDetails = session.load(PurchaseBilling.class, (Integer) payment.get("purchase_billing_id"));
		appoint.setPurchaseBilling(purchaseBillingDetails);
		// Load PettyCash
		PettyCash pettyCashDetails = session.load(PettyCash.class, (Integer) payment.get("petty_cash_id"));
		appoint.setPettyCash(pettyCashDetails);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save payments");
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

	public JSONObject listPayment() {
		System.out.println("Inside Dao1Payment");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Payment> paymentList = null;
		try {
			Query query = session.createQuery("FROM Payment");
			paymentList = query.list();
			status.put("Payment", paymentList);
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

	public JSONObject updatePayment(JSONObject payment) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Payment paymentDetails = session.load(Payment.class,
					(Integer) payment.get("paymentId"));
			session.update(paymentDetails);
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

	public Payment getPayment(Integer paymentId) {
		Payment payment = null;
		try {
			session.beginTransaction();
			session.get(Payment.class, paymentId);
			payment = (Payment) session.get(Payment.class, paymentId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (payment != null) {
			return payment;
		} else {
			return null;
		}
	}

	public JSONObject deletePayment(JSONObject paymentId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Payment paymentDetails = session.load(Payment.class,
					(Integer) paymentId.get("paymentId"));
			session.delete(paymentDetails);
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
