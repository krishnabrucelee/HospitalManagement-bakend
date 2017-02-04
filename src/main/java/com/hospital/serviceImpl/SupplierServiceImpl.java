/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.Supplierdao;
import com.hospital.service.SupplierService;

/**
 * @author Krishna
 *
 */
@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private Supplierdao supplierdao;
	
	@Override
	public JSONObject createSupplier(JSONObject supplier) {
		return supplierdao.createSupplier(supplier);
	}

	@Override
	public JSONObject listSupplier() {
		return supplierdao.listSupplier();
	}

	@Override
	public JSONObject updateSupplier(JSONObject supplier) {
		return supplierdao.updateSupplier(supplier);
	}

	@Override
	public JSONObject deleteSupplier(JSONObject supplierId) {
		return supplierdao.deleteSupplier(supplierId);
	}

	@Override
	public JSONObject getSupplierById(JSONObject supplierId) {
		return supplierdao.getSupplierById(supplierId);
	}
}
