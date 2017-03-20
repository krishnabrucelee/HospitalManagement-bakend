/**
 * 
 */
package com.hospital.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hospital.finanace.model.AccountType;

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
	
	@OneToOne(targetEntity = PurchaseOrder.class)
	@JoinColumn(name = "purchase_order_id", referencedColumnName = "purchase_order_id", foreignKey = @ForeignKey(name = "purchaseOrder_PURBill_FK"))
	private PurchaseOrder purchase;
	
	@OneToOne(targetEntity = Supplier.class)
	@JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id", foreignKey = @ForeignKey(name = "supplier_PURBill_FK"))
	private Supplier supplier;
	
	@Column(name = "mailing_address")
	private String mailingAddress;
	
	@Column(name = "payment_amount")
	private Double paymentAmount;
	
	@Column(name = "terms")
	private String terms;

    /** account category */
    @OneToOne(targetEntity = AccountType.class)
    @JoinColumn(name = "account_type_id", referencedColumnName = "accountTypeId", foreignKey = @ForeignKey(name = "account_type_PURBill_FK"))
    private AccountType accountType;
    
	@Column(name = "account_description")
	private String accountDescription;
	
	@Column(name = "account_amount")
	private Double accountAmount;
	
	@Column(name = "purchase_billing_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date purchaseBillingDate;
	
	@Column(name = "purchase_due_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date purchaseDueDate;
	
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
	 * Get the terms of PurchaseBilling.
	 *
	 * @return the terms
	 */
	public String getTerms() {
		return terms;
	}

	/**
	 * Set the terms of PurchaseBilling.
	 *
	 * @param terms the terms to set
	 */
	public void setTerms(String terms) {
		this.terms = terms;
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
	 * Get the supplier of PurchaseBilling.
	 *
	 * @return the supplier
	 */
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * Set the supplier of PurchaseBilling.
	 *
	 * @param supplier the supplier to set
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	/**
	 * Get the mailingAddress of PurchaseBilling.
	 *
	 * @return the mailingAddress
	 */
	public String getMailingAddress() {
		return mailingAddress;
	}

	/**
	 * Set the mailingAddress of PurchaseBilling.
	 *
	 * @param mailingAddress the mailingAddress to set
	 */
	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	/**
	 * Get the purchaseDueDate of PurchaseBilling.
	 *
	 * @return the purchaseDueDate
	 */
	public Date getPurchaseDueDate() {
		return purchaseDueDate;
	}

	/**
	 * Set the purchaseDueDate of PurchaseBilling.
	 *
	 * @param purchaseDueDate the purchaseDueDate to set
	 */
	public void setPurchaseDueDate(Date purchaseDueDate) {
		this.purchaseDueDate = purchaseDueDate;
	}

	/**
	 * Get the purchase of PurchaseBilling.
	 *
	 * @return the purchase
	 */
	public PurchaseOrder getPurchase() {
		return purchase;
	}

	/**
	 * Set the purchase of PurchaseBilling.
	 *
	 * @param purchase the purchase to set
	 */
	public void setPurchase(PurchaseOrder purchase) {
		this.purchase = purchase;
	}

	/**
	 * Get the accountType of PurchaseBilling.
	 *
	 * @return the accountType
	 */
	public AccountType getAccountType() {
		return accountType;
	}

	/**
	 * Set the accountType of PurchaseBilling.
	 *
	 * @param accountType the accountType to set
	 */
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	/**
	 * Get the accountDescription of PurchaseBilling.
	 *
	 * @return the accountDescription
	 */
	public String getAccountDescription() {
		return accountDescription;
	}

	/**
	 * Set the accountDescription of PurchaseBilling.
	 *
	 * @param accountDescription the accountDescription to set
	 */
	public void setAccountDescription(String accountDescription) {
		this.accountDescription = accountDescription;
	}

	/**
	 * Get the accountAmount of PurchaseBilling.
	 *
	 * @return the accountAmount
	 */
	public Double getAccountAmount() {
		return accountAmount;
	}

	/**
	 * Set the accountAmount of PurchaseBilling.
	 *
	 * @param accountAmount the accountAmount to set
	 */
	public void setAccountAmount(Double accountAmount) {
		this.accountAmount = accountAmount;
	}

}
