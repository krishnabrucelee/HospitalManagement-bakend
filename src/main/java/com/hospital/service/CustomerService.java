/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface CustomerService {

	/**
	 * Create Customer.
	 * 
	 * @param customer
	 * @return customer
	 */
	public JSONObject addCustomer(JSONObject customer);

	/**
	 * List Customer.
	 * 
	 * @param customer
	 * @return customer
	 */
	public JSONObject listCustomer();

	/**
	 * Update Customer.
	 * 
	 * @param customer
	 * @return customer
	 */
	public JSONObject updateCustomer(JSONObject customer);

	/**
	 * Delete Customer.
	 * 
	 * @param customer
	 * @return customer
	 */
	public JSONObject deleteCustomer(JSONObject customerId);

}
