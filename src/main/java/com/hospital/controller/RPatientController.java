package com.hospital.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.hospital.model.Patient;
import com.hospital.serviceImpl.RPatientService;

@Controller
public class RPatientController {
	
	@Autowired
	RPatientService patient;
	
	@Autowired
	RestTemplate restTemplate;
	@SuppressWarnings("unchecked")
	
	@RequestMapping(value = "/")
	public String adminHomePage(HttpSession session) {
		/* HttpHeaders headers = new HttpHeaders();
        headers.add("userid","1");
        headers.add("role","client");
       
      HashMap<String, Object> sample = new HashMap<String, Object>();
      sample.put("email","restTemplate@gmail.com");
      sample.put("password","12334");
      sample.put( "employee_id","EMP96dfd3");
      sample.put("phonenumber","123456756");
      sample.put("userType","employee");
      sample.put("address","Coimbatore");
      sample.put("joindate","06-08-2016");
      sample.put("userid",1);
      
       HttpEntity<HashMap<String, Object>> request = new HttpEntity<HashMap<String,Object>>(sample,headers);
       
       HashMap<String, Object> resutl = new HashMap<String, Object>();
		try {
			resutl = restTemplate.postForObject(new URI("http://localhost:8090/CoEasyRestEndPoint/createEmployee"), request,HashMap.class);
			
			System.out.println(resutl);
				
		} catch (RestClientException | URISyntaxException e) {
			e.printStackTrace();
		}*/
       
		
		return "menubar";
	}
	@RequestMapping(value = "/savePatient",method=RequestMethod.POST)
	public ModelAndView savePatient(@RequestParam HashMap<String,Object> requestParams) {
		ModelAndView mav = new ModelAndView("Patient");
		System.out.println("Patient data="+requestParams);
		 //patient.savePatient(requestParams);
		 return mav;
	}
	@RequestMapping(value="/agentsearchFlight")
	public ModelAndView flightDataForm(){
		System.out.println("Inside agent controller agentsearchFlight ");
		ModelAndView mav = new ModelAndView("Patient");
		
		List<Patient> list =patient.getAllPatient();
		
		for (Patient patient : list) {
			System.out.println(patient.getEmail());
		}
		mav.addObject("listPatient", list);
		return mav;
	}
}
