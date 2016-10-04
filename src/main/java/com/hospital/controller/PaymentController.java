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

import com.hospital.service.PaymentService;

/**
 * @author Krishna
 *
 */
@Controller
public class PaymentController {

	/**
	 * Payment Service.
	 */
	@Autowired
	private PaymentService paymentService;

	/**
	 * Create Payment.
	 * 
	 * @param payment
	 * @return payment
	 */
	@RequestMapping(value = "/addPayment", method = RequestMethod.POST)
	public @ResponseBody JSONObject addPayment(@RequestBody JSONObject payment) {
		return paymentService.addPayment(payment);
	}

	/**
	 * List payment.
	 * 
	 * @param payment
	 * @return payment
	 */
	@RequestMapping(value = "/listPayment")
	public @ResponseBody JSONObject listPayment() {
		return paymentService.listPayment();
	}
	
	/**
	 * Update Payment.
	 * 
	 * @param payment
	 * @return payment
	 */
	@RequestMapping(value = "/updatePayment")
	public @ResponseBody JSONObject updatePayment(@RequestBody JSONObject payment) {
		return paymentService.updatePayment(payment);
	}

	/**
	 * Delete payment.
	 * 
	 * @param payment
	 * @return payment
	 */
	@RequestMapping(value = "/deletePayment", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deletePayment(@RequestBody JSONObject paymentId) {
		return paymentService.deletePayment(paymentId);
	}
}
