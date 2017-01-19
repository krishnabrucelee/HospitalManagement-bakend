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
public class MedicineOrderTypes implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long medicinetypesId;
	
	@Column
	private String medicineName;
	@Column
	private String medicinePower;
	@Column
	private Long  medicineCount;
	
	@Column
	private String medicineTypes;
	@Column
	private String companyName;
	public MedicineOrderTypes() {
		super();
	}
	
	public Long getMedicinetypesId() {
		return medicinetypesId;
	}

	public void setMedicinetypesId(Long medicinetypesId) {
		this.medicinetypesId = medicinetypesId;
	}

	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getMedicinePower() {
		return medicinePower;
	}
	public void setMedicinePower(String medicinePower) {
		this.medicinePower = medicinePower;
	}
	
	public String getMedicineTypes() {
		return medicineTypes;
	}

	public void setMedicineTypes(String medicineTypes) {
		this.medicineTypes = medicineTypes;
	}

	public Long getMedicineCount() {
		return medicineCount;
	}
	public void setMedicineCount(Long medicineCount) {
		this.medicineCount = medicineCount;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
