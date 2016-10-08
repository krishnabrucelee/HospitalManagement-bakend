package com.hospital.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Entity
@Table
public class PatientData implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long patientId;
	
	@Column(name="email",unique=true)
	private String patientEmail;
	@Column
	private String patientName;
	@Column
	private Integer patientAge;
	
	@JsonFormat(pattern="dd-MM-yyyy")	
	@Temporal(TemporalType.DATE)
	@Column
	private Date patientDob;
	@Column
	private  Long phoneNumber;
	@Column
	private String patientAddress;
	@Column
	private String patientGender;
	@Column
	private String patientGuardian;
	@Column
	private String bloodGroup;
	/*@OneToMany(fetch = FetchType.EAGER, mappedBy = "patient")
	private Set<MedicineToPatient> medicineToPatient = new HashSet<MedicineToPatient>();*/
	//
	@OneToOne(targetEntity=LabTestName.class,fetch = FetchType.LAZY)
	private Set<LabTestName> labTestt = new HashSet<LabTestName>();
	//
	/*@OneToOne(targetEntity=LabRequest.class,fetch = FetchType.LAZY, mappedBy = "patientData")
	private Set<LabRequest> labRequest = new HashSet<LabRequest>();*/
	
	@OneToMany(targetEntity=NewLabRequest.class,cascade = {CascadeType.ALL},mappedBy="patientData")
	private List<NewLabRequest> newLabRequest;
	
	public List<NewLabRequest> getNewLabRequest() {
		return newLabRequest;
	}
	public void setNewLabRequest(List<NewLabRequest> newLabRequest) {
		this.newLabRequest = newLabRequest;
	}
	public PatientData() {
		super();
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public String getPatientEmail() {
		return patientEmail;
	}
	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public Integer getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(Integer patientAge) {
		this.patientAge = patientAge;
	}
	public Date getPatientDob() {
		return patientDob;
	}
	public void setPatientDob(Date patientDob) {
		this.patientDob = patientDob;
	}
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPatientAddress() {
		return patientAddress;
	}
	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}
	public String getPatientGender() {
		return patientGender;
	}
	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}
	public String getPatientGuardian() {
		return patientGuardian;
	}
	public void setPatientGuardian(String patientGuardian) {
		this.patientGuardian = patientGuardian;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	
	public Set<LabTestName> getLabTestt() {
		return labTestt;
	}
	public void setLabTestt(Set<LabTestName> labTestt) {
		this.labTestt = labTestt;
	}

	

}
