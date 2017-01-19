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
import com.hospital.model.InterBloodTransfusion;
import com.hospital.model.BloodBank;
import com.hospital.model.Department;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import com.hospital.service.BloodBankService;

/**
 * @author Krishna
 *
 */
@Repository 
public class InterBloodTransfusionDao {

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
		System.out.println("class InterBloodTransfusionDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addInterBloodTransfusion(JSONObject interBloodTrans) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		InterBloodTransfusion appoint = om.convertValue(interBloodTrans, InterBloodTransfusion.class);
		// Load Patient
		Patient patientDetails = session.load(Patient.class, (Integer) interBloodTrans.get("patient_id"));
		appoint.setPatient(patientDetails);
		// Load Department
		Department departmentDetails = session.load(Department.class, (Integer) interBloodTrans.get("department_id"));
		appoint.setDepartment(departmentDetails);
		
		//Have to do (Check If blood available in blood bank)
		Query query = session.createQuery("delete BloodBank where blood_bag_number = :bloodBagNumber");
		query.setParameter("bloodBagNumber", interBloodTrans.get("bloodBagNumber"));
		query.executeUpdate();
		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save interBloodTranss");
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

	public JSONObject listInterBloodTransfusion() {
		System.out.println("Inside Dao1InterBloodTransfusion");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<InterBloodTransfusion> interBloodTransList = null;
		try {
			Query query = session.createQuery("FROM InterBloodTransfusion");
			interBloodTransList = query.list();
			status.put("InterBloodTransfusion", interBloodTransList);
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

	public JSONObject updateInterBloodTransfusion(JSONObject interBloodTrans) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			InterBloodTransfusion interBloodTransDetails = session.load(InterBloodTransfusion.class,
					(Integer) interBloodTrans.get("interBloodTransId"));
			session.update(interBloodTransDetails);
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

	public InterBloodTransfusion getInterBloodTransfusion(Integer interBloodTransId) {
		InterBloodTransfusion interBloodTrans = null;
		try {
			session.beginTransaction();
			session.get(InterBloodTransfusion.class, interBloodTransId);
			interBloodTrans = (InterBloodTransfusion) session.get(InterBloodTransfusion.class, interBloodTransId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		if (interBloodTrans != null) {
			return interBloodTrans;
		} else {
			return null;
		}
	}

	public JSONObject deleteInterBloodTransfusion(JSONObject interBloodTransId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			InterBloodTransfusion interBloodTransDetails = session.load(InterBloodTransfusion.class,
					(Integer) interBloodTransId.get("interBloodTransId"));
			session.delete(interBloodTransDetails);
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
