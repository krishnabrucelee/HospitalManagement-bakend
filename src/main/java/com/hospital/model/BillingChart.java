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
@Table(name = "billingChart")
public class BillingChart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "billing_chart_id")
	private Integer billingChartId;
	
	@Column(name = "billing_chart_ref_number")
	private String billingChartRefNumber;
	
	@Column(name = "module_service")
	private String moduleService;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	/**
	 * Get the billingChartId of BillingChart.
	 *
	 * @return the billingChartId
	 */
	public Integer getBillingChartId() {
		return billingChartId;
	}

	/**
	 * Set the billingChartId of BillingChart.
	 *
	 * @param billingChartId the billingChartId to set
	 */
	public void setBillingChartId(Integer billingChartId) {
		this.billingChartId = billingChartId;
	}


	/**
	 * Get the billingChartRefNumber of BillingChart.
	 *
	 * @return the billingChartRefNumber
	 */
	public String getBillingChartRefNumber() {
		return billingChartRefNumber;
	}

	/**
	 * Set the billingChartRefNumber of BillingChart.
	 *
	 * @param billingChartRefNumber the billingChartRefNumber to set
	 */
	public void setBillingChartRefNumber(String billingChartRefNumber) {
		this.billingChartRefNumber = billingChartRefNumber;
	}

	/**
	 * Get the moduleService of BillingChart.
	 *
	 * @return the moduleService
	 */
	public String getModuleService() {
		return moduleService;
	}

	/**
	 * Set the moduleService of BillingChart.
	 *
	 * @param moduleService the moduleService to set
	 */
	public void setModuleService(String moduleService) {
		this.moduleService = moduleService;
	}

	/**
	 * Get the price of BillingChart.
	 *
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Set the price of BillingChart.
	 *
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Get the createdDate of BillingChart.
	 *
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Set the createdDate of BillingChart.
	 *
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
