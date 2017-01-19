/**
 * 
 */
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author admin
 *
 */
@Entity
@Table(name="patientRadiologyReport")
public class PatientRadiologyReport implements Serializable {
	
	@Column(name="patient_rreportId")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer patientRReportId;
	
	@Column(name="radiology_requestid")
	
	private Integer radiologyRequestId;
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
	private String technology;
	
	@Column 
	private Integer staffId;
	
	@Column 
	private String preparedBy;
	
	@Column
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date testreportTime;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="patient_rreportId",referencedColumnName="patient_rreportId",foreignKey=@ForeignKey(name="Radiology_Subcategry_FK"))
	private List<RadiologyReportSubCategeries> subcategories;
	
	
	public PatientRadiologyReport() {
		super();
	}
	public Integer getPatientRReportId() {
		return patientRReportId;
	}
	public void setPatientRReportId(Integer patientRReportId) {
		this.patientRReportId = patientRReportId;
	}
	public Integer getRadiologyRequestId() {
		return radiologyRequestId;
	}
	public void setRadiologyRequestId(Integer radiologyRequestId) {
		this.radiologyRequestId = radiologyRequestId;
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
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	
	
	public Date getTestreportTime() {
		return testreportTime;
	}
	public void setTestreportTime(Date testreportTime) {
		this.testreportTime = testreportTime;
	}
	public List<RadiologyReportSubCategeries> getSubcategories() {
		return subcategories;
	}
	public void setSubcategories(List<RadiologyReportSubCategeries> subcategories) {
		this.subcategories = subcategories;
	}
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	public String getPreparedBy() {
		return preparedBy;
	}
	public void setPreparedBy(String preparedBy) {
		this.preparedBy = preparedBy;
	}
	@Override
	public String toString() {
		return "PatientRadiologyReport [patientRReportId=" + patientRReportId
				+ ", radiologyRequestId=" + radiologyRequestId + ", testName="
				+ testName + ", patientId=" + patientId + ", patientName="
				+ patientName + ", age_sex=" + age_sex + ", doctorId="
				+ doctorId + ", referingDoctor=" + referingDoctor
				+ ", technology=" + technology + ", staffId=" + staffId
				+ ", preparedBy=" + preparedBy + ", testreportTime="
				+ testreportTime + ", subcategories=" + subcategories + "]";
	}
	
}
