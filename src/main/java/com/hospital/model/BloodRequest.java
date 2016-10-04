/**
 * 
 */
package com.hospital.model;

import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name = "bloodRequest")
public class BloodRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "blood_request_id")
	private Integer bloodRequestId;
	
	@OneToOne(targetEntity = Patient.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", referencedColumnName = "patient_id", foreignKey = @ForeignKey(name = "patient_BREQ_FK"))
	private Patient patient;
	
	@Column(name = "blood_requested_by")
	private String bloodRequestedBy;
	
	@Column(name="blood_requested_date")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date bloodRequestedDate;
	
	@Column(name = "blood_group")
	private String bloodGroup;
	
	@Column(name = "blood_unit")
	private Integer bloodUnit;
	
	@Column(name = "blood_ward")
	private Integer bloodWard;
	
	@Column(name = "blood_request_description")
	private String bloodRequestDescription;

	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_BREQ_FK"))
	private Department department;
	
	/**
	 * Get the bloodRequestId of BloodRequest.
	 *
	 * @return the bloodRequestId
	 */
	public Integer getBloodRequestId() {
		return bloodRequestId;
	}

	/**
	 * Set the bloodRequestId of BloodRequest.
	 *
	 * @param bloodRequestId the bloodRequestId to set
	 */
	public void setBloodRequestId(Integer bloodRequestId) {
		this.bloodRequestId = bloodRequestId;
	}


	/**
	 * Get the patient of BloodRequest.
	 *
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Set the patient of BloodRequest.
	 *
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Get the bloodRequestedBy of BloodRequest.
	 *
	 * @return the bloodRequestedBy
	 */
	public String getBloodRequestedBy() {
		return bloodRequestedBy;
	}

	/**
	 * Set the bloodRequestedBy of BloodRequest.
	 *
	 * @param bloodRequestedBy the bloodRequestedBy to set
	 */
	public void setBloodRequestedBy(String bloodRequestedBy) {
		this.bloodRequestedBy = bloodRequestedBy;
	}

	/**
	 * Get the bloodRequestedDate of BloodRequest.
	 *
	 * @return the bloodRequestedDate
	 */
	public Date getBloodRequestedDate() {
		return bloodRequestedDate;
	}

	/**
	 * Set the bloodRequestedDate of BloodRequest.
	 *
	 * @param bloodRequestedDate the bloodRequestedDate to set
	 */
	public void setBloodRequestedDate(Date bloodRequestedDate) {
		this.bloodRequestedDate = bloodRequestedDate;
	}

	/**
	 * Get the bloodGroup of BloodRequest.
	 *
	 * @return the bloodGroup
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}

	/**
	 * Set the bloodGroup of BloodRequest.
	 *
	 * @param bloodGroup the bloodGroup to set
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	/**
	 * Get the bloodUnit of BloodRequest.
	 *
	 * @return the bloodUnit
	 */
	public Integer getBloodUnit() {
		return bloodUnit;
	}

	/**
	 * Set the bloodUnit of BloodRequest.
	 *
	 * @param bloodUnit the bloodUnit to set
	 */
	public void setBloodUnit(Integer bloodUnit) {
		this.bloodUnit = bloodUnit;
	}

	/**
	 * Get the bloodWard of BloodRequest.
	 *
	 * @return the bloodWard
	 */
	public Integer getBloodWard() {
		return bloodWard;
	}

	/**
	 * Set the bloodWard of BloodRequest.
	 *
	 * @param bloodWard the bloodWard to set
	 */
	public void setBloodWard(Integer bloodWard) {
		this.bloodWard = bloodWard;
	}

	/**
	 * Get the bloodRequestDescription of BloodRequest.
	 *
	 * @return the bloodRequestDescription
	 */
	public String getBloodRequestDescription() {
		return bloodRequestDescription;
	}

	/**
	 * Set the bloodRequestDescription of BloodRequest.
	 *
	 * @param bloodRequestDescription the bloodRequestDescription to set
	 */
	public void setBloodRequestDescription(String bloodRequestDescription) {
		this.bloodRequestDescription = bloodRequestDescription;
	}

	/**
	 * Get the department of BloodRequest.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of BloodRequest.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
