package com.hospital.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="medicineToPatient")
public class MedicineToPatient implements Serializable {	
	@Column(name="medicine_issueid")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer medicineIssueId;
	
	@Column(name="medicine_id")
	private Integer medicineId;	
	
	@Column(name="medicine_name")
	private String medicineName;
	
	@Column(name="medicine_batchid")
	private String medicineBatchId;
	
	@Column(name="medicine_composition")
	private String medicineComposition;
	
	@Column(name="medicine_count")
	private Integer medicineCount;
	
	@Column(name="issue_time")
	@JsonFormat(pattern="dd-MM-yyyy hh:mm:ss a")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date issueTime;
	
	@Column(name="medicine_type")
	private String medicineType;
	
	@ManyToOne(targetEntity=Patient.class)
	@JoinColumn(name="patient_id",referencedColumnName="patient_id",foreignKey=@ForeignKey(name="patient_Medicine_FK"))
	private Patient patient;
	
	public Integer getMedicineIssueId() {
		return medicineIssueId;
	}
	public void setMedicineIssueId(Integer medicineIssueId) {
		this.medicineIssueId = medicineIssueId;
	}
	public Integer getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public Integer getMedicineCount() {
		return medicineCount;
	}
	public void setMedicineCount(Integer medicineCount) {
		this.medicineCount = medicineCount;
	}
	public String getMedicineBatchId() {
		return medicineBatchId;
	}
	public void setMedicineBatchId(String medicineBatchId) {
		this.medicineBatchId = medicineBatchId;
	}
	public String getMedicineComposition() {
		return medicineComposition;
	}
	public void setMedicineComposition(String medicineComposition) {
		this.medicineComposition = medicineComposition;
	}
	public String getMedicineType() {
		return medicineType;
	}
	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Date getIssueTime() {
		return issueTime;
	}
	public void setIssueTime(Date issueTime) {
		this.issueTime = issueTime;
	}
	
}
