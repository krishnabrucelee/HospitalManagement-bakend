/**
 * 
 */
package com.hospital.payroll.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.hospital.model.Staff;

/**
 * @author admin
 *
 */
@Entity
public class StaffSalaryPayoutDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int salary_payout_details_id;
	
	private Date fromDate;
	
	private Date toDate;
	
	private double totalAmout;
	
	private boolean isPayed;

	@ManyToOne(targetEntity=Staff.class)
	@JoinColumn(name="staffId",foreignKey=@ForeignKey(name="StaffSalaryPayoutDetails_Staff_FK"))
	private Staff staff = new Staff();
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="salary_payout_details_id",foreignKey=@ForeignKey(name="StaffSalaryPayoutDetails_EarnedSalaryConfigDetails_FK"))
	private List<EarnedSalaryConfigDetails> configDetails = new ArrayList<EarnedSalaryConfigDetails>(); 
	
	public int getSalary_payout_details_id() {
		return salary_payout_details_id;
	}

	public void setSalary_payout_details_id(int salary_payout_details_id) {
		this.salary_payout_details_id = salary_payout_details_id;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public boolean isPayed() {
		return isPayed;
	}

	public void setPayed(boolean isPayed) {
		this.isPayed = isPayed;
	}

	public double getTotalAmout() {
		return totalAmout;
	}

	public void setTotalAmout(double totalAmout) {
		this.totalAmout = totalAmout;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public List<EarnedSalaryConfigDetails> getConfigDetails() {
		return configDetails;
	}

	public void setConfigDetails(List<EarnedSalaryConfigDetails> configDetails) {
		this.configDetails = configDetails;
	}
	
	

}
