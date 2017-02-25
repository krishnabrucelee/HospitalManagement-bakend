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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hospital.model.Staff;

/**
 * @author admin
 *
 */
@Entity
public class Incentive_BonusManagement {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int incentive_bonus_id;
	
	private boolean isPayed;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date applicableDate;
	
	private double amount;
	
	private String purpose;
	
	@ManyToOne(targetEntity=Staff.class)
	@JoinColumn(name="staffId",foreignKey=@ForeignKey(name="Incentive_BonusManagement_Staff_FK"))
	private Staff staff = new Staff();

	public int getIncentive_bonus_id() {
		return incentive_bonus_id;
	}

	public void setIncentive_bonus_id(int incentive_bonus_id) {
		this.incentive_bonus_id = incentive_bonus_id;
	}

	public boolean isPayed() {
		return isPayed;
	}

	public void setPayed(boolean isPayed) {
		this.isPayed = isPayed;
	}

	public Date getApplicableDate() {
		return applicableDate;
	}

	public void setApplicableDate(Date applicableDate) {
		this.applicableDate = applicableDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	
}
