/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.CashCounterDao;
import com.hospital.service.CashCounterService;

/**
 * @author Krishna
 *
 */
@Service
public class CashCounterServiceImpl implements CashCounterService {

	/**
	 * CashCounter dao
	 */
	@Autowired
	CashCounterDao cashCounterdao;
	
	@Override
	public JSONObject addCashCounter(JSONObject cashCounter) {
		return cashCounterdao.addCashCounter(cashCounter);
	}

	@Override
	public JSONObject listCashCounter() {
		return cashCounterdao.listCashCounter();
	}

	@Override
	public JSONObject updateCashCounter(JSONObject cashCounter) {
		return cashCounterdao.updateCashCounter(cashCounter);
	}

	@Override
	public JSONObject deleteCashCounter(JSONObject cashCounterId) {
		return cashCounterdao.deleteCashCounter(cashCounterId);
	}

}
