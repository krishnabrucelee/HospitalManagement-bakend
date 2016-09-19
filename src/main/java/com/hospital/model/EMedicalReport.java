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
@Table
public class EMedicalReport implements Serializable {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long emr_id;
	@OneToOne(targetEntity=Patient.class,cascade=CascadeType.ALL)
	@JoinColumn(name="patientt_id",referencedColumnName="patient_id",foreignKey=@ForeignKey(name="patient_EMR_FK"))
	private  Patient  patient;
	private String medicalreport;
	@OneToOne(targetEntity=Doctor.class)
	@JoinColumn(name="doctor_id",referencedColumnName="doctor_id",foreignKey=@ForeignKey(name="doctor_EMR_FK"))
	private  Doctor  doctor;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeshedule;
	public Long getEmr_id() {
		return emr_id;
	}
	public void setEmr_id(Long emr_id) {
		this.emr_id = emr_id;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public String getMedicalreport() {
		return medicalreport;
	}
	public void setMedicalreport(String medicalreport) {
		this.medicalreport = medicalreport;
	}
	
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Date getTimeshedule() {
		return timeshedule;
	}
	public void setTimeshedule(Date timeshedule) {
		this.timeshedule = timeshedule;
	}
	
}
