/**
 * 
 */
package com.hospital.model;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author admin
 *
 */
@Entity
@Table(name="patientRequestRadiologyTest")
public class PatientRequestRadiologyTest implements Serializable {
	
	@Column(name="radiology_requestid")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer radiologyRequestId;
	
	@Column(name="department_id")
	private Integer departmentId;
	
	@Column(name="patient_name")
	private String patientName;
	
	@Column(name="doctor_name")
	private String doctorName;
	
	@Column(name="request_status")
	private String requestStatus;
	
	@Column(name="request_time")
	@JsonFormat(pattern="dd-MM-yyyy hh:mm:ss a")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestTime;
	
	@Column(name="radiology_reporttime")
	@JsonFormat(pattern="dd-MM-yyyy hh:mm:ss a")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date radiologyReportTime;
	
	@OneToMany(cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="radiology_requestid",foreignKey=@ForeignKey(name="Labrequest_Radiologyrequest_FK"))
	private Set<PatientRadiologyTestNames> radiologyTestNames; 
	
	@ManyToOne(targetEntity=Patient.class,fetch=FetchType.LAZY)
	@JoinColumn(name="patient_id")
	private Patient patient; 
	
	@ManyToOne(targetEntity=Doctor.class,fetch=FetchType.LAZY)
	@JoinColumn(name="doctor_id")
	private Doctor doctor;
	
	public PatientRequestRadiologyTest() {
		
	}

	public Integer getRadiologyRequestId() {
		return radiologyRequestId;
	}

	public void setRadiologyRequestId(Integer radiologyRequestId) {
		this.radiologyRequestId = radiologyRequestId;
	}


	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	
	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public Date getRadiologyReportTime() {
		return radiologyReportTime;
	}

	public void setRadiologyReportTime(Date radiologyReportTime) {
		this.radiologyReportTime = radiologyReportTime;
	}

	public Set<PatientRadiologyTestNames> getRadiologyTestNames() {
		return radiologyTestNames;
	}

	public void setRadiologyTestNames(Set<PatientRadiologyTestNames> radiologyTestNames) {
		this.radiologyTestNames = radiologyTestNames;
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

	@Override
	public String toString() {
		return "PatientRequestRadiologyTest [radiologyRequestId="
				+ radiologyRequestId + ", departmentId=" + departmentId
				+ ", patientName=" + patientName + ", doctorName=" + doctorName
				+ ", requestStatus=" + requestStatus + ", requestTime="
				+ requestTime + ", radiologyReportTime=" + radiologyReportTime
				+ ", radiologyTestNames=" + radiologyTestNames + "]";
	}
	
}
