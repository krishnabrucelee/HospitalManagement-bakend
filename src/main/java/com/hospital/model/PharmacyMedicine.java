/**
 * 
 */
package com.hospital.model;

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

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "pharmacyMedicine")
public class PharmacyMedicine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pharmacy_medicine_id")
	private Integer pharmacyMedicineId;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<MedicineList> medicineList;
	
	@OneToOne(targetEntity = Patient.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", referencedColumnName = "patient_id", foreignKey = @ForeignKey(name = "patient_PHR_MED_FK"))
	private Patient patient;

	/**
	 * Get the pharmacyMedicineId of PharmacyMedicine.
	 *
	 * @return the pharmacyMedicineId
	 */
	public Integer getPharmacyMedicineId() {
		return pharmacyMedicineId;
	}

	/**
	 * Set the pharmacyMedicineId of PharmacyMedicine.java.
	 *
	 * @param pharmacyMedicineId the pharmacyMedicineId to set
	 */
	public void setPharmacyMedicineId(Integer pharmacyMedicineId) {
		this.pharmacyMedicineId = pharmacyMedicineId;
	}
	
	/**
	 * Get the patient of PharmacyMedicine.
	 *
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Set the patient of PharmacyMedicine.
	 *
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Get the medicineList of PharmacyMedicine.java.
	 *
	 * @return the medicineList
	 */
	public List<MedicineList> getMedicineList() {
		return medicineList;
	}

	/**
	 * Set the medicineList of PharmacyMedicine.java.
	 *
	 * @param medicineList the medicineList to set
	 */
	public void setMedicineList(List<MedicineList> medicineList) {
		this.medicineList = medicineList;
	}
	
}
