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
@Table(name = "surgery")
public class Surgery {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "surgery_id")
	private Long surgeryId;
	
	@OneToOne(targetEntity = Patient.class)
	@JoinColumn(name = "patient_id", referencedColumnName = "patient_id", foreignKey = @ForeignKey(name = "patient_SUR_FK"))
	private Patient patient;
	
	@OneToOne(targetEntity = Doctor.class)
	@JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", foreignKey = @ForeignKey(name = "doctor_SUR_FK"))
	private Doctor doctor;
	
	@OneToOne(targetEntity = Nurse.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "nurse_id", referencedColumnName = "nurse_id", foreignKey = @ForeignKey(name = "nurse_SUR_FK"))
	private Nurse nurse;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_SUR_FK"))
	private Department department;
	
	@Column(name = "surgery_date")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date surgeryDate;
	
	@Column(name = "surgery_description")
	private String surgeryDescription;

	/**
	 * Get the surgeryId of Surgery.
	 *
	 * @return the surgeryId
	 */
	public Long getSurgeryId() {
		return surgeryId;
	}

	/**
	 * Set the surgeryId of Surgery.
	 *
	 * @param surgeryId the surgeryId to set
	 */
	public void setSurgeryId(Long surgeryId) {
		this.surgeryId = surgeryId;
	}

	/**
	 * Get the patient of Surgery.
	 *
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Set the patient of Surgery.
	 *
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Get the doctor of Surgery.
	 *
	 * @return the doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * Set the doctor of Surgery.
	 *
	 * @param doctor the doctor to set
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * Get the nurse of Surgery.
	 *
	 * @return the nurse
	 */
	public Nurse getNurse() {
		return nurse;
	}

	/**
	 * Set the nurse of Surgery.
	 *
	 * @param nurse the nurse to set
	 */
	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}

	/**
	 * Get the department of Surgery.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of Surgery.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the surgeryDate of Surgery.
	 *
	 * @return the surgeryDate
	 */
	public Date getSurgeryDate() {
		return surgeryDate;
	}

	/**
	 * Set the surgeryDate of Surgery.
	 *
	 * @param surgeryDate the surgeryDate to set
	 */
	public void setSurgeryDate(Date surgeryDate) {
		this.surgeryDate = surgeryDate;
	}

	/**
	 * Get the surgeryDescription of Surgery.
	 *
	 * @return the surgeryDescription
	 */
	public String getSurgeryDescription() {
		return surgeryDescription;
	}

	/**
	 * Set the surgeryDescription of Surgery.
	 *
	 * @param surgeryDescription the surgeryDescription to set
	 */
	public void setSurgeryDescription(String surgeryDescription) {
		this.surgeryDescription = surgeryDescription;
	}
	
}
