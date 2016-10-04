/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface TransferDoctorDetailsService {

	/**
	 * Create TransferDoctorDetails.
	 * 
	 * @param transferDoctorDetails
	 * @return transferDoctorDetails
	 */
	public JSONObject addTransferDoctorDetails(JSONObject transferDoctorDetails);

	/**
	 * List TransferDoctorDetails.
	 * 
	 * @param transferDoctorDetails
	 * @return transferDoctorDetails
	 */
	public JSONObject listTransferDoctorDetails();

	/**
	 * Update TransferDoctorDetails.
	 * 
	 * @param transferDoctorDetails
	 * @return transferDoctorDetails
	 */
	public JSONObject updateTransferDoctorDetails(JSONObject transferDoctorDetails);

	/**
	 * Delete TransferDoctorDetails.
	 * 
	 * @param transferDoctorDetails
	 * @return transferDoctorDetails
	 */
	public JSONObject deleteTransferDoctorDetails(JSONObject transferDoctorDetailsId);

}
