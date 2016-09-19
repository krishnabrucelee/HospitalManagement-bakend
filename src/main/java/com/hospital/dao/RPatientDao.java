package com.hospital.dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.hospital.model.Patient;

@Repository
public class RPatientDao {
	@Autowired
	RestTemplate restTemplate;
	public void savePatient(HashMap<String, Object> requestParams) {
		 HttpHeaders headers = new HttpHeaders();
		 headers.add("userid","1");
	        headers.add("role","client");
	        HashMap<String, Object> sample = new HashMap<String, Object>();
	        HttpEntity<HashMap<String, Object>> request = new HttpEntity<HashMap<String,Object>>(requestParams,headers);
	        HashMap<String, Object> resutl = new HashMap<String, Object>();
	 		try {
	 			resutl = restTemplate.postForObject(new URI("http://localhost:8090/CoEasyRestEndPoint/createEmployee"), request,HashMap.class);
	 			
	 			System.out.println(resutl);
	 				
	 		} catch (RestClientException | URISyntaxException e) {
	 			e.printStackTrace();
	 		}
	}
	public List<Patient> getAllPatient() {
		 HttpHeaders headers = new HttpHeaders();
         headers.add("userid","5");
         headers.add("role","client get method");
         HttpEntity<HashMap<String, Object>> requestEntity = new HttpEntity<HashMap<String,Object>>(headers);
         ResponseEntity<HashMap> patientList     = null;
         try {
          patientList     =restTemplate.exchange(new URI("http://localhost:8086/CoEasyRestEndPoint/sampleGet"), HttpMethod.GET, requestEntity, HashMap.class);
         
         System.out.println("GEt Result : "+patientList.getBody());
         
         } catch (RestClientException e) {
             e.printStackTrace();
         } catch (URISyntaxException e) {
             e.printStackTrace();
         }
         if (patientList!=null ) {
    		return (List<Patient>) patientList;
    	} else {
    		return null;
    	}
	}
}
