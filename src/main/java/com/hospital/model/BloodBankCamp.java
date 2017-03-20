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
@Table(name = "bloodBankCamp")
public class BloodBankCamp {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "blood_bank_camp_id")
	private Integer bloodBankCampId;
	
	@Column(name = "blood_bank_camp_name")
	private String bloodBankCampName;
	
	@Column(name = "blood_bank_camp_address")
	private String bloodBankCampAddress;
	
	@Column(name="blood_bank_camp_created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date bloodBankCampCreatedDate;

	/**
	 * Get the bloodBankCampId of BloodBankCamp.
	 *
	 * @return the bloodBankCampId
	 */
	public Integer getBloodBankCampId() {
		return bloodBankCampId;
	}

	/**
	 * Set the bloodBankCampId of BloodBankCamp.
	 *
	 * @param bloodBankCampId the bloodBankCampId to set
	 */
	public void setBloodBankCampId(Integer bloodBankCampId) {
		this.bloodBankCampId = bloodBankCampId;
	}

	/**
	 * Get the bloodBankCampName of BloodBankCamp.
	 *
	 * @return the bloodBankCampName
	 */
	public String getBloodBankCampName() {
		return bloodBankCampName;
	}

	/**
	 * Set the bloodBankCampName of BloodBankCamp.
	 *
	 * @param bloodBankCampName the bloodBankCampName to set
	 */
	public void setBloodBankCampName(String bloodBankCampName) {
		this.bloodBankCampName = bloodBankCampName;
	}

	/**
	 * Get the bloodBankCampAddress of BloodBankCamp.
	 *
	 * @return the bloodBankCampAddress
	 */
	public String getBloodBankCampAddress() {
		return bloodBankCampAddress;
	}

	/**
	 * Set the bloodBankCampAddress of BloodBankCamp.
	 *
	 * @param bloodBankCampAddress the bloodBankCampAddress to set
	 */
	public void setBloodBankCampAddress(String bloodBankCampAddress) {
		this.bloodBankCampAddress = bloodBankCampAddress;
	}

	/**
	 * Get the bloodBankCampCreatedDate of BloodBankCamp.
	 *
	 * @return the bloodBankCampCreatedDate
	 */
	public Date getBloodBankCampCreatedDate() {
		return bloodBankCampCreatedDate;
	}

	/**
	 * Set the bloodBankCampCreatedDate of BloodBankCamp.
	 *
	 * @param bloodBankCampCreatedDate the bloodBankCampCreatedDate to set
	 */
	public void setBloodBankCampCreatedDate(Date bloodBankCampCreatedDate) {
		this.bloodBankCampCreatedDate = bloodBankCampCreatedDate;
	}
	
}
