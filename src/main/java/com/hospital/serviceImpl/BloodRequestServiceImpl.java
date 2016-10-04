/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.BloodRequestDao;
import com.hospital.service.BloodRequestService;

/**
 * @author Krishna
 *
 */
@Service
public class BloodRequestServiceImpl implements BloodRequestService {

	/**
	 * BloodRequest dao
	 */
	@Autowired
	BloodRequestDao bloodRequestdao;
	
	@Override
	public JSONObject addBloodRequest(JSONObject bloodRequest) {
		return bloodRequestdao.addBloodRequest(bloodRequest);
	}

	@Override
	public JSONObject listBloodRequest() {
		return bloodRequestdao.listBloodRequest();
	}

	@Override
	public JSONObject updateBloodRequest(JSONObject bloodRequest) {
		return bloodRequestdao.updateBloodRequest(bloodRequest);
	}

	@Override
	public JSONObject deleteBloodRequest(JSONObject bloodRequestId) {
		return bloodRequestdao.deleteBloodRequest(bloodRequestId);
	}

}
