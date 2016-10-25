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
import com.hospital.model.Doctor.UserType;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "labTechnician")
public class LabTechnician {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "labTechnician_id")
	private Integer labTechnicianId;
	
	@Column(name = "labTechnician_reg_id")
	private Integer labTechnicianRegId;
	
	@Column(name = "staff_id")
	private Integer staffId;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "labTechnician_dob")
	private Date labTechnicianDob;
	
	@Column(name = "labTechnician_email")
	private String labTechnicianEmail;
	
	@Column(name = "labTechnician_phone")
	private Integer labTechnicianPhoneNumber;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_LABTECH_FK"))
	private Department department;

    /** User role. */
    @OneToOne(targetEntity = Role.class)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", foreignKey = @ForeignKey(name = "role_LABTECH_FK"))
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
	 * Get the labTechnicianId of LabTechnician.
	 *
	 * @return the labTechnicianId
	 */
	public Integer getLabTechnicianId() {
		return labTechnicianId;
	}

	/**
	 * Set the labTechnicianId of LabTechnician.
	 *
	 * @param labTechnicianId the labTechnicianId to set
	 */
	public void setLabTechnicianId(Integer labTechnicianId) {
		this.labTechnicianId = labTechnicianId;
	}

	/**
	 * Get the labTechnicianRegId of LabTechnician.
	 *
	 * @return the labTechnicianRegId
	 */
	public Integer getLabTechnicianRegId() {
		return labTechnicianRegId;
	}

	/**
	 * Set the labTechnicianRegId of LabTechnician.
	 *
	 * @param labTechnicianRegId the labTechnicianRegId to set
	 */
	public void setLabTechnicianRegId(Integer labTechnicianRegId) {
		this.labTechnicianRegId = labTechnicianRegId;
	}

	/**
	 * Get the staffId of LabTechnician.
	 *
	 * @return the staffId
	 */
	public Integer getStaffId() {
		return staffId;
	}

	/**
	 * Set the staffId of LabTechnician.
	 *
	 * @param staffId the staffId to set
	 */
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	/**
	 * Get the labTechnicianDob of LabTechnician.
	 *
	 * @return the labTechnicianDob
	 */
	public Date getLabTechnicianDob() {
		return labTechnicianDob;
	}

	/**
	 * Set the labTechnicianDob of LabTechnician.
	 *
	 * @param labTechnicianDob the labTechnicianDob to set
	 */
	public void setLabTechnicianDob(Date labTechnicianDob) {
		this.labTechnicianDob = labTechnicianDob;
	}

	/**
	 * Get the labTechnicianEmail of LabTechnician.
	 *
	 * @return the labTechnicianEmail
	 */
	public String getLabTechnicianEmail() {
		return labTechnicianEmail;
	}

	/**
	 * Set the labTechnicianEmail of LabTechnician.
	 *
	 * @param labTechnicianEmail the labTechnicianEmail to set
	 */
	public void setLabTechnicianEmail(String labTechnicianEmail) {
		this.labTechnicianEmail = labTechnicianEmail;
	}

	/**
	 * Get the labTechnicianPhoneNumber of LabTechnician.
	 *
	 * @return the labTechnicianPhoneNumber
	 */
	public Integer getLabTechnicianPhoneNumber() {
		return labTechnicianPhoneNumber;
	}

	/**
	 * Set the labTechnicianPhoneNumber of LabTechnician.
	 *
	 * @param labTechnicianPhoneNumber the labTechnicianPhoneNumber to set
	 */
	public void setLabTechnicianPhoneNumber(Integer labTechnicianPhoneNumber) {
		this.labTechnicianPhoneNumber = labTechnicianPhoneNumber;
	}

	/**
	 * Get the department of LabTechnician.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of LabTechnician.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the role of LabTechnician.
	 *
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Set the role of LabTechnician.
	 *
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Get the userType of LabTechnician.
	 *
	 * @return the userType
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * Set the userType of LabTechnician.
	 *
	 * @param userType the userType to set
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
}
