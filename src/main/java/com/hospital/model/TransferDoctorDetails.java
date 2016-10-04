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
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "transferDoctorDetails")
public class TransferDoctorDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "transfer_doctor_id")
	private Integer transferDoctorId;
	
	@Column(name = "transfer_doctor_name")
	private String transferDoctorName;
	
	@Column(name = "transfer_doctor_description")
	private String transferDoctorDescription;

	@OneToOne(targetEntity = Doctor.class)
	@JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", foreignKey = @ForeignKey(name = "doctor_TRAN_DOC_FK"))
	private Doctor doctor;
	
	@OneToOne(targetEntity = Patient.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", referencedColumnName = "patient_id", foreignKey = @ForeignKey(name = "patient_TRAN_DOC_FK"))
	private Patient patient;
	
	@Column(name = "appointment_start_time")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date appointmentStartTime;
	
	@Column(name = "appointment_end_time")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date appointmentEndTime;

	/**
	 * Get the transferDoctorId of TransferDoctorDetails.
	 *
	 * @return the transferDoctorId
	 */
	public Integer getTransferDoctorId() {
		return transferDoctorId;
	}

	/**
	 * Set the transferDoctorId of TransferDoctorDetails.
	 *
	 * @param transferDoctorId the transferDoctorId to set
	 */
	public void setTransferDoctorId(Integer transferDoctorId) {
		this.transferDoctorId = transferDoctorId;
	}

	/**
	 * Get the transferDoctorName of TransferDoctorDetails.
	 *
	 * @return the transferDoctorName
	 */
	public String getTransferDoctorName() {
		return transferDoctorName;
	}

	/**
	 * Set the transferDoctorName of TransferDoctorDetails.
	 *
	 * @param transferDoctorName the transferDoctorName to set
	 */
	public void setTransferDoctorName(String transferDoctorName) {
		this.transferDoctorName = transferDoctorName;
	}

	/**
	 * Get the transferDoctorDescription of TransferDoctorDetails.
	 *
	 * @return the transferDoctorDescription
	 */
	public String getTransferDoctorDescription() {
		return transferDoctorDescription;
	}

	/**
	 * Set the transferDoctorDescription of TransferDoctorDetails.
	 *
	 * @param transferDoctorDescription the transferDoctorDescription to set
	 */
	public void setTransferDoctorDescription(String transferDoctorDescription) {
		this.transferDoctorDescription = transferDoctorDescription;
	}

	/**
	 * Get the doctor of TransferDoctorDetails.
	 *
	 * @return the doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * Set the doctor of TransferDoctorDetails.
	 *
	 * @param doctor the doctor to set
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * Get the patient of TransferDoctorDetails.
	 *
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Set the patient of TransferDoctorDetails.
	 *
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Get the appointmentStartTime of TransferDoctorDetails.
	 *
	 * @return the appointmentStartTime
	 */
	public Date getAppointmentStartTime() {
		return appointmentStartTime;
	}

	/**
	 * Set the appointmentStartTime of TransferDoctorDetails.
	 *
	 * @param appointmentStartTime the appointmentStartTime to set
	 */
	public void setAppointmentStartTime(Date appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}

	/**
	 * Get the appointmentEndTime of TransferDoctorDetails.
	 *
	 * @return the appointmentEndTime
	 */
	public Date getAppointmentEndTime() {
		return appointmentEndTime;
	}

	/**
	 * Set the appointmentEndTime of TransferDoctorDetails.
	 *
	 * @param appointmentEndTime the appointmentEndTime to set
	 */
	public void setAppointmentEndTime(Date appointmentEndTime) {
		this.appointmentEndTime = appointmentEndTime;
	}
}
