package com.hospital.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.enums.STATUS;
import com.hospital.model.Staff;
import com.hospital.model.equipmenttracking.Building;
import com.hospital.model.equipmenttracking.Device;
import com.hospital.model.equipmenttracking.Equipment;
import com.hospital.model.equipmenttracking.EquipmentMaintanaceRequest;
import com.hospital.model.equipmenttracking.EquipmentMaintanaceStatus;
import com.hospital.model.equipmenttracking.MaintananceConfiguration;
import com.hospital.model.equipmenttracking.RoomData;

@Repository
public class EquipmentDaos {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public JSONObject saveDevice(JSONObject devicedata) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   Device device = om.convertValue(devicedata,  Device.class);
		try {		
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(device);
			session.getTransaction().commit();
			status.put("status", true);
			
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return status;
	}

	
	//static Logger 
	@SuppressWarnings("unchecked")
	public JSONObject saveBuildingFloorRoom(JSONObject buildingFloorRoom) {
		
		// TODO remove method calling and return
		/*JSONObject resultStatus = saveEquipment(buildingFloorRoom);
		if(resultStatus.containsKey("status"))
			return resultStatus;
		*/
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   Building building = om.convertValue(buildingFloorRoom,  Building.class);
		try {		
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(building);
			session.getTransaction().commit();
			status.put("status", true);
			
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return status;
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject saveEquipment(JSONObject equimentDetails){
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null; Date mfgDate=null;
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		try {
			mfgDate= sdf.parse(equimentDetails.get("manufactureDate").toString());
				
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {	
			
			/**
			 *  1. Load Device 	--> deviceId
				2. Load RoomData --> roomId	
				3. Load MaintananceConfiguration --> maintanaceConfigId
			 */
			
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			
			Device deviceDetails = session.get(Device.class,(int)equimentDetails.get("deviceId"));
			//deviceDetails.setDeviceId((int)equimentDetails.get("deviceId"));
			
			/*Device deviceDetails = session.get(Device.class,(int)equimentDetails.get("deviceId"));
			
			if(deviceDetails == null)
			{
				status.put("status",false);
				status.put("reason","Device not found. Please check deviceId field");
				return status;
			}
			*/
			RoomData roomDataDetails = session.get(RoomData.class,(int)equimentDetails.get("roomId"));
			
			if(roomDataDetails == null)
			{
				status.put("status",false);
				status.put("reason","RoomData not found. Please check roomId field");
				return status;
			}
			
			MaintananceConfiguration maintanceConfigDetails = session.get(MaintananceConfiguration.class,(int)equimentDetails.get("maintanaceConfigId"));
			
			if(maintanceConfigDetails == null)
			{
				status.put("status",false);
				status.put("reason","MaintananceConfiguration not found. Please check maintanaceConfigId field");
				return status;
			}
			
			Equipment equipment = om.convertValue(equimentDetails,  Equipment.class);
			equipment.setManufactureDate(mfgDate);
			equipment.setDevice(deviceDetails);
			equipment.setRoomData(roomDataDetails);
			equipment.setMaintanceConfiguration(maintanceConfigDetails);	
			
			session.save(equipment);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return status;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject saveMaintanaceConfiguration(JSONObject maintanaceConfiguration) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   MaintananceConfiguration maintanace = om.convertValue(maintanaceConfiguration,  MaintananceConfiguration.class);
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(maintanace);
			session.getTransaction().commit();
			status.put("status", true);
			
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return status;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject listDevice() {
		JSONObject status = new JSONObject();
		status.put("status", true);	
		Session session = null;
		List<Device> device =null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM Device");
			device = query.list();	
			
			if (device!=null && !device.isEmpty()) {
				status.put("DeviceList", device);
				status.put("status", true);	
				
			} else {
				status.put("status", "Error happened");
				status.put("originalErrorMsg", "Device table have empty");
				status.put("status", false);
			}
		} catch (Exception e) {
			status.put("status", "Error happened");
			status.put("originalErrorMsg", e.getMessage());
			status.put("status", false);
			e.printStackTrace();
				
		}finally{
			closeSession(session);
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	public JSONObject listMaintanaceConfiguration() {
		JSONObject status = new JSONObject();
		status.put("status", true);	
		Session session = null;
		List<MaintananceConfiguration> maintanance =null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM MaintananceConfiguration");
			maintanance = query.list();	
			
			if (maintanance!=null && !maintanance.isEmpty()) {
				status.put("MaintananceConfiguration", maintanance);
				status.put("status", true);	
				
			} else {
				status.put("status", "Error happened");
				status.put("originalErrorMsg", "MaintananceConfiguration table have empty");
				status.put("status", false);
			}
		} catch (Exception e) {
			status.put("status", "Error happened");
			status.put("originalErrorMsg", e.getMessage());
			status.put("status", false);
			e.printStackTrace();
				
		}finally{
			closeSession(session);
		}
		return status;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject listBuildig() {
		JSONObject status = new JSONObject();
		status.put("status", true);	
		Session session = null;
		List<Building> buildinglist =null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM Building");
			buildinglist = query.list();	
			
			if (buildinglist!=null && !buildinglist.isEmpty()) {
				status.put("BuildingList", buildinglist);
				status.put("status", true);	
				
			} else {
				status.put("status", "Error happened");
				status.put("originalErrorMsg", "MaintananceConfiguration table have empty");
				status.put("status", false);
			}
		} catch (Exception e) {
			status.put("status", "Error happened");
			status.put("originalErrorMsg", e.getMessage());
			status.put("status", false);
			e.printStackTrace();
				
		}finally{
			closeSession(session);
		}
		return status;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getGroupByEquipment() {
		JSONObject status = new JSONObject();
		status.put("status", true);	
		Session session = null;	
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			
		Criteria cri = session.createCriteria(Equipment.class);
		cri.setFetchMode("maintanceConfiguration", FetchMode.JOIN);
		cri.createAlias("device", "device");	
		
		List<Equipment> equimentDetails = cri.list();
		
		Map<String,List<Equipment>> groupByEquimenDetails = equimentDetails.stream().collect(Collectors.groupingBy(v-> {			
			return v.getDevice().getDeviceName();
		}));
		
		status.put("result",groupByEquimenDetails);		
			
		} catch (Exception e) {
			status.put("status", "Error happened");
			status.put("originalErrorMsg", e.getMessage());
			status.put("status", false);
			e.printStackTrace();
				
		}finally{
			closeSession(session);
		}
		return status;
	}
		
	@SuppressWarnings("unchecked")
	public JSONObject getLastEquipmentCheck(JSONObject lastCheckt) {
		JSONObject status = new JSONObject();
		status.put("status", true);	
		Session session = null;	
		List<EquipmentMaintanaceRequest> request=null;EquipmentMaintanaceRequest maintanaceRequest=null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();			
			Equipment equipment=session.get(Equipment.class, (Integer)lastCheckt.get("equipmentId"));
			MaintananceConfiguration configid = equipment.getMaintanceConfiguration();			
			Integer days  = configid.getPeriodDays();				
	String query="FROM EquipmentMaintanaceRequest WHERE equipmentId='"+lastCheckt.get("equipmentId")+"' ORDER BY requestDate DESC";	
			request = session.createQuery(query).list();
			if (request!=null && !request.isEmpty()) {
				maintanaceRequest = request.get(0);				
				Date checkDate = maintanaceRequest.getRequestDate();																
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Calendar c = Calendar.getInstance();
				c.setTime(checkDate);
				c.add(Calendar.DATE, days); 
				Date d = c.getTime();//dbincrementdate				
				Date dt = new Date();
				
				String config = sdf.format(d);
				
				Date incrementconfig = sdf.parse(config);//date+dbincrement days date		
				
				String daa = sdf.format(dt);//current date
				
				Date da = sdf.parse(daa);//format dd=MM-yyyy
				
				System.out.println("###Current Date forma t=="+da);
				
				System.out.println("###DB Incre Date format=="+incrementconfig);
				
				if (da.before(incrementconfig)) {
					System.out.println("Check properly");
					status.put("LastCheck", "Check properly");
					//status.put("EquipmentMaintanaceRequest", maintanaceRequest);
					status.put("status", true);	
				} else {
					status.put("LastCheck", "Not Check properly");
					
				}
				
				/*status.put("EquipmentMaintanaceRequest", maintanaceRequest);
				status.put("status", true);	*/
			} else {
				status.put("status", "Error happened");
				status.put("originalErrorMsg", "EquipmentMaintanaceRequest table have empty");
				status.put("status", false);
			}
			
		} catch (Exception e) {
			status.put("status", "Error happened");
			status.put("originalErrorMsg", e.getMessage());
			status.put("status", false);
			e.printStackTrace();
				
		}finally{
			closeSession(session);
		}
		return status;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject saveEquipmentCheckRequest(JSONObject equipmentCheckRequest) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   EquipmentMaintanaceRequest maintanaceRequest = om.convertValue(equipmentCheckRequest,  EquipmentMaintanaceRequest.class);
		   
		   maintanaceRequest.setRequestDate(new Date());
		   maintanaceRequest.setStatus(STATUS.PENDING);
		   try {		
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			
			  Equipment equipment = session.get(Equipment.class, (int)equipmentCheckRequest.get("equipmentId"));
			  if (equipment!=null) {
					maintanaceRequest.setEquipmentdata(equipment);
				} else {
					status.put("status", "Error happened");
					status.put("originalErrorMsg", "Equipment table have empty");
					status.put("status", false);
				}
			
			Staff staff = session.get(Staff.class, (int)equipmentCheckRequest.get("staffId"));
			if (staff!=null) {
				maintanaceRequest.setAssignedStaff(staff);
			} else {
				status.put("status", "Error happened");
				status.put("originalErrorMsg", "Staff table have empty");
				status.put("status", false);
			}
			
			session.save(maintanaceRequest);
			session.getTransaction().commit();
			status.put("status", true);
			
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return status;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getEquipmentCheckPending() {
		JSONObject status = new JSONObject();
		status.put("status", true);	
		Session session = null;
		List<EquipmentMaintanaceRequest> equipmentMaintanacepending =null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM EquipmentMaintanaceRequest WHERE status=:searchA");
			equipmentMaintanacepending = query.setParameter("searchA", STATUS.PENDING).list();				
			System.out.println("EquipmentMaintanacepending="+equipmentMaintanacepending.size());
			if (equipmentMaintanacepending!=null && !equipmentMaintanacepending.isEmpty()) {
				status.put("EquipmentCheckPending", equipmentMaintanacepending);
				status.put("status", true);	
				
			} else {
				status.put("status", "Error happened");
				status.put("originalErrorMsg", "EquipmentMaintanaceRequest table have empty");
				status.put("status", false);
			}
		} catch (Exception e) {
			status.put("status", "Error happened");
			status.put("originalErrorMsg", e.getMessage());
			status.put("status", false);
			e.printStackTrace();
				
		}finally{
			closeSession(session);
		}
		return status;
	}
	
	

	@SuppressWarnings("unchecked")
	public JSONObject updateEquipmentCheckStatus(JSONObject equipmentCheck) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   EquipmentMaintanaceStatus maintanaceRequestUpdate = om.convertValue(equipmentCheck,  EquipmentMaintanaceStatus.class);		   
		   maintanaceRequestUpdate.setUpdatedDate(new Date());
		   maintanaceRequestUpdate.setStatus(STATUS.COMPLETED);
		   try {		
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();			
			session.save(maintanaceRequestUpdate);
			
			EquipmentMaintanaceRequest request = session.get(EquipmentMaintanaceRequest.class, (int)equipmentCheck.get("equipmentMaintanaceRequestId"));
			request.setEquipmentMaintanaceStatus(maintanaceRequestUpdate);
			request.setStatus(STATUS.COMPLETED);
			session.getTransaction().commit();
			status.put("status", true);			
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return status;
	}
	
	private static void closeSession(Session session){
		if(session!=null&&session.isOpen()){
			
			session.close();
		}		
	}
}
