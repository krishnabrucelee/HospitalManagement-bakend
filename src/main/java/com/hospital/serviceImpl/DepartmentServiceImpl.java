/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.DepartmentDao;
import com.hospital.service.DepartmentService;
import com.hospital.service.DepartmentService;

/**
 * @author Krishna
 *
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

	/**
	 * Department dao
	 */
	@Autowired
	DepartmentDao departmentdao;
	
	@Override
	public JSONObject addDepartment(JSONObject department) {
		return departmentdao.addDepartment(department);
	}

	@Override
	public JSONObject listDepartment() {
		return departmentdao.listDepartment();
	}

	@Override
	public JSONObject updateDepartment(JSONObject department) {
		return departmentdao.updateDepartment(department);
	}

	@Override
	public JSONObject deleteDepartment(JSONObject departmentId) {
		return departmentdao.deleteDepartment(departmentId);
	}

}
