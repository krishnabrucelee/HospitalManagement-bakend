/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.InterBloodTransfusionDao;
import com.hospital.service.InterBloodTransfusionService;

/**
 * @author Krishna
 *
 */
@Service
public class InterBloodTransfusionServiceImpl implements InterBloodTransfusionService {

	/**
	 * InterBloodTransfusion dao
	 */
	@Autowired
	InterBloodTransfusionDao interBloodTransdao;
	
	@Override
	public JSONObject addInterBloodTransfusion(JSONObject interBloodTrans) {
		return interBloodTransdao.addInterBloodTransfusion(interBloodTrans);
	}

	@Override
	public JSONObject listInterBloodTransfusion() {
		return interBloodTransdao.listInterBloodTransfusion();
	}

	@Override
	public JSONObject updateInterBloodTransfusion(JSONObject interBloodTrans) {
		return interBloodTransdao.updateInterBloodTransfusion(interBloodTrans);
	}

	@Override
	public JSONObject deleteInterBloodTransfusion(JSONObject interBloodTransId) {
		return interBloodTransdao.deleteInterBloodTransfusion(interBloodTransId);
	}

}
