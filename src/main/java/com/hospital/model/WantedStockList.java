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
@Table(name = "wantedStockList")
public class WantedStockList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "wanted_stock_list_id")
	private Integer wantedStockListId;
	
	@Column(name="wanted_stock_list_name")
	private String wantedStockListName;
	
	@Column(name="wanted_stock_list_description")
	private String wantedStockListDescription;

	/**
	 * Get the wantedStockListId of WantedStockList.
	 *
	 * @return the wantedStockListId
	 */
	public Integer getWantedStockListId() {
		return wantedStockListId;
	}

	/**
	 * Set the wantedStockListId of WantedStockList.
	 *
	 * @param wantedStockListId the wantedStockListId to set
	 */
	public void setWantedStockListId(Integer wantedStockListId) {
		this.wantedStockListId = wantedStockListId;
	}

	/**
	 * Get the wantedStockListName of WantedStockList.
	 *
	 * @return the wantedStockListName
	 */
	public String getWantedStockListName() {
		return wantedStockListName;
	}

	/**
	 * Set the wantedStockListName of WantedStockList.
	 *
	 * @param wantedStockListName the wantedStockListName to set
	 */
	public void setWantedStockListName(String wantedStockListName) {
		this.wantedStockListName = wantedStockListName;
	}

	/**
	 * Get the wantedStockListDescription of WantedStockList.
	 *
	 * @return the wantedStockListDescription
	 */
	public String getWantedStockListDescription() {
		return wantedStockListDescription;
	}

	/**
	 * Set the wantedStockListDescription of WantedStockList.
	 *
	 * @param wantedStockListDescription the wantedStockListDescription to set
	 */
	public void setWantedStockListDescription(String wantedStockListDescription) {
		this.wantedStockListDescription = wantedStockListDescription;
	}
	

}
