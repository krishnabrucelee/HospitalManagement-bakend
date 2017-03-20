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
@Table(name = "invoiceItems")
public class InvoiceItems {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="invoice_items_id")
	private Integer invoiceItemsId;
    
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "item_description")
	private String itemDescription;
	
	@Column(name = "item_quantity")
	private Integer itemQuantity;
	
	@Column(name = "item_amount")
	private Double itemAmount;

	/**
	 * Get the invoiceItemsId of InvoiceItems.
	 *
	 * @return the invoiceItemsId
	 */
	public Integer getInvoiceItemsId() {
		return invoiceItemsId;
	}

	/**
	 * Set the invoiceItemsId of InvoiceItems.
	 *
	 * @param invoiceItemsId the invoiceItemsId to set
	 */
	public void setInvoiceItemsId(Integer invoiceItemsId) {
		this.invoiceItemsId = invoiceItemsId;
	}

	/**
	 * Get the itemName of InvoiceItems.
	 *
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Set the itemName of InvoiceItems.
	 *
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * Get the itemDescription of InvoiceItems.
	 *
	 * @return the itemDescription
	 */
	public String getItemDescription() {
		return itemDescription;
	}

	/**
	 * Set the itemDescription of InvoiceItems.
	 *
	 * @param itemDescription the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	/**
	 * Get the itemQuantity of InvoiceItems.
	 *
	 * @return the itemQuantity
	 */
	public Integer getItemQuantity() {
		return itemQuantity;
	}

	/**
	 * Set the itemQuantity of InvoiceItems.
	 *
	 * @param itemQuantity the itemQuantity to set
	 */
	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	/**
	 * Get the itemAmount of InvoiceItems.
	 *
	 * @return the itemAmount
	 */
	public Double getItemAmount() {
		return itemAmount;
	}

	/**
	 * Set the itemAmount of InvoiceItems.
	 *
	 * @param itemAmount the itemAmount to set
	 */
	public void setItemAmount(Double itemAmount) {
		this.itemAmount = itemAmount;
	}

}
