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
public class MedicalReport implements Serializable {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long medicalreport_id;
	
	private String departmentname;
	@OneToOne(targetEntity=Doctor.class)
	@JoinColumn(name="doctor_id",referencedColumnName="doctor_id",foreignKey=@ForeignKey(name="doctor_MedicalReport_FK"))
	private  Doctor  doctor;
	@OneToOne(targetEntity=Patient.class)
	@JoinColumn(name="patientt_id",referencedColumnName="patient_id",foreignKey=@ForeignKey(name="patient_MedicalReport_FK"))
	private  Patient  patient;
	private String recordtype;
	private String reoportdata;
	
	public Long getMedicalreport_id() {
		return medicalreport_id;
	}
	public void setMedicalreport_id(Long medicalreport_id) {
		this.medicalreport_id = medicalreport_id;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
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
	public String getRecordtype() {
		return recordtype;
	}
	public void setRecordtype(String recordtype) {
		this.recordtype = recordtype;
	}
	public String getReoportdata() {
		return reoportdata;
	}
	public void setReoportdata(String reoportdata) {
		this.reoportdata = reoportdata;
	}
	
}
