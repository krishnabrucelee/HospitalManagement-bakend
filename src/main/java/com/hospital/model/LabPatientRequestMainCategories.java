package com.hospital.model;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Entity
@Table
public class LabPatientRequestMainCategories implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer patientLabRequestMainCategoryId;
	
	
	@Column
	private String mainCatogeryTestName;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="patientLabRequestMainCategoryId")
	private List<LabPatientRequestSubcategories> patientlabtestsubcategories;
	
	

	public LabPatientRequestMainCategories() {
		super();
	}

	public Integer getPatientLabRequestMainCategoryId() {
		return patientLabRequestMainCategoryId;
	}

	public void setPatientLabRequestMainCategoryId(
			Integer patientLabRequestMainCategoryId) {
		this.patientLabRequestMainCategoryId = patientLabRequestMainCategoryId;
	}

	public String getMainCatogeryTestName() {
		return mainCatogeryTestName;
	}

	public void setMainCatogeryTestName(String mainCatogeryTestName) {
		this.mainCatogeryTestName = mainCatogeryTestName;
	}

	
	public List<LabPatientRequestSubcategories> getPatientlabtestsubcategories() {
		return patientlabtestsubcategories;
	}

	public void setPatientlabtestsubcategories(
			List<LabPatientRequestSubcategories> patientlabtestsubcategories) {
		this.patientlabtestsubcategories = patientlabtestsubcategories;
	}

	
	
	
}
