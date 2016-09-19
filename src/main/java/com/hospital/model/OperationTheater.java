package com.hospital.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class OperationTheater implements Serializable{
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long emr_id;
	@OneToOne(targetEntity=Patient.class)
	@JoinColumn(name="patientt_id",referencedColumnName="patient_id",foreignKey=@ForeignKey(name="patient_OperationTheater_FK"))
	private  Patient  patient;
	@OneToOne(targetEntity=Doctor.class)
	@JoinColumn(name="doctor_id",referencedColumnName="doctor_id",foreignKey=@ForeignKey(name="doctor_OperationTheater_FK"))
	private  Doctor  doctor;
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
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
}
