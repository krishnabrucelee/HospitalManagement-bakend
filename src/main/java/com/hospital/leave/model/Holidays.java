package com.hospital.leave.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table
@Component
public class Holidays implements Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer holidayId;
	
	@Column
	private String leave_type;
	
	@Column
	private String leave_name;
	
	@Column 
	private String day_name;
	
	@Column
	private Integer day_value;
	
	@Column
	private Integer financialyr_id;
	
	@Column
	private Integer numberofDays;
	
	
	
	@JsonFormat(pattern="dd-MM-yyyy")	
	@Temporal(TemporalType.DATE)
	@Column
	private Date daate;
	
	@Column 
	private String createdby;
	
	public Holidays() {
		super();
	}

	public Integer getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(Integer holidayId) {
		this.holidayId = holidayId;
	}

	public String getLeave_type() {
		return leave_type;
	}
	public void setLeave_type(String leave_type) {
		this.leave_type = leave_type;
	}
	public String getLeave_name() {
		return leave_name;
	}
	public void setLeave_name(String leave_name) {
		this.leave_name = leave_name;
	}
	
	public String getDay_name() {
		return day_name;
	}
	public void setDay_name(String day_name) {
		this.day_name = day_name;
	}
	public Integer getDay_value() {
		return day_value;
	}
	public void setDay_value(Integer day_value) {
		this.day_value = day_value;
	}
	public Integer getFinancialyr_id() {
		return financialyr_id;
	}
	public void setFinancialyr_id(Integer financialyr_id) {
		this.financialyr_id = financialyr_id;
	}
	public Date getDaate() {
		return daate;
	}
	public void setDaate(Date daate) {
		this.daate = daate;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	/**
	 * @return the numberofDays
	 */
	public Integer getNumberofDays() {
		return numberofDays;
	}

	/**
	 * @param numberofDays the numberofDays to set
	 */
	public void setNumberofDays(Integer numberofDays) {
		this.numberofDays = numberofDays;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Holidays [holidayId=" + holidayId + ", leave_type="
				+ leave_type + ", leave_name=" + leave_name + ", day_name="
				+ day_name + ", day_value=" + day_value + ", financialyr_id="
				+ financialyr_id + ", numberofDays=" + numberofDays
				+ ", daate=" + daate + ", createdby=" + createdby + "]";
	}

	
	
	
	
}
