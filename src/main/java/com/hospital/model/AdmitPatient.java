/**
 * 
 */
package com.hospital.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "admitPatient")
public class AdmitPatient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "admit_patient_id")
	private Integer admitPatientId;
	
	@Column(name = "patient_id")
	private String patientId;
	
	@Column(name = "ward_number")
	private String wardNumber;
	
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	/**
	 * Get the admitPatientId of AdmitPatient.
	 *
	 * @return the admitPatientId
	 */
	public Integer getAdmitPatientId() {
		return admitPatientId;
	}

	/**
	 * Set the admitPatientId of AdmitPatient.
	 *
	 * @param admitPatientId the admitPatientId to set
	 */
	public void setAdmitPatientId(Integer admitPatientId) {
		this.admitPatientId = admitPatientId;
	}

	/**
	 * Get the patientId of AdmitPatient.
	 *
	 * @return the patientId
	 */
	public String getPatientId() {
		return patientId;
	}

	/**
	 * Set the patientId of AdmitPatient.
	 *
	 * @param patientId the patientId to set
	 */
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	/**
	 * Get the wardNumber of AdmitPatient.
	 *
	 * @return the wardNumber
	 */
	public String getWardNumber() {
		return wardNumber;
	}

	/**
	 * Set the wardNumber of AdmitPatient.
	 *
	 * @param wardNumber the wardNumber to set
	 */
	public void setWardNumber(String wardNumber) {
		this.wardNumber = wardNumber;
	}

	/**
	 * Get the createdDate of AdmitPatient.
	 *
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Set the createdDate of AdmitPatient.
	 *
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
