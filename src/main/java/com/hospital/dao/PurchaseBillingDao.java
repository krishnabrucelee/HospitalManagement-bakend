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
import com.hospital.model.PurchaseBilling;
import com.hospital.model.Doctor;
import com.hospital.model.ExpenseApproval;
import com.hospital.model.Patient;

/**
 * @author Krishna
 *
 */
@Repository
public class PurchaseBillingDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class PurchaseBillingDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addPurchaseBilling(JSONObject purchaseBilling) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		PurchaseBilling appoint = om.convertValue(purchaseBilling, PurchaseBilling.class);
		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save purchaseBillings");
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

	public JSONObject listPurchaseBilling() {
		System.out.println("Inside Dao1PurchaseBilling");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<PurchaseBilling> purchaseBillingList = null;
		try {
			Query query = session.createQuery("FROM PurchaseBilling");
			purchaseBillingList = query.list();
			status.put("PurchaseBilling", purchaseBillingList);
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

	public JSONObject updatePurchaseBilling(JSONObject purchaseBilling) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			PurchaseBilling purchaseBillingDetails = session.load(PurchaseBilling.class,
					(Integer) purchaseBilling.get("purchaseBillingId"));
			session.update(purchaseBillingDetails);
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

	public PurchaseBilling getPurchaseBilling(Integer purchaseBillingId) {
		PurchaseBilling purchaseBilling = null;
		try {
			session.beginTransaction();
			session.get(PurchaseBilling.class, purchaseBillingId);
			purchaseBilling = (PurchaseBilling) session.get(PurchaseBilling.class, purchaseBillingId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (purchaseBilling != null) {
			return purchaseBilling;
		} else {
			return null;
		}
	}

	public JSONObject deletePurchaseBilling(JSONObject purchaseBillingId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			PurchaseBilling purchaseBillingDetails = session.load(PurchaseBilling.class,
					(Integer) purchaseBillingId.get("purchaseBillingId"));
			session.delete(purchaseBillingDetails);
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
	 * @param purchaseBillingId
	 * @return
	 */
	public JSONObject getPurchaseBilling(JSONObject purchaseBillingId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<PurchaseBilling> purchaseBillingList = null;
//		  ObjectMapper mapper = new ObjectMapper();
//        SimpleModule module = new SimpleModule();        
//        module.addSerializer(com.monitorjbl.json.JsonView.class,new JsonViewSerializer());
//        mapper.registerModule(module);
		Integer patientId = Integer.parseInt(purchaseBillingId.get("id").toString());
		try {
			Query query = session.createQuery("FROM PurchaseBilling WHERE purchase_billing_id = :id");
			query.setParameter("id", patientId);
			purchaseBillingList = query.list();
//			String json = mapper.writeValueAsString(JsonView.with(patientDetailsList)
//					.onClass(BusAvailability.class,com.monitorjbl.json.Match.match().exclude("busDetails")));  
//			
//			
//			System.out.println(json);
//             ArrayList<Object> convertedValue = mapper.readValue(json,new TypeReference<ArrayList<Object>>() {});
//                   
//			if(convertedValue.isEmpty() | convertedValue == null)
//			{
//				status.put("status",false);
//				return status;
//			}		
//			status.put("Buses", convertedValue.iterator());
			status.put("PurchaseBilling", purchaseBillingList.iterator());
			System.out.println(" Inside Rest DAO Bus Status="+status);
			transaction.commit();
			return status;	
			
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
