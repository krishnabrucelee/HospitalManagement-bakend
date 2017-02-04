/**
 * 
 */
package com.hospital.dao;

import java.util.HashMap;
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
import com.hospital.model.Department;
import com.hospital.model.EMedicalReport;
import com.hospital.model.StockMedicine;
import com.hospital.model.StockMedicineTransaction;

/**
 * @author Krishna
 *
 */
@Repository
public class StockMedicineDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class StockMedicineDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addStockMedicine(JSONObject stockMedicine) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		StockMedicine appoint = om.convertValue(stockMedicine, StockMedicine.class);
		//Load Department 
		Department departmentDetails = session.load(Department.class, (Integer) stockMedicine.get("department_id"));
		appoint.setDepartment(departmentDetails);
		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save stockMedicines");
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

	public JSONObject listStockMedicine() {
		System.out.println("Inside Dao1StockMedicine");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<StockMedicine> stockMedicineList = null;
		try {
			Query query = session.createQuery("FROM StockMedicine");
			stockMedicineList = query.list();
			status.put("StockMedicine", stockMedicineList);
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

	public JSONObject updateStockMedicine(JSONObject stockMedicine) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			StockMedicine stockMedicineDetails = session.load(StockMedicine.class,
					(Integer) stockMedicine.get("stockMedicineId"));
			session.update(stockMedicineDetails);
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

	public StockMedicine getStockMedicine(Integer stockMedicineId) {
		StockMedicine stockMedicine = null;
		try {
			session.beginTransaction();
			session.get(StockMedicine.class, stockMedicineId);
			stockMedicine = (StockMedicine) session.get(StockMedicine.class, stockMedicineId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (stockMedicine != null) {
			return stockMedicine;
		} else {
			return null;
		}
	}

	public JSONObject deleteStockMedicine(JSONObject stockMedicineId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			StockMedicine stockMedicineDetails = session.load(StockMedicine.class,
					(Integer) stockMedicineId.get("stockMedicineId"));
			session.delete(stockMedicineDetails);
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
	 * @param stockMedicine
	 * @return
	 */
	public JSONObject adjustStockMedicine(JSONObject stockMedicine) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		ObjectMapper om = new ObjectMapper();
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Map<String, Object> stockMap = (Map<String, Object>) stockMedicine.get("stockMedicine");
			List<Object> stockList = (List<Object>) stockMap.get("stockMedicineTransaction");
			
			StockMedicineTransaction stockMedicineTransaction = om.convertValue(stockList.get(0), StockMedicineTransaction.class);
			StockMedicine stockMedicineDetails = session.load(StockMedicine.class, (Integer) stockMap.get("stockMedicineId"));
			stockMedicineDetails.setQuantity(stockMedicineDetails.getQuantity() - Long.parseLong(stockMedicine.get("quantity").toString()));
			List<StockMedicineTransaction> stock = stockMedicineDetails.getStockMedicineTransaction();
			stock.add(stockMedicineTransaction);
			
			System.out.println(stock + "--" + stockMedicineDetails);
//			stockMedicineDetails.setQuantity(stockMedicineDetails.getQuantity() - Long.parseLong(stockMedicine.get("quantity").toString()));
//			session.update(stockMedicineDetails);
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
	 * @param stockMedicineId
	 * @return
	 */
	public JSONObject getStockMedicineById(JSONObject stockMedicine) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<StockMedicineTransaction> stockMedicineTransactionList = null;
		try {
			Query query = session.createQuery("FROM StockMedicineTransaction WHERE department_id = :id");
			query.setParameter("id", stockMedicine.get("departmentId"));
			stockMedicineTransactionList = query.list();
			status.put("StockMedicineTransaction", stockMedicineTransactionList);
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
