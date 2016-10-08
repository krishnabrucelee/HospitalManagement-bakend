package com.hospital.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="equipmentCheckRequest")
public class EquipmentCheckRequest implements Serializable {
	
	@Column(name="equipCheck_RequestId")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer equipCheckRequestId;	
	@Column
	private Integer equipmentId;
	@Column
	private Integer departmentId;
	@Column
	private String requestDepartment;	
	@Column
	private Integer staffId;
	@Column
	private String repairName;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy hh:mm:ss a")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestTime;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy hh:mm:ss a")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date returnDate;
	@Column
	private String  receiveStatus;
	@Column
	private String  returnStatus;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)//labrequestId
	@JoinColumn(name="equipCheck_RequestId",referencedColumnName="equipCheck_RequestId",foreignKey=@ForeignKey(name="equipCheckReq_NameCategry_FK"))
	private List<EquipmentCheckSubCategeries> subcategories;
	public Integer getEquipCheckRequestId() {
		return equipCheckRequestId;
	}
	public void setEquipCheckRequestId(Integer equipCheckRequestId) {
		this.equipCheckRequestId = equipCheckRequestId;
	}
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public String getRequestDepartment() {
		return requestDepartment;
	}
	public void setRequestDepartment(String requestDepartment) {
		this.requestDepartment = requestDepartment;
	}
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	public String getRepairName() {
		return repairName;
	}
	public void setRepairName(String repairName) {
		this.repairName = repairName;
	}
	public Date getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	public String getReceiveStatus() {
		return receiveStatus;
	}
	public void setReceiveStatus(String receiveStatus) {
		this.receiveStatus = receiveStatus;
	}
	
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public String getReturnStatus() {
		return returnStatus;
	}
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}
	public List<EquipmentCheckSubCategeries> getSubcategories() {
		return subcategories;
	}
	public void setSubcategories(List<EquipmentCheckSubCategeries> subcategories) {
		this.subcategories = subcategories;
	}	
}
