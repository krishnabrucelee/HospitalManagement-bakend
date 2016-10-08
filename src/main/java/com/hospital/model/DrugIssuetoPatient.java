package com.hospital.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table
public class DrugIssuetoPatient implements Serializable {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long drugissueId;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="DRUG_PATIENT", 
				joinColumns={@JoinColumn(name="drugissueId")}, 
				inverseJoinColumns={@JoinColumn(name="patient_Id")})
	private Set<Patient> patient = new HashSet<Patient>();
	private String medicineItemCode;
	
	private String medicineName;
	private Integer medicineCount;
	
	private Integer medicinePrice;

	public Long getDrugissueId() {
		return drugissueId;
	}

	public void setDrugissueId(Long drugissueId) {
		this.drugissueId = drugissueId;
	}

	public Set<Patient> getPatient() {
		return patient;
	}

	public void setPatient(Set<Patient> patient) {
		this.patient = patient;
	}

	

	public String getMedicineItemCode() {
		return medicineItemCode;
	}

	public void setMedicineItemCode(String medicineItemCode) {
		this.medicineItemCode = medicineItemCode;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public Integer getMedicineCount() {
		return medicineCount;
	}

	public void setMedicineCount(Integer medicineCount) {
		this.medicineCount = medicineCount;
	}

	public Integer getMedicinePrice() {
		return medicinePrice;
	}

	public void setMedicinePrice(Integer medicinePrice) {
		this.medicinePrice = medicinePrice;
	}
	
}
