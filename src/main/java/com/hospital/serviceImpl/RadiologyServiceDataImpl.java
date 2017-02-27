package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.RadiologyDataDao;
import com.hospital.service.RadiologyService;
@Service
public class RadiologyServiceDataImpl implements RadiologyService {
	
	@Autowired
	RadiologyDataDao radiologydaoo;
 
	@Override
	public JSONObject addRadiologyTestName(JSONObject radiology) {
		return radiologydaoo.addRadiologyTestName(radiology);
	}

	@Override
	public JSONObject listRadiologyTest() {
		return radiologydaoo.listRadiologyTest();
	}

	@Override
	public JSONObject getRadiologyTestNameById(JSONObject radiologyrequestid) {
		return null;
	}

	@Override
	public JSONObject updateRadiologyTestName(JSONObject radiology) {
		return radiologydaoo.updateRadiologyTestName(radiology);
	}

	@Override
	public JSONObject deleteRadiologyTest(JSONObject radiology) {
		return radiologydaoo.deleteRadiologyTest(radiology);
	}

	
	@Override
	public JSONObject listPatientRadiologyTest() {
		System.out.println("Inside webservice IMPL");
		return radiologydaoo.listPatientRadiologyTest();
	}

}
