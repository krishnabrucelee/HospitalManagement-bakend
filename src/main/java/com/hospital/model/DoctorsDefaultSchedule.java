package com.hospital.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hospital.util.TimeSerializer;

@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
public class DoctorsDefaultSchedule {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int doctors_default_schedule_id;
	
	@JsonFormat(pattern="HH:mm",timezone="UTC")
	@Temporal(TemporalType.TIME)
	@JsonSerialize(using=TimeSerializer.class)
	private Date startTime;
	
	@JsonFormat(pattern="HH:mm",timezone="UTC")
	@Temporal(TemporalType.TIME)
	@JsonSerialize(using=TimeSerializer.class)
	private Date endTime;
	
	@Enumerated(EnumType.STRING)
	private DefaultSchedule scheduleName;
	
	@ManyToOne
	@JoinColumn(name="doctorId",foreignKey=@ForeignKey(name="DoctorsDefaultSchedule_Doctor_UK"))
	private Doctor doctorDetails = new Doctor();

	public int getDoctors_default_schedule_id() {
		return doctors_default_schedule_id;
	}

	public void setDoctors_default_schedule_id(int doctors_default_schedule_id) {
		this.doctors_default_schedule_id = doctors_default_schedule_id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public DefaultSchedule getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(DefaultSchedule scheduleName) {
		this.scheduleName = scheduleName;
	}

	public Doctor getDoctorDetails() {
		return doctorDetails;
	}

	public void setDoctorDetails(Doctor doctorDetails) {
		this.doctorDetails = doctorDetails;
	}
	
}

enum DefaultSchedule{
	WORKING_HOURS,BREAK_TIME, LUNCH_TIME;
}

