/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.CostDao;
import com.hospital.service.CostService;

/**
 * @author Krishna
 *
 */
@Service
public class CostServiceImpl implements CostService {

	/**
	 * Cost dao
	 */
	@Autowired
	CostDao costdao;
	
	@Override
	public JSONObject addCost(JSONObject cost) {
		return costdao.addCost(cost);
	}

	@Override
	public JSONObject listCost() {
		return costdao.listCost();
	}

	@Override
	public JSONObject updateCost(JSONObject cost) {
		return costdao.updateCost(cost);
	}

	@Override
	public JSONObject deleteCost(JSONObject costId) {
		return costdao.deleteCost(costId);
	}

}
