/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.PurchaseBillingTransactionDao;
import com.hospital.service.PurchaseBillingTransactionService;

/**
 * @author Krishna
 *
 */
@Service
public class PurchaseBillingTransactionServiceImpl implements PurchaseBillingTransactionService {

	/**
	 * PurchaseBillingTransaction dao
	 */
	@Autowired
	PurchaseBillingTransactionDao purchaseBillingTransactiondao;
	
	@Override
	public JSONObject addPurchaseBillingTransaction(JSONObject purchaseBillingTransaction) {
		return purchaseBillingTransactiondao.addPurchaseBillingTransaction(purchaseBillingTransaction);
	}

	@Override
	public JSONObject listPurchaseBillingTransaction() {
		return purchaseBillingTransactiondao.listPurchaseBillingTransaction();
	}

	@Override
	public JSONObject updatePurchaseBillingTransaction(JSONObject purchaseBillingTransaction) {
		return purchaseBillingTransactiondao.updatePurchaseBillingTransaction(purchaseBillingTransaction);
	}

	@Override
	public JSONObject deletePurchaseBillingTransaction(JSONObject purchaseBillingTransactionId) {
		return purchaseBillingTransactiondao.deletePurchaseBillingTransaction(purchaseBillingTransactionId);
	}

	@Override
	public JSONObject listByTransactionIdAndLastDate(JSONObject purchaseBillingId) {
		return purchaseBillingTransactiondao.listByTransactionIdAndLastDate(purchaseBillingId);
	}

}
