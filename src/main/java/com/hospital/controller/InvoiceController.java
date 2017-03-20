/**
 * 
 */
package com.hospital.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.service.InvoiceService;

/**
 * @author Krishna
 *
 */
@Controller
public class InvoiceController {

	/**
	 * Invoice Service.
	 */
	@Autowired
	private InvoiceService invoiceService;

	/**
	 * Create Invoice.
	 * 
	 * @param invoice
	 * @return invoice
	 */
	@RequestMapping(value = "/addInvoice", method = RequestMethod.POST)
	public @ResponseBody JSONObject addInvoice(@RequestBody JSONObject invoice) {
		return invoiceService.addInvoice(invoice);
	}

	/**
	 * List invoice.
	 * 
	 * @param invoice
	 * @return invoice
	 */
	@RequestMapping(value = "/listInvoice")
	public @ResponseBody JSONObject listInvoice() {
		return invoiceService.listInvoice();
	}
	
	/**
	 * Update Invoice.
	 * 
	 * @param invoice
	 * @return invoice
	 */
	@RequestMapping(value = "/updateInvoice")
	public @ResponseBody JSONObject updateInvoice(@RequestBody JSONObject invoice) {
		return invoiceService.updateInvoice(invoice);
	}

	/**
	 * Delete invoice.
	 * 
	 * @param invoice
	 * @return invoice
	 */
	@RequestMapping(value = "/deleteInvoice", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteInvoice(@RequestBody JSONObject invoiceId) {
		return invoiceService.deleteInvoice(invoiceId);
	}
}
