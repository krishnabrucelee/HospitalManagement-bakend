/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.AmbulanceRequestDao;
import com.hospital.service.AmbulanceRequestService;

/**
 * @author Krishna
 *
 */
@Service
public class AmbulanceRequestServiceImpl implements AmbulanceRequestService {

	/**
	 * AmbulanceRequest dao
	 */
	@Autowired
	AmbulanceRequestDao ambulanceRequestdao;
	
	@Override
	public JSONObject addAmbulanceRequest(JSONObject ambulanceRequest) {
		return ambulanceRequestdao.addAmbulanceRequest(ambulanceRequest);
	}

	@Override
	public JSONObject listAmbulanceRequest() {
		return ambulanceRequestdao.listAmbulanceRequest();
	}

	@Override
	public JSONObject updateAmbulanceRequest(JSONObject ambulanceRequest) {
		return ambulanceRequestdao.updateAmbulanceRequest(ambulanceRequest);
	}

	@Override
	public JSONObject deleteAmbulanceRequest(JSONObject ambulanceRequestId) {
		return ambulanceRequestdao.deleteAmbulanceRequest(ambulanceRequestId);
	}

}
