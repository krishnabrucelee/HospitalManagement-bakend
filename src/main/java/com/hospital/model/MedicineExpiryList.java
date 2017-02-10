package com.hospital.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
@Component
public class MedicineExpiryList implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer medicineExpiryTransferId;
	
	@ManyToOne()
	@JoinColumn(name="medicineId")
	private MedicineItemMaster medicineMaster;
	
	private String itemName;
	
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
	private Long quantity;
	
	@Column
	private String unit;
	
	@Column
	private String supplierName;
	
	

	public MedicineExpiryList() {
		super();
	}

	public Integer getMedicineExpiryTransferId() {
		return medicineExpiryTransferId;
	}

	public void setMedicineExpiryTransferId(Integer medicineExpiryTransferId) {
		this.medicineExpiryTransferId = medicineExpiryTransferId;
	}

	public MedicineItemMaster getMedicineMaster() {
		return medicineMaster;
	}

	public void setMedicineMaster(MedicineItemMaster medicineMaster) {
		this.medicineMaster = medicineMaster;
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

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	

}
