package com.hospital.model;

import java.io.Serializable;
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

	@Column(name = "patient_ref_number")
	private String patientRefNumber;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_patient_FK"))
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

	@OneToMany(mappedBy = "patient")
	private List<MedicineToPatient> drugtopatient = new ArrayList<MedicineToPatient>();

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<DischargeSummary> dischargeSummary;
	
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
	 * Get the patientRefNumber of Patient. 
	 *
	 * @return the patientRefNumber
	 */
	public String getPatientRefNumber() {
		return patientRefNumber;
	}

	/**
	 * Set the patientRefNumber of Patient. 
	 *
	 * @param patientRefNumber the patientRefNumber to set
	 */
	public void setPatientRefNumber(String patientRefNumber) {
		this.patientRefNumber = patientRefNumber;
	}

	/**
	 * Get the drugtopatient of Patient. 
	 *
	 * @return the drugtopatient
	 */
	public List<MedicineToPatient> getDrugtopatient() {
		return drugtopatient;
	}

	/**
	 * Set the drugtopatient of Patient. 
	 *
	 * @param drugtopatient the drugtopatient to set
	 */
	public void setDrugtopatient(List<MedicineToPatient> drugtopatient) {
		this.drugtopatient = drugtopatient;
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

	/**
	 * Get the createdDate of Patient. 
	 *
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Set the createdDate of Patient. 
	 *
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Get the wardNumber of Patient.
	 *
	 * @return the wardNumber
	 */
	public String getWardNumber() {
		return wardNumber;
	}

	/**
	 * Set the wardNumber of Patient.
	 *
	 * @param wardNumber the wardNumber to set
	 */
	public void setWardNumber(String wardNumber) {
		this.wardNumber = wardNumber;
	}

	/**
	 * Get the schemeType of Patient.
	 *
	 * @return the schemeType
	 */
	public String getSchemeType() {
		return schemeType;
	}

	/**
	 * Set the schemeType of Patient.
	 *
	 * @param schemeType the schemeType to set
	 */
	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}

	/**
	 * Get the dischargeSumary of Patient.
	 *
	 * @return the dischargeSumary
	 */
	public List<DischargeSummary> getDischargeSummary() {
		return dischargeSummary;
	}

	/**
	 * Set the dischargeSumary of Patient.
	 *
	 * @param dischargeSumary the dischargeSumary to set
	 */
	public void setDischargeSummary(List<DischargeSummary> dischargeSummary) {
		this.dischargeSummary = dischargeSummary;
	}

	/**
	 * Get the schemeDescription of Patient.
	 *
	 * @return the schemeDescription
	 */
	public String getSchemeDescription() {
		return schemeDescription;
	}

	/**
	 * Set the schemeDescription of Patient.
	 *
	 * @param schemeDescription the schemeDescription to set
	 */
	public void setSchemeDescription(String schemeDescription) {
		this.schemeDescription = schemeDescription;
	}

	/**
	 * Get the department of Patient.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of Patient.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

}
