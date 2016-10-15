/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.BioMedicalWasteDepartmentDao;
import com.hospital.service.BioMedicalWasteDepartmentService;

/**
 * @author Krishna
 *
 */
@Service
public class BioMedicalWasteDepartmentServiceImpl implements BioMedicalWasteDepartmentService {

	/**
	 * BioMedicalWasteDepartment dao
	 */
	@Autowired
	BioMedicalWasteDepartmentDao BioMedicalWasteDepartmentdao;
	
	@Override
	public JSONObject addBioMedicalWasteDepartment(JSONObject BioMedicalWasteDepartment) {
		return BioMedicalWasteDepartmentdao.addBioMedicalWasteDepartment(BioMedicalWasteDepartment);
	}

	@Override
	public JSONObject listBioMedicalWasteDepartment() {
		return BioMedicalWasteDepartmentdao.listBioMedicalWasteDepartment();
	}

	@Override
	public JSONObject updateBioMedicalWasteDepartment(JSONObject BioMedicalWasteDepartment) {
		return BioMedicalWasteDepartmentdao.updateBioMedicalWasteDepartment(BioMedicalWasteDepartment);
	}

	@Override
	public JSONObject deleteBioMedicalWasteDepartment(JSONObject BioMedicalWasteDepartmentId) {
		return BioMedicalWasteDepartmentdao.deleteBioMedicalWasteDepartment(BioMedicalWasteDepartmentId);
	}

}
