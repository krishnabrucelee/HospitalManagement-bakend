/**
 * 
 */
package com.hospital.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hospital.model.AdmitPatient;

/**
 * @author Krishna
 *
 */
@Repository
public class AdmitPatientDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class admissionDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;
	
	/**
	 * @param admission
	 * @return
	 */
	public AdmitPatient addAdmission(AdmitPatient admission) {
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(admission);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return admission;
	}

}
