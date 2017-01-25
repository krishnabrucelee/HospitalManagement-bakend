package com.hospital.model;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table
public class PharmacyRequestMedicine implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long medicineRequestId;
	

	private Integer departmentId;
	
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestDate;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="medicineRequestId",foreignKey=@ForeignKey(name="MedicineRequest_MedicineList_FK"))
	List<RequestMedicineList> medicineList = new ArrayList<RequestMedicineList>();

	public PharmacyRequestMedicine() {
		super();
	}

	public Long getMedicineRequestId() {
		return medicineRequestId;
	}

	public void setMedicineRequestId(Long medicineRequestId) {
		this.medicineRequestId = medicineRequestId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public List<RequestMedicineList> getMedicineList() {
		return medicineList;
	}

	public void setMedicineList(List<RequestMedicineList> medicineList) {
		this.medicineList = medicineList;
	}
	
	
}
