/**
 * 
 */
package com.hospital.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "laboratory")
public class Laboratory implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="laboratory_id")
	private Long laboratoryId;
	
	@OneToOne(targetEntity = Patient.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", referencedColumnName = "patient_id", foreignKey = @ForeignKey(name = "patient_LAB_FK"))
	private Patient patient;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<LaboratoryTest> laboratoryTest;

	/**
	 * Get the LaboratoryId of Laboratory.
	 *
	 * @return the LaboratoryId
	 */
	public Long getLaboratoryId() {
		return laboratoryId;
	}

	/**
	 * Set the laboratoryId of Laboratory.
	 *
	 * @param laboratoryId the emrId to set
	 */
	public void setLaboratoryId(Long laboratoryId) {
		this.laboratoryId = laboratoryId;
	}

	/**
	 * Get the patient of Laboratory.
	 *
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Set the patient of Laboratory.
	 *
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Get the laboratoryTest of Laboratory.
	 *
	 * @return the laboratoryTest
	 */
	public List<LaboratoryTest> getLaboratoryTest() {
		return laboratoryTest;
	}

	/**
	 * Set the laboratoryTest of Laboratory.
	 *
	 * @param laboratoryTest the laboratoryTest to set
	 */
	public void setLaboratoryTest(List<LaboratoryTest> laboratoryTest) {
		this.laboratoryTest = laboratoryTest;
	}

}
