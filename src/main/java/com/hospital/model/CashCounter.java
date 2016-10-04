/**
 * 
 */
package com.hospital.model;

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
@Table(name = "cashCounter")
public class CashCounter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cash_counter_id")
	private Integer cashCounterId;
	
	@OneToOne(targetEntity = PatientBilling.class)
	@JoinColumn(name = "patient_billing_id", referencedColumnName = "patient_billing_id", foreignKey = @ForeignKey(name = "patient_billing_CCOUNT_FK"))
	private PatientBilling patientBilling;
	
	@Column(name = "payment_mode")
	private String paymentMode;
	
	@Column(name = "advance_amount")
	private String advanceAmount;
	
	@Column(name = "due_amount")
	private String dueAmount;

	/**
	 * Get the cashCounterId of CashCounter.
	 *
	 * @return the cashCounterId
	 */
	public Integer getCashCounterId() {
		return cashCounterId;
	}

	/**
	 * Set the cashCounterId of CashCounter.
	 *
	 * @param cashCounterId the cashCounterId to set
	 */
	public void setCashCounterId(Integer cashCounterId) {
		this.cashCounterId = cashCounterId;
	}

	/**
	 * Get the patientBilling of CashCounter.
	 *
	 * @return the patientBilling
	 */
	public PatientBilling getPatientBilling() {
		return patientBilling;
	}

	/**
	 * Set the patientBilling of CashCounter.
	 *
	 * @param patientBilling the patientBilling to set
	 */
	public void setPatientBilling(PatientBilling patientBilling) {
		this.patientBilling = patientBilling;
	}

	/**
	 * Get the paymentMode of CashCounter.
	 *
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}

	/**
	 * Set the paymentMode of CashCounter.
	 *
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * Get the advanceAmount of CashCounter.
	 *
	 * @return the advanceAmount
	 */
	public String getAdvanceAmount() {
		return advanceAmount;
	}

	/**
	 * Set the advanceAmount of CashCounter.
	 *
	 * @param advanceAmount the advanceAmount to set
	 */
	public void setAdvanceAmount(String advanceAmount) {
		this.advanceAmount = advanceAmount;
	}

	/**
	 * Get the dueAmount of CashCounter.
	 *
	 * @return the dueAmount
	 */
	public String getDueAmount() {
		return dueAmount;
	}

	/**
	 * Set the dueAmount of CashCounter.
	 *
	 * @param dueAmount the dueAmount to set
	 */
	public void setDueAmount(String dueAmount) {
		this.dueAmount = dueAmount;
	}
	
}
