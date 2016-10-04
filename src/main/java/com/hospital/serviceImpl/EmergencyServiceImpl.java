/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.EmergencyDao;
import com.hospital.service.EmergencyService;

/**
 * @author Krishna
 *
 */
@Service
public class EmergencyServiceImpl implements EmergencyService {

	/**
	 * Emergency dao
	 */
	@Autowired
	private EmergencyDao emergencydao;
	
	@Override
	public JSONObject addEmergency(JSONObject emergency) {
		return emergencydao.addEmergency(emergency);
	}

	@Override
	public JSONObject listEmergency() {
		return emergencydao.listEmergency();
	}

	@Override
	public JSONObject updateEmergency(JSONObject emergency) {
		return emergencydao.updateEmergency(emergency);
	}

	@Override
	public JSONObject deleteEmergency(JSONObject emergencyId) {
		return emergencydao.deleteEmergency(emergencyId);
	}

}
