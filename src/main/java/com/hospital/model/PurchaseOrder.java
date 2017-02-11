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
	
	@Column(name = "purchase_order_ref_number")
	private String purchaseOrderRefNumber;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_PURODR_FK"))
	private Department department;
	
	@OneToOne(targetEntity = Supplier.class)
	@JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id", foreignKey = @ForeignKey(name = "supplier_PURODR_FK"))
	private Supplier supplier;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "purchase_order_date")
	private Date purchaseOrderDate;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<PurchaseOrderTransaction> purchaseOrderTransaction;

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
	 * Get the supplier of PurchaseOrder.
	 *
	 * @return the supplier
	 */
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * Set the supplier of PurchaseOrder.
	 *
	 * @param supplier the supplier to set
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	/**
	 * Get the purchaseOrderDate of PurchaseOrder.
	 *
	 * @return the purchaseOrderDate
	 */
	public Date getPurchaseOrderDate() {
		return purchaseOrderDate;
	}

	/**
	 * Set the purchaseOrderDate of PurchaseOrder.
	 *
	 * @param purchaseOrderDate the purchaseOrderDate to set
	 */
	public void setPurchaseOrderDate(Date purchaseOrderDate) {
		this.purchaseOrderDate = purchaseOrderDate;
	}

	/**
	 * Get the purchaseOrderTransaction of PurchaseOrder.
	 *
	 * @return the purchaseOrderTransaction
	 */
	public List<PurchaseOrderTransaction> getPurchaseOrderTransaction() {
		return purchaseOrderTransaction;
	}

	/**
	 * Set the purchaseOrderTransaction of PurchaseOrder.
	 *
	 * @param purchaseOrderTransaction the purchaseOrderTransaction to set
	 */
	public void setPurchaseOrderTransaction(List<PurchaseOrderTransaction> purchaseOrderTransaction) {
		this.purchaseOrderTransaction = purchaseOrderTransaction;
	}

	/**
	 * Get the purchaseOrderRefNumber of PurchaseOrder.
	 *
	 * @return the purchaseOrderRefNumber
	 */
	public String getPurchaseOrderRefNumber() {
		return purchaseOrderRefNumber;
	}

	/**
	 * Set the purchaseOrderRefNumber of PurchaseOrder.
	 *
	 * @param purchaseOrderRefNumber the purchaseOrderRefNumber to set
	 */
	public void setPurchaseOrderRefNumber(String purchaseOrderRefNumber) {
		this.purchaseOrderRefNumber = purchaseOrderRefNumber;
	}
	
}
