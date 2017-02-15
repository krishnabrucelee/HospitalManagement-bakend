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
}
