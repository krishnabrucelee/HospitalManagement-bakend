package com.hospital.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
public class Appointment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appoint_id")
	private Integer appointId;
	
	@OneToOne(targetEntity = Doctor.class)
	@JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id")
	private Doctor doctor;
	
	@Column(name = "appoint_time")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date appointTime;
	
	@OneToOne(targetEntity = Patient.class)
	@JoinColumn(name = "patient_id", referencedColumnName = "patient_id")
	private Patient patient;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "department_id", referencedColumnName = "department_id")
	private Department department;
	
	@Column(name = "purpose")
	private String purpose;

	/**
	 * Get the appointId of Appointment.
	 *
	 * @return the appointId
	 */
	public Integer getAppointId() {
		return appointId;
	}

	/**
	 * Set the appointId of Appointment.
	 *
	 * @param appointId the appointId to set
	 */
	public void setAppointId(Integer appointId) {
		this.appointId = appointId;
	}

	/**
	 * Get the doctor of Appointment.
	 *
	 * @return the doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * Set the doctor of Appointment.
	 *
	 * @param doctor the doctor to set
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * Get the appointTime of Appointment.
	 *
	 * @return the appointTime
	 */
	public Date getAppointTime() {
		return appointTime;
	}

	/**
	 * Set the appointTime of Appointment.
	 *
	 * @param appointTime the appointTime to set
	 */
	public void setAppointTime(Date appointTime) {
		this.appointTime = appointTime;
	}

	/**
	 * Get the patient of Appointment.
	 *
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Set the patient of Appointment.
	 *
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Get the department of Appointment.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of Appointment.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the purpose of Appointment.
	 *
	 * @return the purpose
	 */
	public String getPurpose() {
		return purpose;
	}

	/**
	 * Set the purpose of Appointment.
	 *
	 * @param purpose the purpose to set
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

}
