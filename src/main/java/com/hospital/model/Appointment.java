package com.hospital.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Appointment implements Serializable {
	
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer appoint_id;
	@OneToOne(targetEntity=Doctor.class)
	@JoinColumn(name="doctor_id",referencedColumnName="doctor_id")
	private  Doctor  doctor;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date appoint_time;
	@OneToOne(targetEntity=Patient.class)
	@JoinColumn(name="patient_id",referencedColumnName="patient_id")
	private  Patient  patient;
	@OneToOne(targetEntity=Department.class)
	@JoinColumn(name="department_id",referencedColumnName="department_id")
	private  Department  department;
	private String  purpose;

}
