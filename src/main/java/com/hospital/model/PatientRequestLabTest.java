package com.hospital.model;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
@Entity
public class PatientRequestLabTest implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer testId;
	@Column
	private Integer testIdd;
	@Column
	private Integer  patientId;
	@Column
	private String testName;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)//labrequestId
	@JoinColumn(name="testId",referencedColumnName="testId",foreignKey=@ForeignKey(name="Labrequest_PatientRequest_FK"))
	private List<PatientRequestSubCatTest> subcategories;
	
	public Integer getTestIdd() {
		return testIdd;
	}
	public void setTestIdd(Integer testIdd) {
		this.testIdd = testIdd;
	}
	public List<PatientRequestSubCatTest> getSubcategories() {
		return subcategories;
	}
	public void setSubcategories(List<PatientRequestSubCatTest> subcategories) {
		this.subcategories = subcategories;
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
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
		
}
