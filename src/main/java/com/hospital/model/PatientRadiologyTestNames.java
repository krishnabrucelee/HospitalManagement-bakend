/**
 * 
 */
package com.hospital.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author admin
 *
 */
@Entity
@Table(name="patientRadiologyTestNames")
public class PatientRadiologyTestNames implements Serializable {
	
	/**
	 * 
	 */
	//private static final long serialVersionUID = -5806574718177366998L;

	@Column(name="test_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer testId;
	
	@Column(name="scan_name")
	private String scanName;
		
	public PatientRadiologyTestNames() {
		super();
	}

	/**
	 * Get the testId .
	 *
	 * @return the testId
	 */
	public Integer getTestId() {
		return testId;
	}
	
	/**
	 * Set the testId .
	 *
	 * @param testId
	 *            the testId to set
	 */
	public void setTestId(Integer testId) {
		this.testId = testId;
	}
	
	/**
	 * Get the ScanName.
	 *
	 * @return the ScanName
	 */
	public String getScanName() {
		return scanName;
	}
	
	/**
	 * Set the ScanName.
	 *
	 * @param ScanName
	 *            the ScanName to set
	 */
	public void setScanName(String scanName) {
		this.scanName = scanName;
	}
	
	
}
