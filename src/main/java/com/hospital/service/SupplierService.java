/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface SupplierService {

	/**
	 * @param supplier
	 * @return
	 */
	JSONObject createSupplier(JSONObject supplier);

	/**
	 * List Supplier.
	 * 
	 * @param supplier
	 * @return supplier
	 */
	public JSONObject listSupplier();

	/**
	 * Update Supplier.
	 * 
	 * @param supplier
	 * @return supplier
	 */
	public JSONObject updateSupplier(JSONObject supplier);

	/**
	 * Delete Supplier.
	 * 
	 * @param supplier
	 * @return supplier
	 */
	public JSONObject deleteSupplier(JSONObject supplierId);

	/**
	 * @param supplierId
	 * @return
	 */
	public JSONObject getSupplierById(JSONObject supplierId);
}
