package com.hospital.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class PatientSubcategery implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer srId;
	@Column
	private Integer labrequestId;
	@Column
	private Integer patientId;
	@Column
	private String patientName;
	List<PatientSubcategery> subcategery = new ArrayList<PatientSubcategery>();
	public Integer getSrId() {
		return srId;
	}
	public void setSrId(Integer srId) {
		this.srId = srId;
	}
	public List<PatientSubcategery> getSubcategery() {
		return subcategery;
	}
	public void setSubcategery(List<PatientSubcategery> subcategery) {
		this.subcategery = subcategery;
	}
	public Integer getLabrequestId() {
		return labrequestId;
	}
	public void setLabrequestId(Integer labrequestId) {
		this.labrequestId = labrequestId;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	
}
