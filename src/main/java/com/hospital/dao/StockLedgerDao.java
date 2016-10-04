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
import com.hospital.model.StockLedger;
import com.hospital.model.Department;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;

/**
 * @author Krishna
 *
 */
@Repository
public class StockLedgerDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class StockLedgerDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addStockLedger(JSONObject stockLedger) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		StockLedger appoint = om.convertValue(stockLedger, StockLedger.class);
		// Load Department
		Department departmentDetails = session.load(Department.class, (Integer) stockLedger.get("department_id"));
		appoint.setDepartment(departmentDetails);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save stockLedgers");
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

	public JSONObject listStockLedger() {
		System.out.println("Inside Dao1StockLedger");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<StockLedger> stockLedgerList = null;
		try {
			Query query = session.createQuery("FROM StockLedger");
			stockLedgerList = query.list();
			status.put("StockLedger", stockLedgerList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

	public JSONObject updateStockLedger(JSONObject stockLedger) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			StockLedger stockLedgerDetails = session.load(StockLedger.class,
					(Integer) stockLedger.get("stockLedgerId"));
			session.update(stockLedgerDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public StockLedger getStockLedger(Integer stockLedgerId) {
		StockLedger stockLedger = null;
		try {
			session.beginTransaction();
			session.get(StockLedger.class, stockLedgerId);
			stockLedger = (StockLedger) session.get(StockLedger.class, stockLedgerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (stockLedger != null) {
			return stockLedger;
		} else {
			return null;
		}
	}

	public JSONObject deleteStockLedger(JSONObject stockLedgerId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			StockLedger stockLedgerDetails = session.load(StockLedger.class,
					(Integer) stockLedgerId.get("stockLedgerId"));
			session.delete(stockLedgerDetails);
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
