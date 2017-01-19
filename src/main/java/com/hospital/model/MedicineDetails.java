package com.hospital.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Entity
@Table
@Component
public class MedicineDetails implements Serializable {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long medicineDetailId;
	
	@Column
	private String priority;
	
	@Column
	private String medicineTypes;
	
	@Column
	private String medicineName;
	
	@Column
	private String company;
	
	@Column
	private String composition;
	
	@Column
	private String medicinePower;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="medicineDetailId",foreignKey=@ForeignKey(name="MedicineDetailId_MedicineMasterStockId_FK"))
	private List<MedicineMasterStock> medicineMasterStock =new  ArrayList<MedicineMasterStock>();
	

	public MedicineDetails() {
		super();
	}

	public Long getMedicineDetailId() {
		return medicineDetailId;
	}

	public void setMedicineDetailId(Long medicineDetailId) {
		this.medicineDetailId = medicineDetailId;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getMedicineTypes() {
		return medicineTypes;
	}

	public void setMedicineTypes(String medicineTypes) {
		this.medicineTypes = medicineTypes;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public String getMedicinePower() {
		return medicinePower;
	}

	public void setMedicinePower(String medicinePower) {
		this.medicinePower = medicinePower;
	}

	public List<MedicineMasterStock> getMedicineMasterStock() {
		return medicineMasterStock;
	}

	public void setMedicineMasterStock(List<MedicineMasterStock> medicineMasterStock) {
		this.medicineMasterStock = medicineMasterStock;
	}
	
	
}
