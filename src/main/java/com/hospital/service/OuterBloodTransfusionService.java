/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface OuterBloodTransfusionService {

	/**
	 * Create OuterBloodTransfusion.
	 * 
	 * @param outerBloodTrans
	 * @return outerBloodTrans
	 */
	public JSONObject addOuterBloodTransfusion(JSONObject outerBloodTrans);

	/**
	 * List OuterBloodTransfusion.
	 * 
	 * @param outerBloodTrans
	 * @return outerBloodTrans
	 */
	public JSONObject listOuterBloodTransfusion();

	/**
	 * Update OuterBloodTransfusion.
	 * 
	 * @param outerBloodTrans
	 * @return outerBloodTrans
	 */
	public JSONObject updateOuterBloodTransfusion(JSONObject outerBloodTrans);

	/**
	 * Delete OuterBloodTransfusion.
	 * 
	 * @param outerBloodTrans
	 * @return outerBloodTrans
	 */
	public JSONObject deleteOuterBloodTransfusion(JSONObject outerBloodTransId);

}
