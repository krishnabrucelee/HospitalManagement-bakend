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

import com.hospital.service.RoleService;

/**
 * @author Krishna
 *
 */
@Controller
public class RoleController {

	/**
	 * Role Service.
	 */
	@Autowired
	private RoleService roleService;

	/**
	 * Create Role.
	 * 
	 * @param role
	 * @return role
	 */
	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	public @ResponseBody JSONObject addRole(@RequestBody JSONObject role) {
		return roleService.addRole(role);
	}

	/**
	 * List role.
	 * 
	 * @param role
	 * @return role
	 */
	@RequestMapping(value = "/listRole")
	public @ResponseBody JSONObject listRole() {
		return roleService.listRole();
	}
	
	/**
	 * Update Role.
	 * 
	 * @param role
	 * @return role
	 */
	@RequestMapping(value = "/updateRole")
	public @ResponseBody JSONObject updateRole(@RequestBody JSONObject role) {
		return roleService.updateRole(role);
	}

	/**
	 * Delete role.
	 * 
	 * @param role
	 * @return role
	 */
	@RequestMapping(value = "/deleteRole", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteRole(@RequestBody JSONObject roleId) {
		return roleService.deleteRole(roleId);
	}
}
