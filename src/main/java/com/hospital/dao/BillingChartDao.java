/**
 * 
 */
package com.hospital.dao;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.BillingChart;
import com.hospital.model.BillingMaster;

/**
 * @author Krishna
 *
 */
@Repository
public class BillingChartDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	private SessionFactory sessionFactory;	

	static {
		System.out.println("class BillingChartDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;
	
	/**
	 * @param billingChart
	 * @return
	 */
	public JSONObject addBillingChart(JSONObject billingChart) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		BillingChart appoint = om.convertValue(billingChart, BillingChart.class);
		appoint.setCreatedDate(new Date());
		try {
			System.out.println("Inside Dao11 PATIENT");
			session.merge(appoint);
			transaction.commit();
			System.out.println("Save billingChart");
			status.put("success", "billingChart saved");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}

}
