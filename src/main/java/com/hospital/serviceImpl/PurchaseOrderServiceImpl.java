/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.PurchaseOrderDao;
import com.hospital.service.PurchaseOrderService;

/**
 * @author Krishna
 *
 */
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	/**
	 * PurchaseOrder dao
	 */
	@Autowired
	PurchaseOrderDao purchaseOrderdao;
	
	@Override
	public JSONObject addPurchaseOrder(JSONObject purchaseOrder) {
		return purchaseOrderdao.addPurchaseOrder(purchaseOrder);
	}

	@Override
	public JSONObject listPurchaseOrder() {
		return purchaseOrderdao.listPurchaseOrder();
	}

	@Override
	public JSONObject updatePurchaseOrder(JSONObject purchaseOrder) {
		return purchaseOrderdao.updatePurchaseOrder(purchaseOrder);
	}

	@Override
	public JSONObject deletePurchaseOrder(JSONObject purchaseOrderId) {
		return purchaseOrderdao.deletePurchaseOrder(purchaseOrderId);
	}

	@Override
	public JSONObject getItemsByDepartment(JSONObject department) {
		return purchaseOrderdao.getItemsByDepartment(department);
	}

}
