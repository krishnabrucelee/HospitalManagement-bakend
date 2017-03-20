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

import com.hospital.finanace.model.AccountDetailType;
import com.hospital.finanace.model.AccountType;

/**
 * @author Krishna
 * 
 * Purchase payment.
 */
@Entity
@Table(name="purchaseBillingTransaction")
public class PurchaseBillingTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="purchase_billing_transaction_id")
	private Integer purchaseBillingTransactionId;
	
    /** account category */
    @OneToOne(targetEntity = AccountDetailType.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "account_detail_type_id", referencedColumnName = "accountDetailTypeId", foreignKey = @ForeignKey(name = "account__detail_type_PURBILLTRANS_FK"))
    private AccountDetailType accountDetailType;
	
	@Column(name = "bill_amount")
	private Double billAmount;
	
	@Column(name = "payment_amount")
	private Double paymentAmount;
	
	@Column(name = "balance_amount")
	private Double balanceAmount;
	
	@Column(name = "advance_amount")
	private Double advanceAmount;
	
	@Column(name = "purchase_billing_transaction_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date purchaseBillingTransactionDate;
	
	@OneToOne(targetEntity = PurchaseBilling.class)
	@JoinColumn(name = "purchase_billing_id", referencedColumnName = "purchase_billing_id", foreignKey = @ForeignKey(name = "purchase_billing_PURBillTrans_FK"))
	private PurchaseBilling purchaseBilling;

	/**
	 * Get the purchaseBillingTransactionId of PurchaseBillingTransaction.
	 *
	 * @return the purchaseBillingTransactionId
	 */
	public Integer getPurchaseBillingTransactionId() {
		return purchaseBillingTransactionId;
	}

	/**
	 * Set the purchaseBillingTransactionId of PurchaseBillingTransaction.
	 *
	 * @param purchaseBillingTransactionId the purchaseBillingTransactionId to set
	 */
	public void setPurchaseBillingTransactionId(Integer purchaseBillingTransactionId) {
		this.purchaseBillingTransactionId = purchaseBillingTransactionId;
	}

	/**
	 * Get the paymentAmount of PurchaseBillingTransaction.
	 *
	 * @return the paymentAmount
	 */
	public Double getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * Set the paymentAmount of PurchaseBillingTransaction.
	 *
	 * @param paymentAmount the paymentAmount to set
	 */
	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	/**
	 * Get the balanceAmount of PurchaseBillingTransaction.
	 *
	 * @return the balanceAmount
	 */
	public Double getBalanceAmount() {
		return balanceAmount;
	}

	/**
	 * Set the balanceAmount of PurchaseBillingTransaction.
	 *
	 * @param balanceAmount the balanceAmount to set
	 */
	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	/**
	 * Get the purchaseBillingTransactionDate of PurchaseBillingTransaction.
	 *
	 * @return the purchaseBillingTransactionDate
	 */
	public Date getPurchaseBillingTransactionDate() {
		return purchaseBillingTransactionDate;
	}

	/**
	 * Set the purchaseBillingTransactionDate of PurchaseBillingTransaction.
	 *
	 * @param purchaseBillingTransactionDate the purchaseBillingTransactionDate to set
	 */
	public void setPurchaseBillingTransactionDate(Date purchaseBillingTransactionDate) {
		this.purchaseBillingTransactionDate = purchaseBillingTransactionDate;
	}

	/**
	 * Get the billAmount of PurchaseBillingTransaction.
	 *
	 * @return the billAmount
	 */
	public Double getBillAmount() {
		return billAmount;
	}

	/**
	 * Set the billAmount of PurchaseBillingTransaction.
	 *
	 * @param billAmount the billAmount to set
	 */
	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}

	/**
	 * Get the advanceAmount of PurchaseBillingTransaction.
	 *
	 * @return the advanceAmount
	 */
	public Double getAdvanceAmount() {
		return advanceAmount;
	}

	/**
	 * Set the advanceAmount of PurchaseBillingTransaction.
	 *
	 * @param advanceAmount the advanceAmount to set
	 */
	public void setAdvanceAmount(Double advanceAmount) {
		this.advanceAmount = advanceAmount;
	}


	/**
	 * Get the accountDetailType of PurchaseBillingTransaction.
	 *
	 * @return the accountDetailType
	 */
	public AccountDetailType getAccountDetailType() {
		return accountDetailType;
	}

	/**
	 * Set the accountDetailType of PurchaseBillingTransaction.
	 *
	 * @param accountDetailType the accountDetailType to set
	 */
	public void setAccountDetailType(AccountDetailType accountDetailType) {
		this.accountDetailType = accountDetailType;
	}

	/**
	 * Get the purchaseBilling of PurchaseBillingTransaction.
	 *
	 * @return the purchaseBilling
	 */
	public PurchaseBilling getPurchaseBilling() {
		return purchaseBilling;
	}

	/**
	 * Set the purchaseBilling of PurchaseBillingTransaction.
	 *
	 * @param purchaseBilling the purchaseBilling to set
	 */
	public void setPurchaseBilling(PurchaseBilling purchaseBilling) {
		this.purchaseBilling = purchaseBilling;
	}
	
}
