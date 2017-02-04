/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import com.hospital.dao.StockMedicineDao;
import com.hospital.service.StockMedicineService;

/**
 * @author Krishna
 *
 */
@Service
public class StockMedicineServiceImpl implements StockMedicineService {

	/**
	 * StockMedicine dao
	 */
	@Autowired
	StockMedicineDao stockMedicinedao;
	
	@Override
	public JSONObject addStockMedicine(JSONObject stockMedicine) {
		return stockMedicinedao.addStockMedicine(stockMedicine);
	}

	@Override
	public JSONObject listStockMedicine() {
		return stockMedicinedao.listStockMedicine();
	}

	@Override
	public JSONObject updateStockMedicine(JSONObject stockMedicine) {
		return stockMedicinedao.updateStockMedicine(stockMedicine);
	}

	@Override
	public JSONObject deleteStockMedicine(JSONObject stockMedicineId) {
		return stockMedicinedao.deleteStockMedicine(stockMedicineId);
	}

	@Override
	public JSONObject adjustStockMedicine(JSONObject stockMedicine) {
		return stockMedicinedao.adjustStockMedicine(stockMedicine);
	}
	
	@Override
	public JSONObject getStockMedicineById(JSONObject stockMedicineId) {
		return stockMedicinedao.getStockMedicineById(stockMedicineId);
	}
}
