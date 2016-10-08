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
public class MaintananceScheduleSubCategeries implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer checkCatogeryId;
	@Column
	private String checkCatogeryName;
	
	@Column
	private String checkYes;
	
	@Column
	private String checkedBy;
	
	@Column
	@JsonFormat(pattern="dd-MM-yyyy")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date testDate;
	public MaintananceScheduleSubCategeries() {
		super();
	}

	public Integer getCheckCatogeryId() {
		return checkCatogeryId;
	}

	public void setCheckCatogeryId(Integer checkCatogeryId) {
		this.checkCatogeryId = checkCatogeryId;
	}

	public String getCheckCatogeryName() {
		return checkCatogeryName;
	}

	public void setCheckCatogeryName(String checkCatogeryName) {
		this.checkCatogeryName = checkCatogeryName;
	}

	public String getCheckYes() {
		return checkYes;
	}

	public void setCheckYes(String checkYes) {
		this.checkYes = checkYes;
	}

	public String getCheckedBy() {
		return checkedBy;
	}

	public void setCheckedBy(String checkedBy) {
		this.checkedBy = checkedBy;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	
}
