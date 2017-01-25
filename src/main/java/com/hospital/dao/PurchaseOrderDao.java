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
import com.hospital.model.PurchaseOrder;
import com.hospital.model.StockLedger;
import com.hospital.model.Department;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;

/**
 * @author Krishna
 *
 */
@Repository
public class PurchaseOrderDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class PurchaseOrderDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addPurchaseOrder(JSONObject purchaseOrder) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		PurchaseOrder appoint = om.convertValue(purchaseOrder, PurchaseOrder.class);
		// Load StockLedger
		StockLedger stockLedgerDetails = session.load(StockLedger.class, (Integer) purchaseOrder.get("stock_ledger_id"));
		appoint.setStockLedger(stockLedgerDetails);
		// Load Department
		Department departmentDetails = session.load(Department.class, (Integer) purchaseOrder.get("department_id"));
		appoint.setDepartment(departmentDetails);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save purchaseOrders");
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

	public JSONObject listPurchaseOrder() {
		System.out.println("Inside Dao1PurchaseOrder");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<PurchaseOrder> purchaseOrderList = null;
		try {
			Query query = session.createQuery("FROM PurchaseOrder");
			purchaseOrderList = query.list();
			status.put("PurchaseOrder", purchaseOrderList);
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

	public JSONObject updatePurchaseOrder(JSONObject purchaseOrder) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			PurchaseOrder purchaseOrderDetails = session.load(PurchaseOrder.class,
					(Integer) purchaseOrder.get("purchaseOrderId"));
			session.update(purchaseOrderDetails);
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

	public PurchaseOrder getPurchaseOrder(Integer purchaseOrderId) {
		PurchaseOrder purchaseOrder = null;
		try {
			session.beginTransaction();
			session.get(PurchaseOrder.class, purchaseOrderId);
			purchaseOrder = (PurchaseOrder) session.get(PurchaseOrder.class, purchaseOrderId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (purchaseOrder != null) {
			return purchaseOrder;
		} else {
			return null;
		}
	}

	public JSONObject deletePurchaseOrder(JSONObject purchaseOrderId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			PurchaseOrder purchaseOrderDetails = session.load(PurchaseOrder.class,
					(Integer) purchaseOrderId.get("purchaseOrderId"));
			session.delete(purchaseOrderDetails);
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
