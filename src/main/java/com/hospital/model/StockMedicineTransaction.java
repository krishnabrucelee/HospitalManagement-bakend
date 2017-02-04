/**
 * 
 */
package com.hospital.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "stockMedicineTransaction")
public class StockMedicineTransaction {

	@Column(name = "stock_medicine_transaction_id")	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long stockMedicineTransactionId;
	
	@Column(name = "item_name")	
	private String itemName;
	
	@Column(name = "department_id")
	private Integer departmentId;
	
	@Column(name = "purchase_date")	
	@Temporal(TemporalType.DATE)
	private Date purchaseDate;
	
	@Column(name = "batch_id")	
	private String batchId;
	
	@Column(name = "manufacture_date")	
	@Temporal(TemporalType.DATE)
	private Date manufactureDate;
	
	@Column(name = "expiry_date")	
	@Temporal(TemporalType.DATE)
	private Date expiryDate;
	
	@Column(name = "item_units")	
	private String itemUnits;
	
	@Column(name = "numbers_in_unit")	
	private Integer numbersInUnit;
	
	@Column(name = "number_of_units")	
	private Integer numberofUnits;
	
	@Column(name = "quantity")	
	private Long quantity;

	@Column(name = "price")	
	private Double price;
	
	@OneToOne(targetEntity = PharmacyRequestMedicine.class)
	@JoinColumn(name = "pharmacyRequestMedicineId", foreignKey = @ForeignKey(name = "pharmacyRequestMedicine_stock_medicine_transaction_FK"))
	private PharmacyRequestMedicine pharmacyRequestMedicine;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	/**
	 * Get the stockMedicineTransactionId of StockMedicineTransaction.
	 *
	 * @return the stockMedicineTransactionId
	 */
	public Long getStockMedicineTransactionId() {
		return stockMedicineTransactionId;
	}

	/**
	 * Set the stockMedicineTransactionId of StockMedicineTransaction.
	 *
	 * @param stockMedicineTransactionId the stockMedicineTransactionId to set
	 */
	public void setStockMedicineTransactionId(Long stockMedicineTransactionId) {
		this.stockMedicineTransactionId = stockMedicineTransactionId;
	}

	/**
	 * Get the itemName of StockMedicineTransaction.
	 *
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Set the itemName of StockMedicineTransaction.
	 *
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	/**
	 * Get the departmentId of StockMedicineTransaction.
	 *
	 * @return the departmentId
	 */
	public Integer getDepartmentId() {
		return departmentId;
	}

	/**
	 * Set the departmentId of StockMedicineTransaction.
	 *
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * Get the purchaseDate of StockMedicineTransaction.
	 *
	 * @return the purchaseDate
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * Set the purchaseDate of StockMedicineTransaction.
	 *
	 * @param purchaseDate the purchaseDate to set
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	/**
	 * Get the batchId of StockMedicineTransaction.
	 *
	 * @return the batchId
	 */
	public String getBatchId() {
		return batchId;
	}

	/**
	 * Set the batchId of StockMedicineTransaction.
	 *
	 * @param batchId the batchId to set
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	/**
	 * Get the manufactureDate of StockMedicineTransaction.
	 *
	 * @return the manufactureDate
	 */
	public Date getManufactureDate() {
		return manufactureDate;
	}

	/**
	 * Set the manufactureDate of StockMedicineTransaction.
	 *
	 * @param manufactureDate the manufactureDate to set
	 */
	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	/**
	 * Get the expiryDate of StockMedicineTransaction.
	 *
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * Set the expiryDate of StockMedicineTransaction.
	 *
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * Get the itemUnits of StockMedicineTransaction.
	 *
	 * @return the itemUnits
	 */
	public String getItemUnits() {
		return itemUnits;
	}

	/**
	 * Set the itemUnits of StockMedicineTransaction.
	 *
	 * @param itemUnits the itemUnits to set
	 */
	public void setItemUnits(String itemUnits) {
		this.itemUnits = itemUnits;
	}

	/**
	 * Get the numbersInUnit of StockMedicineTransaction.
	 *
	 * @return the numbersInUnit
	 */
	public Integer getNumbersInUnit() {
		return numbersInUnit;
	}

	/**
	 * Set the numbersInUnit of StockMedicineTransaction.
	 *
	 * @param numbersInUnit the numbersInUnit to set
	 */
	public void setNumbersInUnit(Integer numbersInUnit) {
		this.numbersInUnit = numbersInUnit;
	}

	/**
	 * Get the numberofUnits of StockMedicineTransaction.
	 *
	 * @return the numberofUnits
	 */
	public Integer getNumberofUnits() {
		return numberofUnits;
	}

	/**
	 * Set the numberofUnits of StockMedicineTransaction.
	 *
	 * @param numberofUnits the numberofUnits to set
	 */
	public void setNumberofUnits(Integer numberofUnits) {
		this.numberofUnits = numberofUnits;
	}

	/**
	 * Get the quantity of StockMedicineTransaction.
	 *
	 * @return the quantity
	 */
	public Long getQuantity() {
		return quantity;
	}

	/**
	 * Set the quantity of StockMedicineTransaction.
	 *
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	/**
	 * Get the price of StockMedicineTransaction.
	 *
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Set the price of StockMedicineTransaction.
	 *
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Get the pharmacyRequestMedicine of StockMedicineTransaction.
	 *
	 * @return the pharmacyRequestMedicine
	 */
	public PharmacyRequestMedicine getPharmacyRequestMedicine() {
		return pharmacyRequestMedicine;
	}

	/**
	 * Set the pharmacyRequestMedicine of StockMedicineTransaction.
	 *
	 * @param pharmacyRequestMedicine the pharmacyRequestMedicine to set
	 */
	public void setPharmacyRequestMedicine(PharmacyRequestMedicine pharmacyRequestMedicine) {
		this.pharmacyRequestMedicine = pharmacyRequestMedicine;
	}

	/**
	 * Get the createdDate of StockMedicineTransaction.
	 *
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Set the createdDate of StockMedicineTransaction.
	 *
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
