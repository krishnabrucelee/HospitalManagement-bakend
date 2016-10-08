package com.hospital.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "inPatient")
public class InPatient implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "in_patient_id")
	private Integer inPatientId;

	@Column(name = "appointed_doctor")
	private String appointedDoctor;
	
	@Column(name = "doctor_reference")
	private String doctorReference;
	
	@Column(name = "in_patient_description")
	private String inPatientDescription;
	
	@OneToOne(targetEntity = Ward.class)
	@JoinColumn(name = "ward_id", referencedColumnName = "ward_id", foreignKey = @ForeignKey(name = "ward_INP_FK"))
	private Ward ward;

	/**
	 * Get the inPatientId of InPatient.
	 *
	 * @return the inPatientId
	 */
	public Integer getInPatientId() {
		return inPatientId;
	}

	/**
	 * Set the inPatientId of InPatient.
	 *
	 * @param inPatientId the inPatientId to set
	 */
	public void setInPatientId(Integer inPatientId) {
		this.inPatientId = inPatientId;
	}

	/**
	 * Get the appointedDoctor of InPatient.
	 *
	 * @return the appointedDoctor
	 */
	public String getAppointedDoctor() {
		return appointedDoctor;
	}

	/**
	 * Set the appointedDoctor of InPatient.
	 *
	 * @param appointedDoctor the appointedDoctor to set
	 */
	public void setAppointedDoctor(String appointedDoctor) {
		this.appointedDoctor = appointedDoctor;
	}

	/**
	 * Get the doctorReference of InPatient.
	 *
	 * @return the doctorReference
	 */
	public String getDoctorReference() {
		return doctorReference;
	}

	/**
	 * Set the doctorReference of InPatient.
	 *
	 * @param doctorReference the doctorReference to set
	 */
	public void setDoctorReference(String doctorReference) {
		this.doctorReference = doctorReference;
	}

	/**
	 * Get the inPatientDescription of InPatient.
	 *
	 * @return the inPatientDescription
	 */
	public String getInPatientDescription() {
		return inPatientDescription;
	}

	/**
	 * Set the inPatientDescription of InPatient.
	 *
	 * @param inPatientDescription the inPatientDescription to set
	 */
	public void setInPatientDescription(String inPatientDescription) {
		this.inPatientDescription = inPatientDescription;
	}

	/**
	 * Get the ward of InPatient.
	 *
	 * @return the ward
	 */
	public Ward getWard() {
		return ward;
	}

	/**
	 * Set the ward of InPatient.
	 *
	 * @param ward the ward to set
	 */
	public void setWard(Ward ward) {
		this.ward = ward;
	}
	
}