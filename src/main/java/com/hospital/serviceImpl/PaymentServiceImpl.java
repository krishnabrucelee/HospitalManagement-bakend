/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.PaymentDao;
import com.hospital.service.PaymentService;

/**
 * @author Krishna
 *
 */
@Service
public class PaymentServiceImpl implements PaymentService {

	/**
	 * Payment dao
	 */
	@Autowired
	PaymentDao paymentdao;
	
	@Override
	public JSONObject addPayment(JSONObject payment) {
		return paymentdao.addPayment(payment);
	}

	@Override
	public JSONObject listPayment() {
		return paymentdao.listPayment();
	}

	@Override
	public JSONObject updatePayment(JSONObject payment) {
		return paymentdao.updatePayment(payment);
	}

	@Override
	public JSONObject deletePayment(JSONObject paymentId) {
		return paymentdao.deletePayment(paymentId);
	}

}
