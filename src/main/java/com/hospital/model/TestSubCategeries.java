package com.hospital.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class TestSubCategeries implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer catogeryId;
	@Column
	private String catogeryName;
	@Column
	private String rangeLowHigh_Female;
	@Column
	private String rangeLowHigh_Male;
	
	@Column
	private String rangeLowHigh;
	@Column
	private Double price;
	public TestSubCategeries() {
		super();
	}
	public Integer getCatogeryId() {
		return catogeryId;
	}
	public void setCatogeryId(Integer catogeryId) {
		this.catogeryId = catogeryId;
	}
	public String getCatogeryName() {
		return catogeryName;
	}
	public void setCatogeryName(String catogeryName) {
		this.catogeryName = catogeryName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getRangeLowHigh_Female() {
		return rangeLowHigh_Female;
	}
	public void setRangeLowHigh_Female(String rangeLowHigh_Female) {
		this.rangeLowHigh_Female = rangeLowHigh_Female;
	}
	public String getRangeLowHigh_Male() {
		return rangeLowHigh_Male;
	}
	public void setRangeLowHigh_Male(String rangeLowHigh_Male) {
		this.rangeLowHigh_Male = rangeLowHigh_Male;
	}
	public String getRangeLowHigh() {
		return rangeLowHigh;
	}
	public void setRangeLowHigh(String rangeLowHigh) {
		this.rangeLowHigh = rangeLowHigh;
	}
	
	
}
