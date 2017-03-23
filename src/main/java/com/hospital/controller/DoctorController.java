/**
 * 
 */
package com.hospital.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.service.DoctorService;

/**
 * @author Krishna
 *
 */
@Controller
public class DoctorController {

	/**
	 * Doctor Service.
	 */
	@Autowired
	private DoctorService doctorService;

	/**
	 * Create Doctor.
	 * 
	 * @param doctor
	 * @return doctor
	 */
	@RequestMapping(value = "/addDoctor", method = RequestMethod.POST)
	public @ResponseBody JSONObject addDoctor(@RequestBody JSONObject doctor) {
		return doctorService.addDoctor(doctor);
	}

	/**
	 * List doctor.
	 * 
	 * @param doctor
	 * @return doctor
	 */
	@RequestMapping(value = "/listDoctor")
	public @ResponseBody JSONObject listDoctor() {
		return doctorService.listDoctor();
	}
	
	/**
	 * Update Doctor.
	 * 
	 * @param doctor
	 * @return doctor
	 */
	@RequestMapping(value = "/updateDoctor")
	public @ResponseBody JSONObject updateDoctor(@RequestBody JSONObject doctor) {
		return doctorService.updateDoctor(doctor);
	}

	/**
	 * Delete doctor.
	 * 
	 * @param doctor
	 * @return doctor
	 */
	@RequestMapping(value = "/deleteDoctor", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteDoctor(@RequestBody JSONObject doctorId) {
		return doctorService.deleteDoctor(doctorId);
	}
	
	@RequestMapping(value = "/getDoctorByEmail", method = RequestMethod.POST)
	public @ResponseBody JSONObject getDoctorByEmail(@RequestBody JSONObject doctorEmail) {
		return doctorService.getDoctorByEmail(doctorEmail);
	}
	
	@RequestMapping(value = "/listConsultant")
	public @ResponseBody JSONObject listConsultant() {
		return doctorService.listConsultant();
	}
}
