package com.hospital.model;

import java.io.Serializable;

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
@Entity
@Table
public class Timeschedule implements Serializable{
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer timeschdule_id;
	@OneToOne(targetEntity=Doctor.class)
	@JoinColumn(name="doctor_id",referencedColumnName="doctor_id",foreignKey=@ForeignKey(name="doctor_Timeschdule_FK"))
	private  Doctor  doctor;
	@OneToOne(targetEntity=Patient.class)
	@JoinColumn(name="patientt_id",referencedColumnName="patient_id",foreignKey=@ForeignKey(name="patient_Timeschdule_FK"))
	private  Patient  patient;
	public Integer getTimeschdule_id() {
		return timeschdule_id;
	}
	public void setTimeschdule_id(Integer timeschdule_id) {
		this.timeschdule_id = timeschdule_id;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
