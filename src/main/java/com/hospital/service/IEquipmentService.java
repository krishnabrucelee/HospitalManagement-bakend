package com.hospital.service;

import org.json.simple.JSONObject;

public interface IEquipmentService {
	public JSONObject saveEuipment(JSONObject equipmentdata);
	public JSONObject saveEquipmentCheckMaster(JSONObject equipmentCheckMaster);
	public JSONObject viewEuipment();
	public JSONObject getEuipmentById(JSONObject equipmentId);
	
	public JSONObject requestEuipmentChecklist(JSONObject euipmentChecklist);//
	public JSONObject viewEuipmentChecklist();
	public JSONObject getEuipmentChecklistById(JSONObject checklistrequest);
	public JSONObject updateEquipmentCheckRequest(JSONObject updateStatus);
	//
	
	//
	public JSONObject saveMaintananceSchedule(JSONObject maintananceSchedule);
	public JSONObject orderEquipmentParts(JSONObject orderEquipmentParts);
	public JSONObject viewOrderEquipmentParts();
	public JSONObject scheduleMaintanance(JSONObject scheduleMaintanance);
	public JSONObject getScheduleMaintananceById(JSONObject maintananceschedule);
	public JSONObject saveRoutinescheduleCheckData(JSONObject routinescheduleCheckData);
}
