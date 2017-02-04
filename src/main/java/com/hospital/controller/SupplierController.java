/**
 * 
 */
package com.hospital.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.service.SupplierService;

/**
 * @author Krishna
 *
 */
@Controller
public class SupplierController {

	/**
	 * Supplier Service.
	 */
	@Autowired
	private SupplierService supplierService;

	/**
	 * Create Supplier.
	 * 
	 * @param supplier
	 * @return supplier
	 */
	@RequestMapping(value = "/createSupplier", method = RequestMethod.POST)
	public @ResponseBody JSONObject createSupplier(@RequestBody JSONObject supplier) {
		return supplierService.createSupplier(supplier);
	}
	
	/**
	 * List supplier.
	 * 
	 * @param supplier
	 * @return supplier
	 */
	@RequestMapping(value = "/listSupplier")
	public @ResponseBody JSONObject listSupplier() {
		return supplierService.listSupplier();
	}
	
	/**
	 * Update Supplier.
	 * 
	 * @param supplier
	 * @return supplier
	 */
	@RequestMapping(value = "/updateSupplier")
	public @ResponseBody JSONObject updateSupplier(@RequestBody JSONObject supplier) {
		return supplierService.updateSupplier(supplier);
	}

	/**
	 * Delete supplier.
	 * 
	 * @param supplier
	 * @return supplier
	 */
	@RequestMapping(value = "/deleteSupplier", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteSupplier(@RequestBody JSONObject supplierId) {
		return supplierService.deleteSupplier(supplierId);
	}
	
	@RequestMapping(value = "/getSupplierById", method = RequestMethod.POST)
	public @ResponseBody JSONObject getSupplierById(@RequestBody JSONObject supplierId) {
		return supplierService.getSupplierById(supplierId);
	}
}
