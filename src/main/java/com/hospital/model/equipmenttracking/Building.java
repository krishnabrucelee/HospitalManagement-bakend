package com.hospital.model.equipmenttracking;

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
import javax.persistence.Table;
@Entity

public class Building implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer buildingId;	
	
	@Column
	private String buildingName;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="buildingId",foreignKey=@ForeignKey(name="Buildingdata_Floordata_FK"))
	private List<FloorData> floorlist;

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public List<FloorData> getFloorlist() {
		return floorlist;
	}

	public void setFloorlist(List<FloorData> floorlist) {
		this.floorlist = floorlist;
	}
	
	

}
