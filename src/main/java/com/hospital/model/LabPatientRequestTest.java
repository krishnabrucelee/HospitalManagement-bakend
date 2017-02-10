package com.hospital.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table
public class LabPatientRequestTest implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer patientLabRequestId;
		
	@ManyToOne()
	@JoinColumn(name="patientId")
	private Patient patient;
	
	@ManyToOne()
	@JoinColumn(name="doctorId")
	private Doctor doctor;
	
	@ManyToOne()
	@JoinColumn(name="departmentId")
	private Department department;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date labRequestDate;
	
	@Column
	private boolean isAllTestCompleted;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="patientLabRequestId")
	private List<PatientLabTestStatus> patientLabTestStatus = new ArrayList<PatientLabTestStatus>();
	
	
	
	public LabPatientRequestTest() {
		super();
	}

	public Integer getPatientLabRequestId() {
		return patientLabRequestId;
	}

	public void setPatientLabRequestId(Integer patientLabRequestId) {
		this.patientLabRequestId = patientLabRequestId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	

	public Date getLabRequestDate() {
		return labRequestDate;
	}

	public void setLabRequestDate(Date labRequestDate) {
		this.labRequestDate = labRequestDate;
	}

	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<PatientLabTestStatus> getPatientlabtestsubcategories() {
		return patientLabTestStatus;
	}

	public void setPatientlabtestsubcategories(
			List<PatientLabTestStatus> patientLabTestStatus) {
		this.patientLabTestStatus = patientLabTestStatus;
	}

	public boolean isAllTestCompleted() {
		return isAllTestCompleted;
	}

	public void setAllTestCompleted(boolean isAllTestCompleted) {
		this.isAllTestCompleted = isAllTestCompleted;
	}

	public List<PatientLabTestStatus> getPatientLabTestStatus() {
		return patientLabTestStatus;
	}

	public void setPatientLabTestStatus(
			List<PatientLabTestStatus> patientLabTestStatus) {
		this.patientLabTestStatus = patientLabTestStatus;
	}


}
