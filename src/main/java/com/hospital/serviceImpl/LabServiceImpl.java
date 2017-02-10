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
	@Override
	public JSONObject addMasterLabConfigure(JSONObject masterLabConfigure) {		
		return labdao.addMasterLabConfigure(masterLabConfigure);
	}
	@Override
	public JSONObject listMasterLabTest() {
		return labdao.listMasterLabTest();
	}
	@Override
	public JSONObject updateMasterLabTest(JSONObject masterLabTest) {
		return labdao.updateMasterLabTest(masterLabTest);
	}
	@Override
	public JSONObject deleteMasterLabTest(JSONObject masterLabTestId) {
		return labdao.deleteMasterLabTest(masterLabTestId);
	}
	@Override
	public JSONObject saveMasterLabTestReport(JSONObject masterLabTestReport) {//MasterReport
		return labdao.saveMasterLabTestReport(masterLabTestReport);
	}
	@Override
	public JSONObject getMasterLabTestReportByAgeRangeId(
			JSONObject masterLabTestReportId) {
		return null;
	}
	@Override
	public JSONObject updateMasterLabTestReport(JSONObject masterLabTestReport) {
		return null;
	}
	@Override
	public JSONObject deleteMasterLabTestReportById(
			JSONObject masterLabTestReportId) {
		return null;
	}
	@Override
	public JSONObject savePatientRequestLabTest(JSONObject patientRequestLabTest) {
		System.out.println("savePatientLabRequest Interface");
		return labdao.savePatientRequestLabTest(patientRequestLabTest);
	}
	@Override
	public JSONObject listPatientRequestLabTest() {
		return labdao.listPatientRequestLabTest();
	}
	@Override
	public JSONObject getPatientRequestLabTestById(
			JSONObject patientRequestLabTest) {
		return null;
	}
	@Override
	public JSONObject updatePatientRequestLabTest(JSONObject labTestReport) {
		return labdao.updatePatientRequestLabTest(labTestReport);
	}
	@Override
	public JSONObject deletePatientRequestLabTestById(
			JSONObject patientRequestLabTest) {
		return null;
	}
	@Override
	public JSONObject getPatientRequestLabTestByPatientId(
			JSONObject patientRequestLabTestByPatientId) {
		return null;
	}
	@Override
	public JSONObject savePatientLabTestReport(JSONObject patientLabTestReport) {
		return null;
	}
	@Override
	public JSONObject listPatientLabTestReport() {
		return null;
	}
	@Override
	public JSONObject getPatientLabTestReportById(
			JSONObject patientLabTestReport) {
		return null;
	}
	@Override
	public JSONObject updatePatientLabTestReport(JSONObject patientLabTestReport) {
		return null;
	}
	@Override
	public JSONObject deletePatientLabTestReportId(
			JSONObject patientLabTestReportId) {
		return null;
	}
	@Override
	public JSONObject getPatientLabTestReportByPatientId(
			JSONObject labTestReportByPatientId) {
		return null;
	}
	

}
