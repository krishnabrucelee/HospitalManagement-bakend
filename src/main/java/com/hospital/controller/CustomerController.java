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

import com.hospital.service.CustomerService;

@Controller
public class CustomerController {

	/**
	 * Customer Service.
	 */
	@Autowired
	private CustomerService customerService;

	/**
	 * Create Customer.
	 * 
	 * @param customer
	 * @return customer
	 */
	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	public @ResponseBody JSONObject addCustomer(@RequestBody JSONObject customer) {
		return customerService.addCustomer(customer);
	}

	/**
	 * List customer.
	 * 
	 * @param customer
	 * @return customer
	 */
	@RequestMapping(value = "/listCustomer")
	public @ResponseBody JSONObject listCustomer() {
		return customerService.listCustomer();
	}
	
	/**
	 * Update Customer.
	 * 
	 * @param customer
	 * @return customer
	 */
	@RequestMapping(value = "/updateCustomer")
	public @ResponseBody JSONObject updateCustomer(@RequestBody JSONObject customer) {
		return customerService.updateCustomer(customer);
	}

	/**
	 * Delete customer.
	 * 
	 * @param customer
	 * @return customer
	 */
	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteCustomer(@RequestBody JSONObject customerId) {
		return customerService.deleteCustomer(customerId);
	}
}
