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
import com.hospital.service.UserService;

/**
 * @author Krishna
 *
 */
@Controller
public class UserController {

	/**
	 * User Service.
	 */
	@Autowired
	private UserService userService;

	/**
	 * Create User.
	 * 
	 * @param user
	 * @return user
	 */
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public @ResponseBody JSONObject addUser(@RequestBody JSONObject user) {
		return userService.addUser(user);
	}

	/**
	 * List user.
	 * 
	 * @param user
	 * @return user
	 */
	@RequestMapping(value = "/listUser")
	public @ResponseBody JSONObject listUser() {
		return userService.listUser();
	}
	
	/**
	 * Update User.
	 * 
	 * @param user
	 * @return user
	 */
	@RequestMapping(value = "/updateUser")
	public @ResponseBody JSONObject updateUser(@RequestBody JSONObject user) {
		return userService.updateUser(user);
	}

	/**
	 * Delete user.
	 * 
	 * @param user
	 * @return user
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteUser(@RequestBody JSONObject userId) {
		return userService.deleteUser(userId);
	}
	 
		/**
		 * Delete user.
		 * 
		 * @param user
		 * @return user
		 */
		@RequestMapping(value = "/checkUser")
		public @ResponseBody JSONObject checkUser(@RequestBody JSONObject user) throws Exception {
			return userService.checkUser(user);
		}
}
