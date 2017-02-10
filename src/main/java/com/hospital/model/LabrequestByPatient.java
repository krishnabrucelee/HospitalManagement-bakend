package com.hospital.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class LabrequestByPatient implements Serializable {
	
	@Column()
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer labrequestId;
	
	@Column
	private String requestStatus;
	
	@Column
	private Integer doctorId;
	@Column
	private Integer departmentId;
	@Column
	private Integer patientId;
	@Column
	private String patientName;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy hh:mm:ss a")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestTime;
	@OneToMany(cascade=CascadeType.ALL)//labrequestId
	@JoinColumn(name="labTestNames", foreignKey=@ForeignKey(name="Labrequest_Labrequest_FK"))
	private Set<PatientRequestLabTest> labTestNames; 
	public Integer getLabrequestId() {
		return labrequestId;
	}
	public void setLabrequestId(Integer labrequestId) {
		this.labrequestId = labrequestId;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
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
	public Set<PatientRequestLabTest> getLabTestNames() {
		return labTestNames;
	}
	public void setLabTestNames(Set<PatientRequestLabTest> labTestNames) {
		this.labTestNames = labTestNames;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	@Override
	public String toString() {
		return "LabrequestByPatient [labrequestId=" + labrequestId
				+ ", requestStatus=" + requestStatus + ", doctorId=" + doctorId
				+ ", departmentId=" + departmentId + ", patientId=" + patientId
				+ ", patientName=" + patientName + ", requestTime="
				+ requestTime + ", labTestNames=" + labTestNames + "]";
	}
	

}
