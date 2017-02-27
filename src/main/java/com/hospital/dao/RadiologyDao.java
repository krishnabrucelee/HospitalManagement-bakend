package com.hospital.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import com.hospital.model.PatientRadiologyReport;
import com.hospital.model.PatientRadiologyTestNames;
import com.hospital.model.PatientRequestRadiologyTest;
import com.hospital.model.Radiology;
import com.hospital.model.radiology.RadiologyTest;
//import com.monitorjbl.json.JsonResult;
import com.monitorjbl.json.JsonViewSerializer;

/**
 * @author admin
 *
 */
@Repository
public class RadiologyDao {
	@Autowired
	SessionFactory sessionFactory;
	
    
   // private JsonResult json = JsonResult.instance();
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
	public JSONObject getRadiologyTestNameById(JSONObject radiologyrequestid) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		List<Radiology> radiologyData =null;	
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			radiologyData = session.createQuery("FROM Radiology WHERE radiologyId=:searchA").setParameter("searchA", (Integer)radiologyrequestid.get("radiologyId")).list();
			session.getTransaction().commit();
			if(radiologyData!=null &&!radiologyData.isEmpty() ){
				status.put("Radiology", radiologyData.get(0));
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
			Radiology radiology2 = (Radiology)session.get(Radiology.class, (Integer) radiology.get("radiologyId"));
			ObjectMapper om = new ObjectMapper();
			   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);	
			   radiology2=om.convertValue(radiology,  Radiology.class);	   
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
			Radiology radiology2 = (Radiology)session.load(Radiology.class, (Integer) radiology.get("radiologyId"));
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
	public JSONObject savePatientRequestRadiologyTest(JSONObject parientradiology) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   PatientRequestRadiologyTest patientradiologrequest = om.convertValue(parientradiology,  PatientRequestRadiologyTest.class);
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			Patient patient =(Patient)session.get(Patient.class, (Integer)parientradiology.get("patientId"));
			patientradiologrequest.setPatient(patient);
			Doctor doctor = (Doctor) session.get(Doctor.class, (Integer)parientradiology.get("doctorId"));
			patientradiologrequest.setDoctor(doctor);
			session.save(patientradiologrequest);
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
	public JSONObject listPatientRequestRadiologyTest() {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();		
		module.addSerializer(com.monitorjbl.json.JsonView.class,new JsonViewSerializer());
		mapper.registerModule(module);
		List<PatientRequestRadiologyTest> lists =new ArrayList<PatientRequestRadiologyTest>();	
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();	
			lists = session.createQuery("FROM PatientRequestRadiologyTest").list();	
			String json = mapper.writeValueAsString(com.monitorjbl.json.JsonView.with(lists).onClass(PatientRequestRadiologyTest.class, 
			com.monitorjbl.json.Match.match().exclude("patient","doctor")));			
			 ArrayList<Object> convertedValue = mapper.readValue(json,new TypeReference<ArrayList<Object>>() {});
	         status.put("result",convertedValue);
	         status.put("status", true);
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
	public JSONObject updatePatientRequestRadiologyTest(JSONObject radiologyupdate) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			PatientRequestRadiologyTest radiologytest = (PatientRequestRadiologyTest)session.get(PatientRequestRadiologyTest.class, (Integer) radiologyupdate.get("radiologyRequestId"));
			ObjectMapper om = new ObjectMapper();
			   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);	
			   radiologytest=om.convertValue(radiologyupdate,  PatientRequestRadiologyTest.class);	   
			session.merge(radiologytest);
			session.getTransaction().commit();
		} catch (Exception e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject deletePatientRequestRadiologyTest(JSONObject radiology) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			PatientRequestRadiologyTest radiologyrequest = (PatientRequestRadiologyTest)session.load(PatientRequestRadiologyTest.class, (Integer) radiology.get("radiologyRequestId"));
			session.delete(radiologyrequest);
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
	public JSONObject getPatientRequestRadiologyTesById(JSONObject radiologyrequestid) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();		
		module.addSerializer(com.monitorjbl.json.JsonView.class,new JsonViewSerializer());
		mapper.registerModule(module);
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			PatientRequestRadiologyTest pt = session.get(PatientRequestRadiologyTest.class, (Integer) radiologyrequestid.get("radiologyRequestId"));					
			List<PatientRequestRadiologyTest> lists = new ArrayList<PatientRequestRadiologyTest>();			
			lists.add(pt);			
			String json = mapper.writeValueAsString(com.monitorjbl.json.JsonView.with(lists).onClass(PatientRequestRadiologyTest.class, 
					com.monitorjbl.json.Match.match().exclude("patient","doctor")));			
			 ArrayList<Object> convertedValue = mapper.readValue(json,new TypeReference<ArrayList<Object>>() {});
	          status.put("result",convertedValue.get(0));
	          status.put("status", true);
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
	public JSONObject saveRadiologyTestReport(JSONObject radiologyreport) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   PatientRadiologyReport patientradiologreport = om.convertValue(radiologyreport,  PatientRadiologyReport.class);
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();			
			session.save(patientradiologreport);
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
	public JSONObject listRadiologyTestReport() {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
		List<PatientRadiologyReport> reportlists = session.createQuery("FROM PatientRadiologyReport").list();	
		session.getTransaction().commit();
		if(reportlists!=null && !reportlists.isEmpty()){
			status.put("RadiologyReport", reportlists);
			status.put("status", true);
		}
		
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject updateRadiologyTestReport(JSONObject reportupdate) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			PatientRadiologyReport updatereport = (PatientRadiologyReport)session.get(PatientRadiologyReport.class, (Integer) reportupdate.get("patientRReportId"));	
			ObjectMapper om = new ObjectMapper();
			   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			   updatereport = om.convertValue(reportupdate, PatientRadiologyReport.class);		
			   session.merge(updatereport);
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
	public JSONObject deleteRadiologyTestReport(JSONObject reportdelete) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			PatientRadiologyReport radiologyreport = (PatientRadiologyReport)session.load(PatientRadiologyReport.class, (Integer) reportdelete.get("patientRReportId"));
			session.delete(radiologyreport);
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
	public JSONObject getRadiologyTestReportById(JSONObject radiologyreportid) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
		List<PatientRadiologyReport> reportlists = session.createQuery("FROM PatientRadiologyReport WHERE patientRReportId=:searchA").setParameter("searchA", (Integer) radiologyreportid.get("patientRReportId")).list();	
	
		if(reportlists!=null && !reportlists.isEmpty()){
			status.put("RadiologyReport", reportlists.get(0));
			status.put("status", true);
		}else {
			status.put("Ëmpty", "Radiology report Id not found");
		}
		session.getTransaction().commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	
}

