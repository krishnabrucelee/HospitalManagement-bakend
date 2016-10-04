/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.DonorDao;
import com.hospital.service.DonorService;

/**
 * @author Krishna
 *
 */
@Service
public class DonorServiceImpl implements DonorService {

	/**
	 * Donor dao
	 */
	@Autowired
	DonorDao donordao;
	
	@Override
	public JSONObject addDonor(JSONObject donor) {
		return donordao.addDonor(donor);
	}

	@Override
	public JSONObject listDonor() {
		return donordao.listDonor();
	}

	@Override
	public JSONObject updateDonor(JSONObject donor) {
		return donordao.updateDonor(donor);
	}

	@Override
	public JSONObject deleteDonor(JSONObject donorId) {
		return donordao.deleteDonor(donorId);
	}



}
