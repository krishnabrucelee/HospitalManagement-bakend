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
@Table(name = "materialRecieveNote")
public class MaterialRecieveNote {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "material_recieve_note_id")
	private Integer materialRecieveNoteId;
	
	@Column(name = "mrn_number")
	private String mrnNumber;
	
	@OneToOne(targetEntity = PurchaseOrder.class)
	@JoinColumn(name = "purchaseOrder", referencedColumnName = "purchase_order_id", foreignKey = @ForeignKey(name = "purchaseOrder_MRN_FK"))
	private PurchaseOrder purchaseOrder;
	
	@OneToOne(targetEntity = Supplier.class)
	@JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id", foreignKey = @ForeignKey(name = "supplier_MRN_FK"))
	private Supplier supplier;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	private Date createdDate;

	/**
	 * Get the materialRecieveNoteId of MaterialRecieveNote.
	 *
	 * @return the materialRecieveNoteId
	 */
	public Integer getMaterialRecieveNoteId() {
		return materialRecieveNoteId;
	}

	/**
	 * Set the materialRecieveNoteId of MaterialRecieveNote.
	 *
	 * @param materialRecieveNoteId the materialRecieveNoteId to set
	 */
	public void setMaterialRecieveNoteId(Integer materialRecieveNoteId) {
		this.materialRecieveNoteId = materialRecieveNoteId;
	}

	/**
	 * Get the mrnNumber of MaterialRecieveNote.
	 *
	 * @return the mrnNumber
	 */
	public String getMrnNumber() {
		return mrnNumber;
	}

	/**
	 * Set the mrnNumber of MaterialRecieveNote.
	 *
	 * @param mrnNumber the mrnNumber to set
	 */
	public void setMrnNumber(String mrnNumber) {
		this.mrnNumber = mrnNumber;
	}

	/**
	 * Get the purchaseOrder of MaterialRecieveNote.
	 *
	 * @return the purchaseOrder
	 */
	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	/**
	 * Set the purchaseOrder of MaterialRecieveNote.
	 *
	 * @param purchaseOrder the purchaseOrder to set
	 */
	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	/**
	 * Get the createdDate of MaterialRecieveNote.
	 *
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Set the createdDate of MaterialRecieveNote.
	 *
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Get the supplier of MaterialRecieveNote.
	 *
	 * @return the supplier
	 */
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * Set the supplier of MaterialRecieveNote.
	 *
	 * @param supplier the supplier to set
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
}
