/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.PatientBillingTransactionDao;
import com.hospital.service.PatientBillingTransactionService;

/**
 * @author Krishna
 *
 */
@Service
public class PatientBillingTransactionServiceImpl implements PatientBillingTransactionService {

	/**
	 * PatientBillingTransaction dao
	 */
	@Autowired
	PatientBillingTransactionDao patientBillingTransactiondao;
	
	@Override
	public JSONObject addPatientBillingTransaction(JSONObject patientBillingTransaction) {
		return patientBillingTransactiondao.addPatientBillingTransaction(patientBillingTransaction);
	}

	@Override
	public JSONObject listPatientBillingTransaction() {
		return patientBillingTransactiondao.listPatientBillingTransaction();
	}

	@Override
	public JSONObject updatePatientBillingTransaction(JSONObject patientBillingTransaction) {
		return patientBillingTransactiondao.updatePatientBillingTransaction(patientBillingTransaction);
	}

	@Override
	public JSONObject deletePatientBillingTransaction(JSONObject patientBillingTransactionId) {
		return patientBillingTransactiondao.deletePatientBillingTransaction(patientBillingTransactionId);
	}

}
