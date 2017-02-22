/**
 * 
 */
package com.hospital.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "waitingList")
public class WaitingList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "waiting_list_id")
	private Integer waitingListId;

	@Column(name = "patient_ref_number")
	private String patientRefNumber;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_waitingList_FK"))
	private Department department;
	
	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "patient_name")
	private String patientName;

	@Column(name = "patient_age")
	private Integer patientAge;

	@JsonFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)	
	@Column(name = "patient_dob")
	private Date patientDob;

	@Column(name = "phone_number")
	private Long phoneNumber;

	@Column(name = "patient_address")
	private String patientAddress;

	@Column(name = "patient_gender")
	private String patientGender;

	@Column(name = "patient_guardian")
	private String patientGuardian;

	@Column(name = "ward_number")
	private String wardNumber;
	
	@Column(name = "blood_group")
	private String bloodGroup;

	@Column(name = "patient_type")
	private String patientType;
	
	@Column(name = "scheme_type")
	private String schemeType;
	
	@Column(name = "scheme_description")
	private String schemeDescription;

	@Column(name = "class_type")
	private String classType;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(name = "room_book_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date roomBookDate;

	/**
	 * Get the waitingListId of WaitingList.java.
	 *
	 * @return the waitingListId
	 */
	public Integer getWaitingListId() {
		return waitingListId;
	}

	/**
	 * Set the waitingListId of WaitingList.java.
	 *
	 * @param waitingListId the waitingListId to set
	 */
	public void setWaitingListId(Integer waitingListId) {
		this.waitingListId = waitingListId;
	}

	/**
	 * Get the patientRefNumber of WaitingList.
	 *
	 * @return the patientRefNumber
	 */
	public String getPatientRefNumber() {
		return patientRefNumber;
	}

	/**
	 * Set the patientRefNumber of WaitingList.
	 *
	 * @param patientRefNumber the patientRefNumber to set
	 */
	public void setPatientRefNumber(String patientRefNumber) {
		this.patientRefNumber = patientRefNumber;
	}

	/**
	 * Get the department of WaitingList.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of WaitingList.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the email of WaitingList.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the email of WaitingList.
	 *
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the patientName of WaitingList.
	 *
	 * @return the patientName
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * Set the patientName of WaitingList.
	 *
	 * @param patientName the patientName to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * Get the patientAge of WaitingList.
	 *
	 * @return the patientAge
	 */
	public Integer getPatientAge() {
		return patientAge;
	}

	/**
	 * Set the patientAge of WaitingList.
	 *
	 * @param patientAge the patientAge to set
	 */
	public void setPatientAge(Integer patientAge) {
		this.patientAge = patientAge;
	}

	/**
	 * Get the patientDob of WaitingList.
	 *
	 * @return the patientDob
	 */
	public Date getPatientDob() {
		return patientDob;
	}

	/**
	 * Set the patientDob of WaitingList.
	 *
	 * @param patientDob the patientDob to set
	 */
	public void setPatientDob(Date patientDob) {
		this.patientDob = patientDob;
	}

	/**
	 * Get the phoneNumber of WaitingList.
	 *
	 * @return the phoneNumber
	 */
	public Long getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Set the phoneNumber of WaitingList.
	 *
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Get the patientAddress of WaitingList.
	 *
	 * @return the patientAddress
	 */
	public String getPatientAddress() {
		return patientAddress;
	}

	/**
	 * Set the patientAddress of WaitingList.
	 *
	 * @param patientAddress the patientAddress to set
	 */
	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	/**
	 * Get the patientGender of WaitingList.
	 *
	 * @return the patientGender
	 */
	public String getPatientGender() {
		return patientGender;
	}

	/**
	 * Set the patientGender of WaitingList.
	 *
	 * @param patientGender the patientGender to set
	 */
	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	/**
	 * Get the patientGuardian of WaitingList.
	 *
	 * @return the patientGuardian
	 */
	public String getPatientGuardian() {
		return patientGuardian;
	}

	/**
	 * Set the patientGuardian of WaitingList.
	 *
	 * @param patientGuardian the patientGuardian to set
	 */
	public void setPatientGuardian(String patientGuardian) {
		this.patientGuardian = patientGuardian;
	}

	/**
	 * Get the wardNumber of WaitingList.
	 *
	 * @return the wardNumber
	 */
	public String getWardNumber() {
		return wardNumber;
	}

	/**
	 * Set the wardNumber of WaitingList.
	 *
	 * @param wardNumber the wardNumber to set
	 */
	public void setWardNumber(String wardNumber) {
		this.wardNumber = wardNumber;
	}

	/**
	 * Get the bloodGroup of WaitingList.
	 *
	 * @return the bloodGroup
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}

	/**
	 * Set the bloodGroup of WaitingList.
	 *
	 * @param bloodGroup the bloodGroup to set
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	/**
	 * Get the patientType of WaitingList.
	 *
	 * @return the patientType
	 */
	public String getPatientType() {
		return patientType;
	}

	/**
	 * Set the patientType of WaitingList.
	 *
	 * @param patientType the patientType to set
	 */
	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	/**
	 * Get the schemeType of WaitingList.
	 *
	 * @return the schemeType
	 */
	public String getSchemeType() {
		return schemeType;
	}

	/**
	 * Set the schemeType of WaitingList.
	 *
	 * @param schemeType the schemeType to set
	 */
	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}

	/**
	 * Get the schemeDescription of WaitingList.
	 *
	 * @return the schemeDescription
	 */
	public String getSchemeDescription() {
		return schemeDescription;
	}

	/**
	 * Set the schemeDescription of WaitingList.
	 *
	 * @param schemeDescription the schemeDescription to set
	 */
	public void setSchemeDescription(String schemeDescription) {
		this.schemeDescription = schemeDescription;
	}

	/**
	 * Get the createdDate of WaitingList.
	 *
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Set the createdDate of WaitingList.
	 *
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Get the roomBookDate of WaitingList.
	 *
	 * @return the roomBookDate
	 */
	public Date getRoomBookDate() {
		return roomBookDate;
	}

	/**
	 * Set the roomBookDate of WaitingList.
	 *
	 * @param roomBookDate the roomBookDate to set
	 */
	public void setRoomBookDate(Date roomBookDate) {
		this.roomBookDate = roomBookDate;
	}

	/**
	 * Get the classType of WaitingList.
	 *
	 * @return the classType
	 */
	public String getClassType() {
		return classType;
	}

	/**
	 * Set the classType of WaitingList.
	 *
	 * @param classType the classType to set
	 */
	public void setClassType(String classType) {
		this.classType = classType;
	}

	/**
	 * Get the status of WaitingList.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Set the status of WaitingList.
	 *
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
}
