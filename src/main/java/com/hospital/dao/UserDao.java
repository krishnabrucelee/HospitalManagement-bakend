/**
 * 
 */
package com.hospital.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.ActivityLog;
import com.hospital.model.EMedicalReport;
import com.hospital.model.Patient;
import com.hospital.model.Users;
import com.hospital.util.AESCrypt;

/**
 * @author Krishna
 *
 */
@Repository
public class UserDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class UserDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addUser(JSONObject user) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Users appoint = om.convertValue(user, Users.class);

		try {
			System.out.println("Inside Dao11 Add USER");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save users");
			status.put("success", "Users details saved");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}

	public JSONObject listUser() {
		System.out.println("Inside Dao1User");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Users> userList = null;
		try {
			Query query = session.createQuery("FROM Users");
			userList = query.list();
			status.put("Users", userList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}

	public JSONObject updateUser(JSONObject user) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Long userId = Long.parseLong(user.get("userId").toString());
			Users userDetails = session.load(Users.class, userId);
			
			String encryptedPassword = AESCrypt.encrypt(user.get("password").toString());
			userDetails.setUserName(user.get("userName").toString());
			userDetails.setUserEmail(user.get("userEmail").toString());
			userDetails.setPassword(encryptedPassword);
			session.update(userDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}

	public Users getUser(Integer userId) {
		Users user = null;
		try {
			session.beginTransaction();
			session.get(Users.class, userId);
			user = (Users) session.get(Users.class, userId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (user != null) {
			return user;
		} else {
			return null;
		}
	}

	public JSONObject deleteUser(JSONObject userId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Users userDetails = session.load(Users.class,
					(Integer) userId.get("userId"));
			session.delete(userDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}

	/**
	 * @param user
	 * @return
	 */
	public Users addUser(Users user) {
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return user;
	}

	/**
	 * @param user
	 * @return
	 */
	public JSONObject checkUser(JSONObject user) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Users> userList = null;
		
		 
		try {
			Query query = session.createQuery("FROM Users WHERE userEmail = :userEmail");
			query.setParameter("userEmail", user.get("userEmail"));
			userList = query.list();
			
			if (userList.size() == 1) {
				for (Users userDetails : userList) {

					String encryptedPassword = AESCrypt.encrypt(user.get("password").toString());
					
					if (userDetails.getPassword().equals(encryptedPassword)) {
						status.put("user", userList);
						status.put("result", true);
						
						ActivityLog activity = new ActivityLog();
						activity.setUserName(userDetails.getUserName());
						activity.setUserId(userDetails.getUserId().intValue());
						activity.setLoginTime(new Date());
						session.save(activity);
					} else {
						status.put("user", "Password mismatch !!");
						status.put("result", false);
					}
				}
			} else {
				status.put("user", "Duplicate user!!");
				status.put("result", false);
			}
			
//			status.put("user", userList);
//			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		} finally {
			if (session.isOpen()) {
//				// session.close();
			}
		}
		return status;
	}

	/**
	 * @return
	 */
	public JSONObject listActivityLog() {
		System.out.println("Inside Dao1User");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Users> userList = null;
		try {
			Query query = session.createQuery("FROM ActivityLog");
			userList = query.list();
			status.put("ActivityLog", userList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}

	/**
	 * @param user
	 * @return
	 */
	public JSONObject getUserById(JSONObject user) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Users> userList = null;
//		  ObjectMapper mapper = new ObjectMapper();
//        SimpleModule module = new SimpleModule();        
//        module.addSerializer(com.monitorjbl.json.JsonView.class,new JsonViewSerializer());
//        mapper.registerModule(module);
		Integer userId = Integer.parseInt(user.get("id").toString());
		try {
			Query query = session.createQuery("FROM Users WHERE user_id = :id");
			query.setParameter("id", userId);
			userList = query.list();
			transaction.commit();
			if (userList.size() == 1) {
				for (Users userDetails : userList) {

					String decryptedPassword = AESCrypt.decrypt(userDetails.getPassword());
					userDetails.setPassword(decryptedPassword);
					List<Users> users = new ArrayList<>();
					users.add(userDetails);
					status.put("User", users.iterator());
				}
			}
//			String json = mapper.writeValueAsString(JsonView.with(patientDetailsList)
//					.onClass(BusAvailability.class,com.monitorjbl.json.Match.match().exclude("busDetails")));  
//			
//			
//			System.out.println(json);
//             ArrayList<Object> convertedValue = mapper.readValue(json,new TypeReference<ArrayList<Object>>() {});
//                   
//			if(convertedValue.isEmpty() | convertedValue == null)
//			{
//				status.put("status",false);
//				return status;
//			}		
//			status.put("Buses", convertedValue.iterator());
			
			System.out.println(" Inside Rest DAO Bus Status="+status);
			return status;	
			
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}


}
