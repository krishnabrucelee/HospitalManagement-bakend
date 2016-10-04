/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.PatientBillingDao;
import com.hospital.service.PatientBillingService;

/**
 * @author Krishna
 *
 */
@Service
public class PatientBillingServiceImpl implements PatientBillingService {

	/**
	 * PatientBilling dao
	 */
	@Autowired
	PatientBillingDao patientBillingdao;
	
	@Override
	public JSONObject addPatientBilling(JSONObject patientBilling) {
		return patientBillingdao.addPatientBilling(patientBilling);
	}

	@Override
	public JSONObject listPatientBilling() {
		return patientBillingdao.listPatientBilling();
	}

	@Override
	public JSONObject updatePatientBilling(JSONObject patientBilling) {
		return patientBillingdao.updatePatientBilling(patientBilling);
	}

	@Override
	public JSONObject deletePatientBilling(JSONObject patientBillingId) {
		return patientBillingdao.deletePatientBilling(patientBillingId);
	}

}
