package com.hospital.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "department")
public class Department implements Serializable { 

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "department_id")
	private Integer departmentId;
	
	@Column(name = "department_name")
	private String departmentName;
	
	@Column(name = "department_description")
	private String department_description;

	/**
	 * Get the departmentId of Department.
	 *
	 * @return the departmentId
	 */
	public Integer getDepartmentId() {
		return departmentId;
	}

	/**
	 * Set the departmentId of Department.
	 *
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * Get the departmentName of Department.
	 *
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * Set the departmentName of Department.
	 *
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * Get the department_description of Department.
	 *
	 * @return the department_description
	 */
	public String getDepartment_description() {
		return department_description;
	}

	/**
	 * Set the department_description of Department.
	 *
	 * @param department_description the department_description to set
	 */
	public void setDepartment_description(String department_description) {
		this.department_description = department_description;
	}

}
