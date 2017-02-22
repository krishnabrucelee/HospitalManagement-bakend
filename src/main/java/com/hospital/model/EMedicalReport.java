package com.hospital.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "eMedicalReport")
public class EMedicalReport implements Serializable {
	
	@Column(name = "emr_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer emrId;
	
	@OneToOne(targetEntity = Patient.class,cascade=CascadeType.ALL)
	@JoinColumn(name ="patient_id", referencedColumnName="patient_id", foreignKey=@ForeignKey(name="patient_EMR_FK"))
	private Patient  patient;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<PatientDiagnosis> patientDiagnosis;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<PatientTreatment> patientTreatment;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<PatientExamination> patientExamination;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<PatientHistory> patientHistory;
	
	@OneToOne(targetEntity = Doctor.class)
	@JoinColumn(name="doctor_id", referencedColumnName="doctor_id", foreignKey=@ForeignKey(name="doctor_EMR_FK"))
	private  Doctor  doctor;
	
	@OneToOne(targetEntity = Nurse.class)
	@JoinColumn(name="nurse_id", referencedColumnName="nurse_id", foreignKey=@ForeignKey(name="nurse_EMR_FK"))
	private  Nurse  nurse;
	
	@OneToOne(targetEntity = Laboratory.class)
	@JoinColumn(name = "laboratory_id", referencedColumnName = "laboratory_id", foreignKey = @ForeignKey(name = "laboratory_EMR_FK"))
	private Laboratory laboratory;
	
	@OneToOne(targetEntity = PharmacyMedicine.class)
	@JoinColumn(name = "pharmacy_medicine_id", referencedColumnName = "pharmacy_medicine_id", foreignKey = @ForeignKey(name = "pharmacy_EMR_FK"))
	private PharmacyMasterEntry department;
	
	@Column(name = "time_shedule")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeShedule;

	/**
	 * Get the emrId of EMedicalReport.
	 *
	 * @return the emrId
	 */
	public Integer getEmrId() {
		return emrId;
	}

	/**
	 * Set the emrId of EMedicalReport.
	 *
	 * @param emrId the emrId to set
	 */
	public void setEmrId(Integer emrId) {
		this.emrId = emrId;
	}

	/**
	 * Get the patient of EMedicalReport.
	 *
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Set the patient of EMedicalReport.
	 *
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Get the doctor of EMedicalReport.
	 *
	 * @return the doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * Set the doctor of EMedicalReport.
	 *
	 * @param doctor the doctor to set
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * Get the laboratory of EMedicalReport.
	 *
	 * @return the laboratory
	 */
	public Laboratory getLaboratory() {
		return laboratory;
	}

	/**
	 * Set the laboratory of EMedicalReport.
	 *
	 * @param laboratory the laboratory to set
	 */
	public void setLaboratory(Laboratory laboratory) {
		this.laboratory = laboratory;
	}

	/**
	 * Get the department of EMedicalReport.
	 *
	 * @return the department
	 */
	public PharmacyMedicine getDepartment() {
		return department;
	}

	/**
	 * Set the department of EMedicalReport.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(PharmacyMedicine department) {
		this.department = department;
	}

	/**
	 * Get the timeShedule of EMedicalReport.
	 *
	 * @return the timeShedule
	 */
	public Date getTimeShedule() {
		return timeShedule;
	}

	/**
	 * Set the timeShedule of EMedicalReport.
	 *
	 * @param timeShedule the timeShedule to set
	 */
	public void setTimeShedule(Date timeShedule) {
		this.timeShedule = timeShedule;
	}

	/**
	 * Get the patientDiagnosis of EMedicalReport.
	 *
	 * @return the patientDiagnosis
	 */
	public List<PatientDiagnosis> getPatientDiagnosis() {
		return patientDiagnosis;
	}

	/**
	 * Set the patientDiagnosis of EMedicalReport.
	 *
	 * @param patientDiagnosis the patientDiagnosis to set
	 */
	public void setPatientDiagnosis(List<PatientDiagnosis> patientDiagnosis) {
		this.patientDiagnosis = patientDiagnosis;
	}

	/**
	 * Get the patientTreatment of EMedicalReport.
	 *
	 * @return the patientTreatment
	 */
	public List<PatientTreatment> getPatientTreatment() {
		return patientTreatment;
	}

	/**
	 * Set the patientTreatment of EMedicalReport.
	 *
	 * @param patientTreatment the patientTreatment to set
	 */
	public void setPatientTreatment(List<PatientTreatment> patientTreatment) {
		this.patientTreatment = patientTreatment;
	}

	/**
	 * Get the patientExamination of EMedicalReport.
	 *
	 * @return the patientExamination
	 */
	public List<PatientExamination> getPatientExamination() {
		return patientExamination;
	}

	/**
	 * Set the patientExamination of EMedicalReport.
	 *
	 * @param patientExamination the patientExamination to set
	 */
	public void setPatientExamination(List<PatientExamination> patientExamination) {
		this.patientExamination = patientExamination;
	}

	/**
	 * Get the patientHistory of EMedicalReport.
	 *
	 * @return the patientHistory
	 */
	public List<PatientHistory> getPatientHistory() {
		return patientHistory;
	}

	/**
	 * Set the patientHistory of EMedicalReport.
	 *
	 * @param patientHistory the patientHistory to set
	 */
	public void setPatientHistory(List<PatientHistory> patientHistory) {
		this.patientHistory = patientHistory;
	}

	/**
	 * Get the nurse of EMedicalReport.
	 *
	 * @return the nurse
	 */
	public Nurse getNurse() {
		return nurse;
	}

	/**
	 * Set the nurse of EMedicalReport.
	 *
	 * @param nurse the nurse to set
	 */
	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}

}
