/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.AmbulanceDao;
import com.hospital.service.AmbulanceService;

/**
 * @author Krishna
 *
 */
@Service
public class AmbulanceServiceImpl implements AmbulanceService {

	/**
	 * Ambulance dao
	 */
	@Autowired
	AmbulanceDao ambulancedao;
	
	@Override
	public JSONObject addAmbulance(JSONObject ambulance) {
		return ambulancedao.addAmbulance(ambulance);
	}

	@Override
	public JSONObject listAmbulance() {
		return ambulancedao.listAmbulance();
	}

	@Override
	public JSONObject updateAmbulance(JSONObject ambulance) {
		return ambulancedao.updateAmbulance(ambulance);
	}

	@Override
	public JSONObject deleteAmbulance(JSONObject ambulanceId) {
		return ambulancedao.deleteAmbulance(ambulanceId);
	}

}
