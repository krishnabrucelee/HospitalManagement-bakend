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
@Table(name="patientTreatment")
public class PatientTreatment {

	@Column(name = "patient_treatment_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer patientTreatmentId;
	
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
	 * Get the patientTreatmentId of PatientTreatment.
	 *
	 * @return the patientTreatmentId
	 */
	public Integer getPatientTreatmentId() {
		return patientTreatmentId;
	}

	/**
	 * Set the patientTreatmentId of PatientTreatment.
	 *
	 * @param patientTreatmentId the patientTreatmentId to set
	 */
	public void setPatientTreatmentId(Integer patientTreatmentId) {
		this.patientTreatmentId = patientTreatmentId;
	}

	/**
	 * Get the description of PatientTreatment.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description of PatientTreatment.
	 *
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the reportType of PatientTreatment.
	 *
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * Set the reportType of PatientTreatment.
	 *
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	/**
	 * Get the reportDate of PatientTreatment.
	 *
	 * @return the reportDate
	 */
	public Date getReportDate() {
		return reportDate;
	}

	/**
	 * Set the reportDate of PatientTreatment.
	 *
	 * @param reportDate the reportDate to set
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	/**
	 * Get the reportedBy of PatientTreatment.
	 *
	 * @return the reportedBy
	 */
	public String getReportedBy() {
		return reportedBy;
	}

	/**
	 * Set the reportedBy of PatientTreatment.
	 *
	 * @param reportedBy the reportedBy to set
	 */
	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}

	/**
	 * Get the reportByProfessionType of PatientTreatment.
	 *
	 * @return the reportByProfessionType
	 */
	public String getReportByProfessionType() {
		return reportByProfessionType;
	}

	/**
	 * Set the reportByProfessionType of PatientTreatment.
	 *
	 * @param reportByProfessionType the reportByProfessionType to set
	 */
	public void setReportByProfessionType(String reportByProfessionType) {
		this.reportByProfessionType = reportByProfessionType;
	}
	
}
