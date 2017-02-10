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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Entity
@Table
@Component
public class LabMasterName implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer materLabTestId;
	
	@Column
	private String testName;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="materLabTestId",foreignKey=@ForeignKey(name="LabMasterName_LabMasterSubCategories_FK"))
	private List<LabMasterSubCategories> labmastersubcategeries;

	public LabMasterName() {
		super();
	}

	public Integer getMaterLabTestId() {
		return materLabTestId;
	}

	public void setMaterLabTestId(Integer materLabTestId) {
		this.materLabTestId = materLabTestId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public List<LabMasterSubCategories> getLabmastersubcategeries() {
		return labmastersubcategeries;
	}

	public void setLabmastersubcategeries(
			List<LabMasterSubCategories> labmastersubcategeries) {
		this.labmastersubcategeries = labmastersubcategeries;
	}

	@Override
	public String toString() {
		return "LabMasterName [materLabTestId=" + materLabTestId
				+ ", testName=" + testName + ", labmastersubcategeries="
				+ labmastersubcategeries + "]";
	}
	
	
}
