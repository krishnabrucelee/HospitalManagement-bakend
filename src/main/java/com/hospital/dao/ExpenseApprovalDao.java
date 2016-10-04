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
import com.hospital.model.ExpenseApproval;
import com.hospital.model.Department;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;

/**
 * @author Krishna
 *
 */

@Repository	
public class ExpenseApprovalDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class ExpenseApprovalDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addExpenseApproval(JSONObject expenseApproval) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		ExpenseApproval appoint = om.convertValue(expenseApproval, ExpenseApproval.class);
		// Load Department
		Department departmentDetails = session.load(Department.class, (Integer) expenseApproval.get("department_id"));
		appoint.setDepartment(departmentDetails);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save expenseApprovals");
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

	public JSONObject listExpenseApproval() {
		System.out.println("Inside Dao1ExpenseApproval");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<ExpenseApproval> expenseApprovalList = null;
		try {
			Query query = session.createQuery("FROM ExpenseApproval");
			expenseApprovalList = query.list();
			status.put("ExpenseApproval", expenseApprovalList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

	public JSONObject updateExpenseApproval(JSONObject expenseApproval) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			ExpenseApproval expenseApprovalDetails = session.load(ExpenseApproval.class,
					(Integer) expenseApproval.get("expenseApprovalId"));
			session.update(expenseApprovalDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public ExpenseApproval getExpenseApproval(Integer expenseApprovalId) {
		ExpenseApproval expenseApproval = null;
		try {
			session.beginTransaction();
			session.get(ExpenseApproval.class, expenseApprovalId);
			expenseApproval = (ExpenseApproval) session.get(ExpenseApproval.class, expenseApprovalId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (expenseApproval != null) {
			return expenseApproval;
		} else {
			return null;
		}
	}

	public JSONObject deleteExpenseApproval(JSONObject expenseApprovalId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			ExpenseApproval expenseApprovalDetails = session.load(ExpenseApproval.class,
					(Integer) expenseApprovalId.get("expenseApprovalId"));
			session.delete(expenseApprovalDetails);
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
