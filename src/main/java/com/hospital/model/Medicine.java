package com.hospital.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table
public class Medicine implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer medicineAddId;
	@Column
	private Integer pharmacyId;
	@Column
	private Integer medicineId;
	@Column
	private Integer departmentId;
	@Column
	private String medicineName;
	@Column
	private String medicinePower;
	@Column
	private String medicineBatchId;
	@Column
	private String medicineComposition;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiredDate;	
	@Column
	private Double  medicinePrice;
	@Column
	private Integer medicineCount;
	@Column
	private String medicineType;
	
	public Integer getMedicineAddId() {
		return medicineAddId;
	}
	public void setMedicineAddId(Integer medicineAddId) {
		this.medicineAddId = medicineAddId;
	}
	public Integer getPharmacyId() {
		return pharmacyId;
	}
	public void setPharmacyId(Integer pharmacyId) {
		this.pharmacyId = pharmacyId;
	}
	public Integer getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}
	public String getMedicineComposition() {
		return medicineComposition;
	}
	public void setMedicineComposition(String medicineComposition) {
		this.medicineComposition = medicineComposition;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
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
	
	
	public String getMedicineBatchId() {
		return medicineBatchId;
	}
	public void setMedicineBatchId(String medicineBatchId) {
		this.medicineBatchId = medicineBatchId;
	}
	public Date getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	
	public Double getMedicinePrice() {
		return medicinePrice;
	}
	public void setMedicinePrice(Double medicinePrice) {
		this.medicinePrice = medicinePrice;
	}
	public Integer getMedicineCount() {
		return medicineCount;
	}
	public void setMedicineCount(Integer medicineCount) {
		this.medicineCount = medicineCount;
	}
	public String getMedicineType() {
		return medicineType;
	}
	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}
	
	
}
