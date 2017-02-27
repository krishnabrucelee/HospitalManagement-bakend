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
public class EarnedSalaryConfigDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int earned_salary_config_details_id;
	
	private String name;
	
	private int amount;
	
	
	public int getEarned_salary_config_details_id() {
		return earned_salary_config_details_id;
	}

	public void setEarned_salary_config_details_id(
			int earned_salary_config_details_id) {
		this.earned_salary_config_details_id = earned_salary_config_details_id;
	}

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
	
}
