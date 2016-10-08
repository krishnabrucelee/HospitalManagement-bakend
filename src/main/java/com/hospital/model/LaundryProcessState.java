package com.hospital.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class LaundryProcessState implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer laundryProcessStateId;
	@Column
	private String washingState;
	@Column
	private String hydroextractorState;
	@Column
	private String dryerState;
	@Column
	private String ironState;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date processTime;
	
	public LaundryProcessState() {
		super();
	}


	public Integer getLaundryProcessStateId() {
		return laundryProcessStateId;
	}


	public void setLaundryProcessStateId(Integer laundryProcessStateId) {
		this.laundryProcessStateId = laundryProcessStateId;
	}


	public String getWashingState() {
		return washingState;
	}


	public void setWashingState(String washingState) {
		this.washingState = washingState;
	}


	public String getHydroextractorState() {
		return hydroextractorState;
	}


	public void setHydroextractorState(String hydroextractorState) {
		this.hydroextractorState = hydroextractorState;
	}


	public String getDryerState() {
		return dryerState;
	}


	public void setDryerState(String dryerState) {
		this.dryerState = dryerState;
	}


	public String getIronState() {
		return ironState;
	}


	public void setIronState(String ironState) {
		this.ironState = ironState;
	}


	public Date getProcessTime() {
		return processTime;
	}


	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}


	
}
