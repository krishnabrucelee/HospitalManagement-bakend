package com.hospital.serviceImpl;

import java.util.HashMap;
import java.util.List;

import com.hospital.model.Patient;



public interface RPatientService {
	
	public void savePatient(HashMap<String,Object> requestParams);
	public List<Patient> getAllPatient();
	
}
