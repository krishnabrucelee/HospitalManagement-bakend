/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.ScheduleDao;
import com.hospital.service.IScheduleService;

/**
 * @author admin
 *
 */
@Service
public class ScheduleService implements IScheduleService {

	@Autowired
	private ScheduleDao scheduleDao;
	
	@Override
	public JSONObject generateNurseSchedule(JSONObject scheduleInformation) {
		return scheduleDao.generateNurseSchedule(scheduleInformation);
	}

	@Override
	public JSONObject saveNurseSchedule(JSONObject sheduleInformation) {
		return scheduleDao.saveNurseSchedule(sheduleInformation);
	}

}
