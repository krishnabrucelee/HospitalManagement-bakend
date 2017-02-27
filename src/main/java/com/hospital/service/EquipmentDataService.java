package com.hospital.service;

import org.json.simple.JSONObject;

public interface EquipmentDataService {
	
	/**
	 * Create BuildingFloorRoom
	 * @param buildingFloorRoom
	 * 
	 */
	public JSONObject saveBuildingFloorRoom(JSONObject buildingFloorRoom);
	
	
	public JSONObject saveDevice(JSONObject devicedata);
	
	public JSONObject saveMaintananceConfig(JSONObject maintanancedata);
	/**
	 * Create MaintanaceConfiguration
	 * @param maintanaceConfiguration
	 * 
	 */
	public JSONObject saveMaintanaceConfiguration(JSONObject maintanaceConfiguration);
	
	/**
	 * Create EquipmentData
	 * @param equipmentData
	 * 
	 */
	public JSONObject saveEquipment(JSONObject equipmentDetails);
	
	public JSONObject listBuildig();
	
	public JSONObject listDevice();
	
	public JSONObject listMaintanaceConfiguration();
	
	public JSONObject getGroupByEquipment();
	
	/**
	 * Create EquipmentCheckRequest
	 * @param equipmentCheckRequest
	 * 
	 */
	public JSONObject saveEquipmentCheckRequest(JSONObject equipmentCheckRequest);
	
	public JSONObject checkEquipmentMaintanance(JSONObject checkRequest);
	
	
	public JSONObject getEquipmentCheckPending();
	
	public JSONObject getLastEquipmentCheck(JSONObject lastCheckt);
	
	public JSONObject updateEquipmentCheckStatus(JSONObject equipmentCheck);
}
