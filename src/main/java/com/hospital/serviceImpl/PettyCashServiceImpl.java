/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.PettyCashDao;
import com.hospital.service.PettyCashService;

/**
 * @author Krishna
 *
 */
@Service
public class PettyCashServiceImpl implements PettyCashService {

	/**
	 * PettyCash dao
	 */
	@Autowired
	PettyCashDao pettyCashdao;
	
	@Override
	public JSONObject addPettyCash(JSONObject pettyCash) {
		return pettyCashdao.addPettyCash(pettyCash);
	}

	@Override
	public JSONObject listPettyCash() {
		return pettyCashdao.listPettyCash();
	}

	@Override
	public JSONObject updatePettyCash(JSONObject pettyCash) {
		return pettyCashdao.updatePettyCash(pettyCash);
	}

	@Override
	public JSONObject deletePettyCash(JSONObject pettyCashId) {
		return pettyCashdao.deletePettyCash(pettyCashId);
	}

}
