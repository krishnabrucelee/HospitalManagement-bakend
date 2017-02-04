/**
 * 
 */
package com.hospital.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Supplier;

/**
 * @author Krishna
 *
 */
@Repository
public class Supplierdao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class SupplierDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject createSupplier(JSONObject supplier) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Supplier appoint = om.convertValue(supplier, Supplier.class);
		
		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save Supplier");
			status.put("success", "Supplier saved");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}

	public JSONObject listSupplier() {
		System.out.println("Inside Dao1Supplier");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Supplier> supplierList = null;
		try {
			Query query = session.createQuery("FROM Supplier");
			supplierList = query.list();
			status.put("Supplier", supplierList);
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

	public JSONObject updateSupplier(JSONObject supplier) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Supplier supplierDetails = session.load(Supplier.class,
					(Integer) supplier.get("supplierId"));
			session.update(supplierDetails);
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

	public Supplier getSupplier(Integer supplierId) {
		Supplier supplier = null;
		try {
			session.beginTransaction();
			session.get(Supplier.class, supplierId);
			supplier = (Supplier) session.get(Supplier.class, supplierId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (supplier != null) {
			return supplier;
		} else {
			return null;
		}
	}

	public JSONObject deleteSupplier(JSONObject supplierId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Supplier supplierDetails = session.load(Supplier.class,
					(Integer) supplierId.get("supplierId"));
			session.delete(supplierDetails);
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

	/**
	 * @param supplierId
	 * @return
	 */
	public JSONObject getSupplierById(JSONObject supplier) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
//		List<SupplierTransaction> supplierTransactionList = null;
//		try {
//			Query query = session.createQuery("FROM SupplierTransaction WHERE department_id = :id");
//			query.setParameter("id", supplier.get("departmentId"));
//			supplierTransactionList = query.list();
//			status.put("SupplierTransaction", supplierTransactionList);
//			status.put("result", true);
//			transaction.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//			status.put("result", false);
//		} finally {
//			if (session.isOpen()) {
//				// session.close();
//			}
//		}
		return status;
	}
}
