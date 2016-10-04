/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.SurgeryDao;
import com.hospital.service.SurgeryService;

/**
 * @author Krishna
 *
 */
@Service
public class SurgeryServiceImpl implements SurgeryService {

	/**
	 * Surgery dao
	 */
	@Autowired
	SurgeryDao surgerydao;
	
	@Override
	public JSONObject addSurgery(JSONObject surgery) {
		return surgerydao.addSurgery(surgery);
	}

	@Override
	public JSONObject listSurgery() {
		return surgerydao.listSurgery();
	}

	@Override
	public JSONObject updateSurgery(JSONObject surgery) {
		return surgerydao.updateSurgery(surgery);
	}

	@Override
	public JSONObject deleteSurgery(JSONObject surgeryId) {
		return surgerydao.deleteSurgery(surgeryId);
	}

}
