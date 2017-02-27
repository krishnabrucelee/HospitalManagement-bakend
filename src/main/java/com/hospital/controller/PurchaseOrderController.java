package com.hospital.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.service.PurchaseOrderService;

/**
 * @author Krishna
 *
 */
@Controller
public class PurchaseOrderController {

	/**
	 * PurchaseOrder Service.
	 */
	@Autowired
	private PurchaseOrderService purchaseOrderService;

	/**
	 * Create PurchaseOrder.
	 * 
	 * @param purchaseOrder
	 * @return purchaseOrder
	 */
	@RequestMapping(value = "/addPurchaseOrder", method = RequestMethod.POST)
	public @ResponseBody JSONObject addPurchaseOrder(@RequestBody JSONObject purchaseOrder) {
		return purchaseOrderService.addPurchaseOrder(purchaseOrder);
	}

	/**
	 * List purchaseOrder.
	 * 
	 * @param purchaseOrder
	 * @return purchaseOrder
	 */
	@RequestMapping(value = "/listPurchaseOrder")
	public @ResponseBody JSONObject listPurchaseOrder() {
		return purchaseOrderService.listPurchaseOrder();
	}
	
	/**
	 * Update PurchaseOrder.
	 * 
	 * @param purchaseOrder
	 * @return purchaseOrder
	 */
	@RequestMapping(value = "/updatePurchaseOrder")
	public @ResponseBody JSONObject updatePurchaseOrder(@RequestBody JSONObject purchaseOrder) {
		return purchaseOrderService.updatePurchaseOrder(purchaseOrder);
	}

	/**
	 * Delete purchaseOrder.
	 * 
	 * @param purchaseOrder
	 * @return purchaseOrder
	 */
	@RequestMapping(value = "/deletePurchaseOrder", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deletePurchaseOrder(@RequestBody JSONObject purchaseOrderId) {
		return purchaseOrderService.deletePurchaseOrder(purchaseOrderId);
	}
	
	/**
	 * Update PurchaseOrder.
	 * 
	 * @param purchaseOrder
	 * @return purchaseOrder
	 */
	@RequestMapping(value = "/getItemsByDepartment")
	public @ResponseBody JSONObject getItemsByDepartment(@RequestBody JSONObject department) {
		return purchaseOrderService.getItemsByDepartment(department);
	}
}
