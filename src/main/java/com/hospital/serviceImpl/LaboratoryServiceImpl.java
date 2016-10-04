/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.LaboratoryDao;
import com.hospital.service.LaboratoryService;

/**
 * @author Krishna
 *
 */
@Service
public class LaboratoryServiceImpl implements LaboratoryService {

	/**
	 * Laboratory dao
	 */
	@Autowired
	LaboratoryDao laboratorydao;
	
	@Override
	public JSONObject addLaboratory(JSONObject laboratory) {
		return laboratorydao.addLaboratory(laboratory);
	}

	@Override
	public JSONObject listLaboratory() {
		return laboratorydao.listLaboratory();
	}

	@Override
	public JSONObject updateLaboratory(JSONObject laboratory) {
		return laboratorydao.updateLaboratory(laboratory);
	}

	@Override
	public JSONObject deleteLaboratory(JSONObject laboratoryId) {
		return laboratorydao.deleteLaboratory(laboratoryId);
	}

	@Override
	public JSONObject listByPatientId(JSONObject patientId) {
		return laboratorydao.listByPatientId(patientId);
	}
}
