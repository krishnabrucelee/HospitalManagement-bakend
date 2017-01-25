/**
 * 
 */
package com.hospital.dao;

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
import com.hospital.model.Role;

/**
 * @author Krishna
 *
 */
@Repository
public class RoleDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;

	static {
		System.out.println("class RoleDao executed");
	}
	
	private Session session = null;
	private Transaction transaction = null;
	
	@SuppressWarnings("unchecked")
	public JSONObject addRole(JSONObject role) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Role user = om.convertValue(role, Role.class);
//		//Load Department 
//		Department departmentDetails = session.load(Department.class, (Integer) role.get("department_id"));
//		user.setDepartment(departmentDetails);
		try {
			System.out.println("Inside Dao11 PATIENT");
			session.merge(user);
			transaction.commit();
			System.out.println("Save roles");
			status.put("success", "User details saved");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}

	public JSONObject listRole() {
		System.out.println("Inside Dao1Role");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Role> roleList = null;
		try {
			Query query = session.createQuery("FROM Role");
			roleList = query.list();
			status.put("Role", roleList);
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

	public JSONObject updateRole(JSONObject role) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Role roleDetails = session.load(Role.class, (Integer) role.get("roleId"));
//			roleDetails.setRoleDescription((String) role.get("roleDescription"));
//			//Load Department 
//			Department departmentDetails = session.load(Department.class, (Integer) role.get("department_id"));
//			roleDetails.setDepartment(departmentDetails);
			session.update(roleDetails);
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

	public Role getRole(Integer roleId) {
		Role role = null;
		try {
			session.beginTransaction();
			session.get(Role.class, roleId);
			role = (Role) session.get(Role.class, roleId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (role != null) {
			return role;
		} else {
			return null;
		}
	}

	public JSONObject deleteRole(JSONObject roleId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Role roleDetails = session.load(Role.class, (Integer) roleId.get("role_id"));
			session.delete(roleDetails);
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
	 * @param role
	 * @return
	 */
	public Role addRoleFromStaff(Role role) {
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(role);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return role;
	}

}
