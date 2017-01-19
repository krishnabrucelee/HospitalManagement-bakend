/**
 * 
 */
package com.hospital.model;

import java.util.Date;

import javax.persistence.CascadeType;
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
@Table (name = "billingTransaction")
public class BillingTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="billing_transaction_id")
	private Integer billingTransactionId;
	
	@Column(name = "billing_transaction_ref_number")
	private String billingTransactionRefNumber;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_billingTransaction_FK"))
	private Department department;
	
	@OneToOne(targetEntity = Patient.class,cascade=CascadeType.ALL)
	@JoinColumn(name ="patient_id", referencedColumnName="patient_id", foreignKey=@ForeignKey(name="patient_billingTransaction_FK"))
	private Patient  patient;
	
	@Column(name = "module_service")
	private String moduleService;
	
	@Column(name = "service_amount")
	private Double serviceAmount;
	
	@Column(name = "amount_paid")
	private Double amountPaid;
	
	@Column(name = "total_amount")
	private Double totalAmount;
	
	@Column(name = "is_paid")
	private IsPaid isPaid;
	
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	public enum IsPaid {

		PAID, UNPAID
	}

	/**
	 * Get the billingTransactionId of BillingTransaction.
	 *
	 * @return the billingTransactionId
	 */
	public Integer getBillingTransactionId() {
		return billingTransactionId;
	}

	/**
	 * Set the billingTransactionId of BillingTransaction.
	 *
	 * @param billingTransactionId the billingTransactionId to set
	 */
	public void setBillingTransactionId(Integer billingTransactionId) {
		this.billingTransactionId = billingTransactionId;
	}

	/**
	 * Get the department of BillingTransaction.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of BillingTransaction.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the patient of BillingTransaction.
	 *
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Set the patient of BillingTransaction.
	 *
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Get the serviceAmount of BillingTransaction.
	 *
	 * @return the serviceAmount
	 */
	public Double getServiceAmount() {
		return serviceAmount;
	}

	/**
	 * Set the serviceAmount of BillingTransaction.
	 *
	 * @param serviceAmount the serviceAmount to set
	 */
	public void setServiceAmount(Double serviceAmount) {
		this.serviceAmount = serviceAmount;
	}

	/**
	 * Get the amountPaid of BillingTransaction.
	 *
	 * @return the amountPaid
	 */
	public Double getAmountPaid() {
		return amountPaid;
	}

	/**
	 * Set the amountPaid of BillingTransaction.
	 *
	 * @param amountPaid the amountPaid to set
	 */
	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	/**
	 * Get the totalAmount of BillingTransaction.
	 *
	 * @return the totalAmount
	 */
	public Double getTotalAmount() {
		return totalAmount;
	}

	/**
	 * Set the totalAmount of BillingTransaction.
	 *
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * Get the isPaid of BillingTransaction.
	 *
	 * @return the isPaid
	 */
	public IsPaid getIsPaid() {
		return isPaid;
	}

	/**
	 * Set the isPaid of BillingTransaction.
	 *
	 * @param isPaid the isPaid to set
	 */
	public void setIsPaid(IsPaid isPaid) {
		this.isPaid = isPaid;
	}

	/**
	 * Get the createdDate of BillingTransaction.
	 *
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Set the createdDate of BillingTransaction.
	 *
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Get the billingTransactionRefNumber of BillingTransaction.
	 *
	 * @return the billingTransactionRefNumber
	 */
	public String getBillingTransactionRefNumber() {
		return billingTransactionRefNumber;
	}

	/**
	 * Set the billingTransactionRefNumber of BillingTransaction.
	 *
	 * @param billingTransactionRefNumber the billingTransactionRefNumber to set
	 */
	public void setBillingTransactionRefNumber(String billingTransactionRefNumber) {
		this.billingTransactionRefNumber = billingTransactionRefNumber;
	}

	/**
	 * Get the moduleService of BillingTransaction.
	 *
	 * @return the moduleService
	 */
	public String getModuleService() {
		return moduleService;
	}

	/**
	 * Set the moduleService of BillingTransaction.
	 *
	 * @param moduleService the moduleService to set
	 */
	public void setModuleService(String moduleService) {
		this.moduleService = moduleService;
	}
	
}
