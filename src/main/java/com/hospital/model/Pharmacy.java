package com.hospital.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table
public class Pharmacy implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer pharmacy_id;
	@OneToOne(targetEntity=Department.class)
	@JoinColumn(name="departmentt_id",referencedColumnName="department_id",foreignKey=@ForeignKey(name="department_Pharmacy_FK"))
	private  Department  department;
	private Long medicineId;
	@Column
	private String medicineName;
	
	@Column
	private Integer medicineBatchId;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiredDate;
	
	@Column
	private Integer  medicinePrice;
	@Column
	private Integer medicineCount;
	@Column
	private String medicineType;
	@Column
	private Integer medicinePercentage;
	public Integer getPharmacy_id() {
		return pharmacy_id;
	}
	public void setPharmacy_id(Integer pharmacy_id) {
		this.pharmacy_id = pharmacy_id;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Long getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public Integer getMedicineBatchId() {
		return medicineBatchId;
	}
	public void setMedicineBatchId(Integer medicineBatchId) {
		this.medicineBatchId = medicineBatchId;
	}
	public Date getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	public Integer getMedicinePrice() {
		return medicinePrice;
	}
	public void setMedicinePrice(Integer medicinePrice) {
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
	public Integer getMedicinePercentage() {
		return medicinePercentage;
	}
	public void setMedicinePercentage(Integer medicinePercentage) {
		this.medicinePercentage = medicinePercentage;
	}
	
	
	
}
