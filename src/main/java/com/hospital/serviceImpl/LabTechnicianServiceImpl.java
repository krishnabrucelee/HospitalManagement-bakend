/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.LabTechnicianDao;
import com.hospital.model.LabTechnician;
import com.hospital.service.LabTechnicianService;

/**
 * @author Krishna
 *
 */
@Service
public class LabTechnicianServiceImpl implements LabTechnicianService {

	/**
	 * LabTechnician dao
	 */
	@Autowired
	private LabTechnicianDao labTechniciandao;
	
	@Override
	public JSONObject addLabTechnician(JSONObject labTechnician) {
		return labTechniciandao.addLabTechnician(labTechnician);
	}

	@Override
	public JSONObject listLabTechnician() {
		return labTechniciandao.listLabTechnician();
	}

	@Override
	public JSONObject updateLabTechnician(JSONObject labTechnician) {
		return labTechniciandao.updateLabTechnician(labTechnician);
	}

	@Override
	public JSONObject deleteLabTechnician(JSONObject labTechnicianId) {
		return labTechniciandao.deleteLabTechnician(labTechnicianId);
	}

	@Override
	public LabTechnician addLabTechnicianFromStaff(LabTechnician labTechnician) {
		return labTechniciandao.addLabTechnicianFromStaff(labTechnician);
	}

}
