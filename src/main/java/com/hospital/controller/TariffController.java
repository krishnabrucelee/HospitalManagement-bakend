/**
 * 
 */
package com.hospital.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.service.TariffService;

/**
 * @author Krishna
 *
 */
@Controller
public class TariffController {

	/**
	 * Tariff Service.
	 */
	@Autowired
	private TariffService tariffService;

	/**
	 * Create Tariff.
	 * 
	 * @param tariff
	 * @return tariff
	 */
	@RequestMapping(value = "/addTariff", method = RequestMethod.POST)
	public @ResponseBody JSONObject addTariff(@RequestBody JSONObject tariff) {
		return tariffService.addTariff(tariff);
	}

	/**
	 * List tariff.
	 * 
	 * @param tariff
	 * @return tariff
	 */
	@RequestMapping(value = "/listTariff")
	public @ResponseBody JSONObject listTariff() {
		return tariffService.listTariff();
	}
	
	/**
	 * Update Tariff.
	 * 
	 * @param tariff
	 * @return tariff
	 */
	@RequestMapping(value = "/updateTariff")
	public @ResponseBody JSONObject updateTariff(@RequestBody JSONObject tariff) {
		return tariffService.updateTariff(tariff);
	}

	/**
	 * Delete tariff.
	 * 
	 * @param tariff
	 * @return tariff
	 */
	@RequestMapping(value = "/deleteTariff", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteTariff(@RequestBody JSONObject tariffId) {
		return tariffService.deleteTariff(tariffId);
	}
}
