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
import com.hospital.model.Cssd;
import com.hospital.model.Cssd.Status;
import com.hospital.model.Department;
import com.hospital.model.Doctor.DoctorType;
import com.hospital.util.DateUtil;

/**
 * @author Krishna
 *
 */
@Repository
public class CssdDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class CssdDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addCssd(JSONObject cssd) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Cssd appoint = om.convertValue(cssd, Cssd.class);
		// Load Department
		Department departmentDetails = session.load(Department.class, (Integer) cssd.get("department_id"));
		appoint.setDepartment(departmentDetails);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.merge(appoint);
			transaction.commit();
			System.out.println("Save cssds");
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

	public JSONObject listCssd() {
		System.out.println("Inside Dao1Cssd");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Cssd> cssdList = null;
		try {
			Query query = session.createQuery("FROM Cssd");
			cssdList = query.list();
			status.put("Cssd", cssdList);
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

	public JSONObject updateCssd(JSONObject cssd) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Cssd cssdDetails = session.load(Cssd.class,
					(Integer) cssd.get("cssdId"));
			cssdDetails.setReturnDate(DateUtil.dateTimeUtil(cssd.get("returnDate").toString()));
			
			if (cssd.get("status").toString().equals(Status.STERILIZED.toString())) {
				cssdDetails.setStatus(Status.STERILIZED);
			} 
			if (cssd.get("status").toString().equals(Status.UNSTERILIZED.toString())) {
				cssdDetails.setStatus(Status.UNSTERILIZED);
			} 
			session.update(cssdDetails);
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

	public Cssd getCssd(Integer cssdId) {
		Cssd cssd = null;
		try {
			session.beginTransaction();
			session.get(Cssd.class, cssdId);
			cssd = (Cssd) session.get(Cssd.class, cssdId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		if (cssd != null) {
			return cssd;
		} else {
			return null;
		}
	}

	public JSONObject deleteCssd(JSONObject cssdId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Cssd cssdDetails = session.load(Cssd.class,
					(Integer) cssdId.get("cssdId"));
			session.delete(cssdDetails);
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
