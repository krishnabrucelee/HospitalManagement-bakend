package com.hospital.model;

import java.io.Serializable;
import java.util.Date;
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
 * 
 * @author Krishna
 *
 */
@Entity
@Table (name = "doctor")
public class Doctor implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "doctor_id")
	private Integer doctorId;
	
	@Column(name = "doctor_name")
	private String doctorName;
	
	@Column(name = "doctor_description")
	private String doctorDescription;
	
	@Column(name = "doctor_age")
	private Integer doctorAge;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_doctor_FK"))
	private Department department;
	
	@Column(name = "appointment_start_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date appointmentStartTime;
	
	@Column(name = "appointment_end_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date appointmentEndTime;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "personal_details")
	private String personalDetails;

	/**
	 * Get the doctorId of Doctor.
	 *
	 * @return the doctorId
	 */
	public Integer getDoctorId() {
		return doctorId;
	}

	/**
	 * Set the doctorId of Doctor.
	 *
	 * @param doctorId the doctorId to set
	 */
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	/**
	 * Get the doctorName of Doctor.
	 *
	 * @return the doctorName
	 */
	public String getDoctorName() {
		return doctorName;
	}

	/**
	 * Set the doctorName of Doctor.
	 *
	 * @param doctorName the doctorName to set
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	/**
	 * Get the doctorDescription of Doctor.
	 *
	 * @return the doctorDescription
	 */
	public String getDoctorDescription() {
		return doctorDescription;
	}

	/**
	 * Set the doctorDescription of Doctor.
	 *
	 * @param doctorDescription the doctorDescription to set
	 */
	public void setDoctorDescription(String doctorDescription) {
		this.doctorDescription = doctorDescription;
	}

	/**
	 * Get the doctorAge of Doctor.
	 *
	 * @return the doctorAge
	 */
	public Integer getDoctorAge() {
		return doctorAge;
	}

	/**
	 * Set the doctorAge of Doctor.
	 *
	 * @param doctorAge the doctorAge to set
	 */
	public void setDoctorAge(Integer doctorAge) {
		this.doctorAge = doctorAge;
	}

	/**
	 * Get the department of Doctor.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of Doctor.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the appointmentStartTime of Doctor.
	 *
	 * @return the appointmentStartTime
	 */
	public Date getAppointmentStartTime() {
		return appointmentStartTime;
	}

	/**
	 * Set the appointmentStartTime of Doctor.
	 *
	 * @param appointmentStartTime the appointmentStartTime to set
	 */
	public void setAppointmentStartTime(Date appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}

	/**
	 * Get the appointmentEndTime of Doctor.
	 *
	 * @return the appointmentEndTime
	 */
	public Date getAppointmentEndTime() {
		return appointmentEndTime;
	}

	/**
	 * Set the appointmentEndTime of Doctor.
	 *
	 * @param appointmentEndTime the appointmentEndTime to set
	 */
	public void setAppointmentEndTime(Date appointmentEndTime) {
		this.appointmentEndTime = appointmentEndTime;
	}

	/**
	 * Get the gender of Doctor.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Set the gender of Doctor.
	 *
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Get the personalDetails of Doctor.
	 *
	 * @return the personalDetails
	 */
	public String getPersonalDetails() {
		return personalDetails;
	}

	/**
	 * Set the personalDetails of Doctor.
	 *
	 * @param personalDetails the personalDetails to set
	 */
	public void setPersonalDetails(String personalDetails) {
		this.personalDetails = personalDetails;
	}

}
