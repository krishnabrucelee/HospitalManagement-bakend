package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.EquipmentDaos;
import com.hospital.service.EquipmentDataService;
@Service
public class EquipmentServiceDataImpl implements EquipmentDataService {
	
	@Autowired
	EquipmentDaos equipmentDao;
	
	@Override
	public JSONObject saveBuildingFloorRoom(JSONObject buildingFloorRoom) {
		return equipmentDao.saveBuildingFloorRoom(buildingFloorRoom);
	}

	@Override
	public JSONObject saveMaintanaceConfiguration(
			JSONObject maintanaceConfiguration) {
		return equipmentDao.saveMaintanaceConfiguration(maintanaceConfiguration);
	}

	@Override
	public JSONObject saveEquipment(JSONObject equipmentDetails) {
		return equipmentDao.saveEquipment(equipmentDetails);
	}

	@Override
	public JSONObject listDevice() {
		return equipmentDao.listDevice();
	}

	@Override
	public JSONObject listMaintanaceConfiguration() {
		return equipmentDao.listMaintanaceConfiguration();
	}

	@Override
	public JSONObject listBuildig() {
		return equipmentDao.listBuildig();
	}

	@Override
	public JSONObject getGroupByEquipment() {
		return equipmentDao.getGroupByEquipment();
	}
	
	@Override
	public JSONObject getLastEquipmentCheck(JSONObject lastCheckt) {
		return equipmentDao.getLastEquipmentCheck(lastCheckt);
	}
	@Override
	public JSONObject saveEquipmentCheckRequest(JSONObject equipmentCheckRequest) {
		return equipmentDao.saveEquipmentCheckRequest(equipmentCheckRequest);
	}

	@Override
	public JSONObject getEquipmentCheckPending() {
		return equipmentDao.getEquipmentCheckPending();
	}

	@Override
	public JSONObject checkEquipmentMaintanance(JSONObject checkRequest) {
		return null;
	}

	@Override
	public JSONObject updateEquipmentCheckStatus(JSONObject equipmentCheck) {
		return equipmentDao.updateEquipmentCheckStatus(equipmentCheck);
	}

	@Override
	public JSONObject saveDevice(JSONObject devicedata) {
		return equipmentDao.saveDevice(devicedata);
	}

	@Override
	public JSONObject saveMaintananceConfig(JSONObject maintanancedata) {
		return null;
	}

	

	

}
