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
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "donor")
public class Donor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "donor_id")
	private Integer donorId;
	
	@Column(name = "donor_reg_id")
	private Integer donorRegisterId;
	
	@Column(name = "donor_name")
	private String donorName;
	
	@Column(name = "donor_age")
	private Integer donorAge;
	
	@Column(name = "donor_gender")
	private String donorGender;
	
	@Column(name = "donor_blood_group")
	private String donorBloodGroup;
	
	@Column(name = "donor_phone_number")
	private Integer donorPhoneNumber;
	
	@Column(name = "donor_address")
	private String donorAddress;
	
	@Column(name = "donor_email_id")
	private String donorEmailId;
	
	@Column(name = "donor_disease")
	private String donorDisease;
	
	@Column(name="date_of_register")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfRegister;
	
	@Column(name = "donor_signature")
	private String donorSignature;

	/**
	 * Get the donorId of Donor.
	 *
	 * @return the donorId
	 */
	public Integer getDonorId() {
		return donorId;
	}

	/**
	 * Set the donorId of Donor.
	 *
	 * @param donorId the donorId to set
	 */
	public void setDonorId(Integer donorId) {
		this.donorId = donorId;
	}

	/**
	 * Get the donorRegisterId of Donor.
	 *
	 * @return the donorRegisterId
	 */
	public Integer getDonorRegisterId() {
		return donorRegisterId;
	}

	/**
	 * Set the donorRegisterId of Donor.
	 *
	 * @param donorRegisterId the donorRegisterId to set
	 */
	public void setDonorRegisterId(Integer donorRegisterId) {
		this.donorRegisterId = donorRegisterId;
	}

	/**
	 * Get the donorName of Donor.
	 *
	 * @return the donorName
	 */
	public String getDonorName() {
		return donorName;
	}

	/**
	 * Set the donorName of Donor.
	 *
	 * @param donorName the donorName to set
	 */
	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}

	/**
	 * Get the donorAge of Donor.
	 *
	 * @return the donorAge
	 */
	public Integer getDonorAge() {
		return donorAge;
	}

	/**
	 * Set the donorAge of Donor.
	 *
	 * @param donorAge the donorAge to set
	 */
	public void setDonorAge(Integer donorAge) {
		this.donorAge = donorAge;
	}

	/**
	 * Get the donorGender of Donor.
	 *
	 * @return the donorGender
	 */
	public String getDonorGender() {
		return donorGender;
	}

	/**
	 * Set the donorGender of Donor.
	 *
	 * @param donorGender the donorGender to set
	 */
	public void setDonorGender(String donorGender) {
		this.donorGender = donorGender;
	}

	/**
	 * Get the donorBloodGroup of Donor.
	 *
	 * @return the donorBloodGroup
	 */
	public String getDonorBloodGroup() {
		return donorBloodGroup;
	}

	/**
	 * Set the donorBloodGroup of Donor.
	 *
	 * @param donorBloodGroup the donorBloodGroup to set
	 */
	public void setDonorBloodGroup(String donorBloodGroup) {
		this.donorBloodGroup = donorBloodGroup;
	}

	/**
	 * Get the donorPhoneNumber of Donor.
	 *
	 * @return the donorPhoneNumber
	 */
	public Integer getDonorPhoneNumber() {
		return donorPhoneNumber;
	}

	/**
	 * Set the donorPhoneNumber of Donor.
	 *
	 * @param donorPhoneNumber the donorPhoneNumber to set
	 */
	public void setDonorPhoneNumber(Integer donorPhoneNumber) {
		this.donorPhoneNumber = donorPhoneNumber;
	}

	/**
	 * Get the donorAddress of Donor.
	 *
	 * @return the donorAddress
	 */
	public String getDonorAddress() {
		return donorAddress;
	}

	/**
	 * Set the donorAddress of Donor.
	 *
	 * @param donorAddress the donorAddress to set
	 */
	public void setDonorAddress(String donorAddress) {
		this.donorAddress = donorAddress;
	}

	/**
	 * Get the donoremailId of Donor.
	 *
	 * @return the donoremailId
	 */
	public String getDonorEmailId() {
		return donorEmailId;
	}

	/**
	 * Set the donoremailId of Donor.
	 *
	 * @param donoremailId the donoremailId to set
	 */
	public void setDonorEmailId(String donorEmailId) {
		this.donorEmailId = donorEmailId;
	}

	/**
	 * Get the dateOfRegister of Donor.
	 *
	 * @return the dateOfRegister
	 */
	public Date getDateOfRegister() {
		return dateOfRegister;
	}

	/**
	 * Get the donorDisease of Donor.java.
	 *
	 * @return the donorDisease
	 */
	public String getDonorDisease() {
		return donorDisease;
	}

	/**
	 * Set the donorDisease of Donor.java.
	 *
	 * @param donorDisease the donorDisease to set
	 */
	public void setDonorDisease(String donorDisease) {
		this.donorDisease = donorDisease;
	}

	/**
	 * Set the dateOfRegister of Donor.
	 *
	 * @param dateOfRegister the dateOfRegister to set
	 */
	public void setDateOfRegister(Date dateOfRegister) {
		this.dateOfRegister = dateOfRegister;
	}

	/**
	 * Get the donorSignature of Donor.
	 *
	 * @return the donorSignature
	 */
	public String getDonorSignature() {
		return donorSignature;
	}

	/**
	 * Set the donorSignature of Donor.
	 *
	 * @param donorSignature the donorSignature to set
	 */
	public void setDonorSignature(String donorSignature) {
		this.donorSignature = donorSignature;
	}
	
}
