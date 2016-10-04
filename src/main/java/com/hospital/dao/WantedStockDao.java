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
import com.hospital.model.WantedStock;
import com.hospital.model.Department;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import com.hospital.model.StockLedger;

/**
 * @author Krishna
 *
 */
@Repository
public class WantedStockDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class WantedStockDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addWantedStock(JSONObject wantedStock) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		WantedStock appoint = om.convertValue(wantedStock, WantedStock.class);
		// Load StockLedger
		StockLedger stockLedgerDetails = session.load(StockLedger.class, (Integer) wantedStock.get("stock_ledger_id"));
		appoint.setStockLedger(stockLedgerDetails);
		// Load Department
		Department departmentDetails = session.load(Department.class, (Integer) wantedStock.get("department_id"));
		appoint.setDepartment(departmentDetails);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.merge(appoint);
			transaction.commit();
			System.out.println("Save wantedStocks");
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

	public JSONObject listWantedStock() {
		System.out.println("Inside Dao1WantedStock");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<WantedStock> wantedStockList = null;
		try {
			Query query = session.createQuery("FROM WantedStock");
			wantedStockList = query.list();
			status.put("WantedStock", wantedStockList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

	public JSONObject updateWantedStock(JSONObject wantedStock) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			WantedStock wantedStockDetails = session.load(WantedStock.class,
					(Integer) wantedStock.get("wantedStockId"));
			session.update(wantedStockDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public WantedStock getWantedStock(Integer wantedStockId) {
		WantedStock wantedStock = null;
		try {
			session.beginTransaction();
			session.get(WantedStock.class, wantedStockId);
			wantedStock = (WantedStock) session.get(WantedStock.class, wantedStockId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (wantedStock != null) {
			return wantedStock;
		} else {
			return null;
		}
	}

	public JSONObject deleteWantedStock(JSONObject wantedStockId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			WantedStock wantedStockDetails = session.load(WantedStock.class,
					(Integer) wantedStockId.get("wantedStockId"));
			session.delete(wantedStockDetails);
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
