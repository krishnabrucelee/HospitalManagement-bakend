package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.EquipmentDao;
import com.hospital.service.IEquipmentService;
@Service
public class EquipmentServiceImpl implements IEquipmentService {
	
	@Autowired
	EquipmentDao equipmentDao;
	@Override
	public JSONObject saveEuipment(JSONObject equipmentdata) {
		return equipmentDao.saveEuipment(equipmentdata);
	}
	@Override
	public JSONObject viewEuipment() {
		return equipmentDao.viewEuipment();
	}
	@Override
	public JSONObject getEuipmentById(JSONObject equipmentId) {
		return equipmentDao.getEuipmentById(equipmentId);
	}
	//===
	@Override
	public JSONObject requestEuipmentChecklist(JSONObject euipmentChecklist) {
		return equipmentDao.requestEuipmentChecklist(euipmentChecklist);
	}
	@Override
	public JSONObject viewEuipmentChecklist() {
		return equipmentDao.viewEuipmentChecklist();
	}
	@Override
	public JSONObject getEuipmentChecklistById(JSONObject checklistrequest) {
		return equipmentDao.getEuipmentChecklistById(checklistrequest);
	}
	
	@Override
	public JSONObject saveEquipmentCheckMaster(JSONObject equipmentCheckMaster) {
		return equipmentDao.saveEquipmentCheckMaster(equipmentCheckMaster);
		
	}
	@Override
	public JSONObject saveMaintananceSchedule(JSONObject maintananceSchedule) {
		return equipmentDao.saveMaintananceSchedule(maintananceSchedule);
	}
	@Override
	public JSONObject orderEquipmentParts(JSONObject orderEquipmentParts) {
		return equipmentDao.orderEquipmentParts(orderEquipmentParts);
	}
	@Override
	public JSONObject viewOrderEquipmentParts() {
		return equipmentDao.viewOrderEquipmentParts();
	}
	@Override
	public JSONObject scheduleMaintanance(JSONObject scheduleMaintanance) {
		return equipmentDao.scheduleMaintanance(scheduleMaintanance);
	}
	@Override
	public JSONObject getScheduleMaintananceById(JSONObject maintananceschedule) {
		return equipmentDao.getScheduleMaintananceById(maintananceschedule);
	}
	@Override
	public JSONObject saveRoutinescheduleCheckData(
			JSONObject routinescheduleCheckData) {
		return equipmentDao.saveRoutinescheduleCheckData(routinescheduleCheckData);
	}
	//Last
	@Override
	public JSONObject updateEquipmentCheckRequest(JSONObject updateStatus) {
		return  equipmentDao.updateEquipmentCheckRequest(updateStatus);
	}
	

}
