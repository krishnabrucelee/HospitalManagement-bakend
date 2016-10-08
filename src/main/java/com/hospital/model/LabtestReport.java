package com.hospital.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class LabtestReport implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long testreportId;
	@JsonFormat(pattern="dd-MM-yyyy")	
	@Temporal(TemporalType.DATE)
	@Column
	private Date testreportTime;
	@Column
	private String testName;
	@Column
	private Double labtestPrice;
	
	private Integer patientId;
	
	public LabtestReport() {
		super();
	}

	public Long getTestreportId() {
		return testreportId;
	}

	public void setTestreportId(Long testreportId) {
		this.testreportId = testreportId;
	}

	public Date getTestreportTime() {
		return testreportTime;
	}

	public void setTestreportTime(Date testreportTime) {
		this.testreportTime = testreportTime;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Double getLabtestPrice() {
		return labtestPrice;
	}

	public void setLabtestPrice(Double labtestPrice) {
		this.labtestPrice = labtestPrice;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	
}
