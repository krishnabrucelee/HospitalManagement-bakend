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

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "supplier")
public class Supplier {

	@Column(name = "supplier_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer supplierId;
	
	@Column(name = "title")
	private String title;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "display_name")
	private String displayName;
	
	@Column(name = "company")
	private String company;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "pan_no")
	private String panNo;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "mobile")
	private String mobile;
	
	@Column(name = "fax")
	private String fax;
	
	@Column(name = "website")
	private String website;
	
	@Column(name = "billing_rate")
	private String billingRate;
	
	@Column(name = "terms")
	private String terms;
	
	@Column(name = "opening_balance")
	private String openingBalance;
	
	@Column(name = "as_of")
	private String asOf;
	
	@Column(name = "Account_no")
	private String accountNo;
	
	@Column(name = "tax_registration_no")
	private String taxRegistrationNo;
	
	@Column(name = "effective_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date effectiveDate;
	
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	/**
	 * Get the supplierId of Supplier.
	 *
	 * @return the supplierId
	 */
	public Integer getSupplierId() {
		return supplierId;
	}

	/**
	 * Set the supplierId of Supplier.
	 *
	 * @param supplierId the supplierId to set
	 */
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * Get the title of Supplier.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title of Supplier.
	 *
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the firstName of Supplier.
	 *
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set the firstName of Supplier.
	 *
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get the lastName of Supplier.
	 *
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set the lastName of Supplier.
	 *
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get the displayName of Supplier.
	 *
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Set the displayName of Supplier.
	 *
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Get the company of Supplier.
	 *
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * Set the company of Supplier.
	 *
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * Get the address of Supplier.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set the address of Supplier.
	 *
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Get the panNo of Supplier.
	 *
	 * @return the panNo
	 */
	public String getPanNo() {
		return panNo;
	}

	/**
	 * Set the panNo of Supplier.
	 *
	 * @param panNo the panNo to set
	 */
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	/**
	 * Get the email of Supplier.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the email of Supplier.
	 *
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the mobile of Supplier.
	 *
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Set the mobile of Supplier.
	 *
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * Get the fax of Supplier.
	 *
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * Set the fax of Supplier.
	 *
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * Get the website of Supplier.
	 *
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * Set the website of Supplier.
	 *
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * Get the billingRate of Supplier.
	 *
	 * @return the billingRate
	 */
	public String getBillingRate() {
		return billingRate;
	}

	/**
	 * Set the billingRate of Supplier.
	 *
	 * @param billingRate the billingRate to set
	 */
	public void setBillingRate(String billingRate) {
		this.billingRate = billingRate;
	}

	/**
	 * Get the terms of Supplier.
	 *
	 * @return the terms
	 */
	public String getTerms() {
		return terms;
	}

	/**
	 * Set the terms of Supplier.
	 *
	 * @param terms the terms to set
	 */
	public void setTerms(String terms) {
		this.terms = terms;
	}

	/**
	 * Get the openingBalance of Supplier.
	 *
	 * @return the openingBalance
	 */
	public String getOpeningBalance() {
		return openingBalance;
	}

	/**
	 * Set the openingBalance of Supplier.
	 *
	 * @param openingBalance the openingBalance to set
	 */
	public void setOpeningBalance(String openingBalance) {
		this.openingBalance = openingBalance;
	}

	/**
	 * Get the asOf of Supplier.
	 *
	 * @return the asOf
	 */
	public String getAsOf() {
		return asOf;
	}

	/**
	 * Set the asOf of Supplier.
	 *
	 * @param asOf the asOf to set
	 */
	public void setAsOf(String asOf) {
		this.asOf = asOf;
	}

	/**
	 * Get the accountNo of Supplier.
	 *
	 * @return the accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * Set the accountNo of Supplier.
	 *
	 * @param accountNo the accountNo to set
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * Get the taxRegistrationNo of Supplier.
	 *
	 * @return the taxRegistrationNo
	 */
	public String getTaxRegistrationNo() {
		return taxRegistrationNo;
	}

	/**
	 * Set the taxRegistrationNo of Supplier.
	 *
	 * @param taxRegistrationNo the taxRegistrationNo to set
	 */
	public void setTaxRegistrationNo(String taxRegistrationNo) {
		this.taxRegistrationNo = taxRegistrationNo;
	}

	/**
	 * Get the effectiveDate of Supplier.
	 *
	 * @return the effectiveDate
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * Set the effectiveDate of Supplier.
	 *
	 * @param effectiveDate the effectiveDate to set
	 */
	public void setEffectiveDate(Date effectiveDate) {
		effectiveDate = effectiveDate;
	}

	/**
	 * Get the createdDate of Supplier.
	 *
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Set the createdDate of Supplier.
	 *
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
