/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.WardDao;
import com.hospital.service.WardService;

/**
 * @author Krishna
 *
 */
@Service
public class WardServiceImpl implements WardService {


	/**
	 * Ward dao
	 */
	@Autowired
	private WardDao Warddao;
	
	@Override
	public JSONObject addWard(JSONObject Ward) {
		return Warddao.addWard(Ward);
	}

	@Override
	public JSONObject listWard() {
		return Warddao.listWard();
	}

	@Override
	public JSONObject updateWard(JSONObject Ward) {
		return Warddao.updateWard(Ward);
	}

	@Override
	public JSONObject deleteWard(JSONObject WardId) {
		return Warddao.deleteWard(WardId);
	}


}
