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
import com.hospital.model.LabTechnician;

/**
 * @author Krishna
 *
 */
@Repository
public class LabTechnicianDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class LabTechnicianDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addLabTechnician(JSONObject labTechnician) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		LabTechnician appoint = om.convertValue(labTechnician, LabTechnician.class);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save labTechnicians");
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

	public JSONObject listLabTechnician() {
		System.out.println("Inside Dao1LabTechnician");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<LabTechnician> labTechnicianList = null;
		try {
			Query query = session.createQuery("FROM LabTechnician");
			labTechnicianList = query.list();
			status.put("LabTechnician", labTechnicianList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

	public JSONObject updateLabTechnician(JSONObject labTechnician) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			LabTechnician labTechnicianDetails = session.load(LabTechnician.class,
					(Integer) labTechnician.get("labTechnicianId"));
			session.update(labTechnicianDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public LabTechnician getLabTechnician(Integer labTechnicianId) {
		LabTechnician labTechnician = null;
		try {
			session.beginTransaction();
			session.get(LabTechnician.class, labTechnicianId);
			labTechnician = (LabTechnician) session.get(LabTechnician.class, labTechnicianId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (labTechnician != null) {
			return labTechnician;
		} else {
			return null;
		}
	}

	public JSONObject deleteLabTechnician(JSONObject labTechnicianId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			LabTechnician labTechnicianDetails = session.load(LabTechnician.class,
					(Integer) labTechnicianId.get("labTechnicianId"));
			session.delete(labTechnicianDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	/**
	 * @param labTechnician
	 * @return
	 */
	public LabTechnician addLabTechnicianFromStaff(LabTechnician labTechnician) {
		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(labTechnician);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return labTechnician;
	}

}
