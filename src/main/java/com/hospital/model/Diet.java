/**
 * 
 */
package com.hospital.model;

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
@Table(name = "diet")
public class Diet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "diet_id")
	private Integer dietId;
	
	@OneToOne(targetEntity = Patient.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", referencedColumnName = "patient_id", foreignKey = @ForeignKey(name = "patient_DIET_FK"))
	private Patient patient;
	
	@OneToOne(targetEntity = Doctor.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", foreignKey = @ForeignKey(name = "doctor_DIET_FK"))
	private Doctor doctor;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<DietList> dietList;

	/**
	 * Get the dietId of Diet.
	 *
	 * @return the dietId
	 */
	public Integer getDietId() {
		return dietId;
	}

	/**
	 * Set the dietId of Diet.
	 *
	 * @param dietId the dietId to set
	 */
	public void setDietId(Integer dietId) {
		this.dietId = dietId;
	}

	/**
	 * Get the patient of Diet.
	 *
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Set the patient of Diet.
	 *
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Get the doctor of Diet.
	 *
	 * @return the doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * Set the doctor of Diet.
	 *
	 * @param doctor the doctor to set
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * Get the dietList of Diet.
	 *
	 * @return the dietList
	 */
	public List<DietList> getDietList() {
		return dietList;
	}

	/**
	 * Set the dietList of Diet.
	 *
	 * @param dietList the dietList to set
	 */
	public void setDietList(List<DietList> dietList) {
		this.dietList = dietList;
	}
	
}
