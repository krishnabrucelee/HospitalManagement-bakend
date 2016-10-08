package com.hospital.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.EquipmentCheckData;
import com.hospital.model.EquipmentCheckRequest;
import com.hospital.model.EquipmentData;
import com.hospital.model.HouseKeepingItemMaster;
import com.hospital.model.LabTest;
import com.hospital.model.MaintananceSchedule;
import com.hospital.model.MaintananceScheduling;
import com.hospital.model.OrderRepairProduct;
import com.hospital.model.PeriodicScheduleMaintanaceData;

@Repository
public class EquipmentDao {
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public JSONObject saveEuipment(JSONObject equipmentdata) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   EquipmentData equipmentdatas = om.convertValue(equipmentdata,  EquipmentData.class);
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(equipmentdatas);
			session.getTransaction().commit();
			status.put("status", true);
			System.out.println("Aftyer save");
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject viewEuipment() {
		System.out.println("Inside  saveEuipment");
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		List<EquipmentData> equipmentDatas =null;	
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			equipmentDatas = session.createQuery("FROM EquipmentData").list();
			session.getTransaction().commit();
			if(equipmentDatas!=null &&!equipmentDatas.isEmpty() ){
				status.put("Equipment", equipmentDatas);
			}		
			status.put("status", true);
			System.out.println("  saveEuipment saved");
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject getEuipmentById(JSONObject equipmentId) {		
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		Integer eqipmentid = (Integer)equipmentId.get("equipmentId");
		System.out.println("equipmentId="+equipmentId);
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			EquipmentData equipment	=(EquipmentData)session.get(EquipmentData.class, eqipmentid);
			System.out.println("Equipment data="+equipment);
			session.getTransaction().commit();
			if(equipment!=null){
				status.put("Equipmentt", equipment);
			}
			status.put("status", true);
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject requestEuipmentChecklist(JSONObject euipmentChecklist) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);		 
		   EquipmentCheckRequest equipmentCheckList = om.convertValue(euipmentChecklist, EquipmentCheckRequest.class);
		   try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(equipmentCheckList);
			session.getTransaction().commit();
			status.put("status", true);
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject viewEuipmentChecklist() {
		System.out.println("Inside viewEuipmentChecklist");
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		List< EquipmentCheckRequest> equipmentCheckLists =null;	
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			equipmentCheckLists = session.createQuery("FROM  EquipmentCheckRequest").list();
			session.getTransaction().commit();
			if(equipmentCheckLists!=null &&!equipmentCheckLists.isEmpty() ){
				status.put("EquipmentCheck", equipmentCheckLists);
			}		
			status.put("status", true);
			System.out.println("  viewEuipmentChecklist");
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject getEuipmentChecklistById(JSONObject checklistrequest) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		Integer equipCheckrequestId = (Integer)checklistrequest.get("equipCheckRequestId");
		System.out.println("equipmentId="+equipCheckrequestId);
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			 EquipmentCheckRequest equipmentCheckList	=( EquipmentCheckRequest)session.get( EquipmentCheckRequest.class, equipCheckrequestId);
			System.out.println("Equipment data="+equipmentCheckList);
			session.getTransaction().commit();
			if(equipmentCheckList!=null){
				status.put("EquipmentCheckList",equipmentCheckList);
			}
			status.put("status", true);
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject updateEquipmentCheckRequest(JSONObject updateStatus) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		String receivestatus= updateStatus.get("receiveStatus").toString();
		String returnstatus= updateStatus.get("returnStatus").toString();
		Integer reqid = (Integer)updateStatus.get("equipCheckRequestId");
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();	
			Query query =session.createQuery("update EquipmentCheckRequest set receiveStatus=:searchA  ,returnStatus=:searchB where equipCheckRequestId=:searchC");
			query.setParameter("searchA", receivestatus); 
			query.setParameter("searchB", returnstatus); 
			query.setParameter("searchC", reqid); 
			query.executeUpdate();
			session.getTransaction().commit();
			status.put("status", true);
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	
	////////***ADMIN CONFIGURE PERMANENT***/////////Insert is done but not select methoad//
	@SuppressWarnings("unchecked")
	public JSONObject saveEquipmentCheckMaster(JSONObject equipmentCheckMaster) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   EquipmentCheckData equipmentCheckData= om.convertValue(equipmentCheckMaster, EquipmentCheckData.class);
		try {	
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(equipmentCheckData);
			session.getTransaction().commit();
			status.put("status", true);		
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject saveMaintananceSchedule(JSONObject maintananceSchedule) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   MaintananceSchedule maintananceSchedules= om.convertValue(maintananceSchedule, MaintananceSchedule.class);
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(maintananceSchedules);
			session.getTransaction().commit();
			status.put("status", true);
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject orderEquipmentParts(JSONObject orderEquipmentParts) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   OrderRepairProduct maintananceSchedules= om.convertValue(orderEquipmentParts, OrderRepairProduct.class);
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(maintananceSchedules);
			session.getTransaction().commit();
			status.put("status", true);
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject viewOrderEquipmentParts() {	
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		List< OrderRepairProduct> orderRepairProductLists =null;	
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			orderRepairProductLists = session.createQuery("FROM  OrderRepairProduct").list();
			session.getTransaction().commit();
			if(orderRepairProductLists!=null &&!orderRepairProductLists.isEmpty() ){
				status.put("OrderRepairProduct", orderRepairProductLists);
			}		
			status.put("status", true);	
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject scheduleMaintanance(JSONObject scheduleMaintanance) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   MaintananceScheduling maintananceScheduling= om.convertValue(scheduleMaintanance, MaintananceScheduling.class);
		try {
			System.out.println("Inside try");
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(maintananceScheduling);
			session.getTransaction().commit();
			status.put("status", true);
			System.out.println("After save");
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject getScheduleMaintananceById(JSONObject maintananceschedule) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		Integer scheduleId = (Integer)maintananceschedule.get("maintananceScheduleId");
		//List< OrderRepairProduct> orderRepairProductLists =null;	
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			MaintananceScheduling maintananceScheduling	=(MaintananceScheduling)session.get(MaintananceScheduling.class, scheduleId);	
			//orderRepairProductLists = session.createQuery("FROM  OrderRepairProduct").list();
			session.getTransaction().commit();
			if(maintananceScheduling!=null  ){
				status.put("MaintananceScheduling", maintananceScheduling);
			}		
			status.put("status", true);	
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject saveRoutinescheduleCheckData(
			JSONObject routinescheduleCheckData) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   PeriodicScheduleMaintanaceData  periodicMaintanaceData= om.convertValue(routinescheduleCheckData, PeriodicScheduleMaintanaceData.class);
		try {
			System.out.println("Inside try");
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(periodicMaintanaceData);
			session.getTransaction().commit();
			status.put("status", true);
			System.out.println("After save");
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
}
