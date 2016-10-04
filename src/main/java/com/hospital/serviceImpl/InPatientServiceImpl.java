/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.InPatientDao;
import com.hospital.service.InPatientService;

/**
 * @author Krishna
 *
 */
@Service
public class InPatientServiceImpl implements InPatientService {

	/**
	 * InPatient dao
	 */
	@Autowired
	private InPatientDao inPatientdao;
	
	@Override
	public JSONObject addInPatient(JSONObject inPatient) {
		return inPatientdao.addInPatient(inPatient);
	}

	@Override
	public JSONObject listInPatient() {
		return inPatientdao.listInPatient();
	}

	@Override
	public JSONObject updateInPatient(JSONObject inPatient) {
		return inPatientdao.updateInPatient(inPatient);
	}

	@Override
	public JSONObject deleteInPatient(JSONObject inPatientId) {
		return inPatientdao.deleteInPatient(inPatientId);
	}

}
