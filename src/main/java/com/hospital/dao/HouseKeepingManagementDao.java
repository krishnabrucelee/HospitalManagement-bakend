package com.hospital.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.EquipmentData;
import com.hospital.model.HouseKeepingItemMaster;
import com.hospital.model.LaundryProcessState;
import com.hospital.model.RequestHouseKeep;
@Repository
public class HouseKeepingManagementDao {
	@Autowired
	SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")
	public JSONObject saveHouseKeepItemMaster(JSONObject itemMaster) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   HouseKeepingItemMaster houseKeepItemMaster = om.convertValue(itemMaster, HouseKeepingItemMaster.class);
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(houseKeepItemMaster);
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
	public JSONObject laundryprocessState(JSONObject processState) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   LaundryProcessState laundryProcessState = om.convertValue(processState, LaundryProcessState.class);
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(laundryProcessState);
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
	public JSONObject requestHouseKeep(JSONObject houseKeepData) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   RequestHouseKeep houseKeepRequest = om.convertValue(houseKeepData,  RequestHouseKeep.class);
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(houseKeepRequest);		
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
	public JSONObject getHouseKeepRequestId(JSONObject houseKeepId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		Integer houseKeeppId = (Integer)houseKeepId.get("houseKeepRequestId");
		try {		
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			RequestHouseKeep houseKeepRequest	=(RequestHouseKeep)session.get(RequestHouseKeep.class, houseKeeppId);
			System.out.println("HouseKeepRequest="+houseKeepRequest);
			session.getTransaction().commit();
			if(houseKeepRequest!=null){
				status.put("HouseKeepRequest", houseKeepRequest);							
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
	public JSONObject houseKeepRequestUpdate(JSONObject houseKeepUpdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
		Date datee =null;
		try {
			datee = sdf.parse(houseKeepUpdate.get("issuetoLaundryDate").toString());			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		Integer houseKeepId = (Integer)houseKeepUpdate.get("houseKeepRequestId");
		String state = houseKeepUpdate.get("giveToLaundaryStatus").toString();
		System.out.println("houseKeepId="+houseKeepId);
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("update RequestHouseKeep set issuetoLaundryDate=:searchA , giveToLaundaryStatus=:searchB where houseKeep_RequestId=:searchC");
			query.setParameter("searchA", datee).setParameter("searchB", state).setParameter("searchC", houseKeepId);
			int i = query.executeUpdate();			
			session.getTransaction().commit();		
			status.put("status", true);
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
}
