package com.hospital.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class LabTest implements Serializable {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer labtestId;
	
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
	private Double testcost;
	public Integer getLabtestId() {
		return labtestId;
	}
	public void setLabtestId(Integer labtestId) {
		this.labtestId = labtestId;
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
	public Double getTestcost() {
		return testcost;
	}
	public void setTestcost(Double testcost) {
		this.testcost = testcost;
	}
		
}
