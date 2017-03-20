/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface SalesRecieptService {

	/**
	 * Create SalesReciept.
	 * 
	 * @param salesReciept
	 * @return salesReciept
	 */
	public JSONObject addSalesReciept(JSONObject salesReciept);

	/**
	 * List SalesReciept.
	 * 
	 * @param salesReciept
	 * @return salesReciept
	 */
	public JSONObject listSalesReciept();

	/**
	 * Update SalesReciept.
	 * 
	 * @param salesReciept
	 * @return salesReciept
	 */
	public JSONObject updateSalesReciept(JSONObject salesReciept);

	/**
	 * Delete SalesReciept.
	 * 
	 * @param salesReciept
	 * @return salesReciept
	 */
	public JSONObject deleteSalesReciept(JSONObject salesRecieptId);

}
