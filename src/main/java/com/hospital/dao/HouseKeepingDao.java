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
import com.hospital.model.HouseKeeping;

/**
 * @author Krishna
 *
 */
@Repository
public class HouseKeepingDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class HouseKeepingDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addHouseKeeping(JSONObject houseKeeping) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		HouseKeeping appoint = om.convertValue(houseKeeping, HouseKeeping.class);
		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save houseKeepings");
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

	public JSONObject listHouseKeeping() {
		System.out.println("Inside Dao1HouseKeeping");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<HouseKeeping> houseKeepingList = null;
		try {
			Query query = session.createQuery("FROM HouseKeeping");
			houseKeepingList = query.list();
			status.put("HouseKeeping", houseKeepingList);
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

	public JSONObject updateHouseKeeping(JSONObject houseKeeping) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			HouseKeeping houseKeepingDetails = session.load(HouseKeeping.class,
					(Integer) houseKeeping.get("houseKeepingId"));
			session.update(houseKeepingDetails);
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

	public HouseKeeping getHouseKeeping(Integer houseKeepingId) {
		HouseKeeping houseKeeping = null;
		try {
			session.beginTransaction();
			session.get(HouseKeeping.class, houseKeepingId);
			houseKeeping = (HouseKeeping) session.get(HouseKeeping.class, houseKeepingId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (houseKeeping != null) {
			return houseKeeping;
		} else {
			return null;
		}
	}

	public JSONObject deleteHouseKeeping(JSONObject houseKeepingId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			HouseKeeping houseKeepingDetails = session.load(HouseKeeping.class,
					(Integer) houseKeepingId.get("houseKeepingId"));
			session.delete(houseKeepingDetails);
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
	 * @param houseKeeping
	 * @return
	 */
	public HouseKeeping addHouseKeepingFromStaff(HouseKeeping houseKeeping) {
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(houseKeeping);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return houseKeeping;
	}

}
