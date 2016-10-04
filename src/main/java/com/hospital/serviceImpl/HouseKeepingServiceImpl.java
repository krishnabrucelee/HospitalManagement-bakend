/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.HouseKeepingDao;
import com.hospital.model.HouseKeeping;
import com.hospital.service.HouseKeepingService;

/**
 * @author Krishna
 *
 */
@Service
public class HouseKeepingServiceImpl implements HouseKeepingService {

	/**
	 * HouseKeeping dao
	 */
	@Autowired
	HouseKeepingDao houseKeepingdao;
	
	@Override
	public JSONObject addHouseKeeping(JSONObject houseKeeping) {
		return houseKeepingdao.addHouseKeeping(houseKeeping);
	}

	@Override
	public JSONObject listHouseKeeping() {
		return houseKeepingdao.listHouseKeeping();
	}

	@Override
	public JSONObject updateHouseKeeping(JSONObject houseKeeping) {
		return houseKeepingdao.updateHouseKeeping(houseKeeping);
	}

	@Override
	public JSONObject deleteHouseKeeping(JSONObject houseKeepingId) {
		return houseKeepingdao.deleteHouseKeeping(houseKeepingId);
	}

	@Override
	public HouseKeeping addHouseKeepingFromStaff(HouseKeeping houseKeeping) {
		return houseKeepingdao.addHouseKeepingFromStaff(houseKeeping);
	}

}
