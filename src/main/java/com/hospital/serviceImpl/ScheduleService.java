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

	@Override
	public JSONObject generateDoctorSchedule(JSONObject scheduleInformation) {
		return scheduleDao.generateDoctorSchedule(scheduleInformation);
	}

	@Override
	public JSONObject saveDoctorSchedule(JSONObject scheduleInformation) {
		return scheduleDao.saveDoctorSchedule(scheduleInformation);
	}

	@Override
	public JSONObject getCurrentMonthNurseSchedule(JSONObject nurseDetail) {
		return scheduleDao.getCurrentMonthNurseSchedule(nurseDetail);
	}

	@Override
	public JSONObject getCurrentMonthDoctorSchedule(JSONObject doctorDetail) {
		return scheduleDao.getCurrentMonthDoctorSchedule(doctorDetail);
	}

}
