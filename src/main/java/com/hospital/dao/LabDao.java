package com.hospital.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.ExternalLab;
import com.hospital.model.LabTest;
import com.hospital.model.LabTestName;
import com.hospital.model.LabrequestByPatient;
import com.hospital.model.LabtestTypes;
import com.hospital.model.MedicineEntryMaster;
import com.hospital.model.NewLabRequest;
import com.hospital.model.PatientData;
import com.hospital.model.PatientLabtestReportNames;
@Repository
public class LabDao {
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public JSONObject addLabConfigure(JSONObject labconfigure) {
		System.out.println("Inside Dao1Patient");
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   LabTest labtest = om.convertValue(labconfigure, LabTest.class);
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(labtest);
			session.getTransaction().commit();
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject addLabTypes(JSONObject labTypes) {
		System.out.println("Inside Dao1Patient");
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   LabtestTypes labtesttypes = om.convertValue(labTypes, LabtestTypes.class);
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(labtesttypes);
			session.getTransaction().commit();
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject addExternalLab(JSONObject externalLab) {
		System.out.println("Inside addExternalLab");
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   ExternalLab externallab = om.convertValue(externalLab, ExternalLab.class);
		  
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(externallab);
			session.getTransaction().commit();
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject addLabtestNames(JSONObject labtestNames) {
		System.out.println("Inside Dao1Patient");
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   LabTestName labTestName = om.convertValue(labtestNames, LabTestName.class);
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(labTestName);
			session.getTransaction().commit();
			status.put("status", true);
			status.put("status", "Test names with sub categeries are added");
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject labrequestToPatient(JSONObject labrequestToPatient) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   LabrequestByPatient labRequests = om.convertValue(labrequestToPatient, LabrequestByPatient.class);		  
		try {			
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(labRequests);		
			session.getTransaction().commit();
			status.put("status", true);
			status.put("status", "Lab request saved");		
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject approveTestByLab(JSONObject approveTestByLab) {
		System.out.println("Inside approveTestByLab");
		JSONObject status = new JSONObject();
		status.put("status", true);
		Integer requestId = (Integer)approveTestByLab.get("labrequestId");
		String requestStatus = approveTestByLab.get("requestStatus").toString();
		Session session = null;List<LabrequestByPatient> labrequestByPatients=null;
		LabrequestByPatient labrequestByPatient=null;
		try {
			System.out.println("Inside try");
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM LabrequestByPatient WHERE labrequestId =:searchA");
			labrequestByPatients = query.setParameter("searchA", requestId).list();
			labrequestByPatient= labrequestByPatients.get(0);
			labrequestByPatient.setRequestStatus(requestStatus);
			System.out.println("Before update approveTestByLab ");
			session.merge(labrequestByPatient);
			System.out.println("After update approveTestByLab");
			session.getTransaction().commit();
			status.put("status", true);
			status.put("status", "Lab request saved");
			System.out.println("Lab request for patient is saved");
		} catch (Exception e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	/*
	 * @SuppressWarnings("unchecked")
	public JSONObject patientRequestLabtest(JSONObject patientRequestLabtest) {
		System.out.println("Inside labrequestToPatient");
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		   //NewLabRequest labRequests = om.convertValue(labrequestToPatient, NewLabRequest.class);
		   System.out.println("Inside try66");//RequestLabtestByPatient
		   //LabrequestByPatient labRequests = om.convertValue(patientRequestLabtest, LabrequestByPatient.class);
		   RequestLabtestByPatient labRequests = om.convertValue(patientRequestLabtest, RequestLabtestByPatient.class);
		   //PatientLabRequestNames patientLabRequestNames = new PatientLabRequestNames();
		try {
			System.out.println("Inside try");
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(labRequests);
			
			  PatientLabRequestNames	patientLabRequestNames =om.convertValue(patientRequestLabtest, PatientLabRequestNames.class);
			patientLabRequestNames.setLabRequestId(labRequests.getLabrequestId());
			session.save(patientLabRequestNames);
			System.out.println("After save  LabrequestByPatient labRequests");
			session.getTransaction().commit();
			status.put("status", true);
			status.put("status", "Lab request saved");
			System.out.println("Lab request for patient is saved");
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}*/
	@SuppressWarnings("unchecked")
	public JSONObject genarateLabTestResultMaster(JSONObject genarateLabResultMaster) {
		System.out.println("Inside approveTestByLab");
		JSONObject status = new JSONObject();
		status.put("status", true);
		Integer testId = (Integer)genarateLabResultMaster.get("testId");
		
		Session session = null;List<LabTestName> labTestNames=null;
		LabTestName labTestName=null;
		try {
			System.out.println("Inside try");
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM LabTestName WHERE testId =:searchA");
			labTestNames = query.setParameter("searchA", testId).list();
			labTestName= labTestNames.get(0);
			
			System.out.println("After update approveTestByLab");
			session.getTransaction().commit();
		
			status.put("Labtest",labTestNames );
			status.put("status", true);
			System.out.println("Lab request for patient is saved");
		} catch (Exception e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject labtestReportForPatient(JSONObject labtestReportForPatient) {
		System.out.println("Inside approveTestByLab");
		JSONObject status = new JSONObject();
		status.put("status", true);
		Integer requestId = (Integer)labtestReportForPatient.get("labrequestId");		
		Session session = null;
		List<LabrequestByPatient> patientRequestLabTests=null;//$$##
		LabrequestByPatient patientRequestLabTest=null;
		try {
			System.out.println("Inside try");
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM PatientRequestLabTest WHERE labrequestId =:searchA");
			patientRequestLabTests = query.setParameter("searchA", requestId ).list();
			patientRequestLabTest= patientRequestLabTests.get(0);			
			System.out.println("After update approveTestByLab");
			session.getTransaction().commit();		
			status.put("Labtest",patientRequestLabTests );
			status.put("status", true);
			System.out.println("Lab request for patient is saved");
		} catch (Exception e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	///// Save Test Report////
	@SuppressWarnings("unchecked")
	public JSONObject savepatientLabtestReport(JSONObject savepatientLabtestReport) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);		
		   System.out.println("Inside try66");//RequestLabtestByPatient		 
		   PatientLabtestReportNames labRequests = om.convertValue(savepatientLabtestReport, PatientLabtestReportNames.class);		  
		try {			
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(labRequests);					
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
	public JSONObject viewPatientLabtestReport(JSONObject labtestReportId) {	
		JSONObject status = new JSONObject();
		status.put("status", true);
		Integer labtestReportIdd = (Integer)labtestReportId.get("labtestReportId");		
		Session session = null;List<PatientLabtestReportNames> patientLabtestReportNames=null;
		PatientLabtestReportNames patientLabtestReportName=null;
		try {	
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();	
			Query query = session.createQuery("FROM PatientLabtestReportNames WHERE labtestReportId =:searchA");
			patientLabtestReportNames = query.setParameter("searchA", labtestReportIdd).list();
			patientLabtestReportName= patientLabtestReportNames.get(0);						
			session.getTransaction().commit();		
			status.put("Labtest",patientLabtestReportNames);
			status.put("status", true);	
		} catch (Exception e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
}
