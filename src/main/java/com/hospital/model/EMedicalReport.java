package com.hospital.model;

import java.io.Serializable;
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

@Entity
@Table(name = "eMedicalReport")
public class EMedicalReport implements Serializable {
	
	@Column(name = "emr_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long emrId;
	
	@OneToOne(targetEntity = Patient.class,cascade=CascadeType.ALL)
	@JoinColumn(name ="patient_id", referencedColumnName="patient_id", foreignKey=@ForeignKey(name="patient_EMR_FK"))
	private Patient  patient;
	
	@Column(name = "medical_report")
	private String medicalReport;
	
	@OneToOne(targetEntity = Doctor.class)
	@JoinColumn(name="doctor_id", referencedColumnName="doctor_id", foreignKey=@ForeignKey(name="doctor_EMR_FK"))
	private  Doctor  doctor;
	
	@OneToOne(targetEntity = Laboratory.class)
	@JoinColumn(name = "laboratory_id", referencedColumnName = "laboratory_id", foreignKey = @ForeignKey(name = "laboratory_EMR_FK"))
	private Laboratory laboratory;
	
	@OneToOne(targetEntity = PharmacyMedicine.class)
	@JoinColumn(name = "pharmacy_medicine_id", referencedColumnName = "pharmacy_medicine_id", foreignKey = @ForeignKey(name = "pharmacy_EMR_FK"))
	private PharmacyMedicine department;
	
	@Column(name = "time_shedule")
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeShedule;

	/**
	 * Get the emrId of EMedicalReport.java.
	 *
	 * @return the emrId
	 */
	public Long getEmrId() {
		return emrId;
	}

	/**
	 * Set the emrId of EMedicalReport.java.
	 *
	 * @param emrId the emrId to set
	 */
	public void setEmrId(Long emrId) {
		this.emrId = emrId;
	}

	/**
	 * Get the patient of EMedicalReport.java.
	 *
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Set the patient of EMedicalReport.java.
	 *
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Get the medicalReport of EMedicalReport.java.
	 *
	 * @return the medicalReport
	 */
	public String getMedicalReport() {
		return medicalReport;
	}

	/**
	 * Set the medicalReport of EMedicalReport.java.
	 *
	 * @param medicalReport the medicalReport to set
	 */
	public void setMedicalReport(String medicalReport) {
		this.medicalReport = medicalReport;
	}

	/**
	 * Get the doctor of EMedicalReport.java.
	 *
	 * @return the doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * Set the doctor of EMedicalReport.java.
	 *
	 * @param doctor the doctor to set
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * Get the laboratory of EMedicalReport.java.
	 *
	 * @return the laboratory
	 */
	public Laboratory getLaboratory() {
		return laboratory;
	}

	/**
	 * Set the laboratory of EMedicalReport.java.
	 *
	 * @param laboratory the laboratory to set
	 */
	public void setLaboratory(Laboratory laboratory) {
		this.laboratory = laboratory;
	}

	/**
	 * Get the department of EMedicalReport.java.
	 *
	 * @return the department
	 */
	public PharmacyMedicine getDepartment() {
		return department;
	}

	/**
	 * Set the department of EMedicalReport.java.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(PharmacyMedicine department) {
		this.department = department;
	}

	/**
	 * Get the timeShedule of EMedicalReport.java.
	 *
	 * @return the timeShedule
	 */
	public Date getTimeShedule() {
		return timeShedule;
	}

	/**
	 * Set the timeShedule of EMedicalReport.java.
	 *
	 * @param timeShedule the timeShedule to set
	 */
	public void setTimeShedule(Date timeShedule) {
		this.timeShedule = timeShedule;
	}
	
}
