package com.hospital.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class EquipmentData implements Serializable {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer equipmentId;
	@Column
	private String equipmentName;
	@Column
	private String  location;
	@Column
	private String department;
	@Column
	private String manufactureName;
	@Column
	private String  supplierName;
	@Column
	private String  waranty;
	@Column
	private Double price;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date manufactureDate;
	@Column
	private String routineCheck;
	
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getManufactureName() {
		return manufactureName;
	}
	public void setManufactureName(String manufactureName) {
		this.manufactureName = manufactureName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getWaranty() {
		return waranty;
	}
	public void setWaranty(String waranty) {
		this.waranty = waranty;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getManufactureDate() {
		return manufactureDate;
	}
	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}
	
	public String getRoutineCheck() {
		return routineCheck;
	}
	public void setRoutineCheck(String routineCheck) {
		this.routineCheck = routineCheck;
	}
	public EquipmentData() {
		super();
	}
	@Override
	public String toString() {
		return "EquipmentData [equipmentId=" + equipmentId + ", equipmentName="
				+ equipmentName + ", location=" + location + ", department="
				+ department + ", manufactureName=" + manufactureName
				+ ", supplierName=" + supplierName + ", waranty=" + waranty
				+ ", price=" + price + ", manufactureDate=" + manufactureDate
				+ ", routineCheck=" + routineCheck + "]";
	}
	
	
	
}
