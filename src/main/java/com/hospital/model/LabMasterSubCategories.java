package com.hospital.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Entity
@Table
@Component
public class LabMasterSubCategories implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer materLabSubcategoryId;
		
	@Column
	private String testName;
		
	@Column
	private String testMethodology;
	
	@Column
	private String testSample;
	
	@Column
	private String testVolume;
	
	@Column
	private String testStorage;
	
	@Column
	private String testReport;
	
	@Column
	private String testInstruction;
	
	@Column
	private Double price;
	
	@Column
	private String  testHigh;
	
	@Column
	private String testLow;
	
	public LabMasterSubCategories() {
		super();
	}

	public Integer getMaterLabSubcategoryId() {
		return materLabSubcategoryId;
	}

	public void setMaterLabSubcategoryId(Integer materLabSubcategoryId) {
		this.materLabSubcategoryId = materLabSubcategoryId;
	}
	

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestMethodology() {
		return testMethodology;
	}

	public void setTestMethodology(String testMethodology) {
		this.testMethodology = testMethodology;
	}

	public String getTestSample() {
		return testSample;
	}

	public void setTestSample(String testSample) {
		this.testSample = testSample;
	}

	public String getTestVolume() {
		return testVolume;
	}

	public void setTestVolume(String testVolume) {
		this.testVolume = testVolume;
	}

	public String getTestStorage() {
		return testStorage;
	}

	public void setTestStorage(String testStorage) {
		this.testStorage = testStorage;
	}

	public String getTestReport() {
		return testReport;
	}

	public void setTestReport(String testReport) {
		this.testReport = testReport;
	}

	public String getTestInstruction() {
		return testInstruction;
	}

	public void setTestInstruction(String testInstruction) {
		this.testInstruction = testInstruction;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTestHigh() {
		return testHigh;
	}

	public void setTestHigh(String testHigh) {
		this.testHigh = testHigh;
	}

	public String getTestLow() {
		return testLow;
	}

	public void setTestLow(String testLow) {
		this.testLow = testLow;
	}

}
