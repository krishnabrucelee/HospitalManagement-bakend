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
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "patientBilling")
public class PatientBilling {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "patient_billing_id")
	private Integer patientBillingId;
	
	@OneToOne(targetEntity = Patient.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", referencedColumnName = "patient_id", foreignKey = @ForeignKey(name = "patient_PBILL_FK"))
	private Patient patient;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_PBILL_FK"))
	private Department department;
	
	@Column(name = "price")
	private Double price;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.DATE)
	@Column(name = "patient_billing_date")
	private Date patientBillingDate;
	
	@OneToOne(targetEntity = LaboratoryBilling.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "laboratory_billing_id", referencedColumnName = "laboratory_billing_id", foreignKey = @ForeignKey(name = "laboratory_billing_PBILL_FK"))
	private LaboratoryBilling laboratoryBilling;

	/**
	 * Get the patientBillingId of PatientBilling.
	 *
	 * @return the patientBillingId
	 */
	public Integer getPatientBillingId() {
		return patientBillingId;
	}

	/**
	 * Set the patientBillingId of PatientBilling.
	 *
	 * @param patientBillingId the patientBillingId to set
	 */
	public void setPatientBillingId(Integer patientBillingId) {
		this.patientBillingId = patientBillingId;
	}

	/**
	 * Get the patient of PatientBilling.
	 *
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Set the patient of PatientBilling.
	 *
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Get the department of PatientBilling.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of PatientBilling.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the price of PatientBilling.
	 *
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Set the price of PatientBilling.
	 *
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Get the patientBillingDate of PatientBilling.
	 *
	 * @return the patientBillingDate
	 */
	public Date getPatientBillingDate() {
		return patientBillingDate;
	}

	/**
	 * Set the patientBillingDate of PatientBilling.
	 *
	 * @param patientBillingDate the patientBillingDate to set
	 */
	public void setPatientBillingDate(Date patientBillingDate) {
		this.patientBillingDate = patientBillingDate;
	}

	/**
	 * Get the laboratoryBilling of PatientBilling.
	 *
	 * @return the laboratoryBilling
	 */
	public LaboratoryBilling getLaboratoryBilling() {
		return laboratoryBilling;
	}

	/**
	 * Set the laboratoryBilling of PatientBilling.
	 *
	 * @param laboratoryBilling the laboratoryBilling to set
	 */
	public void setLaboratoryBilling(LaboratoryBilling laboratoryBilling) {
		this.laboratoryBilling = laboratoryBilling;
	}
	
}
