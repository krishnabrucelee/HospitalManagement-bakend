package com.hospital.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class RequestMedicineList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 10874889813139L;

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long medicineListId;
	
	@Column
	private Integer medicineId;
	
	@Column
	private Integer quantity;

	public RequestMedicineList() {
		super();
	}

	public Long getMedicineListId() {
		return medicineListId;
	}

	public void setMedicineListId(Long medicineListId) {
		this.medicineListId = medicineListId;
	}

	public Integer getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
