package com.hospital.service;

import org.json.simple.JSONObject;

public interface IHouseKeepingManagementService {
	public JSONObject saveHouseKeepItemMaster(JSONObject itemMaster);
	public JSONObject laundryprocessState(JSONObject processState);
	public JSONObject requestHouseKeep(JSONObject houseKeepData);
	public JSONObject getHouseKeepRequestId(JSONObject houseKeepId);
	public JSONObject houseKeepRequestUpdate(JSONObject houseKeepUpdate);
}
