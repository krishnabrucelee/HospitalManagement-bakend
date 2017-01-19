/**
 * 
 */
package com.hospital.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name="patientHistory")
public class PatientHistory {

	@Column(name = "patient_history_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer patientHistoryId;
	
	@Column(name="description")
	private String description;
	
	@Column(name="report_type")
	private String reportType;
	
	@Column(name="report_by")
	private String reportedBy;
	
	@Column(name="report_by_profession_type")
	private String reportByProfessionType;
	
	@Column(name = "report_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date reportDate;

	/**
	 * Get the patientHistoryId of PatientHistory.
	 *
	 * @return the patientHistoryId
	 */
	public Integer getPatientHistoryId() {
		return patientHistoryId;
	}

	/**
	 * Set the patientHistoryId of PatientHistory.
	 *
	 * @param patientHistoryId the patientHistoryId to set
	 */
	public void setPatientHistoryId(Integer patientHistoryId) {
		this.patientHistoryId = patientHistoryId;
	}

	/**
	 * Get the description of PatientHistory.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description of PatientHistory.
	 *
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the reportType of PatientHistory.
	 *
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * Set the reportType of PatientHistory.
	 *
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	/**
	 * Get the reportDate of PatientHistory.
	 *
	 * @return the reportDate
	 */
	public Date getReportDate() {
		return reportDate;
	}

	/**
	 * Set the reportDate of PatientHistory.
	 *
	 * @param reportDate the reportDate to set
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	/**
	 * Get the reportedBy of PatientHistory.
	 *
	 * @return the reportedBy
	 */
	public String getReportedBy() {
		return reportedBy;
	}

	/**
	 * Set the reportedBy of PatientHistory.
	 *
	 * @param reportedBy the reportedBy to set
	 */
	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}

	/**
	 * Get the reportByProfessionType of PatientHistory.
	 *
	 * @return the reportByProfessionType
	 */
	public String getReportByProfessionType() {
		return reportByProfessionType;
	}

	/**
	 * Set the reportByProfessionType of PatientHistory.
	 *
	 * @param reportByProfessionType the reportByProfessionType to set
	 */
	public void setReportByProfessionType(String reportByProfessionType) {
		this.reportByProfessionType = reportByProfessionType;
	}
	
}
