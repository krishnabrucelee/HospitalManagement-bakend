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
import com.hospital.model.Department;
import com.hospital.model.Doctor;

/**
 * @author Krishna
 *
 */
@Repository
public class DoctorDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class DoctorDao executed");
	}
	
	private Session session = null;
	private Transaction transaction = null;
	
	@SuppressWarnings("unchecked")
	public JSONObject addDoctor(JSONObject doctor) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Doctor user = om.convertValue(doctor, Doctor.class);
		//Load Department 
		Department departmentDetails = session.load(Department.class, (Integer) doctor.get("department_id"));
		user.setDepartment(departmentDetails);
		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(user);
			transaction.commit();
			System.out.println("Save doctors");
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

	public JSONObject listDoctor() {
		System.out.println("Inside Dao1Doctor");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Doctor> doctorList = null;
		try {
			Query query = session.createQuery("FROM Doctor");
			doctorList = query.list();
			status.put("Doctor", doctorList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

	public JSONObject updateDoctor(JSONObject doctor) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Doctor doctorDetails = session.load(Doctor.class, (Integer) doctor.get("doctorId"));
			doctorDetails.setDoctorDescription((String) doctor.get("doctorDescription"));
			//Load Department 
			Department departmentDetails = session.load(Department.class, (Integer) doctor.get("department_id"));
			doctorDetails.setDepartment(departmentDetails);
			session.update(doctorDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public Doctor getDoctor(Integer doctorId) {
		Doctor doctor = null;
		try {
			session.beginTransaction();
			session.get(Doctor.class, doctorId);
			doctor = (Doctor) session.get(Doctor.class, doctorId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (doctor != null) {
			return doctor;
		} else {
			return null;
		}
	}

	public JSONObject deleteDoctor(JSONObject doctorId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Doctor doctorDetails = session.load(Doctor.class, (Integer) doctorId.get("doctor_id"));
			session.delete(doctorDetails);
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
	 * @param doctor
	 * @return
	 */
	public Doctor addDoctorFromStaff(Doctor doctor) {
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(doctor);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctor;
	}

}
