/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.BloodReturnDao;
import com.hospital.service.BloodReturnService;

/**
 * @author Krishna
 *
 */
@Service
public class BloodReturnServiceImpl implements BloodReturnService {

	/**
	 * BloodReturn dao
	 */
	@Autowired
	BloodReturnDao bloodReturndao;
	
	@Override
	public JSONObject addBloodReturn(JSONObject bloodReturn) {
		return bloodReturndao.addBloodReturn(bloodReturn);
	}

	@Override
	public JSONObject listBloodReturn() {
		return bloodReturndao.listBloodReturn();
	}

	@Override
	public JSONObject updateBloodReturn(JSONObject bloodReturn) {
		return bloodReturndao.updateBloodReturn(bloodReturn);
	}

	@Override
	public JSONObject deleteBloodReturn(JSONObject bloodReturnId) {
		return bloodReturndao.deleteBloodReturn(bloodReturnId);
	}

}
