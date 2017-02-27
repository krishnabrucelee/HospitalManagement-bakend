package com.hospital.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.service.RadiologyService;

@Controller
public class RadiologyDataController {
	
	@Autowired
	RadiologyService iradiologyService;
	
	/**
	 * List listPatientRadiologyTest.
	 * 
	 * @return listPatientRadiologyTest
	 */
	@RequestMapping(value = "/listPatientRadiologyTestrequest")
	public @ResponseBody JSONObject llistPatientRadiologyTest() {
		JSONObject sadf = iradiologyService.listPatientRadiologyTest();
		System.out.println("Inside webservice controller"+sadf);
		
		return sadf;
	}
	

}
