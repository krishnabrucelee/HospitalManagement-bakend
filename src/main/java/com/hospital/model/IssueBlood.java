/**
 * 
 */
package com.hospital.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "issueBlood")
public class IssueBlood {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "issue_blood_id")
	private Integer issueBloodId;
	
	@Column(name = "blood_quantity")
	private Integer bloodQuantity;
	
	@Column(name = "blood_group")
	private String bloodGroup;
	
	@Column(name = "blood_unit")
	private Integer bloodUnit;
	
	@OneToOne(targetEntity = Patient.class,cascade=CascadeType.ALL)
	@JoinColumn(name ="patient_id", referencedColumnName="patient_id", foreignKey=@ForeignKey(name="patient_Blood_FK"))
	private Patient patient;
	
	@Column(name = "issue_date")	
	@Temporal(TemporalType.DATE)
	private Date issueDate;

	/**
	 * Get the issueBloodId of IssueBlood.
	 *
	 * @return the issueBloodId
	 */
	public Integer getIssueBloodId() {
		return issueBloodId;
	}

	/**
	 * Set the issueBloodId of IssueBlood.
	 *
	 * @param issueBloodId the issueBloodId to set
	 */
	public void setIssueBloodId(Integer issueBloodId) {
		this.issueBloodId = issueBloodId;
	}

	/**
	 * Get the bloodQuantity of IssueBlood.
	 *
	 * @return the bloodQuantity
	 */
	public Integer getBloodQuantity() {
		return bloodQuantity;
	}

	/**
	 * Set the bloodQuantity of IssueBlood.
	 *
	 * @param bloodQuantity the bloodQuantity to set
	 */
	public void setBloodQuantity(Integer bloodQuantity) {
		this.bloodQuantity = bloodQuantity;
	}

	/**
	 * Get the bloodGroup of IssueBlood.
	 *
	 * @return the bloodGroup
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}

	/**
	 * Set the bloodGroup of IssueBlood.
	 *
	 * @param bloodGroup the bloodGroup to set
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	/**
	 * Get the bloodUnit of IssueBlood.
	 *
	 * @return the bloodUnit
	 */
	public Integer getBloodUnit() {
		return bloodUnit;
	}

	/**
	 * Set the bloodUnit of IssueBlood.
	 *
	 * @param bloodUnit the bloodUnit to set
	 */
	public void setBloodUnit(Integer bloodUnit) {
		this.bloodUnit = bloodUnit;
	}

	/**
	 * Get the patient of IssueBlood.
	 *
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Set the patient of IssueBlood.
	 *
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Get the issueDate of IssueBlood.
	 *
	 * @return the issueDate
	 */
	public Date getIssueDate() {
		return issueDate;
	}

	/**
	 * Set the issueDate of IssueBlood.
	 *
	 * @param issueDate the issueDate to set
	 */
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	
}
