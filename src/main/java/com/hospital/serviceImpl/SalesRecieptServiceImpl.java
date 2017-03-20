/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.SalesRecieptDao;
import com.hospital.service.SalesRecieptService;

/**
 * @author Krishna
 *
 */
@Service
public class SalesRecieptServiceImpl implements SalesRecieptService {

	/**
	 * SalesReciept dao
	 */
	@Autowired
	SalesRecieptDao salesRecieptdao;
	
	@Override
	public JSONObject addSalesReciept(JSONObject salesReciept) {
		return salesRecieptdao.addSalesReciept(salesReciept);
	}

	@Override
	public JSONObject listSalesReciept() {
		return salesRecieptdao.listSalesReciept();
	}

	@Override
	public JSONObject updateSalesReciept(JSONObject salesReciept) {
		return salesRecieptdao.updateSalesReciept(salesReciept);
	}

	@Override
	public JSONObject deleteSalesReciept(JSONObject salesRecieptId) {
		return salesRecieptdao.deleteSalesReciept(salesRecieptId);
	}

}
