package com.hospital.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PatientLabTestReport {

	@Id	
	private Integer patientLabTestReport_id;
	
	private Integer preparedBy;
	
	@Column
	private String testResult;
	
	private String notes;
		
	
	
}
