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
@Table(name = "transactionPayment")
public class TransactionPayment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="transaction_payment_id")
	private Integer transactionPaymentId;
	
	@Column(name = "transaction_amount")
	private Double transactionAmount;
	
	@Column(name = "transaction_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;

	/**
	 * Get the transactionPaymentId of TransactionPayment.
	 *
	 * @return the transactionPaymentId
	 */
	public Integer getTransactionPaymentId() {
		return transactionPaymentId;
	}

	/**
	 * Set the transactionPaymentId of TransactionPayment.
	 *
	 * @param transactionPaymentId the transactionPaymentId to set
	 */
	public void setTransactionPaymentId(Integer transactionPaymentId) {
		this.transactionPaymentId = transactionPaymentId;
	}

	/**
	 * Get the transactionAmount of TransactionPayment.
	 *
	 * @return the transactionAmount
	 */
	public Double getTransactionAmount() {
		return transactionAmount;
	}

	/**
	 * Set the transactionAmount of TransactionPayment.
	 *
	 * @param transactionAmount the transactionAmount to set
	 */
	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	/**
	 * Get the transactionDate of TransactionPayment.
	 *
	 * @return the transactionDate
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}

	/**
	 * Set the transactionDate of TransactionPayment.
	 *
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
}
