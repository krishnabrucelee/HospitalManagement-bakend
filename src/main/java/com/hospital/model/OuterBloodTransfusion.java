/**
 * 
 */
package com.hospital.model;

import java.util.Date;
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
@Table(name = "outerBloodTransfusion")
public class OuterBloodTransfusion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "outer_blood_id")
	private Integer outerBloodId;
	
	@Column(name = "patient_name")
	private String patientName;
	
	@Column(name = "patient_age")
	private Integer patientAge;
	
	@Column(name = "patient_gender")
	private String patientGender;
	
	@Column(name = "patient_blood_group")
	private String patientBloodGroup;
	
	@Column(name = "blood_bag_number")
	private Integer bloodBagNumber;
	
	@Column(name = "patient_phone_number")
	private Integer patientPhoneNumber;
	
	@Column(name = "patient_address")
	private String patientAddress;
	
	@Column(name="date_of_transfusion")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfTransfusion;
	
	@Column(name = "transfer_bank_name")
	private String transferBankName;
	
	@Column(name = "patient_confirmation")
	private Boolean patientConfirmation;
	
	/**
	 * Get the outerBloodId of OuterBloodTransfusion.
	 *
	 * @return the outerBloodId
	 */
	public Integer getOuterBloodId() {
		return outerBloodId;
	}

	/**
	 * Set the outerBloodId of OuterBloodTransfusion.
	 *
	 * @param outerBloodId the outerBloodId to set
	 */
	public void setOuterBloodId(Integer outerBloodId) {
		this.outerBloodId = outerBloodId;
	}

	/**
	 * Get the patientName of OuterBloodTransfusion.
	 *
	 * @return the patientName
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * Set the patientName of OuterBloodTransfusion.
	 *
	 * @param patientName the patientName to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * Get the patientAge of OuterBloodTransfusion.
	 *
	 * @return the patientAge
	 */
	public Integer getPatientAge() {
		return patientAge;
	}

	/**
	 * Set the patientAge of OuterBloodTransfusion.
	 *
	 * @param patientAge the patientAge to set
	 */
	public void setPatientAge(Integer patientAge) {
		this.patientAge = patientAge;
	}

	/**
	 * Get the patientGender of OuterBloodTransfusion.
	 *
	 * @return the patientGender
	 */
	public String getPatientGender() {
		return patientGender;
	}

	/**
	 * Set the patientGender of OuterBloodTransfusion.
	 *
	 * @param patientGender the patientGender to set
	 */
	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	/**
	 * Get the patientBloodGroup of OuterBloodTransfusion.
	 *
	 * @return the patientBloodGroup
	 */
	public String getPatientBloodGroup() {
		return patientBloodGroup;
	}

	/**
	 * Set the patientBloodGroup of OuterBloodTransfusion.
	 *
	 * @param patientBloodGroup the patientBloodGroup to set
	 */
	public void setPatientBloodGroup(String patientBloodGroup) {
		this.patientBloodGroup = patientBloodGroup;
	}

	/**
	 * Get the patientPhoneNumber of OuterBloodTransfusion.
	 *
	 * @return the patientPhoneNumber
	 */
	public Integer getPatientPhoneNumber() {
		return patientPhoneNumber;
	}

	/**
	 * Set the patientPhoneNumber of OuterBloodTransfusion.
	 *
	 * @param patientPhoneNumber the patientPhoneNumber to set
	 */
	public void setPatientPhoneNumber(Integer patientPhoneNumber) {
		this.patientPhoneNumber = patientPhoneNumber;
	}

	/**
	 * Get the patientAddress of OuterBloodTransfusion.
	 *
	 * @return the patientAddress
	 */
	public String getPatientAddress() {
		return patientAddress;
	}

	/**
	 * Set the patientAddress of OuterBloodTransfusion.
	 *
	 * @param patientAddress the patientAddress to set
	 */
	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	/**
	 * Get the dateOfTransfusion of OuterBloodTransfusion.
	 *
	 * @return the dateOfTransfusion
	 */
	public Date getDateOfTransfusion() {
		return dateOfTransfusion;
	}

	/**
	 * Set the dateOfTransfusion of OuterBloodTransfusion.
	 *
	 * @param dateOfTransfusion the dateOfTransfusion to set
	 */
	public void setDateOfTransfusion(Date dateOfTransfusion) {
		this.dateOfTransfusion = dateOfTransfusion;
	}

	/**
	 * Get the transferBankName of OuterBloodTransfusion.
	 *
	 * @return the transferBankName
	 */
	public String getTransferBankName() {
		return transferBankName;
	}

	/**
	 * Set the transferBankName of OuterBloodTransfusion.
	 *
	 * @param transferBankName the transferBankName to set
	 */
	public void setTransferBankName(String transferBankName) {
		this.transferBankName = transferBankName;
	}

	/**
	 * Get the patientConfirmation of OuterBloodTransfusion.
	 *
	 * @return the patientConfirmation
	 */
	public Boolean getPatientConfirmation() {
		return patientConfirmation;
	}

	/**
	 * Set the patientConfirmation of OuterBloodTransfusion.
	 *
	 * @param patientConfirmation the patientConfirmation to set
	 */
	public void setPatientConfirmation(Boolean patientConfirmation) {
		this.patientConfirmation = patientConfirmation;
	}

	/**
	 * Get the bloodBagNumber of OuterBloodTransfusion.
	 *
	 * @return the bloodBagNumber
	 */
	public Integer getBloodBagNumber() {
		return bloodBagNumber;
	}

	/**
	 * Set the bloodBagNumber of OuterBloodTransfusion.
	 *
	 * @param bloodBagNumber the bloodBagNumber to set
	 */
	public void setBloodBagNumber(Integer bloodBagNumber) {
		this.bloodBagNumber = bloodBagNumber;
	}

}
