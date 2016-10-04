/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.TransferDoctorDetailsDao;

/**
 * @author Krishna
 *
 */
@Service
public class TransferDoctorDetailsService implements com.hospital.service.TransferDoctorDetailsService {

	/**
	 * TransferDoctorDetails dao
	 */
	@Autowired
	TransferDoctorDetailsDao transferDoctorDetailsdao;
	
	@Override
	public JSONObject addTransferDoctorDetails(JSONObject transferDoctorDetails) {
		return transferDoctorDetailsdao.addTransferDoctorDetails(transferDoctorDetails);
	}

	@Override
	public JSONObject listTransferDoctorDetails() {
		return transferDoctorDetailsdao.listTransferDoctorDetails();
	}

	@Override
	public JSONObject updateTransferDoctorDetails(JSONObject transferDoctorDetails) {
		return transferDoctorDetailsdao.updateTransferDoctorDetails(transferDoctorDetails);
	}

	@Override
	public JSONObject deleteTransferDoctorDetails(JSONObject transferDoctorDetailsId) {
		return transferDoctorDetailsdao.deleteTransferDoctorDetails(transferDoctorDetailsId);
	}

}
