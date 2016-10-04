/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.StockLedgerDao;
import com.hospital.service.StockLedgerService;

/**
 * @author Krishna
 *
 */
@Service
public class StockLedgerServiceImpl implements StockLedgerService {

	/**
	 * StockLedger dao
	 */
	@Autowired
	StockLedgerDao stockLedgerdao;
	
	@Override
	public JSONObject addStockLedger(JSONObject stockLedger) {
		return stockLedgerdao.addStockLedger(stockLedger);
	}

	@Override
	public JSONObject listStockLedger() {
		return stockLedgerdao.listStockLedger();
	}

	@Override
	public JSONObject updateStockLedger(JSONObject stockLedger) {
		return stockLedgerdao.updateStockLedger(stockLedger);
	}

	@Override
	public JSONObject deleteStockLedger(JSONObject stockLedgerId) {
		return stockLedgerdao.deleteStockLedger(stockLedgerId);
	}

}
