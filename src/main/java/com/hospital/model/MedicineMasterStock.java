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
public class MedicineMasterStock implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer medicineMasterStockId;
	
	@Column
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")	
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
	private String batchId;
	
	@Column
	private String unitsDetails;
	
	@Column
	private Integer quantity;
	
	@Column
	private Double  medicinePrice;

	public MedicineMasterStock() {
		super();
	}

	public Integer getMedicineMasterStockId() {
		return medicineMasterStockId;
	}

	public void setMedicineMasterStockId(Integer medicineMasterStockId) {
		this.medicineMasterStockId = medicineMasterStockId;
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

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getUnitsDetails() {
		return unitsDetails;
	}

	public void setUnitsDetails(String unitsDetails) {
		this.unitsDetails = unitsDetails;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getMedicinePrice() {
		return medicinePrice;
	}

	public void setMedicinePrice(Double medicinePrice) {
		this.medicinePrice = medicinePrice;
	}
	
	
	

}
