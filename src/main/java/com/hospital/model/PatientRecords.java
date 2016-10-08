package com.hospital.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class PatientRecords {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long patientId;
	private Set<DrugtoPatient> drugtoPatient = new HashSet<DrugtoPatient>(0);
	@Column
	private String patient_name;
	@Column
	private Integer patient_age;
	
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public Integer getPatient_age() {
		return patient_age;
	}
	public void setPatient_age(Integer patient_age) {
		this.patient_age = patient_age;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "drugtopatient")
	public Set<DrugtoPatient> getDrugtoPatient() {
		return drugtoPatient;
	}
	public void setDrugtoPatient(Set<DrugtoPatient> drugtoPatient) {
		this.drugtoPatient = drugtoPatient;
	}
	
	
	
	
	
}
