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
@Table(name = "salesRecieptItems")
public class SalesRecieptItems {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sales_reciept_items_id")
	private Integer salesRecieptItemsId;
    
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "item_description")
	private String itemDescription;
	
	@Column(name = "item_quantity")
	private Integer itemQuantity;
	
	@Column(name = "item_amount")
	private Double itemAmount;

	/**
	 * Get the salesRecieptItemsId of SalesRecieptItems.
	 *
	 * @return the salesRecieptItemsId
	 */
	public Integer getSalesRecieptItemsId() {
		return salesRecieptItemsId;
	}

	/**
	 * Set the salesRecieptItemsId of SalesRecieptItems.
	 *
	 * @param salesRecieptItemsId the salesRecieptItemsId to set
	 */
	public void setSalesRecieptItemsId(Integer salesRecieptItemsId) {
		this.salesRecieptItemsId = salesRecieptItemsId;
	}

	/**
	 * Get the itemName of SalesRecieptItems.
	 *
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Set the itemName of SalesRecieptItems.
	 *
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * Get the itemDescription of SalesRecieptItems.
	 *
	 * @return the itemDescription
	 */
	public String getItemDescription() {
		return itemDescription;
	}

	/**
	 * Set the itemDescription of SalesRecieptItems.
	 *
	 * @param itemDescription the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	/**
	 * Get the itemQuantity of SalesRecieptItems.
	 *
	 * @return the itemQuantity
	 */
	public Integer getItemQuantity() {
		return itemQuantity;
	}

	/**
	 * Set the itemQuantity of SalesRecieptItems.
	 *
	 * @param itemQuantity the itemQuantity to set
	 */
	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	/**
	 * Get the itemAmount of SalesRecieptItems.
	 *
	 * @return the itemAmount
	 */
	public Double getItemAmount() {
		return itemAmount;
	}

	/**
	 * Set the itemAmount of SalesRecieptItems.
	 *
	 * @param itemAmount the itemAmount to set
	 */
	public void setItemAmount(Double itemAmount) {
		this.itemAmount = itemAmount;
	}
	
}
