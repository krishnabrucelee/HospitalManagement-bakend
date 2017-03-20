/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface InvoiceService {

	/**
	 * Create Invoice.
	 * 
	 * @param invoice
	 * @return invoice
	 */
	public JSONObject addInvoice(JSONObject invoice);

	/**
	 * List Invoice.
	 * 
	 * @param invoice
	 * @return invoice
	 */
	public JSONObject listInvoice();

	/**
	 * Update Invoice.
	 * 
	 * @param invoice
	 * @return invoice
	 */
	public JSONObject updateInvoice(JSONObject invoice);

	/**
	 * Delete Invoice.
	 * 
	 * @param invoice
	 * @return invoice
	 */
	public JSONObject deleteInvoice(JSONObject invoiceId);

}
