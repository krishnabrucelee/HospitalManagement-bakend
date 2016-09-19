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
public class Outpatient implements Serializable{
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long outpatient_id;
	@OneToOne(targetEntity=Patient.class)
	@JoinColumn(name="patientt_id",referencedColumnName="patient_id",foreignKey=@ForeignKey(name="patient_Outpatient_FK"))
	private  Patient  patient;
	@OneToOne(targetEntity=MedicalReport.class)
	@JoinColumn(name="medicalreport_id",referencedColumnName="medicalreport_id",foreignKey=@ForeignKey(name="patient_EMR_FK"))
	private  MedicalReport  medicalreport;
	public Long getOutpatient_id() {
		return outpatient_id;
	}
	public void setOutpatient_id(Long outpatient_id) {
		this.outpatient_id = outpatient_id;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public MedicalReport getMedicalreport() {
		return medicalreport;
	}
	public void setMedicalreport(MedicalReport medicalreport) {
		this.medicalreport = medicalreport;
	}
	
	
}
