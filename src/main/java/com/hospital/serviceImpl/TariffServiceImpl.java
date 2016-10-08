/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.TariffDao;
import com.hospital.service.TariffService;

/**
 * @author Krishna
 *
 */
@Service
public class TariffServiceImpl implements TariffService {

	/**
	 * Tariff dao
	 */
	@Autowired
	TariffDao tariffdao;
	
	@Override
	public JSONObject addTariff(JSONObject tariff) {
		return tariffdao.addTariff(tariff);
	}

	@Override
	public JSONObject listTariff() {
		return tariffdao.listTariff();
	}

	@Override
	public JSONObject updateTariff(JSONObject tariff) {
		return tariffdao.updateTariff(tariff);
	}

	@Override
	public JSONObject deleteTariff(JSONObject tariffId) {
		return tariffdao.deleteTariff(tariffId);
	}

}
