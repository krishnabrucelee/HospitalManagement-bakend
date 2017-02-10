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
import javax.persistence.ManyToOne;
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
	private Integer labTestId;
	
	@ManyToOne()
	@JoinColumn(name="patientId")
	private Patient patient;
	
	@ManyToOne()
	@JoinColumn(name="doctorId")
	private Doctor doctor;
	
	@ManyToOne()
	@JoinColumn(name="departmentId")
	private Department department;
	
	@Column
	private String testName;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)//labrequestId
	@JoinColumn(name="subcategories",foreignKey=@ForeignKey(name="Labrequest_PatientRequestLabTest_FK"))
	private List<PatientRequestSubCatTest> subcategories;
	
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
	
		
}
