/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface LaboratoryBillingService {

	/**
	 * Create LaboratoryBilling.
	 * 
	 * @param laboratoryBilling
	 * @return laboratoryBilling
	 */
	public JSONObject addLaboratoryBilling(JSONObject laboratoryBilling);

	/**
	 * List LaboratoryBilling.
	 * 
	 * @param laboratoryBilling
	 * @return laboratoryBilling
	 */
	public JSONObject listLaboratoryBilling();

	/**
	 * Update LaboratoryBilling.
	 * 
	 * @param laboratoryBilling
	 * @return laboratoryBilling
	 */
	public JSONObject updateLaboratoryBilling(JSONObject laboratoryBilling);

	/**
	 * Delete LaboratoryBilling.
	 * 
	 * @param laboratoryBilling
	 * @return laboratoryBilling
	 */
	public JSONObject deleteLaboratoryBilling(JSONObject laboratoryBillingId);

}
