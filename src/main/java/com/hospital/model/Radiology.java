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
@Table(name="radiology")
public class Radiology implements Serializable {
	
	@Column(name="radiology_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer radiologyId;
	
	@Column(name="radiology_testname")
	private String radiologyTestName;
	
	@Column(name="radiology_description")
	private String radiologyDescription;
	
	public Radiology() {
		super();
	}
	/**
	 * Get the radiologyId of Radiology.
	 *
	 * @return the radiologyId
	 */
	public Integer getRadiologyId() {
		return radiologyId;
	}
	/**
	 * Set the radiologyId of Radiology.
	 *
	 * @param radiologyId
	 *            the radiologyId to set
	 */
	public void setRadiologyId(Integer radiologyId) {
		this.radiologyId = radiologyId;
	}
	/**
	 * Get the radiologyTestName of Radiology.
	 *
	 * @return the radiologyTestName
	 */
	public String getRadiologyTestName() {
		return radiologyTestName;
	}
	/**
	 * Set the radiologyTestName of Radiology.
	 *
	 * @param radiologyTestName
	 *            the radiologyTestName to set
	 */
	public void setRadiologyTestName(String radiologyTestName) {
		this.radiologyTestName = radiologyTestName;
	}
	/**
	 * Get the radiologyTestDescription of Radiology.
	 *
	 * @return the radiologyTestDescription
	 */
	public String getRadiologyDescription() {
		return radiologyDescription;
	}
	/**
	 * Set the radiologyTestDescription of Radiology.
	 *
	 * @param radiologyTestName
	 *            the radiologyTestDescription to set
	 */
	public void setRadiologyDescription(String radiologyDescription) {
		this.radiologyDescription = radiologyDescription;
	}
	
}
