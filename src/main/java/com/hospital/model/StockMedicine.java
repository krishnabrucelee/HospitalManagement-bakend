/**
 * 
 */
package com.hospital.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "stockMedicine")
public class StockMedicine {
	
	@Column(name = "stock_medicine_id")	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer stockMedicineId;
	
	@Column(name = "item_name")	
	private String itemName;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_stock_medicine_FK"))
	private Department department;
	
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
	private Double purchasePrice;
	
	@Column(name = "sales_price")	
	private Double salesPrice;

	@OneToOne(targetEntity = PurchaseOrder.class)
	@JoinColumn(name = "purchaseOrder_id", referencedColumnName = "purchase_order_id", foreignKey = @ForeignKey(name = "purchaseOrder_STK_FK"))
	private PurchaseOrder purchaseOrder;
	
	@OneToOne(targetEntity = MaterialRecieveNote.class)
	@JoinColumn(name = "materialRecieveNote", referencedColumnName = "material_recieve_note_id", foreignKey = @ForeignKey(name = "materialRecieveNote_STK_FK"))
	private MaterialRecieveNote materialRecieveNote;
	
	@OneToOne(targetEntity = MaterialRecieveNote.class)
	@JoinColumn(name = "medicineItemMaster")
	private MedicineItemMaster medicineItemMaster;
	
	@OneToMany(cascade = CascadeType.ALL)
	List<StockMedicineTransaction> stockMedicineTransaction;
	
	/**
	 * Get the stockMedicineId of StockMedicine.
	 *
	 * @return the stockMedicineId
	 */
	public Integer getStockMedicineId() {
		return stockMedicineId;
	}

	/**
	 * Set the stockMedicineId of StockMedicine.
	 *
	 * @param stockMedicineId the stockMedicineId to set
	 */
	public void setStockMedicineId(Integer stockMedicineId) {
		this.stockMedicineId = stockMedicineId;
	}

	/**
	 * Get the itemName of StockMedicine.
	 *
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Set the itemName of StockMedicine.
	 *
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * Get the purchaseDate of StockMedicine.
	 *
	 * @return the purchaseDate
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * Set the purchaseDate of StockMedicine.
	 *
	 * @param purchaseDate the purchaseDate to set
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	/**
	 * Get the batchId of StockMedicine.
	 *
	 * @return the batchId
	 */
	public String getBatchId() {
		return batchId;
	}

	/**
	 * Set the batchId of StockMedicine.
	 *
	 * @param batchId the batchId to set
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	/**
	 * Get the manufactureDate of StockMedicine.
	 *
	 * @return the manufactureDate
	 */
	public Date getManufactureDate() {
		return manufactureDate;
	}

	/**
	 * Set the manufactureDate of StockMedicine.
	 *
	 * @param manufactureDate the manufactureDate to set
	 */
	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	/**
	 * Get the expiryDate of StockMedicine.
	 *
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * Set the expiryDate of StockMedicine.
	 *
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * Get the itemUnits of StockMedicine.
	 *
	 * @return the itemUnits
	 */
	public String getItemUnits() {
		return itemUnits;
	}

	/**
	 * Set the itemUnits of StockMedicine.
	 *
	 * @param itemUnits the itemUnits to set
	 */
	public void setItemUnits(String itemUnits) {
		this.itemUnits = itemUnits;
	}

	/**
	 * Get the numbersInUnit of StockMedicine.
	 *
	 * @return the numbersInUnit
	 */
	public Integer getNumbersInUnit() {
		return numbersInUnit;
	}

	/**
	 * Set the numbersInUnit of StockMedicine.
	 *
	 * @param numbersInUnit the numbersInUnit to set
	 */
	public void setNumbersInUnit(Integer numbersInUnit) {
		this.numbersInUnit = numbersInUnit;
	}

	/**
	 * Get the numberofUnits of StockMedicine.
	 *
	 * @return the numberofUnits
	 */
	public Integer getNumberofUnits() {
		return numberofUnits;
	}

	/**
	 * Set the numberofUnits of StockMedicine.
	 *
	 * @param numberofUnits the numberofUnits to set
	 */
	public void setNumberofUnits(Integer numberofUnits) {
		this.numberofUnits = numberofUnits;
	}

	/**
	 * Get the quantity of StockMedicine.
	 *
	 * @return the quantity
	 */
	public Long getQuantity() {
		return quantity;
	}

	/**
	 * Set the quantity of StockMedicine.
	 *
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}


	/**
	 * Get the purchasePrice of StockMedicine.
	 *
	 * @return the purchasePrice
	 */
	public Double getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * Set the purchasePrice of StockMedicine.
	 *
	 * @param purchasePrice the purchasePrice to set
	 */
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/**
	 * Get the salesPrice of StockMedicine.
	 *
	 * @return the salesPrice
	 */
	public Double getSalesPrice() {
		return salesPrice;
	}

	/**
	 * Set the salesPrice of StockMedicine.
	 *
	 * @param salesPrice the salesPrice to set
	 */
	public void setSalesPrice(Double salesPrice) {
		this.salesPrice = salesPrice;
	}

	/**
	 * Get the department of StockMedicine.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of StockMedicine.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the stockMedicineTransaction of StockMedicine.
	 *
	 * @return the stockMedicineTransaction
	 */
	public List<StockMedicineTransaction> getStockMedicineTransaction() {
		return stockMedicineTransaction;
	}

	/**
	 * Set the stockMedicineTransaction of StockMedicine.
	 *
	 * @param stockMedicineTransaction the stockMedicineTransaction to set
	 */
	public void setStockMedicineTransaction(List<StockMedicineTransaction> stockMedicineTransaction) {
		this.stockMedicineTransaction = stockMedicineTransaction;
	}

	/**
	 * Get the purchaseOrder of StockMedicine.
	 *
	 * @return the purchaseOrder
	 */
	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	/**
	 * Set the purchaseOrder of StockMedicine.
	 *
	 * @param purchaseOrder the purchaseOrder to set
	 */
	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	/**
	 * Get the materialRecieveNote of StockMedicine.
	 *
	 * @return the materialRecieveNote
	 */
	public MaterialRecieveNote getMaterialRecieveNote() {
		return materialRecieveNote;
	}

	/**
	 * Set the materialRecieveNote of StockMedicine.
	 *
	 * @param materialRecieveNote the materialRecieveNote to set
	 */
	public void setMaterialRecieveNote(MaterialRecieveNote materialRecieveNote) {
		this.materialRecieveNote = materialRecieveNote;
	}

	/**
	 * Get the medicineItemMaster of StockMedicine.
	 *
	 * @return the medicineItemMaster
	 */
	public MedicineItemMaster getMedicineItemMaster() {
		return medicineItemMaster;
	}

	/**
	 * Set the medicineItemMaster of StockMedicine.
	 *
	 * @param medicineItemMaster the medicineItemMaster to set
	 */
	public void setMedicineItemMaster(MedicineItemMaster medicineItemMaster) {
		this.medicineItemMaster = medicineItemMaster;
	}
	
}
