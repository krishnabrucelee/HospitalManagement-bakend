/**
 * 
 */
package com.hospital.model;

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

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "pharmacyBilling")
public class PharmacyBilling {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pharmacy_billing_id")
	private Integer pharmacyBillingId;
	
	@OneToOne(targetEntity = Patient.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", referencedColumnName = "patient_id", foreignKey = @ForeignKey(name = "patient_PBILL_FK"))
	private Patient patient;

	@OneToOne(targetEntity = Cost.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "cost_id", referencedColumnName = "cost_id", foreignKey = @ForeignKey(name = "cost_PBILL_FK"))
	private Cost cost;
	
	@Column(name = "pharmacy_billing_details")
	private String pharmacyBillingDetails;

	/**
	 * Get the pharmacyBillingId of PharmacyBilling.
	 *
	 * @return the pharmacyBillingId
	 */
	public Integer getPharmacyBillingId() {
		return pharmacyBillingId;
	}

	/**
	 * Set the pharmacyBillingId of PharmacyBilling.
	 *
	 * @param pharmacyBillingId the pharmacyBillingId to set
	 */
	public void setPharmacyBillingId(Integer pharmacyBillingId) {
		this.pharmacyBillingId = pharmacyBillingId;
	}

	/**
	 * Get the patient of PharmacyBilling.
	 *
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Set the patient of PharmacyBilling.
	 *
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Get the cost of PharmacyBilling.
	 *
	 * @return the cost
	 */
	public Cost getCost() {
		return cost;
	}

	/**
	 * Set the cost of PharmacyBilling.
	 *
	 * @param cost the cost to set
	 */
	public void setCost(Cost cost) {
		this.cost = cost;
	}

	/**
	 * Get the pharmacyBillingDetails of PharmacyBilling.
	 *
	 * @return the pharmacyBillingDetails
	 */
	public String getPharmacyBillingDetails() {
		return pharmacyBillingDetails;
	}

	/**
	 * Set the pharmacyBillingDetails of PharmacyBilling.
	 *
	 * @param pharmacyBillingDetails the pharmacyBillingDetails to set
	 */
	public void setPharmacyBillingDetails(String pharmacyBillingDetails) {
		this.pharmacyBillingDetails = pharmacyBillingDetails;
	}
	
}
