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
import com.hospital.model.BloodReturn;
import com.hospital.model.Department;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import com.hospital.service.BloodBankService;
import com.hospital.service.InterBloodTransfusionService;

/**
 * @author Krishna
 *
 */
@Repository
public class BloodReturnDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	private InterBloodTransfusionService interBloodTransService;
	
	static {
		System.out.println("class BloodReturnDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addBloodReturn(JSONObject bloodReturn) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		BloodReturn appoint = om.convertValue(bloodReturn, BloodReturn.class);
		// Load Department
		BloodBank bloodBankDetails = session.load(BloodBank.class, (Integer) bloodReturn.get("blood_bank_id"));
//		appoint.setBloodBank(bloodBankDetails);
		appoint.setStaus(BloodReturn.Status.valueOf(bloodReturn.get("status").toString()));
		// Load Department
		Department departmentDetails = session.load(Department.class, (Integer) bloodReturn.get("department_id"));
		appoint.setDepartment(departmentDetails);
		
		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save bloodReturns");
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

	public JSONObject listBloodReturn() {
		System.out.println("Inside Dao1BloodReturn");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<BloodReturn> bloodReturnList = null;
		try {
			Query query = session.createQuery("FROM BloodReturn");
			bloodReturnList = query.list();
			status.put("BloodReturn", bloodReturnList);
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

	public JSONObject updateBloodReturn(JSONObject bloodReturn) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			BloodReturn bloodReturnDetails = session.load(BloodReturn.class,
					(Integer) bloodReturn.get("bloodReturnId"));
			session.update(bloodReturnDetails);
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

	public BloodReturn getBloodReturn(Integer bloodReturnId) {
		BloodReturn bloodReturn = null;
		try {
			session.beginTransaction();
			session.get(BloodReturn.class, bloodReturnId);
			bloodReturn = (BloodReturn) session.get(BloodReturn.class, bloodReturnId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		if (bloodReturn != null) {
			return bloodReturn;
		} else {
			return null;
		}
	}

	public JSONObject deleteBloodReturn(JSONObject bloodReturnId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			BloodReturn bloodReturnDetails = session.load(BloodReturn.class,
					(Integer) bloodReturnId.get("bloodReturnId"));
			session.delete(bloodReturnDetails);
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
