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
@Table(name="invoice")
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="invoice_id")
	private Integer invoiceId;
	
	@OneToOne(targetEntity = Patient.class,cascade=CascadeType.ALL)
	@JoinColumn(name ="patient_id", referencedColumnName="patient_id", foreignKey=@ForeignKey(name="patient_invoice_FK"))
	private Patient  patient;

	@Column(name="patient_address")
	private String patientAddress;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_invoice_FK"))
	private Department department;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "invoice_date")
	private Date invoiceDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "due_date")
	private Date dueDate;
	
	@Column(name = "payment_method")
	private String paymentMethod;
	
	@OneToMany(cascade=CascadeType.ALL)	
	List<InvoiceItems> invoiceItems;

	/**
	 * Get the invoiceId of Invoice.
	 *
	 * @return the invoiceId
	 */
	public Integer getInvoiceId() {
		return invoiceId;
	}

	/**
	 * Set the invoiceId of Invoice.
	 *
	 * @param invoiceId the invoiceId to set
	 */
	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	/**
	 * Get the patient of Invoice.
	 *
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Set the patient of Invoice.
	 *
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Get the patientAddress of Invoice.
	 *
	 * @return the patientAddress
	 */
	public String getPatientAddress() {
		return patientAddress;
	}

	/**
	 * Set the patientAddress of Invoice.
	 *
	 * @param patientAddress the patientAddress to set
	 */
	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	/**
	 * Get the department of Invoice.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of Invoice.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the invoiceDate of Invoice.
	 *
	 * @return the invoiceDate
	 */
	public Date getInvoiceDate() {
		return invoiceDate;
	}

	/**
	 * Set the invoiceDate of Invoice.
	 *
	 * @param invoiceDate the invoiceDate to set
	 */
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	/**
	 * Get the dueDate of Invoice.
	 *
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * Set the dueDate of Invoice.
	 *
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * Get the paymentMethod of Invoice.
	 *
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * Set the paymentMethod of Invoice.
	 *
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * Get the invoiceItems of Invoice.
	 *
	 * @return the invoiceItems
	 */
	public List<InvoiceItems> getInvoiceItems() {
		return invoiceItems;
	}

	/**
	 * Set the invoiceItems of Invoice.
	 *
	 * @param invoiceItems the invoiceItems to set
	 */
	public void setInvoiceItems(List<InvoiceItems> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}
	
	
}
