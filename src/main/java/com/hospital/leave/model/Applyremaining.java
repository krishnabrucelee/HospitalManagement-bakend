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



public class Applyremaining {
	
	private Integer id;
	
	private Integer employee_id;
	
	private Integer financialid;
	
	private Integer leaveconfigid;

	private Long applydays;
	
	private Integer remainingdays;
	private String message;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	
	public Integer getFinancialid() {
		return financialid;
	}
	public void setFinancialid(Integer financialid) {
		this.financialid = financialid;
	}
	public Integer getLeaveconfigid() {
		return leaveconfigid;
	}
	public void setLeaveconfigid(Integer leaveconfigid) {
		this.leaveconfigid = leaveconfigid;
	}
	public Long getApplydays() {
		return applydays;
	}
	public void setApplydays(Long applydays) {
		this.applydays = applydays;
	}
	public Integer getRemainingdays() {
		return remainingdays;
	}
	public void setRemainingdays(Integer remainingdays) {
		this.remainingdays = remainingdays;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
