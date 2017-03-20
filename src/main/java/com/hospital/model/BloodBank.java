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

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "bloodBank")
public class BloodBank {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "blood_bank_id")
	private Integer bloodBankId;
	
	@Column(name = "blood_bag_number")
	private Integer bloodBagNumber;
	
	@Column(name = "blood_quantity")
	private Integer bloodQuantity;
	
	@Column(name = "blood_group")
	private String bloodGroup;
	
	@Column(name = "blood_unit")
	private Integer bloodUnit;
	
	@Column(name = "created_date")	
	@Temporal(TemporalType.DATE)
	private Date createdDate;

	@OneToOne(targetEntity = Donor.class)
	@JoinColumn(name = "donor", referencedColumnName = "donor_id", foreignKey = @ForeignKey(name = "donor_id_bloodBank_FK"))
	private Donor donor;
	
	/**
	 * Get the bloodBankId of BloodBank.
	 *
	 * @return the bloodBankId
	 */
	public Integer getBloodBankId() {
		return bloodBankId;
	}

	/**
	 * Set the bloodBankId of BloodBank.
	 *
	 * @param bloodBankId the bloodBankId to set
	 */
	public void setBloodBankId(Integer bloodBankId) {
		this.bloodBankId = bloodBankId;
	}


	/**
	 * Get the bloodBagNumber of BloodBank.
	 *
	 * @return the bloodBagNumber
	 */
	public Integer getBloodBagNumber() {
		return bloodBagNumber;
	}

	/**
	 * Set the bloodBagNumber of BloodBank.
	 *
	 * @param bloodBagNumber the bloodBagNumber to set
	 */
	public void setBloodBagNumber(Integer bloodBagNumber) {
		this.bloodBagNumber = bloodBagNumber;
	}

	/**
	 * Get the bloodGroup of BloodBank.
	 *
	 * @return the bloodGroup
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}

	/**
	 * Set the bloodGroup of BloodBank.
	 *
	 * @param bloodGroup the bloodGroup to set
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	/**
	 * Get the bloodQuantity of BloodBank.
	 *
	 * @return the bloodQuantity
	 */
	public Integer getBloodQuantity() {
		return bloodQuantity;
	}

	/**
	 * Set the bloodQuantity of BloodBank.
	 *
	 * @param bloodQuantity the bloodQuantity to set
	 */
	public void setBloodQuantity(Integer bloodQuantity) {
		this.bloodQuantity = bloodQuantity;
	}

	/**
	 * Get the bloodUnit of BloodBank.
	 *
	 * @return the bloodUnit
	 */
	public Integer getBloodUnit() {
		return bloodUnit;
	}

	/**
	 * Set the bloodUnit of BloodBank.
	 *
	 * @param bloodUnit the bloodUnit to set
	 */
	public void setBloodUnit(Integer bloodUnit) {
		this.bloodUnit = bloodUnit;
	}

	/**
	 * Get the createdDate of BloodBank.
	 *
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Set the createdDate of BloodBank.
	 *
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Get the donor of BloodBank.
	 *
	 * @return the donor
	 */
	public Donor getDonor() {
		return donor;
	}

	/**
	 * Set the donor of BloodBank.
	 *
	 * @param donor the donor to set
	 */
	public void setDonor(Donor donor) {
		this.donor = donor;
	}
	
}
