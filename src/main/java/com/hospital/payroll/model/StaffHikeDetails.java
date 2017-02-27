/**
 * 
 */
package com.hospital.payroll.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.hospital.model.Staff;

/**
 * @author admin
 *
 */
@Entity
public class StaffHikeDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int staff_hike_details_id;
	
	private int percentage;
	
	private int amount;
	
	private Date creationDate;
	
	@ManyToOne(targetEntity=Staff.class)
	@JoinColumn(name="staffId",foreignKey=@ForeignKey(name="StaffHikeDetails_Staff_FK"))
	private Staff staff = new Staff();


	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getStaff_hike_details_id() {
		return staff_hike_details_id;
	}

	public void setStaff_hike_details_id(int staff_hike_details_id) {
		this.staff_hike_details_id = staff_hike_details_id;
	}
	
	
}

