package com.hospital.dao;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.hospital.model.AdmitPatient;
import com.hospital.model.Floor;
import com.hospital.model.Patient;
import com.hospital.model.RoomManagement;
import com.hospital.service.AdmitPatientService;
import com.hospital.service.FloorService;
import com.hospital.service.RoomManagementService;

/**
 * Patient Dao
 * 
 * @author Krishna
 *
 */
@Repository
public class PatientDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	private AdmitPatientService admitPatientService;
	
	@Autowired
	private RoomManagementService roomManagementService;
	
	@Autowired
	private FloorService floorService;

	static {
		System.out.println("class PatientDao executed");
	}
	
	private Session session = null;
	private Transaction transaction = null;
	
	@SuppressWarnings("unchecked")
	public JSONObject addPatient(JSONObject patient) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Patient patientDetails = om.convertValue(patient, Patient.class);
		patientDetails.setPatientRefNumber(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));
		patientDetails.setCreatedDate(new Date());
		
		AdmitPatient admission = new AdmitPatient();
		admission.setPatientId(patientDetails.getPatientRefNumber());
		admission.setWardNumber(patientDetails.getWardNumber());
		admission.setCreatedDate(patientDetails.getCreatedDate());
		admitPatientService.addAdmission(admission);
		
		List<Floor> floorList = floorService.getRoomDetailsByWardNumber(patient);
		
		for(Floor floor : floorList) {
			System.out.println(floor.getRoomManagement());
			for(RoomManagement room : floor.getRoomManagement()) {
				System.out.println(room);
				if (room.getWardNumber().equals(patient.get("wardNumber"))) {
					room.setIsAvailable(true);
					roomManagementService.addRoomManagement(room);
				}
			}
		}
		
		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(patientDetails);
			transaction.commit();
			System.out.println("Save patients");
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

	public JSONObject listPatient() {
		System.out.println("Inside Dao1Patient");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Patient> patientList = null;
		try {
			Query query = session.createQuery("FROM Patient");
			patientList = query.list();
			System.out.println("DB PATIENT="+patientList);
			status.put("Patient", patientList);
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

	public JSONObject updatePatient(JSONObject patient) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Patient patientDetails = session.load(Patient.class, (Integer) patient.get("patientId"));
			patientDetails.setPatientName((String) patient.get("patientName"));
			session.update(patientDetails);
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

	public Patient getPatient(Integer patientId) {
		Patient patient = null;
		try {
			session.beginTransaction();
			session.get(Patient.class, patientId);
			patient = (Patient) session.get(Patient.class, patientId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (patient != null) {
			return patient;
		} else {
			return null;
		}
	}

	public JSONObject deletePatient(JSONObject patientId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Patient patientDetails = session.load(Patient.class, (Integer) patientId.get("patient_id"));
			session.delete(patientDetails);
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
	 * @param patient
	 * @return
	 */
	public JSONObject getPatientDetailsById(JSONObject patient) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Patient> patientDetailsList = null;
//		  ObjectMapper mapper = new ObjectMapper();
//        SimpleModule module = new SimpleModule();        
//        module.addSerializer(com.monitorjbl.json.JsonView.class,new JsonViewSerializer());
//        mapper.registerModule(module);
		Integer patientId = Integer.parseInt(patient.get("id").toString());
		try {
			Query query = session.createQuery("FROM Patient WHERE patient_id = :id");
			query.setParameter("id", patientId);
			patientDetailsList = query.list();
//			String json = mapper.writeValueAsString(JsonView.with(patientDetailsList)
//					.onClass(BusAvailability.class,com.monitorjbl.json.Match.match().exclude("busDetails")));  
//			
//			
//			System.out.println(json);
//             ArrayList<Object> convertedValue = mapper.readValue(json,new TypeReference<ArrayList<Object>>() {});
//                   
//			if(convertedValue.isEmpty() | convertedValue == null)
//			{
//				status.put("status",false);
//				return status;
//			}		
//			status.put("Buses", convertedValue.iterator());
			status.put("Patient", patientDetailsList.iterator());
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
