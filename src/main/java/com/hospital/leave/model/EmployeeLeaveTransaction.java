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
public class EmployeeLeaveTransaction implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;
	@Column
	private Integer employee_id;	
	
	private Date FromDate;
	
	private Date ToDate;
	
	private Integer totaldays;
	
	private Date creationDate;
	
	private Integer leaveConfigurationId;
	
	private String status;
	
	private String actionDate;
	private Integer financialyearid;
	private String reason;
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

	public Integer getTotaldays() {
		return totaldays;
	}

	public void setTotaldays(Integer totaldays) {
		this.totaldays = totaldays;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getLeaveConfigurationId() {
		return leaveConfigurationId;
	}

	public void setLeaveConfigurationId(Integer leaveConfigurationId) {
		this.leaveConfigurationId = leaveConfigurationId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getActionDate() {
		return actionDate;
	}

	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}

	public Integer getFinancialyearid() {
		return financialyearid;
	}

	public void setFinancialyearid(Integer financialyearid) {
		this.financialyearid = financialyearid;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "EmployeeLeaveTransaction [id=" + id + ", employee_id="
				+ employee_id + ", FromDate=" + FromDate + ", ToDate=" + ToDate
				+ ", totaldays=" + totaldays + ", creationDate=" + creationDate
				+ ", leaveConfigurationId=" + leaveConfigurationId
				+ ", status=" + status + ", actionDate=" + actionDate
				+ ", financialyearid=" + financialyearid + ", reason=" + reason
				+ "]";
	}
 

}
