/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.BillingDao;
import com.hospital.service.BillingService;

/**
 * @author Krishna
 *
 */
@Service
public class BillingServiceImpl implements BillingService {

	/**
	 * Billing dao
	 */
	@Autowired
	BillingDao billingdao;
	
	@Override
	public JSONObject addBilling(JSONObject billing) {
		return billingdao.addBilling(billing);
	}

	@Override
	public JSONObject listBilling() {
		return billingdao.listBilling();
	}

	@Override
	public JSONObject updateBilling(JSONObject billing) {
		return billingdao.updateBilling(billing);
	}

	@Override
	public JSONObject deleteBilling(JSONObject billingId) {
		return billingdao.deleteBilling(billingId);
	}

}
