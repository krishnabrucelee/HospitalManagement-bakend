package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.HouseKeepingManagementDao;
import com.hospital.service.IHouseKeepingManagementService;
@Service
public class HouseKeepingManagementServiceImpl implements IHouseKeepingManagementService {
	@Autowired
	private HouseKeepingManagementDao houseKeepingManagementDao;

	@Override
	public JSONObject saveHouseKeepItemMaster(JSONObject itemMaster) {
		return houseKeepingManagementDao.saveHouseKeepItemMaster(itemMaster);
	}

	@Override
	public JSONObject laundryprocessState(JSONObject processState) {
		return houseKeepingManagementDao.laundryprocessState(processState);
	}

	@Override
	public JSONObject requestHouseKeep(JSONObject houseKeepData) {
		return houseKeepingManagementDao.requestHouseKeep(houseKeepData);
	}

	@Override
	public JSONObject getHouseKeepRequestId(JSONObject houseKeepId) {
		return houseKeepingManagementDao.getHouseKeepRequestId(houseKeepId);
	}

	@Override
	public JSONObject houseKeepRequestUpdate(JSONObject houseKeepUpdate) {
		return houseKeepingManagementDao.houseKeepRequestUpdate(houseKeepUpdate);
	}

}
