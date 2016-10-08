package com.hospital.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class MaintananceSchedule implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer scheduleId;
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
	private Date datee;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datee2;
	@Column
	private String  status;
	public MaintananceSchedule() {
		super();
	}
	public Integer getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
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
	public Date getDatee() {
		return datee;
	}
	public void setDatee(Date datee) {
		this.datee = datee;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDatee2() {
		return datee2;
	}
	public void setDatee2(Date datee2) {
		this.datee2 = datee2;
	}
	
}
