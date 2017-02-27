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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
@Component
public class Leavedetails implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;
	@Column
	private String leave_name;
	
	@Column
	private Integer allowed_days;
		
	@Column
	private String  leave_days_type;
	
	@Column
	private String pro_leave;
	
	@Column
	private String createdBy;
	
	@Column
	private Integer financialyear_id;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLeave_name() {
		return leave_name;
	}

	public void setLeave_name(String leave_name) {
		this.leave_name = leave_name;
	}

	public Integer getAllowed_days() {
		return allowed_days;
	}

	public void setAllowed_days(Integer allowed_days) {
		this.allowed_days = allowed_days;
	}

	public String getLeave_days_type() {
		return leave_days_type;
	}

	public void setLeave_days_type(String leave_days_type) {
		this.leave_days_type = leave_days_type;
	}

	public String getPro_leave() {
		return pro_leave;
	}

	public void setPro_leave(String pro_leave) {
		this.pro_leave = pro_leave;
	}

	

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getFinancialyear_id() {
		return financialyear_id;
	}

	public void setFinancialyear_id(Integer financialyear_id) {
		this.financialyear_id = financialyear_id;
	}

	@Override
	public String toString() {
		return "Leavedetails [id=" + id + ", leave_name=" + leave_name
				+ ", allowed_days=" + allowed_days + ", leave_days_type="
				+ leave_days_type + ", pro_leave=" + pro_leave + ", createdBy="
				+ createdBy + ", financialyear_id=" + financialyear_id + "]";
	}
	
}
