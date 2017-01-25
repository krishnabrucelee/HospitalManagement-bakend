package com.hospital.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table
public class MasterMedicineOrder implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 513190400849678199L;
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long medicineOrderId;
	@Column
	private String  supplierName;
	
	@Column
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="medicineOrderId",referencedColumnName="medicineOrderId",foreignKey=@ForeignKey(name="MedicineOrderId_Medicinetypes_FK"))
	private List<MedicineOrderTypes> medicineTypes  = new ArrayList<MedicineOrderTypes>();

	public MasterMedicineOrder() {
		super();
	}

	public Long getMedicineOrderId() {
		return medicineOrderId;
	}

	public void setMedicineOrderId(Long medicineOrderId) {
		this.medicineOrderId = medicineOrderId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public List<MedicineOrderTypes> getMedicineTypes() {
		return medicineTypes;
	}

	public void setMedicineTypes(List<MedicineOrderTypes> medicineTypes) {
		this.medicineTypes = medicineTypes;
	}


	

}
