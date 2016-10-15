/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.BioMedicalDisposalManagementDao;
import com.hospital.service.BioMedicalDisposalManagementService;

/**
 * @author Krishna
 *
 */
@Service
public class BioMedicalDisposalManagementServiceImpl implements BioMedicalDisposalManagementService {

	/**
	 * BioMedicalDisposalManagement dao
	 */
	@Autowired
	BioMedicalDisposalManagementDao bioMedicalDisposalManagementdao;
	
	@Override
	public JSONObject addBioMedicalDisposalManagement(JSONObject bioMedicalDisposalManagement) {
		return bioMedicalDisposalManagementdao.addBioMedicalDisposalManagement(bioMedicalDisposalManagement);
	}

	@Override
	public JSONObject listBioMedicalDisposalManagement() {
		return bioMedicalDisposalManagementdao.listBioMedicalDisposalManagement();
	}

	@Override
	public JSONObject updateBioMedicalDisposalManagement(JSONObject bioMedicalDisposalManagement) {
		return bioMedicalDisposalManagementdao.updateBioMedicalDisposalManagement(bioMedicalDisposalManagement);
	}

	@Override
	public JSONObject deleteBioMedicalDisposalManagement(JSONObject bioMedicalDisposalManagementId) {
		return bioMedicalDisposalManagementdao.deleteBioMedicalDisposalManagement(bioMedicalDisposalManagementId);
	}

}
