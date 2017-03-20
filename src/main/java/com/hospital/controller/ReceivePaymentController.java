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

import com.hospital.service.ReceivePaymentService;

/**
 * @author Krishna
 *
 */
@Controller
public class ReceivePaymentController {

	/**
	 * ReceivePayment Service.
	 */
	@Autowired
	private ReceivePaymentService receivePaymentService;

	/**
	 * Create ReceivePayment.
	 * 
	 * @param receivePayment
	 * @return receivePayment
	 */
	@RequestMapping(value = "/addReceivePayment", method = RequestMethod.POST)
	public @ResponseBody JSONObject addReceivePayment(@RequestBody JSONObject receivePayment) {
		return receivePaymentService.addReceivePayment(receivePayment);
	}

	/**
	 * List receivePayment.
	 * 
	 * @param receivePayment
	 * @return receivePayment
	 */
	@RequestMapping(value = "/listReceivePayment")
	public @ResponseBody JSONObject listReceivePayment() {
		return receivePaymentService.listReceivePayment();
	}
	
	/**
	 * Update ReceivePayment.
	 * 
	 * @param receivePayment
	 * @return receivePayment
	 */
	@RequestMapping(value = "/updateReceivePayment")
	public @ResponseBody JSONObject updateReceivePayment(@RequestBody JSONObject receivePayment) {
		return receivePaymentService.updateReceivePayment(receivePayment);
	}

	/**
	 * Delete receivePayment.
	 * 
	 * @param receivePayment
	 * @return receivePayment
	 */
	@RequestMapping(value = "/deleteReceivePayment", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteReceivePayment(@RequestBody JSONObject receivePaymentId) {
		return receivePaymentService.deleteReceivePayment(receivePaymentId);
	}
}
