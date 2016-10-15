/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.BioMedicalTypeDao;
import com.hospital.service.BioMedicalTypeService;

/**
 * @author Krishna
 *
 */
@Service
public class BioMedicalTypeServiceImpl implements BioMedicalTypeService {

	/**
	 * BioMedicalType dao
	 */
	@Autowired
	BioMedicalTypeDao bioMedicalTypedao;
	
	@Override
	public JSONObject addBioMedicalType(JSONObject bioMedicalType) {
		return bioMedicalTypedao.addBioMedicalType(bioMedicalType);
	}

	@Override
	public JSONObject listBioMedicalType() {
		return bioMedicalTypedao.listBioMedicalType();
	}

	@Override
	public JSONObject updateBioMedicalType(JSONObject bioMedicalType) {
		return bioMedicalTypedao.updateBioMedicalType(bioMedicalType);
	}

	@Override
	public JSONObject deleteBioMedicalType(JSONObject bioMedicalTypeId) {
		return bioMedicalTypedao.deleteBioMedicalType(bioMedicalTypeId);
	}

}
