/**
 * 
 */
package com.hospital.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "receptionist")
public class Receptionist {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "receptionist_id")
	private Integer receptionistId;
	
	@Column(name = "receptionist_reg_id")
	private String receptionistRegId;
	
	@Column(name = "staff_id")
	private String staffId;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "receptionist_dob")
	private Date receptionistDob;
	
	@Column(name = "receptionist_email")
	private String receptionistEmail;
	
	@Column(name = "receptionist_phone")
	private String receptionistPhoneNumber;
		
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_receptionist_FK"))
	private Department department;

    /** User role. */
    @OneToOne(targetEntity = Role.class)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", foreignKey = @ForeignKey(name = "role_receptionist_FK"))
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
	 * Get the receptionistId of Receptionist.java.
	 *
	 * @return the receptionistId
	 */
	public Integer getReceptionistId() {
		return receptionistId;
	}

	/**
	 * Set the receptionistId of Receptionist.java.
	 *
	 * @param receptionistId the receptionistId to set
	 */
	public void setReceptionistId(Integer receptionistId) {
		this.receptionistId = receptionistId;
	}

	/**
	 * Get the receptionistRegId of Receptionist.java.
	 *
	 * @return the receptionistRegId
	 */
	public String getReceptionistRegId() {
		return receptionistRegId;
	}

	/**
	 * Set the receptionistRegId of Receptionist.java.
	 *
	 * @param string the receptionistRegId to set
	 */
	public void setReceptionistRegId(String string) {
		this.receptionistRegId = string;
	}

	/**
	 * Get the staffId of Receptionist.java.
	 *
	 * @return the staffId
	 */
	public String getStaffId() {
		return staffId;
	}

	/**
	 * Set the staffId of Receptionist.java.
	 *
	 * @param staffId the staffId to set
	 */
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	/**
	 * Get the receptionistDob of Receptionist.java.
	 *
	 * @return the receptionistDob
	 */
	public Date getReceptionistDob() {
		return receptionistDob;
	}

	/**
	 * Set the receptionistDob of Receptionist.java.
	 *
	 * @param receptionistDob the receptionistDob to set
	 */
	public void setReceptionistDob(Date receptionistDob) {
		this.receptionistDob = receptionistDob;
	}

	/**
	 * Get the receptionistEmail of Receptionist.java.
	 *
	 * @return the receptionistEmail
	 */
	public String getReceptionistEmail() {
		return receptionistEmail;
	}

	/**
	 * Set the receptionistEmail of Receptionist.java.
	 *
	 * @param receptionistEmail the receptionistEmail to set
	 */
	public void setReceptionistEmail(String receptionistEmail) {
		this.receptionistEmail = receptionistEmail;
	}

	/**
	 * Get the receptionistPhoneNumber of Receptionist.java.
	 *
	 * @return the receptionistPhoneNumber
	 */
	public String getReceptionistPhoneNumber() {
		return receptionistPhoneNumber;
	}

	/**
	 * Set the receptionistPhoneNumber of Receptionist.java.
	 *
	 * @param string the receptionistPhoneNumber to set
	 */
	public void setReceptionistPhoneNumber(String string) {
		this.receptionistPhoneNumber = string;
	}

	/**
	 * Get the department of Receptionist.java.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of Receptionist.java.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the role of Receptionist.java.
	 *
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Set the role of Receptionist.java.
	 *
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Get the userType of Receptionist.java.
	 *
	 * @return the userType
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * Set the userType of Receptionist.java.
	 *
	 * @param userType the userType to set
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
    
}
