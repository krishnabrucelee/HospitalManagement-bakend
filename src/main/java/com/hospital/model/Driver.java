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

import com.hospital.model.Doctor.UserType;

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

	@Column(name = "driver_email")
	private String driverEmail;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_driver_FK"))
	private Department department;
	
    /** User role. */
    @OneToOne(targetEntity = Role.class)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", foreignKey = @ForeignKey(name = "role_driver_FK"))
    private Role role;
    
    /** User type of the user. */
    @Column(name = "user_type")
    private UserType userType;
	
    /** Define user type. */
    public enum UserType {
        /** Define type constant. */
        DOMAIN_ADMIN, ROOT_ADMIN, DOMAIN_USER, USER;
    }
    
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

	/**
	 * Get the driverEmail of Driver.
	 *
	 * @return the driverEmail
	 */
	public String getDriverEmail() {
		return driverEmail;
	}

	/**
	 * Set the driverEmail of Driver.
	 *
	 * @param driverEmail the driverEmail to set
	 */
	public void setDriverEmail(String driverEmail) {
		this.driverEmail = driverEmail;
	}

	/**
	 * Get the role of Driver.
	 *
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Set the role of Driver.
	 *
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Get the userType of Driver.
	 *
	 * @return the userType
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * Set the userType of Driver.
	 *
	 * @param userType the userType to set
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
}
