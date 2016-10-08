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
public class MedicineTypes implements Serializable {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer entryId;
	@Column
	private Integer medicineId;
	@Column
	private String medicineName;
	@Column
	private String medicinePower;
	@Column
	private Long medicineCount;
	@Column
	private String medicineComposition;
	public Integer getEntryId() {
		return entryId;
	}
	public void setEntryId(Integer entryId) {
		this.entryId = entryId;
	}
	
	public Integer getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
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
	public String getMedicineComposition() {
		return medicineComposition;
	}
	public void setMedicineComposition(String medicineComposition) {
		this.medicineComposition = medicineComposition;
	}
	public Long getMedicineCount() {
		return medicineCount;
	}
	public void setMedicineCount(Long medicineCount) {
		this.medicineCount = medicineCount;
	}
	
}
