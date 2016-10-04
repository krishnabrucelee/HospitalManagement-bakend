/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.WantedStockDao;
import com.hospital.service.WantedStockService;

/**
 * @author Krishna
 *
 */
@Service
public class WantedStockServiceImpl implements WantedStockService {

	/**
	 * WantedStock dao
	 */
	@Autowired
	WantedStockDao wantedStockdao;
	
	@Override
	public JSONObject addWantedStock(JSONObject wantedStock) {
		return wantedStockdao.addWantedStock(wantedStock);
	}

	@Override
	public JSONObject listWantedStock() {
		return wantedStockdao.listWantedStock();
	}

	@Override
	public JSONObject updateWantedStock(JSONObject wantedStock) {
		return wantedStockdao.updateWantedStock(wantedStock);
	}

	@Override
	public JSONObject deleteWantedStock(JSONObject wantedStockId) {
		return wantedStockdao.deleteWantedStock(wantedStockId);
	}

}
