/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

import com.hospital.model.Users;

/**
 * @author Krishna
 *
 */
public interface UserService {

	/**
	 * Create User.
	 * 
	 * @param user
	 * @return user
	 */
	public JSONObject addUser(JSONObject user);

	/**
	 * List User.
	 * 
	 * @param user
	 * @return user
	 */
	public JSONObject listUser();

	/**
	 * Update User.
	 * 
	 * @param user
	 * @return user
	 */
	public JSONObject updateUser(JSONObject user);

	/**
	 * Delete User.
	 * 
	 * @param user
	 * @return user
	 */
	public JSONObject deleteUser(JSONObject userId);

	/**
	 * @param user
	 */
	public Users addUser(Users user);

	/**
	 * @param user
	 * @return
	 */
	public JSONObject checkUser(JSONObject user);

}
