/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.PharmacistDao;
import com.hospital.model.Pharmacist;
import com.hospital.service.PharmacistService;

/**
 * @author Krishna
 *
 */
@Service
public class PharmacistServiceImpl implements PharmacistService {

	/**
	 * Pharmacist dao
	 */
	@Autowired
	private PharmacistDao pharmacistdao;
	
	@Override
	public JSONObject addPharmacist(JSONObject pharmacist) {
		return pharmacistdao.addPharmacist(pharmacist);
	}

	@Override
	public JSONObject listPharmacist() {
		return pharmacistdao.listPharmacist();
	}

	@Override
	public JSONObject updatePharmacist(JSONObject pharmacist) {
		return pharmacistdao.updatePharmacist(pharmacist);
	}

	@Override
	public JSONObject deletePharmacist(JSONObject pharmacistId) {
		return pharmacistdao.deletePharmacist(pharmacistId);
	}

	@Override
	public Pharmacist addPharmacistFromStaff(Pharmacist pharmacist) {
		return pharmacistdao.addPharmacistFromStaff(pharmacist);
	}

}
