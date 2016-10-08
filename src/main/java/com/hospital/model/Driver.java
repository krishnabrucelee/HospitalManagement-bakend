/**
 * 
 */
package com.hospital.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "driver")
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "driver_id")
	private Integer driverId;
	
	@Column(name = "driver_reg_id")
	private Integer driverRegId;

	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_driver_FK"))
	private Department department;
	
	/**
	 * Get the driverId of Driver.
	 *
	 * @return the driverId
	 */
	public Integer getDriverId() {
		return driverId;
	}

	/**
	 * Set the driverId of Driver.
	 *
	 * @param driverId the driverId to set
	 */
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}


	/**
	 * Get the department of Driver.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of Driver.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the driverRegId of Driver.
	 *
	 * @return the driverRegId
	 */
	public Integer getDriverRegId() {
		return driverRegId;
	}

	/**
	 * Set the driverRegId of Driver.
	 *
	 * @param driverRegId the driverRegId to set
	 */
	public void setDriverRegId(Integer driverRegId) {
		this.driverRegId = driverRegId;
	}
	
	
}
