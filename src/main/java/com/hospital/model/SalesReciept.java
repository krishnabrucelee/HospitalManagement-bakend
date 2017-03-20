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

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "salesReciept")
public class SalesReciept {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="sales_reciept_id")
	private Integer salesRecieptId;
	
	@OneToOne(targetEntity = Customer.class,cascade=CascadeType.ALL)
	@JoinColumn(name ="customer_id", referencedColumnName="customer_id", foreignKey=@ForeignKey(name="customer_sales_FK"))
	private Customer customer;
	
	@Column(name="customer_address")
	private String customerAddress;
	
	 /** account category */
    @OneToOne(targetEntity = AccountCategoryChart.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "account_category_chart_id", referencedColumnName = "account_category_chart_id", foreignKey = @ForeignKey(name = "account_chart_sales_FK"))
    private AccountCategoryChart accountCategoryChart;
    
	@Temporal(TemporalType.DATE)
	@Column(name = "sales_receipt_date")
	private Date salesReceiptDate;
	
	@Column(name = "payment_method")
	private String paymentMethod;
	
	@Column(name = "sales_receipt_amount")
	private Double salesReceiptAmount;
	
	@OneToMany(cascade=CascadeType.ALL)	
	List<SalesRecieptItems> salesRecieptItems;

	/**
	 * Get the salesRecieptId of SalesReciept.
	 *
	 * @return the salesRecieptId
	 */
	public Integer getSalesRecieptId() {
		return salesRecieptId;
	}

	/**
	 * Set the salesRecieptId of SalesReciept.
	 *
	 * @param salesRecieptId the salesRecieptId to set
	 */
	public void setSalesRecieptId(Integer salesRecieptId) {
		this.salesRecieptId = salesRecieptId;
	}

	/**
	 * Get the customer of SalesReciept.
	 *
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Set the customer of SalesReciept.
	 *
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	/**
	 * Get the customerAddress of SalesReciept.
	 *
	 * @return the customerAddress
	 */
	public String getCustomerAddress() {
		return customerAddress;
	}

	/**
	 * Set the customerAddress of SalesReciept.
	 *
	 * @param customerAddress the customerAddress to set
	 */
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	/**
	 * Get the accountCategoryChart of SalesReciept.
	 *
	 * @return the accountCategoryChart
	 */
	public AccountCategoryChart getAccountCategoryChart() {
		return accountCategoryChart;
	}

	/**
	 * Set the accountCategoryChart of SalesReciept.
	 *
	 * @param accountCategoryChart the accountCategoryChart to set
	 */
	public void setAccountCategoryChart(AccountCategoryChart accountCategoryChart) {
		this.accountCategoryChart = accountCategoryChart;
	}

	/**
	 * Get the salesReceiptDate of SalesReciept.
	 *
	 * @return the salesReceiptDate
	 */
	public Date getSalesReceiptDate() {
		return salesReceiptDate;
	}

	/**
	 * Set the salesReceiptDate of SalesReciept.
	 *
	 * @param salesReceiptDate the salesReceiptDate to set
	 */
	public void setSalesReceiptDate(Date salesReceiptDate) {
		this.salesReceiptDate = salesReceiptDate;
	}

	/**
	 * Get the paymentMethod of SalesReciept.
	 *
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * Set the paymentMethod of SalesReciept.
	 *
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * Get the salesReceiptAmount of SalesReciept.
	 *
	 * @return the salesReceiptAmount
	 */
	public Double getSalesReceiptAmount() {
		return salesReceiptAmount;
	}

	/**
	 * Set the salesReceiptAmount of SalesReciept.
	 *
	 * @param salesReceiptAmount the salesReceiptAmount to set
	 */
	public void setSalesReceiptAmount(Double salesReceiptAmount) {
		this.salesReceiptAmount = salesReceiptAmount;
	}

	/**
	 * Get the salesRecieptItems of SalesReciept.
	 *
	 * @return the salesRecieptItems
	 */
	public List<SalesRecieptItems> getSalesRecieptItems() {
		return salesRecieptItems;
	}

	/**
	 * Set the salesRecieptItems of SalesReciept.
	 *
	 * @param salesRecieptItems the salesRecieptItems to set
	 */
	public void setSalesRecieptItems(List<SalesRecieptItems> salesRecieptItems) {
		this.salesRecieptItems = salesRecieptItems;
	}
	
}
