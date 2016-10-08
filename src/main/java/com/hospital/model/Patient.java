package com.hospital.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Patient entity
 * 
 * @author Krishna
 *
 */
@Entity
@Table(name = "patient")
public class Patient implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "patient_id")
	private Integer patientId;

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

	@Column(name = "blood_group")
	private String bloodGroup;

	@Column(name = "patient_type")
	private String patientType;

	@OneToMany(mappedBy = "patient")
	private List<MedicineToPatient> drugtopatient = new ArrayList<MedicineToPatient>();

	/**
	 * Get the patientId of Patient.
	 *
	 * @return the patientId
	 */
	public Integer getPatientId() {
		return patientId;
	}

	/**
	 * Set the patientId of Patient.
	 *
	 * @param patientId
	 *            the patientId to set
	 */
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	/**
	 * Get the email of Patient.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the email of Patient.
	 *
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the patientName of Patient.
	 *
	 * @return the patientName
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * Set the patientName of Patient.
	 *
	 * @param patientName
	 *            the patientName to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * Get the patientAge of Patient.
	 *
	 * @return the patientAge
	 */
	public Integer getPatientAge() {
		return patientAge;
	}

	/**
	 * Set the patientAge of Patient.
	 *
	 * @param patientAge
	 *            the patientAge to set
	 */
	public void setPatientAge(Integer patientAge) {
		this.patientAge = patientAge;
	}

	/**
	 * Get the patientDob of Patient.
	 *
	 * @return the patientDob
	 */
	public Date getPatientDob() {
		return patientDob;
	}

	/**
	 * Set the patientDob of Patient.
	 *
	 * @param patientDob
	 *            the patientDob to set
	 */
	public void setPatientDob(Date patientDob) {
		this.patientDob = patientDob;
	}

	/**
	 * Get the phoneNumber of Patient.
	 *
	 * @return the phoneNumber
	 */
	public Long getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Set the phoneNumber of Patient.
	 *
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Get the patientAddress of Patient.
	 *
	 * @return the patientAddress
	 */
	public String getPatientAddress() {
		return patientAddress;
	}

	/**
	 * Set the patientAddress of Patient.
	 *
	 * @param patientAddress
	 *            the patientAddress to set
	 */
	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	/**
	 * Get the patientGender of Patient.
	 *
	 * @return the patientGender
	 */
	public String getPatientGender() {
		return patientGender;
	}

	/**
	 * Set the patientGender of Patient.
	 *
	 * @param patientGender
	 *            the patientGender to set
	 */
	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	/**
	 * Get the patientGuardian of Patient.
	 *
	 * @return the patientGuardian
	 */
	public String getPatientGuardian() {
		return patientGuardian;
	}

	/**
	 * Set the patientGuardian of Patient.
	 *
	 * @param patientGuardian
	 *            the patientGuardian to set
	 */
	public void setPatientGuardian(String patientGuardian) {
		this.patientGuardian = patientGuardian;
	}

	/**
	 * Get the bloodGroup of Patient.
	 *
	 * @return the bloodGroup
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}

	/**
	 * Set the bloodGroup of Patient.
	 *
	 * @param bloodGroup
	 *            the bloodGroup to set
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	/**
	 * Get the patientType of Patient.
	 *
	 * @return the patientType
	 */
	public String getPatientType() {
		return patientType;
	}

	/**
	 * Set the patientType of Patient.
	 *
	 * @param patientType
	 *            the patientType to set
	 */
	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

}
