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

import com.hospital.service.SalesRecieptService;

/**
 * @author Krishna
 *
 */
@Controller
public class SalesRecieptController {

	/**
	 * SalesReciept Service.
	 */
	@Autowired
	private SalesRecieptService salesRecieptService;

	/**
	 * Create SalesReciept.
	 * 
	 * @param salesReciept
	 * @return salesReciept
	 */
	@RequestMapping(value = "/addSalesReciept", method = RequestMethod.POST)
	public @ResponseBody JSONObject addSalesReciept(@RequestBody JSONObject salesReciept) {
		return salesRecieptService.addSalesReciept(salesReciept);
	}

	/**
	 * List salesReciept.
	 * 
	 * @param salesReciept
	 * @return salesReciept
	 */
	@RequestMapping(value = "/listSalesReciept")
	public @ResponseBody JSONObject listSalesReciept() {
		return salesRecieptService.listSalesReciept();
	}
	
	/**
	 * Update SalesReciept.
	 * 
	 * @param salesReciept
	 * @return salesReciept
	 */
	@RequestMapping(value = "/updateSalesReciept")
	public @ResponseBody JSONObject updateSalesReciept(@RequestBody JSONObject salesReciept) {
		return salesRecieptService.updateSalesReciept(salesReciept);
	}

	/**
	 * Delete salesReciept.
	 * 
	 * @param salesReciept
	 * @return salesReciept
	 */
	@RequestMapping(value = "/deleteSalesReciept", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteSalesReciept(@RequestBody JSONObject salesRecieptId) {
		return salesRecieptService.deleteSalesReciept(salesRecieptId);
	}
}
