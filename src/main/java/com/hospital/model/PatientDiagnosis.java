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
@Table(name="patientDiagnosis")
public class PatientDiagnosis {

	@Column(name = "patient_diagnosis_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer patientDiagnosisId;
	
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
	 * Get the patientDiagnosisId of PatientDiagnosis.
	 *
	 * @return the patientDiagnosisId
	 */
	public Integer getPatientDiagnosisId() {
		return patientDiagnosisId;
	}

	/**
	 * Set the patientDiagnosisId of PatientDiagnosis.
	 *
	 * @param patientDiagnosisId the patientDiagnosisId to set
	 */
	public void setPatientDiagnosisId(Integer patientDiagnosisId) {
		this.patientDiagnosisId = patientDiagnosisId;
	}

	/**
	 * Get the description of PatientDiagnosis.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description of PatientDiagnosis.
	 *
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the reportType of PatientDiagnosis.
	 *
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * Set the reportType of PatientDiagnosis.
	 *
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	/**
	 * Get the reportDate of PatientDiagnosis.
	 *
	 * @return the reportDate
	 */
	public Date getReportDate() {
		return reportDate;
	}

	/**
	 * Set the reportDate of PatientDiagnosis.
	 *
	 * @param reportDate the reportDate to set
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	/**
	 * Get the reportedBy of PatientDiagnosis.
	 *
	 * @return the reportedBy
	 */
	public String getReportedBy() {
		return reportedBy;
	}

	/**
	 * Set the reportedBy of PatientDiagnosis.
	 *
	 * @param reportedBy the reportedBy to set
	 */
	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}

	/**
	 * Get the reportByProfessionType of PatientDiagnosis.
	 *
	 * @return the reportByProfessionType
	 */
	public String getReportByProfessionType() {
		return reportByProfessionType;
	}

	/**
	 * Set the reportByProfessionType of PatientDiagnosis.
	 *
	 * @param reportByProfessionType the reportByProfessionType to set
	 */
	public void setReportByProfessionType(String reportByProfessionType) {
		this.reportByProfessionType = reportByProfessionType;
	}
	
	
}
