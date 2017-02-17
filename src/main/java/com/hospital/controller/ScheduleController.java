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

import com.hospital.service.IScheduleService;

/**
 * @author admin
 *
 */
@Controller
public class ScheduleController {

	@Autowired
	IScheduleService scheduleService;
	
	@RequestMapping(value="generateNurseSchedule",method=RequestMethod.POST)
	public @ResponseBody JSONObject generateNurseSchedule(@RequestBody JSONObject scheduleInformation){
		return scheduleService.generateNurseSchedule(scheduleInformation);
	}
	
	@RequestMapping(value="saveNurseSchedule",method=RequestMethod.POST)
	public @ResponseBody JSONObject saveNurseSchedule(@RequestBody JSONObject scheduleInformation)
	{
		return scheduleService.saveNurseSchedule(scheduleInformation);
	}	
	
	@RequestMapping(value="generateDoctorSchedule",method=RequestMethod.POST)
	public @ResponseBody JSONObject generateDoctorSchedule(@RequestBody JSONObject scheduleInformation){
		return scheduleService.generateDoctorSchedule(scheduleInformation);
	}
	
	@RequestMapping(value="saveDoctorSchedule",method=RequestMethod.POST)
	public @ResponseBody JSONObject saveDoctorSchedule(@RequestBody JSONObject scheduleInformation)
	{
		return scheduleService.saveDoctorSchedule(scheduleInformation);
	}
	
	@RequestMapping(value="getCurrentMonthNurseSchedule",method=RequestMethod.POST)
	public @ResponseBody JSONObject getCurrentMonthNurseSchedule(@RequestBody JSONObject nurseDetails)
	{
		return scheduleService.getCurrentMonthNurseSchedule(nurseDetails);
	}
	
	@RequestMapping(value="getCurrentMonthDoctorSchedule",method=RequestMethod.POST)
	public @ResponseBody JSONObject getCurrentMonthDoctorSchedule(@RequestBody JSONObject doctorDetails)
	{
		return scheduleService.getCurrentMonthDoctorSchedule(doctorDetails);
	}
}
