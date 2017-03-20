/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.CustomerDao;
import com.hospital.service.CustomerService;

/**
 * @author Krishna
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	/**
	 * Customer dao
	 */
	@Autowired
	CustomerDao customerdao;
	
	@Override
	public JSONObject addCustomer(JSONObject customer) {
		return customerdao.addCustomer(customer);
	}

	@Override
	public JSONObject listCustomer() {
		return customerdao.listCustomer();
	}

	@Override
	public JSONObject updateCustomer(JSONObject customer) {
		return customerdao.updateCustomer(customer);
	}

	@Override
	public JSONObject deleteCustomer(JSONObject customerId) {
		return customerdao.deleteCustomer(customerId);
	}

}
