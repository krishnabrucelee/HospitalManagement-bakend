package com.hospital.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class LabtestReportMaster implements Serializable {
		
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long sId;
	@Column
	private Long labtestId;
	@Column 
	private String rangeHigh;
	@Column 
	private String rangelow;
	@Column 
	private String reportName;
	@Column 
	private String reportType;
	@Column 
	private String reportId;
	public Long getsId() {
		return sId;
	}
	public void setsId(Long sId) {
		this.sId = sId;
	}
	public Long getLabtestId() {
		return labtestId;
	}
	public void setLabtestId(Long labtestId) {
		this.labtestId = labtestId;
	}
	public String getRangeHigh() {
		return rangeHigh;
	}
	public void setRangeHigh(String rangeHigh) {
		this.rangeHigh = rangeHigh;
	}
	public String getRangelow() {
		return rangelow;
	}
	public void setRangelow(String rangelow) {
		this.rangelow = rangelow;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	
	
}
