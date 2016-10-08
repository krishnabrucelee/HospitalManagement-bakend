package com.hospital.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class NewLabRequest implements Serializable {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer labrequestId;
	/*@Column
	private Integer doctorId;
	@Column
	private Integer patientId;*/
	@Column
	private String patientName;
	@ManyToOne(targetEntity=PatientData.class,cascade = {CascadeType.ALL})
	@JoinColumn(name="patientId",referencedColumnName="patientId",foreignKey=@ForeignKey(name="labrequest_Patient_FK"))
	private PatientData patientData;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="doctor_id",referencedColumnName="doctor_id",foreignKey=@ForeignKey(name="labrequest_Doctor_FK"))
	private Doctor doctor;
		
	@Column
	@JsonFormat(pattern="dd-MM-yyyy hh:mm:ss a")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestTime;
	@OneToMany(cascade=CascadeType.ALL)//labrequestId
	@JoinColumn(name="labrequestId",referencedColumnName="labrequestId",foreignKey=@ForeignKey(name="Labrequest_NameCategry_FK"))
	private Set<LabTestName> labTestNames; 
	@Column
	private String testNames;
	@Column
	private String requestStatus;

	public NewLabRequest() {
		super();
	}

	public Integer getLabrequestId() {
		return labrequestId;
	}

	public void setLabrequestId(Integer labrequestId) {
		this.labrequestId = labrequestId;
	}

	public PatientData getPatientData() {
		return patientData;
	}

	public void setPatientData(PatientData patientData) {
		this.patientData = patientData;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public Set<LabTestName> getLabTestNames() {
		return labTestNames;
	}

	public void setLabTestNames(Set<LabTestName> labTestNames) {
		this.labTestNames = labTestNames;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	/*public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}*/

	/*public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}*/

	public String getTestNames() {
		return testNames;
	}

	public void setTestNames(String testNames) {
		this.testNames = testNames;
	}
	
}
