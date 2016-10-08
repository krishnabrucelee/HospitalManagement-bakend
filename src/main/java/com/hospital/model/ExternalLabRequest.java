package com.hospital.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class ExternalLabRequest implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer externalLabrequestId;
	@Column
	private Integer testId;
	@Column
	private String testName;
	@Column
	private String Description;
	
	@Column
	private Integer departmentId;
	@Column
	private String referenceDoctor;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy hh:mm:ss a")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestTime;
	
	public ExternalLabRequest() {
		super();
	}
	public Integer getExternalLabrequestId() {
		return externalLabrequestId;
	}
	public void setExternalLabrequestId(Integer externalLabrequestId) {
		this.externalLabrequestId = externalLabrequestId;
	}
	public Integer getTestId() {
		return testId;
	}
	public void setTestId(Integer testId) {
		this.testId = testId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public String getReferenceDoctor() {
		return referenceDoctor;
	}
	public void setReferenceDoctor(String referenceDoctor) {
		this.referenceDoctor = referenceDoctor;
	}
	public Date getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
		
}
