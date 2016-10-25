/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

import com.hospital.model.Role;

/**
 * @author Krishna
 *
 */
public interface RoleService {

	/**
	 * Create Role.
	 * 
	 * @param role
	 * @return role
	 */
	public JSONObject addRole(JSONObject role);

	/**
	 * List Role.
	 * 
	 * @param role
	 * @return role
	 */
	public JSONObject listRole();

	/**
	 * Update Role.
	 * 
	 * @param role
	 * @return role
	 */
	public JSONObject updateRole(JSONObject role);

	/**
	 * Delete Role.
	 * 
	 * @param role
	 * @return role
	 */
	public JSONObject deleteRole(JSONObject roleId);

	/**
	 * @param role
	 * @return
	 */
	public Role addRoleFromStaff(Role role);
}
