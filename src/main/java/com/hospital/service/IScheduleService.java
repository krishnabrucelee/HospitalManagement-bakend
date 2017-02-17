/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author admin
 *
 */
public interface IScheduleService {

	public JSONObject generateNurseSchedule(JSONObject scheduleInformation);
	
	public JSONObject saveNurseSchedule(JSONObject scheduleInformation);
	
	public JSONObject getCurrentMonthNurseSchedule(JSONObject nurseDetail);
	
	public JSONObject generateDoctorSchedule(JSONObject scheduleInformation);
	
	public JSONObject saveDoctorSchedule(JSONObject scheduleInformation);
	
	public JSONObject getCurrentMonthDoctorSchedule(JSONObject doctorDetail);
	
}
