/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.ReceivePaymentDao;
import com.hospital.service.ReceivePaymentService;

/**
 * @author Krishna
 *
 */
@Service
public class ReceivePaymentServiceImpl implements ReceivePaymentService {

	/**
	 * ReceivePayment dao
	 */
	@Autowired
	ReceivePaymentDao receivePaymentdao;
	
	@Override
	public JSONObject addReceivePayment(JSONObject receivePayment) {
		return receivePaymentdao.addReceivePayment(receivePayment);
	}

	@Override
	public JSONObject listReceivePayment() {
		return receivePaymentdao.listReceivePayment();
	}

	@Override
	public JSONObject updateReceivePayment(JSONObject receivePayment) {
		return receivePaymentdao.updateReceivePayment(receivePayment);
	}

	@Override
	public JSONObject deleteReceivePayment(JSONObject receivePaymentId) {
		return receivePaymentdao.deleteReceivePayment(receivePaymentId);
	}

}
