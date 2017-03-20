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
import com.hospital.model.EMedicalReport;
import com.hospital.model.PurchaseBillingTransaction;

/**
 * @author Krishna
 *
 */
@Repository
public class PurchaseBillingTransactionDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class PurchaseBillingTransactionDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addPurchaseBillingTransaction(JSONObject purchaseBillingTransaction) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		PurchaseBillingTransaction appoint = om.convertValue(purchaseBillingTransaction, PurchaseBillingTransaction.class);

		if (appoint.getPaymentAmount() <= appoint.getBillAmount()) {
			appoint.setBalanceAmount(appoint.getBillAmount() - appoint.getPaymentAmount());
		} else {
			appoint.setAdvanceAmount(appoint.getPaymentAmount() - appoint.getBillAmount());
		}
		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save purchaseBillingTransactions");
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

	public JSONObject listPurchaseBillingTransaction() {
		System.out.println("Inside Dao1PurchaseBillingTransaction");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<PurchaseBillingTransaction> purchaseBillingTransactionList = null;
		try {
			Query query = session.createQuery("FROM PurchaseBillingTransaction");
			purchaseBillingTransactionList = query.list();
			status.put("PurchaseBillingTransaction", purchaseBillingTransactionList);
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

	public JSONObject updatePurchaseBillingTransaction(JSONObject purchaseBillingTransaction) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			PurchaseBillingTransaction purchaseBillingTransactionDetails = session.load(PurchaseBillingTransaction.class,
					(Integer) purchaseBillingTransaction.get("purchaseBillingTransactionId"));
			session.update(purchaseBillingTransactionDetails);
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

	public PurchaseBillingTransaction getPurchaseBillingTransaction(Integer purchaseBillingTransactionId) {
		PurchaseBillingTransaction purchaseBillingTransaction = null;
		try {
			session.beginTransaction();
			session.get(PurchaseBillingTransaction.class, purchaseBillingTransactionId);
			purchaseBillingTransaction = (PurchaseBillingTransaction) session.get(PurchaseBillingTransaction.class, purchaseBillingTransactionId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (purchaseBillingTransaction != null) {
			return purchaseBillingTransaction;
		} else {
			return null;
		}
	}

	public JSONObject deletePurchaseBillingTransaction(JSONObject purchaseBillingTransactionId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			PurchaseBillingTransaction purchaseBillingTransactionDetails = session.load(PurchaseBillingTransaction.class,
					(Integer) purchaseBillingTransactionId.get("purchaseBillingTransactionId"));
			session.delete(purchaseBillingTransactionDetails);
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
	
	
	public JSONObject listByTransactionIdAndLastDate(JSONObject purchaseBillingId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<PurchaseBillingTransaction> eMedicalReportList = null;
		try {
			Query query = session.createQuery("FROM PurchaseBillingTransaction WHERE purchase_billing_id = :purchaseBillingId AND purchase_billing_transaction_date IN "
					+ "(SELECT MAX(purchase_billing_transaction_date)) ORDER BY purchase_billing_transaction_date DESC LIMIT 1");
			query.setParameter("purchaseBillingId", purchaseBillingId.get("id"));
			eMedicalReportList = query.list();
			status.put("PurchaseBillingTransaction", eMedicalReportList);
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
}
