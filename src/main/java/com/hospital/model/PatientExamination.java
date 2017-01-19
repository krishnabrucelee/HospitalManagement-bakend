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
@Table(name="patientExamination")
public class PatientExamination {

	@Column(name = "patient_examination_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer patientExaminationId;
	
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
	 * Get the patientExaminationId of PatientExamination.
	 *
	 * @return the patientExaminationId
	 */
	public Integer getPatientExaminationId() {
		return patientExaminationId;
	}

	/**
	 * Set the patientExaminationId of PatientExamination.
	 *
	 * @param patientExaminationId the patientExaminationId to set
	 */
	public void setPatientExaminationId(Integer patientExaminationId) {
		this.patientExaminationId = patientExaminationId;
	}

	/**
	 * Get the description of PatientExamination.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description of PatientExamination.
	 *
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the reportType of PatientExamination.
	 *
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * Set the reportType of PatientExamination.
	 *
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	/**
	 * Get the reportDate of PatientExamination.
	 *
	 * @return the reportDate
	 */
	public Date getReportDate() {
		return reportDate;
	}

	/**
	 * Set the reportDate of PatientExamination.
	 *
	 * @param reportDate the reportDate to set
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	/**
	 * Get the reportedBy of PatientExamination.
	 *
	 * @return the reportedBy
	 */
	public String getReportedBy() {
		return reportedBy;
	}

	/**
	 * Set the reportedBy of PatientExamination.
	 *
	 * @param reportedBy the reportedBy to set
	 */
	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}

	/**
	 * Get the reportByProfessionType of PatientExamination.
	 *
	 * @return the reportByProfessionType
	 */
	public String getReportByProfessionType() {
		return reportByProfessionType;
	}

	/**
	 * Set the reportByProfessionType of PatientExamination.
	 *
	 * @param reportByProfessionType the reportByProfessionType to set
	 */
	public void setReportByProfessionType(String reportByProfessionType) {
		this.reportByProfessionType = reportByProfessionType;
	}
	
}
