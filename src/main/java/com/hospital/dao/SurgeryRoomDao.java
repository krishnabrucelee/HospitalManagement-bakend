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
import com.hospital.model.SurgeryRoom;

/**
 * @author Krishna
 *
 */
@Repository
public class SurgeryRoomDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class SurgeryRoomDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addSurgeryRoom(JSONObject surgeryRoom) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		SurgeryRoom appoint = om.convertValue(surgeryRoom, SurgeryRoom.class);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save surgeryRooms");
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

	public JSONObject listSurgeryRoom() {
		System.out.println("Inside Dao1SurgeryRoom");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<SurgeryRoom> surgeryRoomList = null;
		try {
			Query query = session.createQuery("FROM SurgeryRoom");
			surgeryRoomList = query.list();
			status.put("SurgeryRoom", surgeryRoomList);
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

	public JSONObject updateSurgeryRoom(JSONObject surgeryRoom) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			SurgeryRoom surgeryRoomDetails = session.load(SurgeryRoom.class,
					(Integer) surgeryRoom.get("surgeryRoomId"));
			session.update(surgeryRoomDetails);
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

	public SurgeryRoom getSurgeryRoom(Integer surgeryRoomId) {
		SurgeryRoom surgeryRoom = null;
		try {
			session.beginTransaction();
			session.get(SurgeryRoom.class, surgeryRoomId);
			surgeryRoom = (SurgeryRoom) session.get(SurgeryRoom.class, surgeryRoomId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (surgeryRoom != null) {
			return surgeryRoom;
		} else {
			return null;
		}
	}

	public JSONObject deleteSurgeryRoom(JSONObject surgeryRoomId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			SurgeryRoom surgeryRoomDetails = session.load(SurgeryRoom.class,
					(Integer) surgeryRoomId.get("surgeryRoomId"));
			session.delete(surgeryRoomDetails);
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

}
