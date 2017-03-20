/**
 * 
 */
package com.hospital.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.BloodBank;
import com.hospital.model.EMedicalReport;
import com.hospital.model.IssueBlood;

/**
 * @author Krishna
 *
 */
@Repository
public class IssueBloodDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class IssueBloodDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addIssueBlood(JSONObject issueBlood) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		IssueBlood appoint = om.convertValue(issueBlood, IssueBlood.class);

		
		List<BloodBank> bloodBankList = null;
			Query query = session.createQuery("FROM BloodBank WHERE bloodGroup = :bloodGroup");
			query.setParameter("bloodGroup", issueBlood.get("bloodGroup"));
			bloodBankList = query.list();
			Integer bloodQ = null;
		for (BloodBank blood : bloodBankList) {
			Integer bq = Integer.parseInt(issueBlood.get("bloodQuantity").toString());
			if (bq > blood.getBloodQuantity()) {
				if ((bq - blood.getBloodQuantity()) > 0) {
					bloodQ = bq - blood.getBloodQuantity();
					blood.setBloodQuantity(bloodQ);
					session.save(blood);
				}
			} else {
				bloodQ = blood.getBloodQuantity() - bq;
				blood.setBloodQuantity(bloodQ);
				session.save(blood);
			}
		}
		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save issueBloods");
			status.put("success", "User details saved");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}

	public JSONObject listIssueBlood() {
		System.out.println("Inside Dao1IssueBlood");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<IssueBlood> issueBloodList = null;
		try {
			Query query = session.createQuery("FROM IssueBlood");
			issueBloodList = query.list();
			status.put("IssueBlood", issueBloodList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}

	public JSONObject updateIssueBlood(JSONObject issueBlood) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			IssueBlood issueBloodDetails = session.load(IssueBlood.class,
					(Integer) issueBlood.get("issueBloodId"));
			session.update(issueBloodDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}

	public IssueBlood getIssueBlood(Integer issueBloodId) {
		IssueBlood issueBlood = null;
		try {
			session.beginTransaction();
			session.get(IssueBlood.class, issueBloodId);
			issueBlood = (IssueBlood) session.get(IssueBlood.class, issueBloodId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (issueBlood != null) {
			return issueBlood;
		} else {
			return null;
		}
	}

	public JSONObject deleteIssueBlood(JSONObject issueBloodId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			IssueBlood issueBloodDetails = session.load(IssueBlood.class,
					(Integer) issueBloodId.get("issueBloodId"));
			session.delete(issueBloodDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}

	/**
	 * @param issueBloodId
	 * @return
	 */
	public IssueBlood updateIssueBloodById(IssueBlood issueBlood) {
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			IssueBlood issueBloodDetails = session.load(IssueBlood.class, issueBlood.getIssueBloodId());
			session.update(issueBloodDetails);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return issueBlood;
	}

	/**
	 * @param issueBloodId
	 * @return
	 */
	public Integer deleteIssueBloodById(Integer issueBloodId) {
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			IssueBlood issueBloodDetails = session.load(IssueBlood.class, issueBloodId);
			session.delete(issueBloodDetails);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return issueBloodId;
	}

}
