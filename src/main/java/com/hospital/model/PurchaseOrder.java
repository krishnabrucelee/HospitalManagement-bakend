/**
 * 
 */
package com.hospital.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "purchaseOrder")
public class PurchaseOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "purchase_order_id")
	private Integer purchaseOrderId;
	
	@OneToOne(targetEntity = StockLedger.class)
	@JoinColumn(name = "stock_ledger_id", referencedColumnName = "stock_ledger_id", foreignKey = @ForeignKey(name = "stock_ledger_PURODR_FK"))
	private StockLedger stockLedger;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_PURODR_FK"))
	private Department department;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "issued_date")
	private Date issuedDate;
	
	@Column(name = "supplier_company_name")
	private String supplierCompanyName;
	
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "item_description")
	private String itemDescription;
	
	@Column(name = "item_count")
	private Integer itemCount;
	
	@Column(name = "price")
	private Double price;

	/**
	 * Get the purchaseOrderId of PurchaseOrder.
	 *
	 * @return the purchaseOrderId
	 */
	public Integer getPurchaseOrderId() {
		return purchaseOrderId;
	}

	/**
	 * Set the purchaseOrderId of PurchaseOrder.
	 *
	 * @param purchaseOrderId the purchaseOrderId to set
	 */
	public void setPurchaseOrderId(Integer purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	/**
	 * Get the stockLedger of PurchaseOrder.
	 *
	 * @return the stockLedger
	 */
	public StockLedger getStockLedger() {
		return stockLedger;
	}

	/**
	 * Set the stockLedger of PurchaseOrder.
	 *
	 * @param stockLedger the stockLedger to set
	 */
	public void setStockLedger(StockLedger stockLedger) {
		this.stockLedger = stockLedger;
	}

	/**
	 * Get the department of PurchaseOrder.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of PurchaseOrder.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the issuedDate of PurchaseOrder.
	 *
	 * @return the issuedDate
	 */
	public Date getIssuedDate() {
		return issuedDate;
	}

	/**
	 * Set the issuedDate of PurchaseOrder.
	 *
	 * @param issuedDate the issuedDate to set
	 */
	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	/**
	 * Get the supplierCompanyName of PurchaseOrder.
	 *
	 * @return the supplierCompanyName
	 */
	public String getSupplierCompanyName() {
		return supplierCompanyName;
	}

	/**
	 * Set the supplierCompanyName of PurchaseOrder.
	 *
	 * @param supplierCompanyName the supplierCompanyName to set
	 */
	public void setSupplierCompanyName(String supplierCompanyName) {
		this.supplierCompanyName = supplierCompanyName;
	}

	/**
	 * Get the itemName of PurchaseOrder.
	 *
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Set the itemName of PurchaseOrder.
	 *
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * Get the itemDescription of PurchaseOrder.
	 *
	 * @return the itemDescription
	 */
	public String getItemDescription() {
		return itemDescription;
	}

	/**
	 * Set the itemDescription of PurchaseOrder.
	 *
	 * @param itemDescription the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	/**
	 * Get the itemCount of PurchaseOrder.
	 *
	 * @return the itemCount
	 */
	public Integer getItemCount() {
		return itemCount;
	}

	/**
	 * Set the itemCount of PurchaseOrder.
	 *
	 * @param itemCount the itemCount to set
	 */
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	/**
	 * Get the price of PurchaseOrder.
	 *
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Set the price of PurchaseOrder.
	 *
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	
}
