package com.hospital.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class SupplierInformation implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer supplierId;
	
	private String  supplierName;
	
	private String  supplierAddress;
	
	private Long  supplierMobileNumber;

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public Long getSupplierMobileNumber() {
		return supplierMobileNumber;
	}

	public void setSupplierMobileNumber(Long supplierMobileNumber) {
		this.supplierMobileNumber = supplierMobileNumber;
	}
	
}
