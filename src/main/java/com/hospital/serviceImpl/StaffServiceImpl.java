/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.StaffDao;
import com.hospital.service.StaffService;

/**
 * @author Krishna
 *
 */
@Service
public class StaffServiceImpl implements StaffService {

	/**
	 * Staff dao
	 */
	@Autowired
	StaffDao staffdao;
	
	@Override
	public JSONObject addStaff(JSONObject staff) throws Exception {
		return staffdao.addStaff(staff);
	}

	@Override
	public JSONObject listStaff() {
		return staffdao.listStaff();
	}

	@Override
	public JSONObject updateStaff(JSONObject staff) {
		return staffdao.updateStaff(staff);
	}

	@Override
	public JSONObject deleteStaff(JSONObject staffId) {
		return staffdao.deleteStaff(staffId);
	}

}
