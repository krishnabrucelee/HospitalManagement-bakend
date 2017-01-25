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
public class MedicineCategories implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer medicineCategoriesId;
	
	@Column
	private String priority;
	
	@Column
	private String medicineCategory;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="medicineCategoriesId",foreignKey=@ForeignKey(name="MedicineCategoriesId_medicineDetailId_FK"))
	private List<MedicineDetails> medicineDetails =new ArrayList<MedicineDetails>();

	public Integer getMedicineCategoriesId() {
		return medicineCategoriesId;
	}

	public void setMedicineCategoriesId(Integer medicineCategoriesId) {
		this.medicineCategoriesId = medicineCategoriesId;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getMedicineCategory() {
		return medicineCategory;
	}

	public void setMedicineCategory(String medicineCategory) {
		this.medicineCategory = medicineCategory;
	}

	public List<MedicineDetails> getMedicineDetails() {
		return medicineDetails;
	}

	public void setMedicineDetails(List<MedicineDetails> medicineDetails) {
		this.medicineDetails = medicineDetails;
	}
	
	
	
	
}
