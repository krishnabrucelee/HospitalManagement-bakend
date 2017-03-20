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
import com.hospital.model.PatientBillingTransaction;

/**
 * @author Krishna
 *
 */
@Repository
public class PatientBillingTransactionDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class PatientBillingTransactionDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addPatientBillingTransaction(JSONObject patientBillingTransaction) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		PatientBillingTransaction appoint = om.convertValue(patientBillingTransaction, PatientBillingTransaction.class);

		if (appoint.getBillAmount() <= appoint.getPaymentAmount()) {
			appoint.setBalanceAmount(appoint.getBillAmount() - appoint.getPaymentAmount());
		} else {
			appoint.setAdvanceAmount(appoint.getPaymentAmount() - appoint.getBillAmount());
		}
		
		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save patientBillingTransactions");
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

	public JSONObject listPatientBillingTransaction() {
		System.out.println("Inside Dao1PatientBillingTransaction");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<PatientBillingTransaction> patientBillingTransactionList = null;
		try {
			Query query = session.createQuery("FROM PatientBillingTransaction");
			patientBillingTransactionList = query.list();
			status.put("PatientBillingTransaction", patientBillingTransactionList);
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

	public JSONObject updatePatientBillingTransaction(JSONObject patientBillingTransaction) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			PatientBillingTransaction patientBillingTransactionDetails = session.load(PatientBillingTransaction.class,
					(Integer) patientBillingTransaction.get("patientBillingTransactionId"));
			session.update(patientBillingTransactionDetails);
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

	public PatientBillingTransaction getPatientBillingTransaction(Integer patientBillingTransactionId) {
		PatientBillingTransaction patientBillingTransaction = null;
		try {
			session.beginTransaction();
			session.get(PatientBillingTransaction.class, patientBillingTransactionId);
			patientBillingTransaction = (PatientBillingTransaction) session.get(PatientBillingTransaction.class, patientBillingTransactionId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (patientBillingTransaction != null) {
			return patientBillingTransaction;
		} else {
			return null;
		}
	}

	public JSONObject deletePatientBillingTransaction(JSONObject patientBillingTransactionId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			PatientBillingTransaction patientBillingTransactionDetails = session.load(PatientBillingTransaction.class,
					(Integer) patientBillingTransactionId.get("patientBillingTransactionId"));
			session.delete(patientBillingTransactionDetails);
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
