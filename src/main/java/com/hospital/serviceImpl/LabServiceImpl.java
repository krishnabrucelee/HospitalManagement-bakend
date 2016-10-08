package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.LabDao;
import com.hospital.service.ILabService;
@Service
public class LabServiceImpl implements ILabService {
	
	@Autowired
	LabDao labdao;
	@Override
	public JSONObject addLabConfigure(JSONObject labconfigure) {
		return labdao.addLabConfigure(labconfigure);
	}
	@Override
	public JSONObject addLabTypes(JSONObject labTypes) {
		return labdao.addLabTypes(labTypes);
	}
	@Override
	public JSONObject addExternalLab(JSONObject externalLab) {
		return labdao.addExternalLab(externalLab);
	}
	@Override
	public JSONObject addLabtestNames(JSONObject labtestNames) {
		return labdao.addLabtestNames(labtestNames);
	}
	@Override
	public JSONObject labrequestToPatient(JSONObject labrequestToPatient) {
		return labdao.labrequestToPatient(labrequestToPatient);
	}
	@Override
	public JSONObject approveTestByLab(JSONObject approveTestByLab) {
		return labdao.approveTestByLab(approveTestByLab);
	}
	/*@Override
	public JSONObject patientRequestLabtest(JSONObject patientRequestLabtest) {
		return labdao.patientRequestLabtest(patientRequestLabtest);
	}*/
	@Override
	public JSONObject genarateLabTestResultMaster(JSONObject genarateLabResultMaster) {
		return labdao.genarateLabTestResultMaster(genarateLabResultMaster);
	}
	@Override
	public JSONObject labtestReportForPatient(JSONObject labtestReportForPatient) {
		return labdao.labtestReportForPatient(labtestReportForPatient);
	}
	@Override
	public JSONObject savePatientLabtestReport(JSONObject savepatientLabtestReport) {
		return labdao.savepatientLabtestReport(savepatientLabtestReport);
	}
	@Override
	public JSONObject viewPatientLabtestReport(JSONObject labtestReportId) {
		return labdao.viewPatientLabtestReport(labtestReportId);
	}

}
