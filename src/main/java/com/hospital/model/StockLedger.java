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
@Table(name = "stockLedger")
public class StockLedger {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "stock_ledger_id")
	private Integer stockLedgerId;
	
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "supplier_company_name")
	private String supplierCompanyName;
	
	@Column(name = "stock")
	private Integer stock;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "purchase_date")
	private Date purchaseDate;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "expire_date")
	private Date expireDate;
	
	@Column(name = "stock_count")
	private Integer stockCount;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "product_type")
	private String productType;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_STKLED_FK"))
	private Department department;

	/**
	 * Get the stockLedgerId of StockLedger.
	 *
	 * @return the stockLedgerId
	 */
	public Integer getStockLedgerId() {
		return stockLedgerId;
	}

	/**
	 * Set the stockLedgerId of StockLedger.
	 *
	 * @param stockLedgerId the stockLedgerId to set
	 */
	public void setStockLedgerId(Integer stockLedgerId) {
		this.stockLedgerId = stockLedgerId;
	}

	/**
	 * Get the itemName of StockLedger.
	 *
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Set the itemName of StockLedger.
	 *
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * Get the supplierCompanyName of StockLedger.
	 *
	 * @return the supplierCompanyName
	 */
	public String getSupplierCompanyName() {
		return supplierCompanyName;
	}

	/**
	 * Set the supplierCompanyName of StockLedger.
	 *
	 * @param supplierCompanyName the supplierCompanyName to set
	 */
	public void setSupplierCompanyName(String supplierCompanyName) {
		this.supplierCompanyName = supplierCompanyName;
	}

	/**
	 * Get the stock of StockLedger.
	 *
	 * @return the stock
	 */
	public Integer getStock() {
		return stock;
	}

	/**
	 * Set the stock of StockLedger.
	 *
	 * @param stock the stock to set
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	/**
	 * Get the purchaseDate of StockLedger.
	 *
	 * @return the purchaseDate
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * Set the purchaseDate of StockLedger.
	 *
	 * @param purchaseDate the purchaseDate to set
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	/**
	 * Get the expireDate of StockLedger.
	 *
	 * @return the expireDate
	 */
	public Date getExpireDate() {
		return expireDate;
	}

	/**
	 * Set the expireDate of StockLedger.
	 *
	 * @param expireDate the expireDate to set
	 */
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * Get the stockCount of StockLedger.
	 *
	 * @return the stockCount
	 */
	public Integer getStockCount() {
		return stockCount;
	}

	/**
	 * Set the stockCount of StockLedger.
	 *
	 * @param stockCount the stockCount to set
	 */
	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}

	/**
	 * Get the price of StockLedger.
	 *
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Set the price of StockLedger.
	 *
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Get the productType of StockLedger.
	 *
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * Set the productType of StockLedger.
	 *
	 * @param productType the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * Get the department of StockLedger.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of StockLedger.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
