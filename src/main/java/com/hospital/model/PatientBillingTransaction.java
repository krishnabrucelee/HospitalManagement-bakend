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
@Table(name="patientBillingTransaction")
public class PatientBillingTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="patient_billing_transaction_id")
	private Integer patientBillingTransactionId;
	
	@OneToOne(targetEntity = Billing.class)
	@JoinColumn(name = "billing_id", referencedColumnName = "billing_id", foreignKey = @ForeignKey(name = "billing_PATBillTrans_FK"))
	private Billing billing;
	
	@OneToOne(targetEntity = Patient.class,cascade=CascadeType.ALL)
	@JoinColumn(name ="patient_id", referencedColumnName="patient_id", foreignKey=@ForeignKey(name="patient_PATBillTrans_FK"))
	private Patient  patient;
	
	@Column(name = "bill_amount")
	private Double billAmount;
	
	@Column(name = "payment_amount")
	private Double paymentAmount;
	
	@Column(name = "balance_amount")
	private Double balanceAmount;
	
	@Column(name = "advance_amount")
	private Double advanceAmount;
	
	@Column(name = "patient_billing_transaction_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date patientBillingTransactionDate;

	/**
	 * Get the patientBillingTransactionId of PatientBillingTransaction.
	 *
	 * @return the patientBillingTransactionId
	 */
	public Integer getPatientBillingTransactionId() {
		return patientBillingTransactionId;
	}

	/**
	 * Set the patientBillingTransactionId of PatientBillingTransaction.
	 *
	 * @param patientBillingTransactionId the patientBillingTransactionId to set
	 */
	public void setPatientBillingTransactionId(Integer patientBillingTransactionId) {
		this.patientBillingTransactionId = patientBillingTransactionId;
	}

	/**
	 * Get the billing of PatientBillingTransaction.
	 *
	 * @return the billing
	 */
	public Billing getBilling() {
		return billing;
	}

	/**
	 * Set the billing of PatientBillingTransaction.
	 *
	 * @param billing the billing to set
	 */
	public void setBilling(Billing billing) {
		this.billing = billing;
	}

	/**
	 * Get the paymentAmount of PatientBillingTransaction.
	 *
	 * @return the paymentAmount
	 */
	public Double getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * Set the paymentAmount of PatientBillingTransaction.
	 *
	 * @param paymentAmount the paymentAmount to set
	 */
	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	/**
	 * Get the balanceAmount of PatientBillingTransaction.
	 *
	 * @return the balanceAmount
	 */
	public Double getBalanceAmount() {
		return balanceAmount;
	}

	/**
	 * Set the balanceAmount of PatientBillingTransaction.
	 *
	 * @param balanceAmount the balanceAmount to set
	 */
	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	/**
	 * Get the patientBillingTransactionDate of PatientBillingTransaction.
	 *
	 * @return the patientBillingTransactionDate
	 */
	public Date getPatientBillingTransactionDate() {
		return patientBillingTransactionDate;
	}

	/**
	 * Set the patientBillingTransactionDate of PatientBillingTransaction.
	 *
	 * @param patientBillingTransactionDate the patientBillingTransactionDate to set
	 */
	public void setPatientBillingTransactionDate(Date patientBillingTransactionDate) {
		this.patientBillingTransactionDate = patientBillingTransactionDate;
	}

	/**
	 * Get the patient of PatientBillingTransaction.
	 *
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Set the patient of PatientBillingTransaction.
	 *
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Get the billAmount of PatientBillingTransaction.
	 *
	 * @return the billAmount
	 */
	public Double getBillAmount() {
		return billAmount;
	}

	/**
	 * Set the billAmount of PatientBillingTransaction.
	 *
	 * @param billAmount the billAmount to set
	 */
	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}

	/**
	 * Get the advanceAmount of PatientBillingTransaction.
	 *
	 * @return the advanceAmount
	 */
	public Double getAdvanceAmount() {
		return advanceAmount;
	}

	/**
	 * Set the advanceAmount of PatientBillingTransaction.
	 *
	 * @param advanceAmount the advanceAmount to set
	 */
	public void setAdvanceAmount(Double advanceAmount) {
		this.advanceAmount = advanceAmount;
	}
	
}
