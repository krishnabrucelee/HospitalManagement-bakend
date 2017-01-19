package com.hospital.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;
@Entity
@Table
@Component
public class MedicineMasterStock implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long medicineMasterStockId;
	
	@Column
	private String medicineType;
	
	@Column
	private String companyName;
	
	@Column
	private String medicineName;
	
	
	@Column
	private Integer medicineTotalUnit;
	
	@Column
	private String medicineUnit;
	
	@Column
	private Double price;
	

	public MedicineMasterStock() {
		super();
	}

	public Long getMedicineMasterStockId() {
		return medicineMasterStockId;
	}

	public void setMedicineMasterStockId(Long medicineMasterStockId) {
		this.medicineMasterStockId = medicineMasterStockId;
	}

	public String getMedicineType() {
		return medicineType;
	}

	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	

	public Integer getMedicineTotalUnit() {
		return medicineTotalUnit;
	}

	public void setMedicineTotalUnit(Integer medicineTotalUnit) {
		this.medicineTotalUnit = medicineTotalUnit;
	}

	public String getMedicineUnit() {
		return medicineUnit;
	}

	public void setMedicineUnit(String medicineUnit) {
		this.medicineUnit = medicineUnit;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
