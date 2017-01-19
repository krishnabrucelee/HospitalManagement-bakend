package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.RadiologyDao;
import com.hospital.service.IRadiology;

/**
 * @author admin
 *
 */
@Service
public class RadiologyServiceImpl implements IRadiology {
	@Autowired
	 RadiologyDao radiologydao;

	/* (non-Javadoc)
	 * @see com.hospital.service.IRadiology#addRadiologyTestName(org.json.simple.JSONObject)
	 */
	@Override
	public JSONObject addRadiologyTestName(JSONObject radiology) {
		return radiologydao.addRadiologyTestName(radiology);
	}

	/* (non-Javadoc)
	 * @see com.hospital.service.IRadiology#listRadiologyTest()
	 */
	@Override
	public JSONObject listRadiologyTest() {
		return radiologydao.listRadiologyTest();
	}

	/* (non-Javadoc)
	 * @see com.hospital.service.IRadiology#updateRadiologyTestName(org.json.simple.JSONObject)
	 */
	@Override
	public JSONObject updateRadiologyTestName(JSONObject radiology) {
		return radiologydao.updateRadiologyTestName(radiology);
	}

	/* (non-Javadoc)
	 * @see com.hospital.service.IRadiology#deleteRadiologyTest(org.json.simple.JSONObject)
	 */
	@Override
	public JSONObject deleteRadiologyTest(JSONObject radiology) {
		return radiologydao.deleteRadiologyTest(radiology);
	}

	/* (non-Javadoc)
	 * @see com.hospital.service.IRadiology#savePatientRequestRadiologyTest(org.json.simple.JSONObject)
	 */
	@Override
	public JSONObject savePatientRequestRadiologyTest(
			JSONObject parientradiology) {
		return radiologydao.savePatientRequestRadiologyTest(parientradiology);
	}

	/* (non-Javadoc)
	 * @see com.hospital.service.IRadiology#listPatientRequestRadiologyTest()
	 */
	@Override
	public JSONObject listPatientRequestRadiologyTest() {
		return radiologydao.listPatientRequestRadiologyTest();
	}

	/* (non-Javadoc)
	 * @see com.hospital.service.IRadiology#updatePatientRequestRadiologyTest(org.json.simple.JSONObject)
	 */
	@Override
	public JSONObject updatePatientRequestRadiologyTest(
			JSONObject radiologyupdate) {
		return radiologydao.updatePatientRequestRadiologyTest(radiologyupdate);
	}

	/* (non-Javadoc)
	 * @see com.hospital.service.IRadiology#deletePatientRequestRadiologyTest(org.json.simple.JSONObject)
	 */
	@Override
	public JSONObject deletePatientRequestRadiologyTest(JSONObject radiology) {
		return radiologydao.deletePatientRequestRadiologyTest(radiology);
	}

	/* (non-Javadoc)
	 * @see com.hospital.service.IRadiology#getPatientRequestRadiologyTesById(org.json.simple.JSONObject)
	 */
	@Override
	public JSONObject getPatientRequestRadiologyTesById(
			JSONObject radiologyrequestid) {
		return radiologydao.getPatientRequestRadiologyTesById(radiologyrequestid);
	}

	/* (non-Javadoc)
	 * @see com.hospital.service.IRadiology#saveRadiologyTestReport(org.json.simple.JSONObject)
	 */
	@Override
	public JSONObject saveRadiologyTestReport(JSONObject radiologyreport) {
		return radiologydao.saveRadiologyTestReport(radiologyreport);
	}

	/* (non-Javadoc)
	 * @see com.hospital.service.IRadiology#listRadiologyTestReport()
	 */
	@Override
	public JSONObject listRadiologyTestReport() {
		return radiologydao.listRadiologyTestReport();
	}

	/* (non-Javadoc)
	 * @see com.hospital.service.IRadiology#updateRadiologyTestReport(org.json.simple.JSONObject)
	 */
	@Override
	public JSONObject updateRadiologyTestReport(JSONObject reportupdate) {
		return radiologydao.updateRadiologyTestReport(reportupdate);
	}

	/* (non-Javadoc)
	 * @see com.hospital.service.IRadiology#deleteRadiologyTestReport(org.json.simple.JSONObject)
	 */
	@Override
	public JSONObject deleteRadiologyTestReport(JSONObject reportdelete) {
		return radiologydao.deleteRadiologyTestReport(reportdelete);
	}

	/* (non-Javadoc)
	 * @see com.hospital.service.IRadiology#getRadiologyTestReportById(org.json.simple.JSONObject)
	 */
	@Override
	public JSONObject getRadiologyTestReportById(JSONObject radiologyreportid) {
		return radiologydao.getRadiologyTestReportById(radiologyreportid);
	}

	/* (non-Javadoc)
	 * @see com.hospital.service.IRadiology#getRadiologyTestNameById(org.json.simple.JSONObject)
	 */
	@Override
	public JSONObject getRadiologyTestNameById(JSONObject radiologyrequestid) {
		return radiologydao.getRadiologyTestNameById(radiologyrequestid);
	}

}
