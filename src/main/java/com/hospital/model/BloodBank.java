/**
 * 
 */
package com.hospital.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private String bloodQuantity;
	
	@Column(name = "blood_group")
	private String bloodGroup;
	
	@Column(name = "blood_unit")
	private String bloodUnit;

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
	public String getBloodQuantity() {
		return bloodQuantity;
	}

	/**
	 * Set the bloodQuantity of BloodBank.
	 *
	 * @param bloodQuantity the bloodQuantity to set
	 */
	public void setBloodQuantity(String bloodQuantity) {
		this.bloodQuantity = bloodQuantity;
	}

	/**
	 * Get the bloodUnit of BloodBank.
	 *
	 * @return the bloodUnit
	 */
	public String getBloodUnit() {
		return bloodUnit;
	}

	/**
	 * Set the bloodUnit of BloodBank.
	 *
	 * @param bloodUnit the bloodUnit to set
	 */
	public void setBloodUnit(String bloodUnit) {
		this.bloodUnit = bloodUnit;
	}
	
}
