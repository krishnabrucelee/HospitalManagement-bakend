/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.BloodBankDao;
import com.hospital.model.BloodBank;
import com.hospital.service.BloodBankService;

/**
 * @author Krishna
 *
 */
@Service
public class BloodBankServiceImpl implements BloodBankService {

	/**
	 * BloodBank dao
	 */
	@Autowired
	BloodBankDao bloodBankdao;
	
	@Override
	public JSONObject addBloodBank(JSONObject bloodBank) {
		return bloodBankdao.addBloodBank(bloodBank);
	}

	@Override
	public JSONObject listBloodBank() {
		return bloodBankdao.listBloodBank();
	}

	@Override
	public JSONObject updateBloodBank(JSONObject bloodBank) {
		return bloodBankdao.updateBloodBank(bloodBank);
	}

	@Override
	public JSONObject deleteBloodBank(JSONObject bloodBankId) {
		return bloodBankdao.deleteBloodBank(bloodBankId);
	}

	@Override
	public BloodBank updateBloodBankById(BloodBank bloodBank) {
		return bloodBankdao.updateBloodBankById(bloodBank);
	}

	@Override
	public Integer deleteBloodBankById(Integer bloodBankId) {
		return bloodBankdao.deleteBloodBankById(bloodBankId);
	}
}
