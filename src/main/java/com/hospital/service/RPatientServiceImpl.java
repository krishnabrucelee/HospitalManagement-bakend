package com.hospital.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.RPatientDao;
import com.hospital.model.Patient;
import com.hospital.serviceImpl.RPatientService;
@Service
public class RPatientServiceImpl implements RPatientService {
	
	@Autowired
	RPatientDao patientdao;
	
	@Override
	public void savePatient(HashMap<String, Object> requestParams) {
		patientdao.savePatient(requestParams);
	}
	@Override
	public List<Patient> getAllPatient() {
		return patientdao.getAllPatient();
	}

}
