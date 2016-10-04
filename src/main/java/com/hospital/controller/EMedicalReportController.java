/**
 * 
 */
package com.hospital.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.service.EMedicalReportService;

/**
 * @author Krishna
 *
 */
@Controller
public class EMedicalReportController {

	/**
	 * EMedicalReport Service.
	 */
	@Autowired
	private EMedicalReportService eMedicalReportService;

	/**
	 * Create EMedicalReport.
	 * 
	 * @param eMedicalReport
	 * @return eMedicalReport
	 */
	@RequestMapping(value = "/addEMedicalReport", method = RequestMethod.POST)
	public @ResponseBody JSONObject addEMedicalReport(@RequestBody JSONObject eMedicalReport) {
		return eMedicalReportService.addEMedicalReport(eMedicalReport);
	}

	/**
	 * List eMedicalReport.
	 * 
	 * @param eMedicalReport
	 * @return eMedicalReport
	 */
	@RequestMapping(value = "/listEMedicalReport")
	public @ResponseBody JSONObject listEMedicalReport() {
		return eMedicalReportService.listEMedicalReport();
	}
	
	/**
	 * Update EMedicalReport.
	 * 
	 * @param eMedicalReport
	 * @return eMedicalReport
	 */
	@RequestMapping(value = "/updateEMedicalReport")
	public @ResponseBody JSONObject updateEMedicalReport(@RequestBody JSONObject eMedicalReport) {
		return eMedicalReportService.updateEMedicalReport(eMedicalReport);
	}

	/**
	 * Delete eMedicalReport.
	 * 
	 * @param eMedicalReport
	 * @return eMedicalReport
	 */
	@RequestMapping(value = "/deleteEMedicalReport", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteEMedicalReport(@RequestBody JSONObject eMedicalReportId) {
		return eMedicalReportService.deleteEMedicalReport(eMedicalReportId);
	}
	
	/**
	 * list EMR by Patient Id.
	 * 
	 * @param eMedicalReport
	 * @return eMedicalReport
	 */
	@RequestMapping(value = "/listEmrByPatientId")
	public @ResponseBody JSONObject listByPatientId(@RequestBody JSONObject patientId) {
		return eMedicalReportService.listByPatientId(patientId);
	}
}
