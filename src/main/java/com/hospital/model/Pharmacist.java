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
@Table(name = "pharmacist")
public class Pharmacist {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pharmacist_id")
	private Integer pharmacistId;
	
	@Column(name = "pharmacist_reg_id")
	private Integer pharmacistRegId;
	
	@Column(name = "staff_id")
	private Integer staffId;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "pharmacist_dob")
	private Date pharmacistDob;
	
	@Column(name = "pharmacist_email")
	private String pharmacistEmail;
	
	@Column(name = "pharmacist_phone")
	private Integer pharmacistPhoneNumber;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_PHAMCIST_FK"))
	private Department department;

    /** User role. */
    @OneToOne(targetEntity = Role.class)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", foreignKey = @ForeignKey(name = "role_PHAMCIST_FK"))
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
	 * Get the pharmacistId of Pharmacist.
	 *
	 * @return the pharmacistId
	 */
	public Integer getPharmacistId() {
		return pharmacistId;
	}

	/**
	 * Set the pharmacistId of Pharmacist.
	 *
	 * @param pharmacistId the pharmacistId to set
	 */
	public void setPharmacistId(Integer pharmacistId) {
		this.pharmacistId = pharmacistId;
	}

	/**
	 * Get the pharmacistRegId of Pharmacist.
	 *
	 * @return the pharmacistRegId
	 */
	public Integer getPharmacistRegId() {
		return pharmacistRegId;
	}

	/**
	 * Set the pharmacistRegId of Pharmacist.
	 *
	 * @param pharmacistRegId the pharmacistRegId to set
	 */
	public void setPharmacistRegId(Integer pharmacistRegId) {
		this.pharmacistRegId = pharmacistRegId;
	}

	/**
	 * Get the staffId of Pharmacist.
	 *
	 * @return the staffId
	 */
	public Integer getStaffId() {
		return staffId;
	}

	/**
	 * Set the staffId of Pharmacist.
	 *
	 * @param staffId the staffId to set
	 */
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	/**
	 * Get the pharmacistDob of Pharmacist.
	 *
	 * @return the pharmacistDob
	 */
	public Date getPharmacistDob() {
		return pharmacistDob;
	}

	/**
	 * Set the pharmacistDob of Pharmacist.
	 *
	 * @param pharmacistDob the pharmacistDob to set
	 */
	public void setPharmacistDob(Date pharmacistDob) {
		this.pharmacistDob = pharmacistDob;
	}

	/**
	 * Get the pharmacistEmail of Pharmacist.
	 *
	 * @return the pharmacistEmail
	 */
	public String getPharmacistEmail() {
		return pharmacistEmail;
	}

	/**
	 * Set the pharmacistEmail of Pharmacist.
	 *
	 * @param pharmacistEmail the pharmacistEmail to set
	 */
	public void setPharmacistEmail(String pharmacistEmail) {
		this.pharmacistEmail = pharmacistEmail;
	}

	/**
	 * Get the pharmacistPhoneNumber of Pharmacist.
	 *
	 * @return the pharmacistPhoneNumber
	 */
	public Integer getPharmacistPhoneNumber() {
		return pharmacistPhoneNumber;
	}

	/**
	 * Set the pharmacistPhoneNumber of Pharmacist.
	 *
	 * @param pharmacistPhoneNumber the pharmacistPhoneNumber to set
	 */
	public void setPharmacistPhoneNumber(Integer pharmacistPhoneNumber) {
		this.pharmacistPhoneNumber = pharmacistPhoneNumber;
	}

	/**
	 * Get the department of Pharmacist.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of Pharmacist.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the role of Pharmacist.
	 *
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Set the role of Pharmacist.
	 *
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Get the userType of Pharmacist.
	 *
	 * @return the userType
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * Set the userType of Pharmacist.
	 *
	 * @param userType the userType to set
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
}
