package com.hospital.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class ExternalLab implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long externalLabId;
	@Column
	private String externalLabName;
	
	@Column
	private String externalLabType;
	@Column
	private String description;
	public ExternalLab() {
		super();
	}
	public Long getExternalLabId() {
		return externalLabId;
	}
	public void setExternalLabId(Long externalLabId) {
		this.externalLabId = externalLabId;
	}
	
	
	
	public String getExternalLabName() {
		return externalLabName;
	}
	public void setExternalLabName(String externalLabName) {
		this.externalLabName = externalLabName;
	}
	public String getExternalLabType() {
		return externalLabType;
	}
	public void setExternalLabType(String externalLabType) {
		this.externalLabType = externalLabType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
