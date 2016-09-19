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
public class InPatient implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer inpatient_id;
	@OneToOne(targetEntity=Patient.class,cascade=CascadeType.ALL)
	@JoinColumn(name="patientt_id",referencedColumnName="patient_id",foreignKey=@ForeignKey(name="patient_Inpatient_FK"))
	private  Patient  patient;
	@Column
	private Integer patient_roomno;
	
	@Column
	private String doctorreference;
	@Column
	private String nursedetails;
	@Column
	private String emr;
	
	public Integer getInpatient_id() {
		return inpatient_id;
	}
	public void setInpatient_id(Integer inpatient_id) {
		this.inpatient_id = inpatient_id;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Integer getPatient_roomno() {
		return patient_roomno;
	}
	public void setPatient_roomno(Integer patient_roomno) {
		this.patient_roomno = patient_roomno;
	}
	public String getDoctorreference() {
		return doctorreference;
	}
	public void setDoctorreference(String doctorreference) {
		this.doctorreference = doctorreference;
	}
	public String getNursedetails() {
		return nursedetails;
	}
	public void setNursedetails(String nursedetails) {
		this.nursedetails = nursedetails;
	}
	public String getEmr() {
		return emr;
	}
	public void setEmr(String emr) {
		this.emr = emr;
	}
	

}
