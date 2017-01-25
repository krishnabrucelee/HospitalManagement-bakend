package com.hospital.controller;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hospital.service.IRadiology;

/**
 * @author admin
 *
 */
@Controller
public class RadiologyController {
	@Autowired
  IRadiology iradiology;
	/**
	 * Create RadiologyTestName.
	 * 
	 * @param radiology
	 * @return radiology
	 */
	@RequestMapping(value = "/addRadiologyTestName", method = RequestMethod.POST)
	public @ResponseBody JSONObject addRadiologyTestName(@RequestBody JSONObject radiology) {
		return iradiology.addRadiologyTestName(radiology);
	}
	/**
	 * List listRadiologyTest.
	 * 
	 * @return listRadiologyTest
	 */
	@RequestMapping(value = "/listRadiologyTest")
	public @ResponseBody JSONObject listRadiologyTest() {
		return iradiology.listRadiologyTest();
	}
	/**
	 * Get RadiologyTest.
	 * 
	 * @param radiologyrequestid
	 *
	 */
	@RequestMapping(value = "/getRadiologyTestNameById", method = RequestMethod.POST)
	public @ResponseBody JSONObject getRadiologyTestNameById(@RequestBody JSONObject radiologyrequestid) {
		return iradiology.getRadiologyTestNameById(radiologyrequestid);
	}
	/**
	 * Update RadiologyTest.
	 * 
	 * @param radiology
	 *
	 */
	@RequestMapping(value = "/updateRadiologyTestName", method = RequestMethod.POST)
	public @ResponseBody JSONObject updateRadiologyTestName(@RequestBody JSONObject radiology) {
		return iradiology.updateRadiologyTestName(radiology);
	}
	/**
	 * Delete RadiologyTest.
	 * 
	 * @param radiologyId
	 *
	 */
	@RequestMapping(value = "/deleteRadiologyTest", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteRadiologyTest(@RequestBody JSONObject radiology) {
		return iradiology.deleteRadiologyTest(radiology);
	}
	
	/**
	 * Save  Patient RadiologyTestName.
	 * 
	 * @param parientradiology
	 * @return parientradiology
	 */
	@RequestMapping(value = "/savePatientRequestRadiologyTest", method = RequestMethod.POST)
	public @ResponseBody JSONObject savePatientRequestRadiologyTest(@RequestBody JSONObject parientradiology) {
		return iradiology.savePatientRequestRadiologyTest(parientradiology);
	}
	/**
	 * List listPatientRadiologyTest.
	 * 
	 * @return listPatientRadiologyTest
	 */
	@RequestMapping(value = "/listPatientRequestRadiologyTest")
	public @ResponseBody JSONObject listPatientRequestRadiologyTest() {
		return iradiology.listPatientRequestRadiologyTest();
	}
	/**
	 * Update  PatientRadiologyTest.
	 * 
	 * @param radiologyupdate
	 *
	 */
	@RequestMapping(value = "/updatePatientRequestRadiologyTest", method = RequestMethod.POST)
	public @ResponseBody JSONObject updatePatientRequestRadiologyTest(@RequestBody JSONObject radiologyupdate) {
		return iradiology.updatePatientRequestRadiologyTest(radiologyupdate);
	}
	
	/**
	 * Delete Patient RadiologyRequestTest.
	 * 
	 * @param radiology
	 *
	 */
	@RequestMapping(value = "/deletePatientRequestRadiologyTest", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deletePatientRequestRadiologyTest(@RequestBody JSONObject radiology) {
		return iradiology.deletePatientRequestRadiologyTest(radiology);
	}
	/**
	 * Delete PatientRadiologyRequestTest.
	 * 
	 * @param radiologyrequestid
	 * @return PatientRadiologyRequestTest row
	 */
	@RequestMapping(value = "/getPatientRequestRadiologyTesById", method = RequestMethod.POST)
	public @ResponseBody JSONObject getPatientRequestRadiologyTesById(@RequestBody JSONObject radiologyrequestid) {
		return iradiology.getPatientRequestRadiologyTesById(radiologyrequestid);
	}
	

	/**
	 * Save  Patient RadiologyTestReport.
	 * 
	 * @param parient radiologyreport
	 * 
	 */
	@RequestMapping(value = "/saveRadiologyTestReport", method = RequestMethod.POST)
	public @ResponseBody JSONObject saveRadiologyTestReport(@RequestBody JSONObject radiologyreport) {
		return iradiology.saveRadiologyTestReport(radiologyreport);
	}
	/**
	 * List listPatientRadiologyReport.
	 * 
	 * @return listPatientRadiologyReport
	 */
	@RequestMapping(value = "/listRadiologyTestReport")
	public @ResponseBody JSONObject listRadiologyTestReport() {
		return iradiology.listRadiologyTestReport();
	}
	/**
	 * Update  PatientRadiologyReport update.
	 * 
	 * @param radiologyreportupdate
	 *
	 */
	@RequestMapping(value = "/updateRadiologyTestReport", method = RequestMethod.POST)
	public @ResponseBody JSONObject updateRadiologyTestReport(@RequestBody JSONObject reportupdate) {
		return iradiology.updateRadiologyTestReport(reportupdate);
	}
	
	/**
	 * Delete Patient RadiologyReport by id.
	 * 
	 * @param reportdelete
	 *
	 */
	@RequestMapping(value = "/deleteRadiologyTestReport", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteRadiologyTestReport(@RequestBody JSONObject reportdelete) {
		return iradiology.deleteRadiologyTestReport(reportdelete);
	}
	/**
	 * Get PatientRadiologyRequestTest.
	 * 
	 * @param radiologyreportid
	 * @return PatientRadiologyReport row
	 */
	@RequestMapping(value = "/getRadiologyTestReportById", method = RequestMethod.POST)
	public @ResponseBody JSONObject getRadiologyTestReportById(@RequestBody JSONObject radiologyreportid) {
		return iradiology.getRadiologyTestReportById(radiologyreportid);
	}
}
