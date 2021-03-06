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
import com.hospital.model.CashCounter;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import com.hospital.model.PatientBilling;

/**
 * @author Krishna
 *
 */
@Repository
public class CashCounterDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class CashCounterDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addCashCounter(JSONObject cashCounter) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		CashCounter appoint = om.convertValue(cashCounter, CashCounter.class);
		// Load PatientBilling
		PatientBilling patientBillingDetails = session.load(PatientBilling.class, (Integer) cashCounter.get("patient_billing_id"));
		appoint.setPatientBilling(patientBillingDetails);
		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save cashCounters");
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

	public JSONObject listCashCounter() {
		System.out.println("Inside Dao1CashCounter");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<CashCounter> cashCounterList = null;
		try {
			Query query = session.createQuery("FROM CashCounter");
			cashCounterList = query.list();
			status.put("CashCounter", cashCounterList);
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

	public JSONObject updateCashCounter(JSONObject cashCounter) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			CashCounter cashCounterDetails = session.load(CashCounter.class,
					(Integer) cashCounter.get("cashCounterId"));
			session.update(cashCounterDetails);
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

	public CashCounter getCashCounter(Integer cashCounterId) {
		CashCounter cashCounter = null;
		try {
			session.beginTransaction();
			session.get(CashCounter.class, cashCounterId);
			cashCounter = (CashCounter) session.get(CashCounter.class, cashCounterId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (cashCounter != null) {
			return cashCounter;
		} else {
			return null;
		}
	}

	public JSONObject deleteCashCounter(JSONObject cashCounterId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			CashCounter cashCounterDetails = session.load(CashCounter.class,
					(Integer) cashCounterId.get("cashCounterId"));
			session.delete(cashCounterDetails);
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
