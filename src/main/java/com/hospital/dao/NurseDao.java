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
import com.hospital.model.Nurse;
import com.hospital.model.Nurse;
import com.hospital.model.InPatient;

/**
 * @author Krishna
 *
 */
@Repository
public class NurseDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class NurseDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addNurse(JSONObject nurse) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Nurse appoint = om.convertValue(nurse, Nurse.class);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save nurses");
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

	public JSONObject listNurse() {
		System.out.println("Inside Dao1Nurse");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Nurse> nurseList = null;
		try {
			Query query = session.createQuery("FROM Nurse");
			nurseList = query.list();
			status.put("Nurse", nurseList);
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

	public JSONObject updateNurse(JSONObject nurse) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Nurse nurseDetails = session.load(Nurse.class,
					(Integer) nurse.get("nurseId"));
			session.update(nurseDetails);
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

	public Nurse getNurse(Integer nurseId) {
		Nurse nurse = null;
		try {
			session.beginTransaction();
			session.get(Nurse.class, nurseId);
			nurse = (Nurse) session.get(Nurse.class, nurseId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (nurse != null) {
			return nurse;
		} else {
			return null;
		}
	}

	public JSONObject deleteNurse(JSONObject nurseId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Nurse nurseDetails = session.load(Nurse.class,
					(Integer) nurseId.get("nurseId"));
			session.delete(nurseDetails);
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
	 * @param nurse
	 * @return
	 */
	public Nurse addNurseFromStaff(Nurse nurse) {
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(nurse);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return nurse;
	}

	/**
	 * @param nurseEmail
	 * @return
	 */
	public JSONObject getNurseByEmail(JSONObject nurseEmail) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Nurse> nurseDetailsList = null;
		try {
			Query query = session.createQuery("FROM Nurse WHERE nurse_email = :email");
			query.setParameter("email", nurseEmail.get("email").toString());
			nurseDetailsList = query.list();
			status.put("Nurse", nurseDetailsList.iterator());
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
