/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface InterBloodTransfusionService {

	/**
	 * Create InterBloodTransfusion.
	 * 
	 * @param interBloodTrans
	 * @return interBloodTrans
	 */
	public JSONObject addInterBloodTransfusion(JSONObject interBloodTrans);

	/**
	 * List InterBloodTransfusion.
	 * 
	 * @param interBloodTrans
	 * @return interBloodTrans
	 */
	public JSONObject listInterBloodTransfusion();

	/**
	 * Update InterBloodTransfusion.
	 * 
	 * @param interBloodTrans
	 * @return interBloodTrans
	 */
	public JSONObject updateInterBloodTransfusion(JSONObject interBloodTrans);

	/**
	 * Delete InterBloodTransfusion.
	 * 
	 * @param interBloodTrans
	 * @return interBloodTrans
	 */
	public JSONObject deleteInterBloodTransfusion(JSONObject interBloodTransId);

}
