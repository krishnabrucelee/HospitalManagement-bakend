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
@Table(name = "laboratoryBilling")
public class LaboratoryBilling {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "laboratory_billing_id")
	private Integer laboratoryBillingId;
	
	@OneToOne(targetEntity = Patient.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", referencedColumnName = "patient_id", foreignKey = @ForeignKey(name = "patient_LBILL_FK"))
	private Patient patient;

	@OneToOne(targetEntity = Cost.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "cost_id", referencedColumnName = "cost_id", foreignKey = @ForeignKey(name = "cost_LBILL_FK"))
	private Cost cost;
	
	@Column(name = "laboratory_billing_details")
	private String laboratoryBillingDetails;

	/**
	 * Get the laboratoryBillingId of LaboratoryBilling.
	 *
	 * @return the laboratoryBillingId
	 */
	public Integer getLaboratoryBillingId() {
		return laboratoryBillingId;
	}

	/**
	 * Set the laboratoryBillingId of LaboratoryBilling.
	 *
	 * @param laboratoryBillingId the laboratoryBillingId to set
	 */
	public void setLaboratoryBillingId(Integer laboratoryBillingId) {
		this.laboratoryBillingId = laboratoryBillingId;
	}

	/**
	 * Get the patient of LaboratoryBilling.
	 *
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Set the patient of LaboratoryBilling.
	 *
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Get the cost of LaboratoryBilling.
	 *
	 * @return the cost
	 */
	public Cost getCost() {
		return cost;
	}

	/**
	 * Set the cost of LaboratoryBilling.
	 *
	 * @param cost the cost to set
	 */
	public void setCost(Cost cost) {
		this.cost = cost;
	}

	/**
	 * Get the laboratoryBillingDetails of LaboratoryBilling.
	 *
	 * @return the laboratoryBillingDetails
	 */
	public String getLaboratoryBillingDetails() {
		return laboratoryBillingDetails;
	}

	/**
	 * Set the laboratoryBillingDetails of LaboratoryBilling.
	 *
	 * @param laboratoryBillingDetails the laboratoryBillingDetails to set
	 */
	public void setLaboratoryBillingDetails(String laboratoryBillingDetails) {
		this.laboratoryBillingDetails = laboratoryBillingDetails;
	}

}
