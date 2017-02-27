/**
 * 
 */
package com.hospital.payroll.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author admin
 *
 */
@Entity
public class StaffSalaryConfigDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int staff_salary_config_details_id;
	
	private String name;
	
	private int percentage;
	
	private int amount;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getStaff_salary_config_details_id() {
		return staff_salary_config_details_id;
	}

	public void setStaff_salary_config_details_id(int staff_salary_config_details_id) {
		this.staff_salary_config_details_id = staff_salary_config_details_id;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	
	
}
