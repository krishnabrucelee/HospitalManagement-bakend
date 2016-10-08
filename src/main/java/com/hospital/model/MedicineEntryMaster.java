package com.hospital.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
public class MedicineEntryMaster implements Serializable {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long entryId;
	@Column
	private Integer medicineId;
	@Column
	private String medicineName;
	@Column
	private String medicinePower;
	@Column
	private Long  medicineCount;
	@Column
	private String  medicineBatchId;
	@Column
	private String  supplierName;
	@Column
	private String medicineComposition;
	@Column
	private String companyName;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date purchaseDate;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date manufactureDate;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryDate;
	@Column
	private Double  medicinePrice;
	@Column
	private String medicineType;
	public Long getEntryId() {
		return entryId;
	}
	public void setEntryId(Long entryId) {
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
	
	public String getMedicineType() {
		return medicineType;
	}
	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}
	public Long getMedicineCount() {
		return medicineCount;
	}
	public void setMedicineCount(Long medicineCount) {
		this.medicineCount = medicineCount;
	}
	
	public String getMedicineBatchId() {
		return medicineBatchId;
	}
	public void setMedicineBatchId(String medicineBatchId) {
		this.medicineBatchId = medicineBatchId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Date getManufactureDate() {
		return manufactureDate;
	}
	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Double getMedicinePrice() {
		return medicinePrice;
	}
	public void setMedicinePrice(Double medicinePrice) {
		this.medicinePrice = medicinePrice;
	}
	public String getMedicineComposition() {
		return medicineComposition;
	}
	public void setMedicineComposition(String medicineComposition) {
		this.medicineComposition = medicineComposition;
	}
	
}
