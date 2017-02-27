package com.hospital.model.radiology;

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

import com.hospital.model.Staff;
import com.hospital.model.radiology.RadiologyTest;
@Entity
public class PatientRadiologyTestStatus implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer radiologyTestStatusId;
	
	@Column
	private boolean isTestCompleted;
	
	@OneToOne(targetEntity=RadiologyTest.class)
	@JoinColumn(name="radiologyTestId",foreignKey=@ForeignKey(name="RadiologyTestStatus_RadiologyTest_FK"))
	private RadiologyTest testDetails = new RadiologyTest();	
	
	private String testResult;
	
	private String notes;
	
	private Date completedDate;
	
	@ManyToOne
	@JoinColumn(name="testPreparedBy",foreignKey=@ForeignKey(name="RadiologyUpdate_Staff_FK"))
	private Staff 	staff;
	
	
	
	public PatientRadiologyTestStatus() {
		super();
	}

	public Integer getRadiologyTestStatusId() {
		return radiologyTestStatusId;
	}

	public void setRadiologyTestStatusId(Integer radiologyTestStatusId) {
		this.radiologyTestStatusId = radiologyTestStatusId;
	}

	public boolean isTestCompleted() {
		return isTestCompleted;
	}

	public void setTestCompleted(boolean isTestCompleted) {
		this.isTestCompleted = isTestCompleted;
	}

	public RadiologyTest getTestDetails() {
		return testDetails;
	}

	public void setTestDetails(RadiologyTest testDetails) {
		this.testDetails = testDetails;
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

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@Override
	public String toString() {
		return "PatientRadiologyTestStatus [radiologyTestStatusId="
				+ radiologyTestStatusId + ", isTestCompleted="
				+ isTestCompleted + ", testDetails=" + testDetails
				+ ", testResult=" + testResult + ", notes=" + notes
				+ ", completedDate=" + completedDate + ", staff=" + staff + "]";
	}

	

	
	
	
}
