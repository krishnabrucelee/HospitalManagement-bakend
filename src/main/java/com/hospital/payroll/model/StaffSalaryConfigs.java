/**
 * 
 */
package com.hospital.payroll.model;

import java.util.ArrayList;
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
public class StaffSalaryConfigs {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int staff_salary_configs_id;
	
	private boolean isCurrent;
	
	private double perMonthAmount;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="employee_salary_configs_id",foreignKey=@ForeignKey(name="StaffSalaryConfigs_StaffSalaryConfigDetails_FK"))
	List<StaffSalaryConfigDetails> salaryConfigDetails = new ArrayList<StaffSalaryConfigDetails>();
	
	
	@ManyToOne(targetEntity=Staff.class)
	@JoinColumn(name="staffId",foreignKey=@ForeignKey(name="StaffSalaryConfigs_Staff_FK"))
	private Staff staff = new Staff();


	public boolean isCurrent() {
		return isCurrent;
	}

	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	public List<StaffSalaryConfigDetails> getSalaryConfigDetails() {
		return salaryConfigDetails;
	}

	public void setSalaryConfigDetails(
			List<StaffSalaryConfigDetails> salaryConfigDetails) {
		this.salaryConfigDetails = salaryConfigDetails;
	}

	public double getPerMonthAmount() {
		return perMonthAmount;
	}

	public void setPerMonthAmount(double perMonthAmount) {
		this.perMonthAmount = perMonthAmount;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public int getStaff_salary_configs_id() {
		return staff_salary_configs_id;
	}

	public void setStaff_salary_configs_id(int staff_salary_configs_id) {
		this.staff_salary_configs_id = staff_salary_configs_id;
	}
	
}
