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
public class HolidaysDetails implements Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;
	@Column
	private String leave_type;
	@Column
	private String leave_name;
	@Column
	private Integer financialyr_id;
	@JsonFormat(pattern="dd-MM-yyyy")	
	@Temporal(TemporalType.DATE)
	@Column
	private Date fromDate;
	@JsonFormat(pattern="dd-MM-yyyy")	
	@Temporal(TemporalType.DATE)
	@Column
	private Date toDate;	
	@Column 
	private String createdby;
	
	@Column
	private Integer numberofDays;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getFinancialyr_id() {
		return financialyr_id;
	}
	public void setFinancialyr_id(Integer financialyr_id) {
		this.financialyr_id = financialyr_id;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	
	public Integer getNumberofDays() {
		return numberofDays;
	}
	public void setNumberofDays(Integer numberofDays) {
		this.numberofDays = numberofDays;
	}
	
}
