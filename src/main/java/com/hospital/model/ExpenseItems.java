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
@Table(name = "expenseItems")
public class ExpenseItems {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="expense_items_id")
	private Integer expenseItemsId;
    
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "item_description")
	private String itemDescription;
	
	@Column(name = "item_quantity")
	private Integer itemQuantity;
	
	@Column(name = "item_amount")
	private Double itemAmount;

	/**
	 * Get the expenseItemsId of ExpenseItems.
	 *
	 * @return the expenseItemsId
	 */
	public Integer getExpenseItemsId() {
		return expenseItemsId;
	}

	/**
	 * Set the expenseItemsId of ExpenseItems.
	 *
	 * @param expenseItemsId the expenseItemsId to set
	 */
	public void setExpenseItemsId(Integer expenseItemsId) {
		this.expenseItemsId = expenseItemsId;
	}

	/**
	 * Get the itemName of ExpenseItems.
	 *
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Set the itemName of ExpenseItems.
	 *
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * Get the itemDescription of ExpenseItems.
	 *
	 * @return the itemDescription
	 */
	public String getItemDescription() {
		return itemDescription;
	}

	/**
	 * Set the itemDescription of ExpenseItems.
	 *
	 * @param itemDescription the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	/**
	 * Get the itemQuantity of ExpenseItems.
	 *
	 * @return the itemQuantity
	 */
	public Integer getItemQuantity() {
		return itemQuantity;
	}

	/**
	 * Set the itemQuantity of ExpenseItems.
	 *
	 * @param itemQuantity the itemQuantity to set
	 */
	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	/**
	 * Get the itemAmount of ExpenseItems.
	 *
	 * @return the itemAmount
	 */
	public Double getItemAmount() {
		return itemAmount;
	}

	/**
	 * Set the itemAmount of ExpenseItems.
	 *
	 * @param itemAmount the itemAmount to set
	 */
	public void setItemAmount(Double itemAmount) {
		this.itemAmount = itemAmount;
	}
	
}
