/**
 * 
 */
package com.hospital.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="customer_id")
	private Integer customerId;
	
	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "company")
	private String company;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "mobile")
	private String mobile;

	/**
	 * Get the customerId of Customer.
	 *
	 * @return the customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * Set the customerId of Customer.
	 *
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * Get the customerName of Customer.
	 *
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Set the customerName of Customer.
	 *
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * Get the company of Customer.
	 *
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * Set the company of Customer.
	 *
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * Get the address of Customer.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set the address of Customer.
	 *
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Get the email of Customer.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the email of Customer.
	 *
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the mobile of Customer.
	 *
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Set the mobile of Customer.
	 *
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
