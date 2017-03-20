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
@Table(name = "receivePayment")
public class ReceivePayment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="receive_payment_id")
	private Integer receivePaymentId;
	
	@OneToOne(targetEntity = Invoice.class,cascade=CascadeType.ALL)
	@JoinColumn(name ="invoice_id", referencedColumnName="invoice_id", foreignKey=@ForeignKey(name="invoice_recvPay_FK"))
	private Invoice invoice;
	
    /** account category */
    @OneToOne(targetEntity = AccountCategoryChart.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "account_category_chart_id", referencedColumnName = "account_category_chart_id", foreignKey = @ForeignKey(name = "account_recvPay_FK"))
    private AccountCategoryChart accountCategoryChart;
    
	@Column(name = "receive_amount")
	private Double receiveAmount;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "receive_payment_date")
	private Date receivePaymentDate;

	/**
	 * Get the receivePaymentId of ReceivePayment.
	 *
	 * @return the receivePaymentId
	 */
	public Integer getReceivePaymentId() {
		return receivePaymentId;
	}

	/**
	 * Set the receivePaymentId of ReceivePayment.
	 *
	 * @param receivePaymentId the receivePaymentId to set
	 */
	public void setReceivePaymentId(Integer receivePaymentId) {
		this.receivePaymentId = receivePaymentId;
	}

	/**
	 * Get the invoice of ReceivePayment.
	 *
	 * @return the invoice
	 */
	public Invoice getInvoice() {
		return invoice;
	}

	/**
	 * Set the invoice of ReceivePayment.
	 *
	 * @param invoice the invoice to set
	 */
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	/**
	 * Get the accountCategoryChart of ReceivePayment.
	 *
	 * @return the accountCategoryChart
	 */
	public AccountCategoryChart getAccountCategoryChart() {
		return accountCategoryChart;
	}

	/**
	 * Set the accountCategoryChart of ReceivePayment.
	 *
	 * @param accountCategoryChart the accountCategoryChart to set
	 */
	public void setAccountCategoryChart(AccountCategoryChart accountCategoryChart) {
		this.accountCategoryChart = accountCategoryChart;
	}

	/**
	 * Get the receiveAmount of ReceivePayment.
	 *
	 * @return the receiveAmount
	 */
	public Double getReceiveAmount() {
		return receiveAmount;
	}

	/**
	 * Set the receiveAmount of ReceivePayment.
	 *
	 * @param receiveAmount the receiveAmount to set
	 */
	public void setReceiveAmount(Double receiveAmount) {
		this.receiveAmount = receiveAmount;
	}

	/**
	 * Get the receivePaymentDate of ReceivePayment.
	 *
	 * @return the receivePaymentDate
	 */
	public Date getReceivePaymentDate() {
		return receivePaymentDate;
	}

	/**
	 * Set the receivePaymentDate of ReceivePayment.
	 *
	 * @param receivePaymentDate the receivePaymentDate to set
	 */
	public void setReceivePaymentDate(Date receivePaymentDate) {
		this.receivePaymentDate = receivePaymentDate;
	}
	
}
