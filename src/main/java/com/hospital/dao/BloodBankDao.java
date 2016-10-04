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
import com.hospital.model.BloodBank;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;

/**
 * @author Krishna
 *
 */
@Repository
public class BloodBankDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class BloodBankDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addBloodBank(JSONObject bloodBank) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		BloodBank appoint = om.convertValue(bloodBank, BloodBank.class);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save bloodBanks");
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

	public JSONObject listBloodBank() {
		System.out.println("Inside Dao1BloodBank");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<BloodBank> bloodBankList = null;
		try {
			Query query = session.createQuery("FROM BloodBank");
			bloodBankList = query.list();
			status.put("BloodBank", bloodBankList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

	public JSONObject updateBloodBank(JSONObject bloodBank) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			BloodBank bloodBankDetails = session.load(BloodBank.class,
					(Integer) bloodBank.get("bloodBankId"));
			session.update(bloodBankDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public BloodBank getBloodBank(Integer bloodBankId) {
		BloodBank bloodBank = null;
		try {
			session.beginTransaction();
			session.get(BloodBank.class, bloodBankId);
			bloodBank = (BloodBank) session.get(BloodBank.class, bloodBankId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (bloodBank != null) {
			return bloodBank;
		} else {
			return null;
		}
	}

	public JSONObject deleteBloodBank(JSONObject bloodBankId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			BloodBank bloodBankDetails = session.load(BloodBank.class,
					(Integer) bloodBankId.get("bloodBankId"));
			session.delete(bloodBankDetails);
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
	 * @param bloodBankId
	 * @return
	 */
	public BloodBank updateBloodBankById(BloodBank bloodBank) {
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			BloodBank bloodBankDetails = session.load(BloodBank.class, bloodBank.getBloodBankId());
			session.update(bloodBankDetails);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bloodBank;
	}

	/**
	 * @param bloodBankId
	 * @return
	 */
	public Integer deleteBloodBankById(Integer bloodBankId) {
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			BloodBank bloodBankDetails = session.load(BloodBank.class, bloodBankId);
			session.delete(bloodBankDetails);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bloodBankId;
	}

}
