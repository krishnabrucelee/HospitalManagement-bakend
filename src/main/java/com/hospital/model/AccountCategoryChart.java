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
@Table(name = "accountCategoryChart")
public class AccountCategoryChart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_category_chart_id")
	private Integer accountCategoryChartId;
	
	@Column(name="name")
	private String name; 
	
	@Column(name="description")
	private String description;
	
	@Column(name="category_type")
	private String categoryType;
	
	@Column(name="detail_Type")
	private String detailType;
	
	@Column(name="quick_book_balance")
	private String quickBookBalance;
	
	@Column(name="bank_balance")
	private String bankBalance;


	/**
	 * Get the accountCategoryChartId of AccountCategoryChart.
	 *
	 * @return the accountCategoryChartId
	 */
	public Integer getAccountCategoryChartId() {
		return accountCategoryChartId;
	}

	/**
	 * Set the accountCategoryChartId of AccountCategoryChart.
	 *
	 * @param accountCategoryChartId the accountCategoryChartId to set
	 */
	public void setAccountCategoryChartId(Integer accountCategoryChartId) {
		this.accountCategoryChartId = accountCategoryChartId;
	}

	/**
	 * Get the name of AccountCategoryChart.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of AccountCategoryChart.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the description of AccountCategoryChart.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description of AccountCategoryChart.
	 *
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the categoryType of AccountCategoryChart.
	 *
	 * @return the categoryType
	 */
	public String getCategoryType() {
		return categoryType;
	}

	/**
	 * Set the categoryType of AccountCategoryChart.
	 *
	 * @param categoryType the categoryType to set
	 */
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	/**
	 * Get the detailType of AccountCategoryChart.
	 *
	 * @return the detailType
	 */
	public String getDetailType() {
		return detailType;
	}

	/**
	 * Set the detailType of AccountCategoryChart.
	 *
	 * @param detailType the detailType to set
	 */
	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}

	/**
	 * Get the quickBookBalance of AccountCategoryChart.
	 *
	 * @return the quickBookBalance
	 */
	public String getQuickBookBalance() {
		return quickBookBalance;
	}

	/**
	 * Set the quickBookBalance of AccountCategoryChart.
	 *
	 * @param quickBookBalance the quickBookBalance to set
	 */
	public void setQuickBookBalance(String quickBookBalance) {
		this.quickBookBalance = quickBookBalance;
	}

	/**
	 * Get the bankBalance of AccountCategoryChart.
	 *
	 * @return the bankBalance
	 */
	public String getBankBalance() {
		return bankBalance;
	}

	/**
	 * Set the bankBalance of AccountCategoryChart.
	 *
	 * @param bankBalance the bankBalance to set
	 */
	public void setBankBalance(String bankBalance) {
		this.bankBalance = bankBalance;
	}
	
}
