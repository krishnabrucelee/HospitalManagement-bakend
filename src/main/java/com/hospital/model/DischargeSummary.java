/**
 * 
 */
package com.hospital.model;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "dischargeSummary")
public class DischargeSummary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "discharge_summary_id")
	private Integer dischargeSummaryId;

	@OneToOne(targetEntity = Patient.class,cascade=CascadeType.ALL)
	@JoinColumn(name ="patient_id")
	private Patient patient;
	
	@OneToOne(targetEntity = Doctor.class)
	@JoinColumn(name="doctor_id")
	private  Doctor  doctor;
	
	@OneToOne(targetEntity = Nurse.class)
	@JoinColumn(name="nurse_id")
	private  Nurse  nurse;
	
	@Column(name = "ward_number")
	private String wardNumber;
	
	@Column(name = "discharge_summary_details")
	private String dischargeSummaryDetails;
	
	@Column(name = "admitted_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date admittedDate;
	
	@Column(name = "discharge_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dischargeDate;

	/**
	 * Get the dischargeSummaryId of DischargeSummary.
	 *
	 * @return the dischargeSummaryId
	 */
	public Integer getDischargeSummaryId() {
		return dischargeSummaryId;
	}

	/**
	 * Set the dischargeSummaryId of DischargeSummary.
	 *
	 * @param dischargeSummaryId the dischargeSummaryId to set
	 */
	public void setDischargeSummaryId(Integer dischargeSummaryId) {
		this.dischargeSummaryId = dischargeSummaryId;
	}

	/**
	 * Get the patient of DischargeSummary.
	 *
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Set the patient of DischargeSummary.
	 *
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Get the dischargeSummaryDetails of DischargeSummary.
	 *
	 * @return the dischargeSummaryDetails
	 */
	public String getDischargeSummaryDetails() {
		return dischargeSummaryDetails;
	}

	/**
	 * Set the dischargeSummaryDetails of DischargeSummary.
	 *
	 * @param dischargeSummaryDetails the dischargeSummaryDetails to set
	 */
	public void setDischargeSummaryDetails(String dischargeSummaryDetails) {
		this.dischargeSummaryDetails = dischargeSummaryDetails;
	}

	/**
	 * Get the dischargeDate of DischargeSummary.
	 *
	 * @return the dischargeDate
	 */
	public Date getDischargeDate() {
		return dischargeDate;
	}

	/**
	 * Set the dischargeDate of DischargeSummary.
	 *
	 * @param dischargeDate the dischargeDate to set
	 */
	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	/**
	 * Get the doctor of DischargeSummary.
	 *
	 * @return the doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * Set the doctor of DischargeSummary.
	 *
	 * @param doctor the doctor to set
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * Get the nurse of DischargeSummary.
	 *
	 * @return the nurse
	 */
	public Nurse getNurse() {
		return nurse;
	}

	/**
	 * Set the nurse of DischargeSummary.
	 *
	 * @param nurse the nurse to set
	 */
	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}

	/**
	 * Get the wardNumber of DischargeSummary.
	 *
	 * @return the wardNumber
	 */
	public String getWardNumber() {
		return wardNumber;
	}

	/**
	 * Set the wardNumber of DischargeSummary.
	 *
	 * @param wardNumber the wardNumber to set
	 */
	public void setWardNumber(String wardNumber) {
		this.wardNumber = wardNumber;
	}

	/**
	 * Get the admittedDate of DischargeSummary.
	 *
	 * @return the admittedDate
	 */
	public Date getAdmittedDate() {
		return admittedDate;
	}

	/**
	 * Set the admittedDate of DischargeSummary.
	 *
	 * @param admittedDate the admittedDate to set
	 */
	public void setAdmittedDate(Date admittedDate) {
		this.admittedDate = admittedDate;
	}
	
}
