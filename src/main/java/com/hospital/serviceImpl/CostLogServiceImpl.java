/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.CostLogDao;
import com.hospital.service.CostLogService;

/**
 * @author Krishna
 *
 */
@Service
public class CostLogServiceImpl implements CostLogService {

	/**
	 * CostLog dao
	 */
	@Autowired
	CostLogDao costLogdao;
	
	@Override
	public JSONObject addCostLog(JSONObject costLog) {
		return costLogdao.addCostLog(costLog);
	}

	@Override
	public JSONObject listCostLog() {
		return costLogdao.listCostLog();
	}

	@Override
	public JSONObject updateCostLog(JSONObject costLog) {
		return costLogdao.updateCostLog(costLog);
	}

	@Override
	public JSONObject deleteCostLog(JSONObject costLogId) {
		return costLogdao.deleteCostLog(costLogId);
	}

}
