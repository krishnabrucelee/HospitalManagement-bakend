/**
 * 
 */
package com.hospital.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "bioMedicalType")
public class BioMedicalType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bio_medical_type_id")
	private Integer bioMedicalTypeId;
	
	@Column(name = "bio_medical_type")
	private String bioMedicalType;
	
	@Column(name = "frequency")
	private Integer frequency;

	/**
	 * Get the bioMedicalTypeId of BioMedicalType.
	 *
	 * @return the bioMedicalTypeId
	 */
	public Integer getBioMedicalTypeId() {
		return bioMedicalTypeId;
	}

	/**
	 * Set the bioMedicalTypeId of BioMedicalType.
	 *
	 * @param bioMedicalTypeId the bioMedicalTypeId to set
	 */
	public void setBioMedicalTypeId(Integer bioMedicalTypeId) {
		this.bioMedicalTypeId = bioMedicalTypeId;
	}

	/**
	 * Get the bioMedicalType of BioMedicalType.
	 *
	 * @return the bioMedicalType
	 */
	public String getBioMedicalType() {
		return bioMedicalType;
	}

	/**
	 * Set the bioMedicalType of BioMedicalType.
	 *
	 * @param bioMedicalType the bioMedicalType to set
	 */
	public void setBioMedicalType(String bioMedicalType) {
		this.bioMedicalType = bioMedicalType;
	}

	/**
	 * Get the frequency of BioMedicalType.
	 *
	 * @return the frequency
	 */
	public Integer getFrequency() {
		return frequency;
	}

	/**
	 * Set the frequency of BioMedicalType.
	 *
	 * @param frequency the frequency to set
	 */
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
	
	
}
