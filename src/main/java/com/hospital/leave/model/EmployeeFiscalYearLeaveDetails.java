package com.hospital.leave.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Entity
@Table
@Component
public class EmployeeFiscalYearLeaveDetails implements Serializable {	
	
	/**
	 * 
	 */
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;
	@Column
	private Integer leaveConfiguration_Id; 
	@Column
	private Integer financialyear_id;
	
	@Column
	private Integer remainingdays;
	@Column
	private Integer employee_id;
	@Column
	private Date FromDate;
	@Column
	private Date ToDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLeaveConfiguration_Id() {
		return leaveConfiguration_Id;
	}
	public void setLeaveConfiguration_Id(Integer leaveConfiguration_Id) {
		this.leaveConfiguration_Id = leaveConfiguration_Id;
	}
	public Integer getFinancialyear_id() {
		return financialyear_id;
	}
	public void setFinancialyear_id(Integer financialyear_id) {
		this.financialyear_id = financialyear_id;
	}
	public Integer getRemainingdays() {
		return remainingdays;
	}
	public void setRemainingdays(Integer remainingdays) {
		this.remainingdays = remainingdays;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public Date getFromDate() {
		return FromDate;
	}
	public void setFromDate(Date fromDate) {
		FromDate = fromDate;
	}
	public Date getToDate() {
		return ToDate;
	}
	public void setToDate(Date toDate) {
		ToDate = toDate;
	}
	
	@Override
	public String toString() {
		return "EmployeeFiscalYearLeaveDetails [id=" + id
				+ ", leaveConfiguration_Id=" + leaveConfiguration_Id
				+ ", financialyear_id=" + financialyear_id + ", remainingdays="
				+ remainingdays + ", employee_id=" + employee_id
				+ ", FromDate=" + FromDate + ", ToDate=" + ToDate + "]";
	}
	
}
