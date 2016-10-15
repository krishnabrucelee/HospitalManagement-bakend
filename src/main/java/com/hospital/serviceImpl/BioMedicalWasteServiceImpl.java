/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.BioMedicalWasteDao;
import com.hospital.service.BioMedicalWasteService;

/**
 * @author Krishna
 *
 */
@Service
public class BioMedicalWasteServiceImpl implements BioMedicalWasteService {

	/**
	 * BioMedicalWaste dao
	 */
	@Autowired
	BioMedicalWasteDao bioMedicalWastedao;
	
	@Override
	public JSONObject addBioMedicalWaste(JSONObject bioMedicalWaste) {
		return bioMedicalWastedao.addBioMedicalWaste(bioMedicalWaste);
	}

	@Override
	public JSONObject listBioMedicalWaste() {
		return bioMedicalWastedao.listBioMedicalWaste();
	}

	@Override
	public JSONObject updateBioMedicalWaste(JSONObject bioMedicalWaste) {
		return bioMedicalWastedao.updateBioMedicalWaste(bioMedicalWaste);
	}

	@Override
	public JSONObject deleteBioMedicalWaste(JSONObject bioMedicalWasteId) {
		return bioMedicalWastedao.deleteBioMedicalWaste(bioMedicalWasteId);
	}

}
