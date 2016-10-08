package com.hospital.model;

import java.io.Serializable;
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
@Entity
public class LabTestName implements Serializable {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer testId;
	
	private String testName;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="testId",referencedColumnName="testId",foreignKey=@ForeignKey(name="Labtest_Categories_FK"))
	private List<TestSubCategeries> subcategories;
	/*@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="testId",referencedColumnName="testId",foreignKey=@ForeignKey(name="Labtest_Categories_FK"))
	private List<TestSubCategeries> subcategories;*/
	public LabTestName() {
		super();
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
	public List<TestSubCategeries> getSubcategories() {
		return subcategories;
	}
	public void setSubcategories(List<TestSubCategeries> subcategories) {
		this.subcategories = subcategories;
	}
	
}
