package com.hospital.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class PatientLabTestStatus implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer patientLabTestStatus;
	
	@Column
	private boolean isTestCompleted;
	
	@OneToOne(targetEntity=LabMasterSubCategories.class)
	@JoinColumn(name="materLabSubcategoryId",foreignKey=@ForeignKey(name="PatientLabTestStatus_LabMasterSubCategories_FK"))
	private LabMasterSubCategories testDetails = new LabMasterSubCategories();	
	
	private String testResult;
	
	private String notes;
	
	private Date completedDate;
	
	private Integer testPreparedBy;

	public Integer getPatientLabTestStatus() {
		return patientLabTestStatus;
	}

	public void setPatientLabTestStatus(Integer patientLabTestStatus) {
		this.patientLabTestStatus = patientLabTestStatus;
	}
	
	public LabMasterSubCategories getTestDetails() {
		return testDetails;
	}

	public void setTestDetails(LabMasterSubCategories testDetails) {
		this.testDetails = testDetails;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public Integer getTestPreparedBy() {
		return testPreparedBy;
	}

	public void setTestPreparedBy(Integer testPreparedBy) {
		this.testPreparedBy = testPreparedBy;
	}

	public boolean isTestCompleted() {
		return isTestCompleted;
	}

	public void setTestCompleted(boolean isTestCompleted) {
		this.isTestCompleted = isTestCompleted;
	}

	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
}
