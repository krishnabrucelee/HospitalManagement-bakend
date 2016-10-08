package com.hospital.service;

import org.json.simple.JSONObject;

public interface ILabService {
	public JSONObject addLabConfigure(JSONObject labconfigure);
	public JSONObject addLabTypes(JSONObject labTypes);
	public JSONObject addLabtestNames(JSONObject labtestNames);//LabtestName with list of subtest catogeries
	public JSONObject addExternalLab(JSONObject externalLab);
	public JSONObject labrequestToPatient(JSONObject labrequestToPatient);
	public JSONObject approveTestByLab(JSONObject approveTestByLab);//approvetest by Lab
	/*public JSONObject patientRequestLabtest(JSONObject patientRequestLabtest);*/
	
	
	public JSONObject genarateLabTestResultMaster(JSONObject genarateLabResultMaster);
	public JSONObject labtestReportForPatient(JSONObject labtestReportForPatient);//Retrive patient request Labtest
	public JSONObject savePatientLabtestReport(JSONObject savepatientLabtestReport);//save lab report
	public JSONObject viewPatientLabtestReport(JSONObject labtestReportId);
	
}
