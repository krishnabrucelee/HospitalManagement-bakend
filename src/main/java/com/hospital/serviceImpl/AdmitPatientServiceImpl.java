/**
 * 
 */
package com.hospital.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.AdmitPatientDao;
import com.hospital.model.AdmitPatient;
import com.hospital.service.AdmitPatientService;

/**
 * @author Krishna
 *
 */
@Service
public class AdmitPatientServiceImpl implements AdmitPatientService {

	@Autowired
	private AdmitPatientDao admitPatientDao;
	
	@Override
	public AdmitPatient addAdmission(AdmitPatient admission) {
		return admitPatientDao.addAdmission(admission);
	}

}
