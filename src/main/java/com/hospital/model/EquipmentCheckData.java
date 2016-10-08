package com.hospital.model;

import java.io.Serializable;
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
@Entity
public class EquipmentCheckData implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer checkId;
	private String  equipmentId;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name=" equipmentId",referencedColumnName=" equipmentId",foreignKey=@ForeignKey(name="Equipmentcheck_NameCategry_FK"))
	private List<EquipmentCheckSubcategry> subcategories;

	public Integer getCheckId() {
		return checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public List<EquipmentCheckSubcategry> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<EquipmentCheckSubcategry> subcategories) {
		this.subcategories = subcategories;
	}

	@Override
	public String toString() {
		return "EquipmentCheckData [checkId=" + checkId + ", equipmentId="
				+ equipmentId + ", subcategories=" + subcategories + "]";
	}
	
	
	
}
