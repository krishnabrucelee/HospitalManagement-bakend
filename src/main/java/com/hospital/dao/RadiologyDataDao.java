package com.hospital.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Patient;
import com.hospital.model.Radiology;
import com.hospital.model.radiology.PatientRadiologyTest;
import com.hospital.model.radiology.RadiologyTest;

@Repository
public class RadiologyDataDao {
		
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	@Qualifier("jsonViewObjectMapper")
	ObjectMapper jsonViewObjectMapper;
		
	@SuppressWarnings("unchecked")
	public JSONObject addRadiologyTestName(JSONObject radiology) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   RadiologyTest radiologydatas = om.convertValue(radiology,  RadiologyTest.class);
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(radiologydatas);
			session.getTransaction().commit();
			status.put("status", true);
		} catch (Exception e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	public JSONObject listRadiologyTest() {
		return null;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getRadiologyTestNameById(JSONObject radiologyrequestid) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		List<RadiologyTest> radiologyDatas =null;	
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			radiologyDatas = session.createQuery("FROM RadiologyTest").list();
			session.getTransaction().commit();
			if(radiologyDatas!=null &&!radiologyDatas.isEmpty() ){
				status.put("RadiologyTest", radiologyDatas);
			}		
			status.put("status", true);
		} catch (Exception e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	public JSONObject updateRadiologyTestName(JSONObject radiology) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			RadiologyTest radiology2 = (RadiologyTest)session.get(RadiologyTest.class, (Integer) radiology.get("radiologyId"));
			ObjectMapper om = new ObjectMapper();
			   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);	
			   radiology2=om.convertValue(radiology,  RadiologyTest.class);	   
			session.merge(radiology2);
			session.getTransaction().commit();
		} catch (Exception e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	public JSONObject deleteRadiologyTest(JSONObject radiology) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			RadiologyTest radiology2 = (RadiologyTest)session.load(RadiologyTest.class, (Integer) radiology.get("radiologyId"));
			session.delete(radiology2);
			session.getTransaction().commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	
	@SuppressWarnings("unchecked")
	public JSONObject listPatientRadiologyTest() {
		JSONObject status = new JSONObject();
		System.out.println("listPatientRadiologyTest DAO");
		status.put("status", true);
		Session session = null;
		List<PatientRadiologyTest> radiologyRequest =null;	
		try {
			System.out.println("listPatientRadiologyTest DAO TRY");
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			radiologyRequest = session.createQuery("FROM PatientRadiologyTest").list();
			System.out.println("radiologyRequest SIZE="+radiologyRequest.size());
			System.out.println("radiologyRequest VALUES&&& ="+radiologyRequest);
			
			if(radiologyRequest!=null &&!radiologyRequest.isEmpty() ){
				String json = jsonViewObjectMapper.writeValueAsString(com.monitorjbl.json.JsonView.with(radiologyRequest).onClass(PatientRadiologyTest.class, 
				com.monitorjbl.json.Match.match().exclude("drugtopatient")));								
				radiologyRequest= jsonViewObjectMapper.readValue(json,new TypeReference<ArrayList<Object>>() {});
				status.put("PatientRadiologyRequest", radiologyRequest);
				status.put("status", true);
			}	else {
				status.put("status", "Error happened");
				status.put("originalErrorMsg", "PatientRadiologyTest table have empty");
				status.put("status", false);
			}	
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

}
