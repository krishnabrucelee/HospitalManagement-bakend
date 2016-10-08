package com.hospital.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class PatientLabReportSubcategeriesNames implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer catogeryId;
	@Column
	private String catogeryName;
	
	@Column
	private String rangeLowHigh;
	@Column
	private String result;
	@Column
	private String  unit;
	
	@Column
	private String methods;
	
	private Integer checkedby;

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

	

	public String getRangeLowHigh() {
		return rangeLowHigh;
	}

	public void setRangeLowHigh(String rangeLowHigh) {
		this.rangeLowHigh = rangeLowHigh;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getMethods() {
		return methods;
	}

	public void setMethods(String methods) {
		this.methods = methods;
	}

	public Integer getCheckedby() {
		return checkedby;
	}

	public void setCheckedby(Integer checkedby) {
		this.checkedby = checkedby;
	}

}
