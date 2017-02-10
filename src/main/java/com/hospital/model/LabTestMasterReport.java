package com.hospital.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Entity
@Table
@Component
public class LabTestMasterReport implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer masterTestReportId;
	
	@Column
	private String testName;
	
	@Column
	private String ageRange;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="masterTestReportId")
	private List<LabTestMasterSubcategories> mastertestReportSubcategories;
	
	
	public LabTestMasterReport() {
		super();
	}

	public Integer getMasterTestReportId() {
		return masterTestReportId;
	}

	public void setMasterTestReportId(Integer masterTestReportId) {
		this.masterTestReportId = masterTestReportId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}

	public List<LabTestMasterSubcategories> getMastertestReportSubcategories() {
		return mastertestReportSubcategories;
	}

	public void setMastertestReportSubcategories(
			List<LabTestMasterSubcategories> mastertestReportSubcategories) {
		this.mastertestReportSubcategories = mastertestReportSubcategories;
	}

	@Override
	public String toString() {
		return "LabTestMasterReport [masterTestReportId=" + masterTestReportId
				+ ", testName=" + testName + ", ageRange=" + ageRange
				+ ", mastertestReportSubcategories="
				+ mastertestReportSubcategories + "]";
	}
	
	
	
	
	
	
}
