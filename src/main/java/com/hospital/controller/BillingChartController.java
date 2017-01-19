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

import com.hospital.service.AppointmentService;
import com.hospital.service.BillingChartService;

@Controller
public class BillingChartController {

	/**
	 * Appointment Service.
	 */
	@Autowired
	private BillingChartService billingChartService;

	/**
	 * Create BillingChart.
	 * 
	 * @param BillingChart
	 * @return BillingChart
	 */
	@RequestMapping(value = "/addBillingChart", method = RequestMethod.POST)
	public @ResponseBody JSONObject addBillingChart(@RequestBody JSONObject billingChart) {
		return billingChartService.addBillingChart(billingChart);
	}
}
