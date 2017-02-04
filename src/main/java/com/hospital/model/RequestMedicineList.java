package com.hospital.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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

	@Column
	private Status status;
	
	@OneToOne(targetEntity = StockMedicine.class)
	@JoinColumn(name = "stock_medicine_id", referencedColumnName = "stock_medicine_id", foreignKey = @ForeignKey(name = "stockMedicine_requestMedicineList_FK"))
	private StockMedicine stockMedicine;
	
	public enum Status {
		Stock_Issued,
		Stock_Issue_Pending,
		Stock_Issue_declined		
	}
	
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

	/**
	 * Get the status of RequestMedicineList.
	 *
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Set the status of RequestMedicineList.
	 *
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * Get the serialversionuid of RequestMedicineList.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Get the stockMedicine of RequestMedicineList
	 *
	 * @return the stockMedicine
	 */
	public StockMedicine getStockMedicine() {
		return stockMedicine;
	}

	/**
	 * Set the stockMedicine of RequestMedicineList
	 *
	 * @param stockMedicine the stockMedicine to set
	 */
	public void setStockMedicine(StockMedicine stockMedicine) {
		this.stockMedicine = stockMedicine;
	}
	
}
