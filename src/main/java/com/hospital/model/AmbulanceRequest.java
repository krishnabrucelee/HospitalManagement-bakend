/**
 * 
 */
package com.hospital.model;

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

import com.hospital.model.Cssd.Status;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "ambulanceRequest")
public class AmbulanceRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ambulance_request_id")
	private Integer ambulanceRequestId;
	
	@OneToOne(targetEntity = Ambulance.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ambulance_id", referencedColumnName = "ambulance_id", foreignKey = @ForeignKey(name = "ambulance_AREQ_FK"))
	private Ambulance ambulance;
	
	@OneToOne(targetEntity = Doctor.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", foreignKey = @ForeignKey(name = "doctor_AREQ_FK"))
	private Doctor doctor;
	
	@OneToOne(targetEntity = Driver.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "driver_id", referencedColumnName = "driver_id", foreignKey = @ForeignKey(name = "driver_AREQ_FK"))
	private Driver driver;
	
	@Column(name = "patient_name")
	private String patientName;
	
	@Column(name = "ambulance_description")
	private String patientDescription;
	
	@Column(name = "status")
	private Status status;
	
	public enum Status {
		COMPLETED,
		INCOMPLETE
	}

	/**
	 * Get the ambulanceRequestId of AmbulanceRequest.
	 *
	 * @return the ambulanceRequestId
	 */
	public Integer getAmbulanceRequestId() {
		return ambulanceRequestId;
	}

	/**
	 * Set the ambulanceRequestId of AmbulanceRequest.
	 *
	 * @param ambulanceRequestId the ambulanceRequestId to set
	 */
	public void setAmbulanceRequestId(Integer ambulanceRequestId) {
		this.ambulanceRequestId = ambulanceRequestId;
	}

	/**
	 * Get the ambulance of AmbulanceRequest.
	 *
	 * @return the ambulance
	 */
	public Ambulance getAmbulance() {
		return ambulance;
	}

	/**
	 * Set the ambulance of AmbulanceRequest.
	 *
	 * @param ambulance the ambulance to set
	 */
	public void setAmbulance(Ambulance ambulance) {
		this.ambulance = ambulance;
	}

	/**
	 * Get the doctor of AmbulanceRequest.
	 *
	 * @return the doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * Set the doctor of AmbulanceRequest.
	 *
	 * @param doctor the doctor to set
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * Get the driver of AmbulanceRequest.
	 *
	 * @return the driver
	 */
	public Driver getDriver() {
		return driver;
	}

	/**
	 * Set the driver of AmbulanceRequest.
	 *
	 * @param driver the driver to set
	 */
	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	/**
	 * Get the patientName of AmbulanceRequest.
	 *
	 * @return the patientName
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * Set the patientName of AmbulanceRequest.
	 *
	 * @param patientName the patientName to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * Get the patientDescription of AmbulanceRequest.
	 *
	 * @return the patientDescription
	 */
	public String getPatientDescription() {
		return patientDescription;
	}

	/**
	 * Set the patientDescription of AmbulanceRequest.
	 *
	 * @param patientDescription the patientDescription to set
	 */
	public void setPatientDescription(String patientDescription) {
		this.patientDescription = patientDescription;
	}

	/**
	 * Get the status of AmbulanceRequest.
	 *
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Set the status of AmbulanceRequest.
	 *
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	
}
