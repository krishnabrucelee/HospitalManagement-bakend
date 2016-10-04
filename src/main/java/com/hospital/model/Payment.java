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
@Table(name = "payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="payment_id")
	private Integer paymentId;
	
	@Column(name = "payment_mode")
	private String paymentMode;
	
	@Column(name = "payment_amount")
	private Double paymentAmount;
	
	@Column(name = "amount_paid")
	private Double amountPaid;

	@Column(name = "amount_due")
	private Double amountDue;
	
	@Column(name = "payment_date")
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date paymentDate;
	
	@OneToOne(targetEntity = PurchaseBilling.class)
	@JoinColumn(name = "purchase_billing_id", referencedColumnName = "purchase_billing_id", foreignKey = @ForeignKey(name = "purchase_billing_PAYBill_FK"))
	private PurchaseBilling purchaseBilling;
	
	@OneToOne(targetEntity = PettyCash.class)
	@JoinColumn(name = "petty_cash_id", referencedColumnName = "petty_cash_id", foreignKey = @ForeignKey(name = "petty_cash_PAYBill_FK"))
	private PettyCash pettyCash;

	/**
	 * Get the paymentId of Payment.
	 *
	 * @return the paymentId
	 */
	public Integer getPaymentId() {
		return paymentId;
	}

	/**
	 * Set the paymentId of Payment.
	 *
	 * @param paymentId the paymentId to set
	 */
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	/**
	 * Get the paymentMode of Payment.
	 *
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}

	/**
	 * Set the paymentMode of Payment.
	 *
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * Get the paymentAmount of Payment.
	 *
	 * @return the paymentAmount
	 */
	public Double getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * Set the paymentAmount of Payment.
	 *
	 * @param paymentAmount the paymentAmount to set
	 */
	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	/**
	 * Get the amountPaid of Payment.
	 *
	 * @return the amountPaid
	 */
	public Double getAmountPaid() {
		return amountPaid;
	}

	/**
	 * Set the amountPaid of Payment.
	 *
	 * @param amountPaid the amountPaid to set
	 */
	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	/**
	 * Get the amountDue of Payment.
	 *
	 * @return the amountDue
	 */
	public Double getAmountDue() {
		return amountDue;
	}

	/**
	 * Set the amountDue of Payment.
	 *
	 * @param amountDue the amountDue to set
	 */
	public void setAmountDue(Double amountDue) {
		this.amountDue = amountDue;
	}

	/**
	 * Get the paymentDate of Payment.
	 *
	 * @return the paymentDate
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * Set the paymentDate of Payment.
	 *
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * Get the purchaseBilling of Payment.
	 *
	 * @return the purchaseBilling
	 */
	public PurchaseBilling getPurchaseBilling() {
		return purchaseBilling;
	}

	/**
	 * Set the purchaseBilling of Payment.
	 *
	 * @param purchaseBilling the purchaseBilling to set
	 */
	public void setPurchaseBilling(PurchaseBilling purchaseBilling) {
		this.purchaseBilling = purchaseBilling;
	}

	/**
	 * Get the pettyCash of Payment.
	 *
	 * @return the pettyCash
	 */
	public PettyCash getPettyCash() {
		return pettyCash;
	}

	/**
	 * Set the pettyCash of Payment.
	 *
	 * @param pettyCash the pettyCash to set
	 */
	public void setPettyCash(PettyCash pettyCash) {
		this.pettyCash = pettyCash;
	}
	
}
