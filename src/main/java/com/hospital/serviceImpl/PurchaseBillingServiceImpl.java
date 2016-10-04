/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.PurchaseBillingDao;
import com.hospital.service.PurchaseBillingService;

/**
 * @author Krishna
 *
 */
@Service
public class PurchaseBillingServiceImpl implements PurchaseBillingService {

	/**
	 * PurchaseBilling dao
	 */
	@Autowired
	PurchaseBillingDao purchaseBillingdao;
	
	@Override
	public JSONObject addPurchaseBilling(JSONObject purchaseBilling) {
		return purchaseBillingdao.addPurchaseBilling(purchaseBilling);
	}

	@Override
	public JSONObject listPurchaseBilling() {
		return purchaseBillingdao.listPurchaseBilling();
	}

	@Override
	public JSONObject updatePurchaseBilling(JSONObject purchaseBilling) {
		return purchaseBillingdao.updatePurchaseBilling(purchaseBilling);
	}

	@Override
	public JSONObject deletePurchaseBilling(JSONObject purchaseBillingId) {
		return purchaseBillingdao.deletePurchaseBilling(purchaseBillingId);
	}

}
