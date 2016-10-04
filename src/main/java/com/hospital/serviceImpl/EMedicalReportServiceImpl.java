/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.EMedicalReportDao;
import com.hospital.service.EMedicalReportService;

/**
 * @author Krishna
 *
 */
@Service
public class EMedicalReportServiceImpl implements EMedicalReportService {

	/**
	 * EMedicalReport dao
	 */
	@Autowired
	EMedicalReportDao eMedicalReportdao;
	
	@Override
	public JSONObject addEMedicalReport(JSONObject eMedicalReport) {
		return eMedicalReportdao.addEMedicalReport(eMedicalReport);
	}

	@Override
	public JSONObject listEMedicalReport() {
		return eMedicalReportdao.listEMedicalReport();
	}

	@Override
	public JSONObject updateEMedicalReport(JSONObject eMedicalReport) {
		return eMedicalReportdao.updateEMedicalReport(eMedicalReport);
	}

	@Override
	public JSONObject deleteEMedicalReport(JSONObject eMedicalReportId) {
		return eMedicalReportdao.deleteEMedicalReport(eMedicalReportId);
	}

	@Override
	public JSONObject listByPatientId(JSONObject patientId) {
		return eMedicalReportdao.listByPatientId(patientId);
	}

}
