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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class MaintananceScheduling implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer maintananceScheduleId;
	
	@Column
	private Integer maintanancePersonId;
	@Column
	private String name;
	@Column
	private String department;
	@Column
	private String  equipmentId;
	@Column
	private String  equipmentName;
	@Column
	private String  location;
	
	@Column
	@JsonFormat(pattern="dd-MM-yyyy")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestDatee;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datee2;
	@Column
	private String  status;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="maintananceScheduleId",referencedColumnName="maintananceScheduleId",foreignKey=@ForeignKey(name="Equipmentcheck_Maintanance_FK"))
	private List<MaintananceScheduleSubCategeries> subcategories;
	public MaintananceScheduling() {
		super();
	}
	
	public Integer getMaintananceScheduleId() {
		return maintananceScheduleId;
	}

	public void setMaintananceScheduleId(Integer maintananceScheduleId) {
		this.maintananceScheduleId = maintananceScheduleId;
	}

	public List<MaintananceScheduleSubCategeries> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(
			List<MaintananceScheduleSubCategeries> subcategories) {
		this.subcategories = subcategories;
	}

	public Integer getMaintanancePersonId() {
		return maintanancePersonId;
	}
	public void setMaintanancePersonId(Integer maintanancePersonId) {
		this.maintanancePersonId = maintanancePersonId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public Date getRequestDatee() {
		return requestDatee;
	}

	public void setRequestDatee(Date requestDatee) {
		this.requestDatee = requestDatee;
	}

	public Date getDatee2() {
		return datee2;
	}
	public void setDatee2(Date datee2) {
		this.datee2 = datee2;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
