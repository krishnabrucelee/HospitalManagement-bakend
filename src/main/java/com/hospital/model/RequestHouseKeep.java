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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="requestHouseKeep")
public class RequestHouseKeep implements Serializable {
	
	@Column(name="houseKeep_RequestId")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer houseKeepRequestId;
	private Integer receiveDeptId;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy hh:mm:ss a")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date giveHousekeppDate;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy hh:mm:ss a")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date returnHousekeppDate;
	private String receiveWards;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy hh:mm:ss a")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date issuetoLaundryDate;
	@Column
	@JsonFormat(pattern="dd-MM-yyyy hh:mm:ss a")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date receiveFromLaundryDate;
	private String receiveStatus;
	private String returnStaus;
	private String returnToWards;
	private String giveToLaundaryStatus;
	private String receivedLaundaryStatus;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="houseKeep_RequestId",referencedColumnName="houseKeep_RequestId",foreignKey=@ForeignKey(name="HousekeepRequest_NameCategry_FK"))
	private List<RequestHouseKeepSubCat> subcategories;
	public RequestHouseKeep() {
		super();
	}
	public Integer getHouseKeepRequestId() {
		return houseKeepRequestId;
	}
	public void setHouseKeepRequestId(Integer houseKeepRequestId) {
		this.houseKeepRequestId = houseKeepRequestId;
	}
	public Integer getReceiveDeptId() {
		return receiveDeptId;
	}
	public void setReceiveDeptId(Integer receiveDeptId) {
		this.receiveDeptId = receiveDeptId;
	}
	public Date getGiveHousekeppDate() {
		return giveHousekeppDate;
	}
	public void setGiveHousekeppDate(Date giveHousekeppDate) {
		this.giveHousekeppDate = giveHousekeppDate;
	}
	public Date getReturnHousekeppDate() {
		return returnHousekeppDate;
	}
	public void setReturnHousekeppDate(Date returnHousekeppDate) {
		this.returnHousekeppDate = returnHousekeppDate;
	}
	public String getReceiveWards() {
		return receiveWards;
	}
	public void setReceiveWards(String receiveWards) {
		this.receiveWards = receiveWards;
	}
	public Date getIssuetoLaundryDate() {
		return issuetoLaundryDate;
	}
	public void setIssuetoLaundryDate(Date issuetoLaundryDate) {
		this.issuetoLaundryDate = issuetoLaundryDate;
	}
	public Date getReceiveFromLaundryDate() {
		return receiveFromLaundryDate;
	}
	public void setReceiveFromLaundryDate(Date receiveFromLaundryDate) {
		this.receiveFromLaundryDate = receiveFromLaundryDate;
	}
	public String getReceiveStatus() {
		return receiveStatus;
	}
	public void setReceiveStatus(String receiveStatus) {
		this.receiveStatus = receiveStatus;
	}
	public String getReturnStaus() {
		return returnStaus;
	}
	public void setReturnStaus(String returnStaus) {
		this.returnStaus = returnStaus;
	}
	public String getReturnToWards() {
		return returnToWards;
	}
	public void setReturnToWards(String returnToWards) {
		this.returnToWards = returnToWards;
	}
	public String getGiveToLaundaryStatus() {
		return giveToLaundaryStatus;
	}
	public void setGiveToLaundaryStatus(String giveToLaundaryStatus) {
		this.giveToLaundaryStatus = giveToLaundaryStatus;
	}
	public String getReceivedLaundaryStatus() {
		return receivedLaundaryStatus;
	}
	public void setReceivedLaundaryStatus(String receivedLaundaryStatus) {
		this.receivedLaundaryStatus = receivedLaundaryStatus;
	}
	public List<RequestHouseKeepSubCat> getSubcategories() {
		return subcategories;
	}
	public void setSubcategories(List<RequestHouseKeepSubCat> subcategories) {
		this.subcategories = subcategories;
	}
	@Override
	public String toString() {
		return "RequestHouseKeep [houseKeepRequestId=" + houseKeepRequestId
				+ ", receiveDeptId=" + receiveDeptId + ", giveHousekeppDate="
				+ giveHousekeppDate + ", returnHousekeppDate="
				+ returnHousekeppDate + ", receiveWards=" + receiveWards
				+ ", issuetoLaundryDate=" + issuetoLaundryDate
				+ ", receiveFromLaundryDate=" + receiveFromLaundryDate
				+ ", receiveStatus=" + receiveStatus + ", returnStaus="
				+ returnStaus + ", returnToWards=" + returnToWards
				+ ", giveToLaundaryStatus=" + giveToLaundaryStatus
				+ ", receivedLaundaryStatus=" + receivedLaundaryStatus
				+ ", subcategories=" + subcategories + "]";
	}
	
}
