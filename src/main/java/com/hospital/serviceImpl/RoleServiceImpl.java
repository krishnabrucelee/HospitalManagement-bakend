/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.RoleDao;
import com.hospital.model.Role;
import com.hospital.service.RoleService;

/**
 * @author Krishna
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	/**
	 * Role dao
	 */
	@Autowired
	RoleDao roledao;
	
	@Override
	public JSONObject addRole(JSONObject role) {
		return roledao.addRole(role);
	}

	@Override
	public JSONObject listRole() {
		return roledao.listRole();
	}

	@Override
	public JSONObject updateRole(JSONObject role) {
		return roledao.updateRole(role);
	}

	@Override
	public JSONObject deleteRole(JSONObject roleId) {
		return roledao.deleteRole(roleId);
	}
	
	@Override
	public Role addRoleFromStaff(Role role) {
		return roledao.addRoleFromStaff(role);
	}

}
