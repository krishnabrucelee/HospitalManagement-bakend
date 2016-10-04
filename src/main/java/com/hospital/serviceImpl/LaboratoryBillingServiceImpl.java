/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.LaboratoryBillingDao;
import com.hospital.service.LaboratoryBillingService;

/**
 * @author Krishna
 *
 */
@Service
public class LaboratoryBillingServiceImpl implements LaboratoryBillingService {

	/**
	 * LaboratoryBilling dao
	 */
	@Autowired
	LaboratoryBillingDao laboratoryBillingdao;
	
	@Override
	public JSONObject addLaboratoryBilling(JSONObject laboratoryBilling) {
		return laboratoryBillingdao.addLaboratoryBilling(laboratoryBilling);
	}

	@Override
	public JSONObject listLaboratoryBilling() {
		return laboratoryBillingdao.listLaboratoryBilling();
	}

	@Override
	public JSONObject updateLaboratoryBilling(JSONObject laboratoryBilling) {
		return laboratoryBillingdao.updateLaboratoryBilling(laboratoryBilling);
	}

	@Override
	public JSONObject deleteLaboratoryBilling(JSONObject laboratoryBillingId) {
		return laboratoryBillingdao.deleteLaboratoryBilling(laboratoryBillingId);
	}

}
