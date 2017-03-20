/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.InvoiceDao;
import com.hospital.service.InvoiceService;

/**
 * @author Krishna
 *
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {

	/**
	 * Invoice dao
	 */
	@Autowired
	InvoiceDao invoicedao;
	
	@Override
	public JSONObject addInvoice(JSONObject invoice) {
		return invoicedao.addInvoice(invoice);
	}

	@Override
	public JSONObject listInvoice() {
		return invoicedao.listInvoice();
	}

	@Override
	public JSONObject updateInvoice(JSONObject invoice) {
		return invoicedao.updateInvoice(invoice);
	}

	@Override
	public JSONObject deleteInvoice(JSONObject invoiceId) {
		return invoicedao.deleteInvoice(invoiceId);
	}

}
