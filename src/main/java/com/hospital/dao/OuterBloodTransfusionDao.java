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
import com.hospital.model.OuterBloodTransfusion;
import com.hospital.model.BloodBank;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import com.hospital.service.BloodBankService;

/**
 * @author Krishna
 *
 */
@Repository
public class OuterBloodTransfusionDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	private BloodBankService bloodBankService;
	
	static {
		System.out.println("class OuterBloodTransfusionDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addOuterBloodTransfusion(JSONObject outerBloodTrans) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		OuterBloodTransfusion appoint = om.convertValue(outerBloodTrans, OuterBloodTransfusion.class);

//		// Have to do (Check If blood available in blood bank)
		Query query = session.createQuery("delete BloodBank where blood_bag_number = :bloodBagNumber");
		query.setParameter("bloodBagNumber", outerBloodTrans.get("bloodBagNumber"));
		query.executeUpdate();

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save outerBloodTranss");
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

	public JSONObject listOuterBloodTransfusion() {
		System.out.println("Inside Dao1OuterBloodTransfusion");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<OuterBloodTransfusion> outerBloodTransList = null;
		try {
			Query query = session.createQuery("FROM OuterBloodTransfusion");
			outerBloodTransList = query.list();
			status.put("OuterBloodTransfusion", outerBloodTransList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return status;
	}

	public JSONObject updateOuterBloodTransfusion(JSONObject outerBloodTrans) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			OuterBloodTransfusion outerBloodTransDetails = session.load(OuterBloodTransfusion.class,
					(Integer) outerBloodTrans.get("outerBloodTransId"));
			session.update(outerBloodTransDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return status;
	}

	public OuterBloodTransfusion getOuterBloodTransfusion(Integer outerBloodTransId) {
		OuterBloodTransfusion outerBloodTrans = null;
		try {
			session.beginTransaction();
			session.get(OuterBloodTransfusion.class, outerBloodTransId);
			outerBloodTrans = (OuterBloodTransfusion) session.get(OuterBloodTransfusion.class, outerBloodTransId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		if (outerBloodTrans != null) {
			return outerBloodTrans;
		} else {
			return null;
		}
	}

	public JSONObject deleteOuterBloodTransfusion(JSONObject outerBloodTransId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			OuterBloodTransfusion outerBloodTransDetails = session.load(OuterBloodTransfusion.class,
					(Integer) outerBloodTransId.get("outerBloodTransId"));
			session.delete(outerBloodTransDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return status;
	}

}
