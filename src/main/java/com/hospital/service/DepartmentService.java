/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface DepartmentService {

	/**
	 * Create Department.
	 * 
	 * @param department
	 * @return department
	 */
	public JSONObject addDepartment(JSONObject department);

	/**
	 * List Department.
	 * 
	 * @param department
	 * @return department
	 */
	public JSONObject listDepartment();

	/**
	 * Update Department.
	 * 
	 * @param department
	 * @return department
	 */
	public JSONObject updateDepartment(JSONObject department);

	/**
	 * Delete Department.
	 * 
	 * @param department
	 * @return department
	 */
	public JSONObject deleteDepartment(JSONObject departmentId);
}
