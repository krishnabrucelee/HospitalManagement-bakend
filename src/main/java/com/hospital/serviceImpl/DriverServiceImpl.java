/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.DriverDao;
import com.hospital.model.Driver;
import com.hospital.service.DriverService;

/**
 * @author Krishna
 *
 */
@Service
public class DriverServiceImpl implements DriverService {

	/**
	 * Driver dao
	 */
	@Autowired
	DriverDao driverdao;
	
	@Override
	public JSONObject addDriver(JSONObject driver) {
		return driverdao.addDriver(driver);
	}

	@Override
	public JSONObject listDriver() {
		return driverdao.listDriver();
	}

	@Override
	public JSONObject updateDriver(JSONObject driver) {
		return driverdao.updateDriver(driver);
	}

	@Override
	public JSONObject deleteDriver(JSONObject driverId) {
		return driverdao.deleteDriver(driverId);
	}

	@Override
	public Driver addDriverFromStaff(Driver driver) {
		return driverdao.addDriverFromStaff(driver);
	}

}
