/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface TariffService {

	/**
	 * Create Tariff.
	 * 
	 * @param tariff
	 * @return tariff
	 */
	public JSONObject addTariff(JSONObject tariff);

	/**
	 * List Tariff.
	 * 
	 * @param tariff
	 * @return tariff
	 */
	public JSONObject listTariff();

	/**
	 * Update Tariff.
	 * 
	 * @param tariff
	 * @return tariff
	 */
	public JSONObject updateTariff(JSONObject tariff);

	/**
	 * Delete Tariff.
	 * 
	 * @param tariff
	 * @return tariff
	 */
	public JSONObject deleteTariff(JSONObject tariffId);

}
