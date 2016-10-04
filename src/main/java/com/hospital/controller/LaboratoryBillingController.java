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

import com.hospital.service.LaboratoryBillingService;

/**
 * @author Krishna
 *
 */
@Controller
public class LaboratoryBillingController {

	/**
	 * LaboratoryBilling Service.
	 */
	@Autowired
	private LaboratoryBillingService appointmentService;

	/**
	 * Create LaboratoryBilling.
	 * 
	 * @param appointment
	 * @return appointment
	 */
	@RequestMapping(value = "/addLaboratoryBilling", method = RequestMethod.POST)
	public @ResponseBody JSONObject addLaboratoryBilling(@RequestBody JSONObject appointment) {
		return appointmentService.addLaboratoryBilling(appointment);
	}

	/**
	 * List appointment.
	 * 
	 * @param appointment
	 * @return appointment
	 */
	@RequestMapping(value = "/listLaboratoryBilling")
	public @ResponseBody JSONObject listLaboratoryBilling() {
		return appointmentService.listLaboratoryBilling();
	}
	
	/**
	 * Update LaboratoryBilling.
	 * 
	 * @param appointment
	 * @return appointment
	 */
	@RequestMapping(value = "/updateLaboratoryBilling")
	public @ResponseBody JSONObject updateLaboratoryBilling(@RequestBody JSONObject appointment) {
		return appointmentService.updateLaboratoryBilling(appointment);
	}

	/**
	 * Delete appointment.
	 * 
	 * @param appointment
	 * @return appointment
	 */
	@RequestMapping(value = "/deleteLaboratoryBilling", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteLaboratoryBilling(@RequestBody JSONObject appointmentId) {
		return appointmentService.deleteLaboratoryBilling(appointmentId);
	}
}
