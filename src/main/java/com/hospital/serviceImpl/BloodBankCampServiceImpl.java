/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.BloodBankCampDao;
import com.hospital.service.BloodBankCampService;

/**
 * @author Krishna
 *
 */
@Service
public class BloodBankCampServiceImpl implements BloodBankCampService {

	/**
	 * BloodBankCamp dao
	 */
	@Autowired
	BloodBankCampDao bloodBankCampdao;
	
	@Override
	public JSONObject addBloodBankCamp(JSONObject bloodBankCamp) {
		return bloodBankCampdao.addBloodBankCamp(bloodBankCamp);
	}

	@Override
	public JSONObject listBloodBankCamp() {
		return bloodBankCampdao.listBloodBankCamp();
	}

	@Override
	public JSONObject updateBloodBankCamp(JSONObject bloodBankCamp) {
		return bloodBankCampdao.updateBloodBankCamp(bloodBankCamp);
	}

	@Override
	public JSONObject deleteBloodBankCamp(JSONObject bloodBankCampId) {
		return bloodBankCampdao.deleteBloodBankCamp(bloodBankCampId);
	}
	
}
