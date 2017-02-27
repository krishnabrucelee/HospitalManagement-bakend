package com.hospital.model.radiology;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class RadiologyTest implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer radiologyTestId;
	
	@Column
	private String radiologyTestName;
	
	@Column
	private String testDescription;
	
	

	public RadiologyTest() {
		super();
	}

	

	public String getRadiologyTestName() {
		return radiologyTestName;
	}

	public void setRadiologyTestName(String radiologyTestName) {
		this.radiologyTestName = radiologyTestName;
	}



	public Integer getRadiologyTestId() {
		return radiologyTestId;
	}



	public void setRadiologyTestId(Integer radiologyTestId) {
		this.radiologyTestId = radiologyTestId;
	}



	public String getTestDescription() {
		return testDescription;
	}



	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}



	@Override
	public String toString() {
		return "RadiologyTest [radiologyTestId=" + radiologyTestId
				+ ", radiologyTestName=" + radiologyTestName
				+ ", testDescription=" + testDescription + "]";
	}

	
	
	
}
