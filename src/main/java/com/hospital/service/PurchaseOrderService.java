/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface PurchaseOrderService {

	/**
	 * Create PurchaseOrder.
	 * 
	 * @param purchaseOrder
	 * @return purchaseOrder
	 */
	public JSONObject addPurchaseOrder(JSONObject purchaseOrder);

	/**
	 * List PurchaseOrder.
	 * 
	 * @param purchaseOrder
	 * @return purchaseOrder
	 */
	public JSONObject listPurchaseOrder();

	/**
	 * Update PurchaseOrder.
	 * 
	 * @param purchaseOrder
	 * @return purchaseOrder
	 */
	public JSONObject updatePurchaseOrder(JSONObject purchaseOrder);

	/**
	 * Delete PurchaseOrder.
	 * 
	 * @param purchaseOrder
	 * @return purchaseOrder
	 */
	public JSONObject deletePurchaseOrder(JSONObject purchaseOrderId);

}
