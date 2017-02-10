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
public class LabPatientRequestSubcategories implements Serializable {	
		
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer patientLabRequestSubCategoryId;
	
	@Column
	private String subCatogeryTestName;
	

	
	public LabPatientRequestSubcategories() {
		super();
	}

	


	public Integer getPatientLabRequestSubCategoryId() {
		return patientLabRequestSubCategoryId;
	}


	public void setPatientLabRequestSubCategoryId(
			Integer patientLabRequestSubCategoryId) {
		this.patientLabRequestSubCategoryId = patientLabRequestSubCategoryId;
	}



	public String getSubCatogeryTestName() {
		return subCatogeryTestName;
	}


	public void setSubCatogeryTestName(String subCatogeryTestName) {
		this.subCatogeryTestName = subCatogeryTestName;
	}
	

}
