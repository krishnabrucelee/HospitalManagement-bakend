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

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "purchaseOrderTransaction")
public class PurchaseOrderTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "purchase_order_transaction_id")
	private Integer purchaseOrderTransactionId;
	
	@Column(name = "medicine_id")
	private Integer medicineId;
	
	@OneToOne(targetEntity = MedicineItemMaster.class)
	@JoinColumn(name = "medicineItemMaster")
	private MedicineItemMaster medicineItemMaster;
	
	@Column(name = "item_quantity")
	private Integer quantity;
	
	@Column(name = "rate")
	private Integer rate;
	
	@Column(name = "price")
	private Double price;
	
	@OneToOne(targetEntity = MaterialRecieveNote.class)
	@JoinColumn(name = "materialRecieveNote", referencedColumnName = "material_recieve_note_id", foreignKey = @ForeignKey(name = "materialRecieveNote_POT_FK"))
	private MaterialRecieveNote materialRecieveNote;
	
	@Column(name = "ship_to")
	private String shipTo;
	
	@Column(name = "ship_address")
	private String shipingAddress;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	private Date createdDate;

	/**
	 * Get the purchaseOrderTransactionId of PurchaseOrderTransaction.
	 *
	 * @return the purchaseOrderTransactionId
	 */
	public Integer getPurchaseOrderTransactionId() {
		return purchaseOrderTransactionId;
	}

	/**
	 * Set the purchaseOrderTransactionId of PurchaseOrderTransaction.
	 *
	 * @param purchaseOrderTransactionId the purchaseOrderTransactionId to set
	 */
	public void setPurchaseOrderTransactionId(Integer purchaseOrderTransactionId) {
		this.purchaseOrderTransactionId = purchaseOrderTransactionId;
	}

	/**
	 * Get the itemQuantity of PurchaseOrderTransaction.
	 *
	 * @return the itemQuantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * Set the itemQuantity of PurchaseOrderTransaction.
	 *
	 * @param itemQuantity the itemQuantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * Get the rate of PurchaseOrderTransaction.
	 *
	 * @return the rate
	 */
	public Integer getRate() {
		return rate;
	}

	/**
	 * Set the rate of PurchaseOrderTransaction.
	 *
	 * @param rate the rate to set
	 */
	public void setRate(Integer rate) {
		this.rate = rate;
	}

	/**
	 * Get the price of PurchaseOrderTransaction.
	 *
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Set the price of PurchaseOrderTransaction.
	 *
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}


	/**
	 * Get the materialRecieveNote of PurchaseOrderTransaction.
	 *
	 * @return the materialRecieveNote
	 */
	public MaterialRecieveNote getMaterialRecieveNote() {
		return materialRecieveNote;
	}

	/**
	 * Set the materialRecieveNote of PurchaseOrderTransaction.
	 *
	 * @param materialRecieveNote the materialRecieveNote to set
	 */
	public void setMaterialRecieveNote(MaterialRecieveNote materialRecieveNote) {
		this.materialRecieveNote = materialRecieveNote;
	}

	/**
	 * Get the createdDate of PurchaseOrderTransaction.
	 *
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Set the createdDate of PurchaseOrderTransaction.
	 *
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Get the shipTo of PurchaseOrderTransaction.
	 *
	 * @return the shipTo
	 */
	public String getShipTo() {
		return shipTo;
	}

	/**
	 * Set the shipTo of PurchaseOrderTransaction.
	 *
	 * @param shipTo the shipTo to set
	 */
	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}

	/**
	 * Get the shipingAddress of PurchaseOrderTransaction.
	 *
	 * @return the shipingAddress
	 */
	public String getShipingAddress() {
		return shipingAddress;
	}

	/**
	 * Set the shipingAddress of PurchaseOrderTransaction.
	 *
	 * @param shipingAddress the shipingAddress to set
	 */
	public void setShipingAddress(String shipingAddress) {
		this.shipingAddress = shipingAddress;
	}

	/**
	 * Get the medicineId of PurchaseOrderTransaction.
	 *
	 * @return the medicineId
	 */
	public Integer getMedicineId() {
		return medicineId;
	}

	/**
	 * Set the medicineId of PurchaseOrderTransaction.
	 *
	 * @param medicineId the medicineId to set
	 */
	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}

	/**
	 * Get the medicineItemMaster of PurchaseOrderTransaction.
	 *
	 * @return the medicineItemMaster
	 */
	public MedicineItemMaster getMedicineItemMaster() {
		return medicineItemMaster;
	}

	/**
	 * Set the medicineItemMaster of PurchaseOrderTransaction.
	 *
	 * @param medicineItemMaster the medicineItemMaster to set
	 */
	public void setMedicineItemMaster(MedicineItemMaster medicineItemMaster) {
		this.medicineItemMaster = medicineItemMaster;
	}
	
}
