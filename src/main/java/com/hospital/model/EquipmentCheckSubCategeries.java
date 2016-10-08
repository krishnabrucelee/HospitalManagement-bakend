package com.hospital.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class EquipmentCheckSubCategeries implements Serializable {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer checkCatogeryId;
	@Column
	private String checkCatogeryName;
	public Integer getCheckCatogeryId() {
		return checkCatogeryId;
	}
	public void setCheckCatogeryId(Integer checkCatogeryId) {
		this.checkCatogeryId = checkCatogeryId;
	}
	public String getCheckCatogeryName() {
		return checkCatogeryName;
	}
	public void setCheckCatogeryName(String checkCatogeryName) {
		this.checkCatogeryName = checkCatogeryName;
	}
	
}
