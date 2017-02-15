package com.hospital.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
public class DoctorAppointment {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int doctor_availability_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="dd-MM-yyyy HH:mm")
	private Date starttime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="dd-MM-yyyy HH:mm")
	private Date endtime;
		
	private String appointment_title;
	
	private String booking_status;	
	
	private String notes;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="doctor_details_id",foreignKey=@ForeignKey(name="DoctorAppointment_DoctorDetails_FK"))
	private Doctor doctorDetails;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="patient_id",foreignKey=@ForeignKey(name="DoctorAppointment_PatientDetails_FK"))	
	private Patient patientDetails;

	public int getDoctor_availability_id() {
		return doctor_availability_id;
	}

	public void setDoctor_availability_id(int doctor_availability_id) {
		this.doctor_availability_id = doctor_availability_id;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}	

	public String getBooking_status() {
		return booking_status;
	}

	public void setBooking_status(String booking_status) {
		this.booking_status = booking_status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getAppointment_title() {
		return appointment_title;
	}

	public void setAppointment_title(String appointment_title) {
		this.appointment_title = appointment_title;
	}

	public Doctor getDoctorDetails() {
		return doctorDetails;
	}

	public void setDoctorDetails(Doctor doctorDetails) {
		this.doctorDetails = doctorDetails;
	}

	public Patient getPatientDetails() {
		return patientDetails;
	}

	public void setPatientDetails(Patient patientDetails) {
		this.patientDetails = patientDetails;
	}
	
}
