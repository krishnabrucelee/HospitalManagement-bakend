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
public class OrderRepairProduct implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer repairOrderid;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date maintananceDate;
	@Column
	private String repairRequest;
	@Column
	private String  status;
	@Column
	private String  repairPerson;
	@Column
	private String replacementPart;
	
	@Column
	@JsonFormat(pattern="dd-MM-yyyy")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderIssueDate;
	@Column
	private String  statusBB;
	public OrderRepairProduct() {
		super();
	}

	public Integer getRepairOrderid() {
		return repairOrderid;
	}

	public void setRepairOrderid(Integer repairOrderid) {
		this.repairOrderid = repairOrderid;
	}

	public Date getMaintananceDate() {
		return maintananceDate;
	}

	public void setMaintananceDate(Date maintananceDate) {
		this.maintananceDate = maintananceDate;
	}

	public String getRepairRequest() {
		return repairRequest;
	}

	public void setRepairRequest(String repairRequest) {
		this.repairRequest = repairRequest;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRepairPerson() {
		return repairPerson;
	}

	public void setRepairPerson(String repairPerson) {
		this.repairPerson = repairPerson;
	}

	public String getReplacementPart() {
		return replacementPart;
	}

	public void setReplacementPart(String replacementPart) {
		this.replacementPart = replacementPart;
	}

	public Date getOrderIssueDate() {
		return orderIssueDate;
	}

	public void setOrderIssueDate(Date orderIssueDate) {
		this.orderIssueDate = orderIssueDate;
	}

	public String getStatusBB() {
		return statusBB;
	}

	public void setStatusBB(String statusBB) {
		this.statusBB = statusBB;
	}
	
}
