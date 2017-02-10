package com.hospital.service;

import org.json.simple.JSONObject;

public interface ILabService {
	public JSONObject addLabConfigure(JSONObject labconfigure);
	
	
	public JSONObject addLabTypes(JSONObject labTypes);
	
	
	public JSONObject addExternalLab(JSONObject externalLab);
	public JSONObject labrequestToPatient(JSONObject labrequestToPatient);
	public JSONObject approveTestByLab(JSONObject approveTestByLab);//approvetest by Lab
	/*public JSONObject patientRequestLabtest(JSONObject patientRequestLabtest);*/
	
	
	public JSONObject genarateLabTestResultMaster(JSONObject genarateLabResultMaster);
	public JSONObject labtestReportForPatient(JSONObject labtestReportForPatient);//Retrive patient request Labtest
	public JSONObject savePatientLabtestReport(JSONObject savepatientLabtestReport);//save lab report
	public JSONObject viewPatientLabtestReport(JSONObject labtestReportId);
	
	//New Type
	public JSONObject addMasterLabConfigure(JSONObject masterLabConfigure);
	
	public JSONObject listMasterLabTest();
	public JSONObject updateMasterLabTest(JSONObject masterLabTest);
	public JSONObject deleteMasterLabTest(JSONObject masterLabTestId);
	
	public JSONObject saveMasterLabTestReport(JSONObject masterLabTestReport);	
	public JSONObject getMasterLabTestReportByAgeRangeId(JSONObject masterLabTestReportId);	
	public JSONObject updateMasterLabTestReport(JSONObject masterLabTestReport);	
	public JSONObject deleteMasterLabTestReportById(JSONObject masterLabTestReportId);
	
	public JSONObject savePatientRequestLabTest(JSONObject patientRequestLabTest);	
	public JSONObject listPatientRequestLabTest();
	public JSONObject getPatientRequestLabTestById(JSONObject patientRequestLabTestId);	
	public JSONObject updatePatientRequestLabTest(JSONObject patientRequestLabTest);	
	public JSONObject deletePatientRequestLabTestById(JSONObject patientRequestLabTestId);
	public JSONObject getPatientRequestLabTestByPatientId(JSONObject patientRequestLabTestByPatientId);	
	
	public JSONObject savePatientLabTestReport(JSONObject patientLabTestReport);	
	public JSONObject listPatientLabTestReport();
	public JSONObject getPatientLabTestReportById(JSONObject patientLabTestReport);	
	public JSONObject updatePatientLabTestReport(JSONObject patientLabTestReport);	
	public JSONObject deletePatientLabTestReportId(JSONObject patientLabTestReportId);	
	public JSONObject getPatientLabTestReportByPatientId(JSONObject labTestReportByPatientId);	
	
	
	
}
