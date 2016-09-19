package com.hospital.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"ward", "roomnumber"}))
public class Room {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer room_id;
	@Column
	private String ward;
	@Column
	private Integer roomnumber;
	@Column
	private String roomtype;
	@Column  
	private Integer price;
	@Column
	private String bed_details;
	public Integer getRoom_id() {
		return room_id;
	}
	public void setRoom_id(Integer room_id) {
		this.room_id = room_id;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public Integer getRoomnumber() {
		return roomnumber;
	}
	public void setRoomnumber(Integer roomnumber) {
		this.roomnumber = roomnumber;
	}
	public String getRoomtype() {
		return roomtype;
	}
	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getBed_details() {
		return bed_details;
	}
	public void setBed_details(String bed_details) {
		this.bed_details = bed_details;
	}
	

}
