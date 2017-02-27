package com.hospital.model.radiology;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.hospital.model.Department;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import com.hospital.model.radiology.PatientRadiologyTestStatus;
@Entity
public class PatientRadiologyTest implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer patientRadiologyRequestId;
	
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
	private Date radiologyRequestDate;
	
	@Column
	private boolean isAllTestCompleted;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="patientRadiologyRequestId")
	private List<PatientRadiologyTestStatus> patientRadiologyTestStatus = new ArrayList<PatientRadiologyTestStatus>();
	
	
	public PatientRadiologyTest() {
		super();
	}

	public Integer getPatientRadiologyRequestId() {
		return patientRadiologyRequestId;
	}

	public void setPatientRadiologyRequestId(Integer patientRadiologyRequestId) {
		this.patientRadiologyRequestId = patientRadiologyRequestId;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	

	public boolean isAllTestCompleted() {
		return isAllTestCompleted;
	}

	public void setAllTestCompleted(boolean isAllTestCompleted) {
		this.isAllTestCompleted = isAllTestCompleted;
	}

	public List<PatientRadiologyTestStatus> getPatientRadiologyTestStatus() {
		return patientRadiologyTestStatus;
	}

	public void setPatientRadiologyTestStatus(
			List<PatientRadiologyTestStatus> patientRadiologyTestStatus) {
		this.patientRadiologyTestStatus = patientRadiologyTestStatus;
	}

	public Date getRadiologyRequestDate() {
		return radiologyRequestDate;
	}

	public void setRadiologyRequestDate(Date radiologyRequestDate) {
		this.radiologyRequestDate = radiologyRequestDate;
	}

	@Override
	public String toString() {
		return "PatientRadiologyTest [patientRadiologyRequestId="
				+ patientRadiologyRequestId + ", patient=" + patient
				+ ", doctor=" + doctor + ", department=" + department
				+ ", radiologyRequestDate=" + radiologyRequestDate
				+ ", isAllTestCompleted=" + isAllTestCompleted
				+ ", patientRadiologyTestStatus=" + patientRadiologyTestStatus
				+ "]";
	}

	
	
	
	
}
