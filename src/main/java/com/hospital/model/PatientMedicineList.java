package com.hospital.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class PatientMedicineList implements Serializable {
	
		
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer medicineListId;
	
	@Column
	private Integer pharmacyMasterId;
	
	@Column
	private  String itemName;
	
	@Column
	private  String batchId;
	
	@Column
	private Date expiryDate;
	
	@Column
	private  Long quantity;
	
	@Column
	private  Double price;
	
	
	
	@Column
	private  int  amount;
	
	
	
	
	public PatientMedicineList() {
		super();
	}

	public Integer getMedicineListId() {
		return medicineListId;
	}

	public void setMedicineListId(Integer medicineListId) {
		this.medicineListId = medicineListId;
	}

	

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	
	public Integer getPharmacyMasterId() {
		return pharmacyMasterId;
	}

	public void setPharmacyMasterId(Integer pharmacyMasterId) {
		this.pharmacyMasterId = pharmacyMasterId;
	}

	

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	
	
		
}
