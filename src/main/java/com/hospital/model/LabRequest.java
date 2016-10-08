package com.hospital.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class LabRequest implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer labrequestId;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy hh:mm:ss a")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestTime;
	
	@ManyToOne(targetEntity=PatientData.class)
	@JoinColumn(name="patientId",referencedColumnName="patientId",foreignKey=@ForeignKey(name="labrequest_Patient_FK"))
	private PatientData patientData;
	@OneToOne(targetEntity=Doctor.class)
	@JoinColumn(name="doctorId",referencedColumnName="doctorId",foreignKey=@ForeignKey(name="labrequest_Doctor_FK"))
	private Doctor doctorr;
	@ManyToOne(targetEntity=LabTest.class)
	@JoinColumn(name="labtestId",referencedColumnName="labtestId",foreignKey=@ForeignKey(name="labrequest_Labtest_FK"))
	private LabTest labTestt;
	@Column
	private String status;
	public LabRequest() {
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

	public Date getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	public Doctor getDoctorr() {
		return doctorr;
	}
	public void setDoctorr(Doctor doctorr) {
		this.doctorr = doctorr;
	}
	public LabTest getLabTestt() {
		return labTestt;
	}
	public void setLabTestt(LabTest labTestt) {
		this.labTestt = labTestt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	
}
