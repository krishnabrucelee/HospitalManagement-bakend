/**
 * 
 */
package com.hospital.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "expenseApproval")
public class ExpenseApproval {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="expense_approval_id")
	private Integer expenseApprovalId;
	
	@Column(name = "expense_date")
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expenseDate;
	
	@Column(name = "expense_description")
	private String expenseDescription;
	
	@Column(name = "product_type")
	private String productType;
	
	@Column(name = "expense_amount")
	private Double expenseAmount;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_EXPAP_FK"))
	private Department department;
	
	@Column(name = "status", columnDefinition = "varchar(20) default 'OPEN'")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public enum Status {
		OPEN,
		APPROVED
	}
	
	@Column(name = "purchase_id")
	private Integer purchaseId;

	/**
	 * Get the expenseApprovalId of ExpenseApproval.
	 *
	 * @return the expenseApprovalId
	 */
	public Integer getExpenseApprovalId() {
		return expenseApprovalId;
	}

	/**
	 * Set the expenseApprovalId of ExpenseApproval.
	 *
	 * @param expenseApprovalId the expenseApprovalId to set
	 */
	public void setExpenseApprovalId(Integer expenseApprovalId) {
		this.expenseApprovalId = expenseApprovalId;
	}

	/**
	 * Get the expenseDate of ExpenseApproval.
	 *
	 * @return the expenseDate
	 */
	public Date getExpenseDate() {
		return expenseDate;
	}

	/**
	 * Set the expenseDate of ExpenseApproval.
	 *
	 * @param expenseDate the expenseDate to set
	 */
	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	/**
	 * Get the expenseDescription of ExpenseApproval.
	 *
	 * @return the expenseDescription
	 */
	public String getExpenseDescription() {
		return expenseDescription;
	}

	/**
	 * Set the expenseDescription of ExpenseApproval.
	 *
	 * @param expenseDescription the expenseDescription to set
	 */
	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}

	/**
	 * Get the productType of ExpenseApproval.
	 *
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * Set the productType of ExpenseApproval.
	 *
	 * @param productType the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * Get the expenseAmount of ExpenseApproval.
	 *
	 * @return the expenseAmount
	 */
	public Double getExpenseAmount() {
		return expenseAmount;
	}

	/**
	 * Set the expenseAmount of ExpenseApproval.
	 *
	 * @param expenseAmount the expenseAmount to set
	 */
	public void setExpenseAmount(Double expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	/**
	 * Get the department of ExpenseApproval.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of ExpenseApproval.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the status of ExpenseApproval.
	 *
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Set the status of ExpenseApproval.
	 *
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * Get the purchase_id of ExpenseApproval.
	 *
	 * @return the purchase_id
	 */
	public Integer getPurchaseId() {
		return purchaseId;
	}

	/**
	 * Set the purchase_id of ExpenseApproval.
	 *
	 * @param purchase_id the purchase_id to set
	 */
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}

}
