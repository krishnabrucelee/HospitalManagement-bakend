package com.hospital.model;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table
public class PharmacyBill implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer pharmacyBillId;
	
	@ManyToOne
	@JoinColumn(name="staffId",foreignKey=@ForeignKey(name="PharmacyBill_Staff_FK"))
	private Staff staff;
	
	@Column
	private Date billDate;
	
	private String billNumber;
	
	@ManyToOne()
	@JoinColumn(name="patientId",foreignKey=@ForeignKey(name="PharmacyBill_Patient_FK"))
	private Patient patient;
	
	@ManyToOne()
	@JoinColumn(name="doctorId",foreignKey=@ForeignKey(name="PharmacyBill_Doctor_FK"))
	private Doctor doctor;
	
	@Column
	private String generatedBy;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)	
	@JoinColumn(name="pharmacyBillId",foreignKey=@ForeignKey(name="pharmacyBill_MedicineList_FK"))
	private List<PatientMedicineList> patientMedicineList;
	
	private int discountPercent;
	
	private int discount_amount;
	
	private int taxPercent;
	
	private int tax_amount;
	
	private int totalAmount;
	
	private int netTotalAmount;
	
	
	public PharmacyBill() {
		super();
	}

	public Integer getPharmacyBillId() {
		return pharmacyBillId;
	}

	public void setPharmacyBillId(Integer pharmacyBillId) {
		this.pharmacyBillId = pharmacyBillId;
	}

	

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	

	public String getGeneratedBy() {
		return generatedBy;
	}

	public void setGeneratedBy(String generatedBy) {
		this.generatedBy = generatedBy;
	}

	public List<PatientMedicineList> getPatientMedicineList() {
		return patientMedicineList;
	}

	public void setPatientMedicineList(List<PatientMedicineList> patientMedicineList) {
		this.patientMedicineList = patientMedicineList;
	}

	/*public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}*/
	

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public double getDiscountPercent() {
		return discountPercent;
	}

	
	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public int getDiscount_amount() {
		return discount_amount;
	}

	public void setDiscount_amount(int discount_amount) {
		this.discount_amount = discount_amount;
	}

	public int getTaxPercent() {
		return taxPercent;
	}

	public void setTaxPercent(int taxPercent) {
		this.taxPercent = taxPercent;
	}

	public int getTax_amount() {
		return tax_amount;
	}

	public void setTax_amount(int tax_amount) {
		this.tax_amount = tax_amount;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getNetTotalAmount() {
		return netTotalAmount;
	}

	public void setNetTotalAmount(int netTotalAmount) {
		this.netTotalAmount = netTotalAmount;
	}

	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}

	
	
				
}
