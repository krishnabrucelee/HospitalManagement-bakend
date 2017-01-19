/**
 * 
 */
package com.hospital.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "billingMaster")
public class BillingMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "billing_master_id")
	private Integer billingMasterId;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<BillingChart> billingChart;

	/**
	 * Get the billingMasterId of BillingMaster.
	 *
	 * @return the billingMasterId
	 */
	public Integer getBillingMasterId() {
		return billingMasterId;
	}

	/**
	 * Set the billingMasterId of BillingMaster.
	 *
	 * @param billingMasterId the billingMasterId to set
	 */
	public void setBillingMasterId(Integer billingMasterId) {
		this.billingMasterId = billingMasterId;
	}

	/**
	 * Get the billingChart of BillingMaster.
	 *
	 * @return the billingChart
	 */
	public List<BillingChart> getBillingChart() {
		return billingChart;
	}

	/**
	 * Set the billingChart of BillingMaster.
	 *
	 * @param billingChart the billingChart to set
	 */
	public void setBillingChart(List<BillingChart> billingChart) {
		this.billingChart = billingChart;
	}
	
}
