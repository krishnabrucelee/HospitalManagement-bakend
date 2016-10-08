/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.DietDao;
import com.hospital.service.DietService;

/**
 * @author prem
 *
 */
@Service
public class DietServiceImpl implements DietService {

	/**
	 * Diet dao
	 */
	@Autowired
	DietDao dietdao;
	
	@Override
	public JSONObject addDiet(JSONObject diet) {
		return dietdao.addDiet(diet);
	}

	@Override
	public JSONObject listDiet() {
		return dietdao.listDiet();
	}

	@Override
	public JSONObject updateDiet(JSONObject diet) {
		return dietdao.updateDiet(diet);
	}

	@Override
	public JSONObject deleteDiet(JSONObject dietId) {
		return dietdao.deleteDiet(dietId);
	}

	@Override
	public JSONObject listByPatientId(JSONObject patientId) {
		return dietdao.listByPatientId(patientId);
	}

}
