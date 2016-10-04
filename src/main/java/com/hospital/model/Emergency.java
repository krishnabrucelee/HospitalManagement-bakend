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

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "emergency")
public class Emergency {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="emergency_id")
	private Integer emergencyId;
	
	@Column(name = "admitted_date")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date admittedDate;
	
	@Column(name = "problem_description")
	private String problemDescription;
	
	@Column(name = "guardian_name")
	private String guardianName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "guardian_signature")
	private String guardianSignature;
	
	@OneToOne(targetEntity = InPatient.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "in_patient_id", referencedColumnName = "in_patient_id", foreignKey = @ForeignKey(name = "in_patient_EMRG_FK"))
	private InPatient inPatient;

	/**
	 * Get the emergencyId of Emergency.
	 *
	 * @return the emergencyId
	 */
	public Integer getEmergencyId() {
		return emergencyId;
	}

	/**
	 * Set the emergencyId of Emergency.
	 *
	 * @param emergencyId the emergencyId to set
	 */
	public void setEmergencyId(Integer emergencyId) {
		this.emergencyId = emergencyId;
	}

	/**
	 * Get the admittedDate of Emergency.
	 *
	 * @return the admittedDate
	 */
	public Date getAdmittedDate() {
		return admittedDate;
	}

	/**
	 * Set the admittedDate of Emergency.
	 *
	 * @param admittedDate the admittedDate to set
	 */
	public void setAdmittedDate(Date admittedDate) {
		this.admittedDate = admittedDate;
	}

	/**
	 * Get the problemDescription of Emergency.
	 *
	 * @return the problemDescription
	 */
	public String getProblemDescription() {
		return problemDescription;
	}

	/**
	 * Set the problemDescription of Emergency.
	 *
	 * @param problemDescription the problemDescription to set
	 */
	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

	/**
	 * Get the guardianName of Emergency.
	 *
	 * @return the guardianName
	 */
	public String getGuardianName() {
		return guardianName;
	}

	/**
	 * Set the guardianName of Emergency.
	 *
	 * @param guardianName the guardianName to set
	 */
	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	/**
	 * Get the address of Emergency.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set the address of Emergency.
	 *
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Get the guardianSignature of Emergency.
	 *
	 * @return the guardianSignature
	 */
	public String getGuardianSignature() {
		return guardianSignature;
	}

	/**
	 * Set the guardianSignature of Emergency.
	 *
	 * @param guardianSignature the guardianSignature to set
	 */
	public void setGuardianSignature(String guardianSignature) {
		this.guardianSignature = guardianSignature;
	}

	/**
	 * Get the inPatient of Emergency.
	 *
	 * @return the inPatient
	 */
	public InPatient getInPatient() {
		return inPatient;
	}

	/**
	 * Set the inPatient of Emergency.
	 *
	 * @param inPatient the inPatient to set
	 */
	public void setInPatient(InPatient inPatient) {
		this.inPatient = inPatient;
	}
	
}
