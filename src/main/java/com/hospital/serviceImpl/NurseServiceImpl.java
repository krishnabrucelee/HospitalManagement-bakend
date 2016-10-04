/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.NurseDao;
import com.hospital.model.Nurse;
import com.hospital.service.NurseService;

/**
 * @author Krishna
 *
 */
@Service
public class NurseServiceImpl implements NurseService {

	/**
	 * Nurse dao
	 */
	@Autowired
	private NurseDao nursedao;
	
	@Override
	public JSONObject addNurse(JSONObject nurse) {
		return nursedao.addNurse(nurse);
	}

	@Override
	public JSONObject listNurse() {
		return nursedao.listNurse();
	}

	@Override
	public JSONObject updateNurse(JSONObject nurse) {
		return nursedao.updateNurse(nurse);
	}

	@Override
	public JSONObject deleteNurse(JSONObject nurseId) {
		return nursedao.deleteNurse(nurseId);
	}

	@Override
	public Nurse addNurseFromStaff(Nurse nurse) {
		return nursedao.addNurseFromStaff(nurse);
	}

}
