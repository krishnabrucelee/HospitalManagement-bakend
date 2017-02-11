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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	
	
    @ManyToOne()
    @JoinColumn(name="departmentId")
	private Department department;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestDate;
	
	@OneToMany(cascade=CascadeType.ALL)
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

	@Override
	public String toString() {
		return "PharmacyRequestMedicine [medicineRequestId="
				+ medicineRequestId + ", department=" + department
				+ ", requestDate=" + requestDate + ", medicineList="
				+ medicineList + "]";
	}

	/**
	 * Get the department of PharmacyRequestMedicine.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of PharmacyRequestMedicine.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

}
