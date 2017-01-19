/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.BillingChartDao;
import com.hospital.service.BillingChartService;

/**
 * @author Krishna
 *
 */
@Service
public class BillingChartServiceImpl implements BillingChartService {


	@Autowired
	private BillingChartDao billingChartDao;
	
	@Override
	public JSONObject addBillingChart(JSONObject billingChart) {
		return billingChartDao.addBillingChart(billingChart);
	}

}
