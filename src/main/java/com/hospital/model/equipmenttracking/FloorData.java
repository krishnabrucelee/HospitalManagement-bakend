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

public class FloorData implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer floorprimaryId;	
	
	@Column
	private String floorName;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="floorprimaryId",foreignKey=@ForeignKey(name="Floordata_Roomdata_FK"))
	private List<RoomData> roomlist;
	
	
	public FloorData() {
		super();
	}

	

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public List<RoomData> getRoomlist() {
		return roomlist;
	}

	public void setRoomlist(List<RoomData> roomlist) {
		this.roomlist = roomlist;
	}



	public Integer getFloorprimaryId() {
		return floorprimaryId;
	}



	public void setFloorprimaryId(Integer floorprimaryId) {
		this.floorprimaryId = floorprimaryId;
	}
	
	
	
}
