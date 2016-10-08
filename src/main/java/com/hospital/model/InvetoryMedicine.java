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
public class InvetoryMedicine implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long invetoryId;
	@Column
	private Long  medicineId;
	@Column
	private String medicineBatchId;
	@Column
	private Long  medicineCount;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiredDate;

	public Long getInvetoryId() {
		return invetoryId;
	}

	public void setInvetoryId(Long invetoryId) {
		this.invetoryId = invetoryId;
	}

	
	public Long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
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

	public Long getMedicineCount() {
		return medicineCount;
	}

	public void setMedicineCount(Long medicineCount) {
		this.medicineCount = medicineCount;
	}
	
}
