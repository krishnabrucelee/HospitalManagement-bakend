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
public class SalaryComponents {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int salary_component_id;

	private String name;
	
	private int percentage;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	
	
	
}
