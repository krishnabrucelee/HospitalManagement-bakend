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
@Table(name = "nurse")
public class Nurse {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "nurse_id")
	private Integer nurseId;
	
	@Column(name = "nurse_reg_id")
	private Integer nurseRegId;
	
	@Column(name = "staff_id")
	private Integer staffId;
	
	@Column(name = "nurse_type")
	private String nurseType;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "nurse_dob")
	private Date nurseDob;
	
	@Column(name = "nurse_email")
	private String nurseEmail;
	
	@Column(name = "nurse_phone")
	private Integer nursePhoneNumber;
	
	@Column(name = "nurse_shift")
	private String nurseShift;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_NURSE_FK"))
	private Department department;
	
	@OneToOne(targetEntity = Ward.class)
	@JoinColumn(name = "ward_id", referencedColumnName = "ward_id", foreignKey = @ForeignKey(name = "ward_NURSE_FK"))
	private Ward ward;

	/**
	 * Get the nurseId of Nurse.
	 *
	 * @return the nurseId
	 */
	public Integer getNurseId() {
		return nurseId;
	}

	/**
	 * Set the nurseId of Nurse.
	 *
	 * @param nurseId the nurseId to set
	 */
	public void setNurseId(Integer nurseId) {
		this.nurseId = nurseId;
	}
	
	/**
	 * Get the nurseRegId of Nurse.
	 *
	 * @return the nurseRegId
	 */
	public Integer getNurseRegId() {
		return nurseRegId;
	}

	/**
	 * Set the nurseRegId of Nurse.
	 *
	 * @param nurseRegId the nurseRegId to set
	 */
	public void setNurseRegId(Integer nurseRegId) {
		this.nurseRegId = nurseRegId;
	}

	/**
	 * Get the staffId of Nurse.
	 *
	 * @return the staffId
	 */
	public Integer getStaffId() {
		return staffId;
	}

	/**
	 * Set the staffId of Nurse.
	 *
	 * @param staffId the staffId to set
	 */
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}


	/**
	 * Get the nurseType of Nurse.
	 *
	 * @return the nurseType
	 */
	public String getNurseType() {
		return nurseType;
	}

	/**
	 * Set the nurseType of Nurse.
	 *
	 * @param nurseType the nurseType to set
	 */
	public void setNurseType(String nurseType) {
		this.nurseType = nurseType;
	}

	/**
	 * Get the nurseDob of Nurse.
	 *
	 * @return the nurseDob
	 */
	public Date getNurseDob() {
		return nurseDob;
	}

	/**
	 * Set the nurseDob of Nurse.
	 *
	 * @param nurseDob the nurseDob to set
	 */
	public void setNurseDob(Date nurseDob) {
		this.nurseDob = nurseDob;
	}

	/**
	 * Get the nurseEmail of Nurse.
	 *
	 * @return the nurseEmail
	 */
	public String getNurseEmail() {
		return nurseEmail;
	}

	/**
	 * Set the nurseEmail of Nurse.
	 *
	 * @param nurseEmail the nurseEmail to set
	 */
	public void setNurseEmail(String nurseEmail) {
		this.nurseEmail = nurseEmail;
	}

	/**
	 * Get the nursePhoneNumber of Nurse.
	 *
	 * @return the nursePhoneNumber
	 */
	public Integer getNursePhoneNumber() {
		return nursePhoneNumber;
	}

	/**
	 * Set the nursePhoneNumber of Nurse.
	 *
	 * @param nursePhoneNumber the nursePhoneNumber to set
	 */
	public void setNursePhoneNumber(Integer nursePhoneNumber) {
		this.nursePhoneNumber = nursePhoneNumber;
	}


	/**
	 * Get the nurseShift of Nurse.
	 *
	 * @return the nurseShift
	 */
	public String getNurseShift() {
		return nurseShift;
	}

	/**
	 * Set the nurseShift of Nurse.
	 *
	 * @param nurseShift the nurseShift to set
	 */
	public void setNurseShift(String nurseShift) {
		this.nurseShift = nurseShift;
	}

	/**
	 * Get the department of Nurse.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of Nurse.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	/**
	 * Get the ward of HouseKeeping.
	 *
	 * @return the ward
	 */
	public Ward getWard() {
		return ward;
	}

	/**
	 * Set the ward of HouseKeeping.
	 *
	 * @param ward the ward to set
	 */
	public void setWard(Ward ward) {
		this.ward = ward;
	}
}
