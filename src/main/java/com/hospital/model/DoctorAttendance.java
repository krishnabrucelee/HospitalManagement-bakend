/**
 * 
 */
package com.hospital.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hospital.util.DateSerializer;

/**
 * @author admin
 *
 */
@Entity
public class DoctorAttendance {


	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="doctor_id")
	private Doctor doctordetails;
	
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using=DateSerializer.class)
	private Date date;
	
	private String shiftType;
	
	private String notes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Doctor getDoctordetails() {
		return doctordetails;
	}

	public void setDoctordetails(Doctor doctordetails) {
		this.doctordetails = doctordetails;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getShiftType() {
		return shiftType;
	}

	public void setShiftType(String shiftType) {
		this.shiftType = shiftType;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
