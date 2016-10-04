/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.OuterBloodTransfusionDao;
import com.hospital.service.OuterBloodTransfusionService;

/**
 * @author Krishna
 *
 */
@Service
public class OuterBloodTransfusionServiceImpl implements OuterBloodTransfusionService {

	/**
	 * OuterBloodTransfusion dao
	 */
	@Autowired
	OuterBloodTransfusionDao outerBloodTransdao;
	
	@Override
	public JSONObject addOuterBloodTransfusion(JSONObject outerBloodTrans) {
		return outerBloodTransdao.addOuterBloodTransfusion(outerBloodTrans);
	}

	@Override
	public JSONObject listOuterBloodTransfusion() {
		return outerBloodTransdao.listOuterBloodTransfusion();
	}

	@Override
	public JSONObject updateOuterBloodTransfusion(JSONObject outerBloodTrans) {
		return outerBloodTransdao.updateOuterBloodTransfusion(outerBloodTrans);
	}

	@Override
	public JSONObject deleteOuterBloodTransfusion(JSONObject outerBloodTransId) {
		return outerBloodTransdao.deleteOuterBloodTransfusion(outerBloodTransId);
	}

}
