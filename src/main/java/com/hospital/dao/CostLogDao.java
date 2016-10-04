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
import com.hospital.model.Cost;
import com.hospital.model.CostLog;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;

/**
 * @author Krishna
 *
 */
@Repository
public class CostLogDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class CostLogDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addCostLog(JSONObject costLog) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		CostLog appoint = om.convertValue(costLog, CostLog.class);
		// Load Cost
		Cost costDetails = session.load(Cost.class, (Integer) costLog.get("cost_id"));
		appoint.setCost(costDetails);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save costLogs");
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

	public JSONObject listCostLog() {
		System.out.println("Inside Dao1CostLog");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<CostLog> costLogList = null;
		try {
			Query query = session.createQuery("FROM CostLog");
			costLogList = query.list();
			status.put("CostLog", costLogList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

	public JSONObject updateCostLog(JSONObject costLog) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			CostLog costLogDetails = session.load(CostLog.class,
					(Integer) costLog.get("costLogId"));
			session.update(costLogDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public CostLog getCostLog(Integer costLogId) {
		CostLog costLog = null;
		try {
			session.beginTransaction();
			session.get(CostLog.class, costLogId);
			costLog = (CostLog) session.get(CostLog.class, costLogId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (costLog != null) {
			return costLog;
		} else {
			return null;
		}
	}

	public JSONObject deleteCostLog(JSONObject costLogId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			CostLog costLogDetails = session.load(CostLog.class,
					(Integer) costLogId.get("costLogId"));
			session.delete(costLogDetails);
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
