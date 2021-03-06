package com.hospital.model;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class PatientLabtestReportNames implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer labtestReportId;	
	@Column 
	private String testName;	
	@Column 
	private Integer patientId;
	@Column 
	private String patientName;	
	@Column 
	private String age_sex;	
	@Column 
	private Integer doctorId;
	@Column 
	private String referingDoctor;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date testreportTime;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)//labrequestId
	@JoinColumn(name="labtestReportId",referencedColumnName="labtestReportId",foreignKey=@ForeignKey(name="LabreReport_Subcategery_FK"))
	private List<PatientLabReportSubcategeriesNames> subcategories;
	public Integer getLabtestReportId() {
		return labtestReportId;
	}
	public void setLabtestReportId(Integer labtestReportId) {
		this.labtestReportId = labtestReportId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
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
	
	public String getAge_sex() {
		return age_sex;
	}
	public void setAge_sex(String age_sex) {
		this.age_sex = age_sex;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	
	public String getReferingDoctor() {
		return referingDoctor;
	}
	public void setReferingDoctor(String referingDoctor) {
		this.referingDoctor = referingDoctor;
	}
	public Date getTestreportTime() {
		return testreportTime;
	}
	public void setTestreportTime(Date testreportTime) {
		this.testreportTime = testreportTime;
	}
	public List<PatientLabReportSubcategeriesNames> getSubcategories() {
		return subcategories;
	}
	public void setSubcategories(
			List<PatientLabReportSubcategeriesNames> subcategories) {
		this.subcategories = subcategories;
	}
		
	
}
