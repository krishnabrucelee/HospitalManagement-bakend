/**
 * 
 */
package com.hospital.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hospital.model.EMedicalReport;
import com.hospital.model.Floor;

/**
 * @author Krishna
 *
 */
@Repository
public class FloorDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class FloorDao executed");
	}
	
	private Session session = null;
	private Transaction transaction = null;
	
	@SuppressWarnings("unchecked")
	public JSONObject addFloor(JSONObject floor) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Floor user = om.convertValue(floor, Floor.class);
		
		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(user);
			transaction.commit();
			System.out.println("Save Floors");
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

	public JSONObject listFloor() {
		System.out.println("Inside Dao1Floor");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Floor> floorList = null;
		try {
			Query query = session.createQuery("FROM Floor");
			floorList = query.list();
			status.put("Floor", floorList);
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

	public JSONObject updateFloor(JSONObject floor) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Floor floorDetails = session.load(Floor.class, (Integer) floor.get("floorId"));
			// floorDetails.setFloorNumber((Integer) Floor.get("floorNumber"));
			session.update(floorDetails);
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

	public Floor getFloor(Integer floorId) {
		Floor floor = null;
		try {
			session.beginTransaction();
			floor = (Floor) session.get(Floor.class, floorId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (floor != null) {
			return floor;
		} else {
			return null;
		}
	}

	public JSONObject deleteFloor(JSONObject floorId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Floor floorDetails = session.load(Floor.class, (Integer) floorId.get("floorId"));
			System.out.println("dd" + floorDetails.getFloorId());
			session.delete(floorDetails);
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
	 * @param floor
	 * @return
	 */
	public JSONObject listRoomByFilter(JSONObject floor) {
		JSONObject status = new JSONObject();
		status.put("status",true);
		Session session = null;
		
		ObjectMapper mapper = new ObjectMapper();
		try {			
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();	
			String convetedDate = null;
			if (floor.get("classType") != null || floor.get("isAvailable") != null) {
			
				
			String hql = "FROM RoomManagement WHERE class_type = :classType and is_available = :isAvailable order by ward_number";
			Query query  = session.createQuery(hql);
			query.setParameter("classType", floor.get("classType"));
			query.setParameter("isAvailable", floor.get("isAvailable"));
			List<Floor> results = query.list();
                   
			status.put("Floors", results.iterator());
			System.out.println(" Inside Rest DAO Bus Status="+status);
			}
			return status;							
		} catch (Exception e) {
			status.put("status",false);
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
	 * @param floor
	 * @return
	 */
	public List<Floor> getRoomDetailsByWardNumber(JSONObject wardNumber) {
		JSONObject status = new JSONObject();
		status.put("status",true);
		Session session = null;
		List<Object[]> results = null;
		List<Floor> floorList = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		try {			
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();	
			String convetedDate = null;
			if (wardNumber.get("wardNumber") != null) {
			
			String hql = "FROM Floor floorDetails LEFT JOIN RoomManagement roomManagement ON roomManagement.wardNumber = '"+wardNumber.get("wardNumber")+"'";
			Query query  = session.createQuery(hql);
			results = (List<Object[]>) query.list();
                  
			for(Object[] tuple : results) {
			    System.out.println(((Floor) tuple[0]).getRoomManagement());
			    floorList.add(((Floor) tuple[0]));
			}
			
			status.put("Floors", results);
			System.out.println(" Inside Rest DAO Bus Status="+status);
			return floorList;	
			}
		} catch (Exception e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return floorList;		
	}

	/**
	 * @param object
	 * @return
	 */
	public JSONObject getRoomDetailsByWard(JSONObject wardNumber) {
		JSONObject status = new JSONObject();
		status.put("status",true);
		Session session = null;
		List<Object[]> results = null;
		List<Floor> floorList = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		try {			
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();	
			String convetedDate = null;
			if (wardNumber.get("wardNumber") != null) {
			
			Query query = session.createQuery("FROM RoomManagement WHERE ward_number = :wardNumber");
			query.setParameter("wardNumber", wardNumber.get("wardNumber"));
			floorList = query.list();
			
			status.put("Floors", floorList);
			System.out.println(" Inside Rest DAO Bus Status="+status);
			return status;	
			}
		} catch (Exception e) {
			status.put("status",false);
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
