/**
 * 
 */
package com.hospital.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "inventoryItems")
public class InventoryItems {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "inventory_items_id")
	private Integer inventoryItemsId;
	
	@Column(name="name")
	private String name; 
	
	@Column(name="description")
	private String description;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_inventory_items_FK"))
	private Department department;
	
	@Column(name="initial_quantity")
	private String initialQuantity;
	
	@Column(name="as_of_date")
	private String as_of_date;
	
	@Column(name="low_stock_alert")
	private String lowStockAlert;

	/**
	 * Get the inventoryItemsId of InventoryItems.
	 *
	 * @return the inventoryItemsId
	 */
	public Integer getInventoryItemsId() {
		return inventoryItemsId;
	}

	/**
	 * Set the inventoryItemsId of InventoryItems.
	 *
	 * @param inventoryItemsId the inventoryItemsId to set
	 */
	public void setInventoryItemsId(Integer inventoryItemsId) {
		this.inventoryItemsId = inventoryItemsId;
	}

	/**
	 * Get the name of InventoryItems.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of InventoryItems.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the description of InventoryItems.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the description of InventoryItems.
	 *
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the department of InventoryItems.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of InventoryItems.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the initialQuantity of InventoryItems.
	 *
	 * @return the initialQuantity
	 */
	public String getInitialQuantity() {
		return initialQuantity;
	}

	/**
	 * Set the initialQuantity of InventoryItems.
	 *
	 * @param initialQuantity the initialQuantity to set
	 */
	public void setInitialQuantity(String initialQuantity) {
		this.initialQuantity = initialQuantity;
	}

	/**
	 * Get the as_of_date of InventoryItems.
	 *
	 * @return the as_of_date
	 */
	public String getAs_of_date() {
		return as_of_date;
	}

	/**
	 * Set the as_of_date of InventoryItems.
	 *
	 * @param as_of_date the as_of_date to set
	 */
	public void setAs_of_date(String as_of_date) {
		this.as_of_date = as_of_date;
	}

	/**
	 * Get the lowStockAlert of InventoryItems.
	 *
	 * @return the lowStockAlert
	 */
	public String getLowStockAlert() {
		return lowStockAlert;
	}

	/**
	 * Set the lowStockAlert of InventoryItems.
	 *
	 * @param lowStockAlert the lowStockAlert to set
	 */
	public void setLowStockAlert(String lowStockAlert) {
		this.lowStockAlert = lowStockAlert;
	}
	
}
