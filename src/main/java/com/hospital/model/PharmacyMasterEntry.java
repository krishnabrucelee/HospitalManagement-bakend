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

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table
@Component
public class PharmacyMasterEntry implements Serializable {
	
	@Column	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	 private Long pharmacyMasterId;
	
	
	@Column
	private Integer medicineId;
	
	@Column
	private String itemName;
	
	
	@Column
	@JsonFormat(pattern="dd-MM-yyyy")	
	@Temporal(TemporalType.DATE)
	private Date purchaseDate;
	
	@Column
	private String batchId;
		
	
	
	@Column
	@JsonFormat(pattern="dd-MM-yyyy")	
	@Temporal(TemporalType.DATE)
	private Date manufactureDate;
	
	@Column
	@JsonFormat(pattern="dd-MM-yyyy")	
	@Temporal(TemporalType.DATE)
	private Date expiryDate;
	
	@Column
	private String itemUnits;
	
	@Column
	private Integer numbersInUnit;
	@Column
	private Integer numberofUnits;
	
	
	@Column
	private Long quantity;
	

	@Column
	private Double price;

	public PharmacyMasterEntry() {
		super();
	}

	public Long getPharmacyMasterId() {
		return pharmacyMasterId;
	}

	public void setPharmacyMasterId(Long pharmacyMasterId) {
		this.pharmacyMasterId = pharmacyMasterId;
	}

	

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
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

	public String getItemUnits() {
		return itemUnits;
	}

	public void setItemUnits(String itemUnits) {
		this.itemUnits = itemUnits;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Integer getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getNumberofUnits() {
		return numberofUnits;
	}

	public void setNumberofUnits(Integer numberofUnits) {
		this.numberofUnits = numberofUnits;
	}

	public Integer getNumbersInUnit() {
		return numbersInUnit;
	}

	public void setNumbersInUnit(Integer numbersInUnit) {
		this.numbersInUnit = numbersInUnit;
	}

	@Override
	public String toString() {
		return "PharmacyMasterEntry [pharmacyMasterId=" + pharmacyMasterId
				+ ", medicineId=" + medicineId + ", itemName=" + itemName
				+ ", purchaseDate=" + purchaseDate + ", batchId=" + batchId
				+ ", manufactureDate=" + manufactureDate + ", expiryDate="
				+ expiryDate + ", itemUnits=" + itemUnits + ", numbersInUnit="
				+ numbersInUnit + ", numberofUnits=" + numberofUnits
				+ ", quantity=" + quantity + ", price=" + price + "]";
	}
			
}
