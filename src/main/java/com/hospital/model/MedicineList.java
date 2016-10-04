/**
 * 
 */
package com.hospital.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "medicineList")
public class MedicineList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "medicine_id")
	private String medicineId;
	
	@Column(name = "medicine_name")
	private String medicineName;
	
	@Column(name = "medicine_description")
	private String medicineDescription;

	/**
	 * Get the id of MedicineList.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set the id of MedicineList.
	 *
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Get the medicineId of MedicineList.
	 *
	 * @return the medicineId
	 */
	public String getMedicineId() {
		return medicineId;
	}

	/**
	 * Set the medicineId of MedicineList.
	 *
	 * @param medicineId the medicineId to set
	 */
	public void setMedicineId(String medicineId) {
		this.medicineId = medicineId;
	}

	/**
	 * Get the medicineName of MedicineList.
	 *
	 * @return the medicineName
	 */
	public String getMedicineName() {
		return medicineName;
	}

	/**
	 * Set the medicineName of MedicineList.
	 *
	 * @param medicineName the medicineName to set
	 */
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	/**
	 * Get the medicineDescription of MedicineList.
	 *
	 * @return the medicineDescription
	 */
	public String getMedicineDescription() {
		return medicineDescription;
	}

	/**
	 * Set the medicineDescription of MedicineList.
	 *
	 * @param medicineDescription the medicineDescription to set
	 */
	public void setMedicineDescription(String medicineDescription) {
		this.medicineDescription = medicineDescription;
	}
	
	
}
