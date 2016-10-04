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
@Table(name = "purchaseBilling")
public class PurchaseBilling {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="purchase_billing_id")
	private Integer purchaseBillingId;
	
	@Column(name = "purchase_id")
	private Integer purchaseId;
	
	@Column(name = "billing_id")
	private Integer billingId;
	
	@Column(name = "payment_amount")
	private Double paymentAmount;
	
	@Column(name = "payment_terms")
	private Double paymentTerms;

	@Column(name = "purchase_billing_date")
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date purchaseBillingDate;
	
	@OneToOne(targetEntity = ExpenseApproval.class)
	@JoinColumn(name = "expense_approval_id", referencedColumnName = "expense_approval_id", foreignKey = @ForeignKey(name = "expense_approval_PURBill_FK"))
	private ExpenseApproval expenseApproval;
	
	/**
	 * Get the purchaseBillingId of PurchaseBilling.
	 *
	 * @return the purchaseBillingId
	 */
	public Integer getPurchaseBillingId() {
		return purchaseBillingId;
	}

	/**
	 * Set the purchaseBillingId of PurchaseBilling.
	 *
	 * @param purchaseBillingId the purchaseBillingId to set
	 */
	public void setPurchaseBillingId(Integer purchaseBillingId) {
		this.purchaseBillingId = purchaseBillingId;
	}


	/**
	 * Get the purchaseId of PurchaseBilling.
	 *
	 * @return the purchaseId
	 */
	public Integer getPurchaseId() {
		return purchaseId;
	}

	/**
	 * Set the purchaseId of PurchaseBilling.
	 *
	 * @param purchaseId the purchaseId to set
	 */
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}

	/**
	 * Get the billingId of PurchaseBilling.
	 *
	 * @return the billingId
	 */
	public Integer getBillingId() {
		return billingId;
	}

	/**
	 * Set the billingId of PurchaseBilling.
	 *
	 * @param billingId the billingId to set
	 */
	public void setBillingId(Integer billingId) {
		this.billingId = billingId;
	}

	/**
	 * Get the paymentAmount of PurchaseBilling.
	 *
	 * @return the paymentAmount
	 */
	public Double getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * Set the paymentAmount of PurchaseBilling.
	 *
	 * @param paymentAmount the paymentAmount to set
	 */
	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	/**
	 * Get the paymentTerms of PurchaseBilling.
	 *
	 * @return the paymentTerms
	 */
	public Double getPaymentTerms() {
		return paymentTerms;
	}

	/**
	 * Set the paymentTerms of PurchaseBilling.
	 *
	 * @param paymentTerms the paymentTerms to set
	 */
	public void setPaymentTerms(Double paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	/**
	 * Get the purchaseBillingDate of PurchaseBilling.	
	 *
	 * @return the purchaseBillingDate
	 */
	public Date getPurchaseBillingDate() {
		return purchaseBillingDate;
	}

	/**
	 * Set the purchaseBillingDate of PurchaseBilling.	
	 *
	 * @param purchaseBillingDate the purchaseBillingDate to set
	 */
	public void setPurchaseBillingDate(Date purchaseBillingDate) {
		this.purchaseBillingDate = purchaseBillingDate;
	}

	/**
	 * Get the expenseApproval of PurchaseBilling. 
	 *
	 * @return the expenseApproval
	 */
	public ExpenseApproval getExpenseApproval() {
		return expenseApproval;
	}

	/**
	 * Set the expenseApproval of PurchaseBilling. 
	 *
	 * @param expenseApproval the expenseApproval to set
	 */
	public void setExpenseApproval(ExpenseApproval expenseApproval) {
		this.expenseApproval = expenseApproval;
	}

}
