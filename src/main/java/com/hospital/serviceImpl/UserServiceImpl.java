/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.UserDao;
import com.hospital.model.Users;
import com.hospital.service.UserService;

/**
 * @author Krishna
 *
 */
@Service
public class UserServiceImpl implements UserService {

	/**
	 * User dao
	 */
	@Autowired
	UserDao userdao;
	
	@Override
	public JSONObject addUser(JSONObject user) {
		return userdao.addUser(user);
	}

	@Override
	public JSONObject listUser() {
		return userdao.listUser();
	}

	@Override
	public JSONObject updateUser(JSONObject user) {
		return userdao.updateUser(user);
	}

	@Override
	public JSONObject deleteUser(JSONObject userId) {
		return userdao.deleteUser(userId);
	}

	@Override
	public Users addUser(Users user) {
		return userdao.addUser(user);
	}

	@Override
	public JSONObject checkUser(JSONObject user) {
		return userdao.checkUser(user);
	}

}
