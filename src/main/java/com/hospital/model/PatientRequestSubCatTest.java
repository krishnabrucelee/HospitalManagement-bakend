package com.hospital.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class PatientRequestSubCatTest implements Serializable {
	
	@Column(name="catogery_Id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer catogeryId;
	@Column
	private String testName;
	@Column
	private String catogeryName;
	@Column
	private Double price;
	public Integer getCatogeryId() {
		return catogeryId;
	}
	public void setCatogeryId(Integer catogeryId) {
		this.catogeryId = catogeryId;
	}
	public String getCatogeryName() {
		return catogeryName;
	}
	public void setCatogeryName(String catogeryName) {
		this.catogeryName = catogeryName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}	
}
