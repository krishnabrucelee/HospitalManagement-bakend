/**
 * 
 */
package com.hospital.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "dietList")
public class DietList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "diet_list_id")
	private Integer dietListId;
	
	@Column(name = "food_name")
	private String foodName;
	
	@Column(name = "food_description")
	private String foodDescription;
	
	@Column(name = "calories")
	private Integer calories;

	/**
	 * Get the dietListId of DietList.
	 *
	 * @return the dietListId
	 */
	public Integer getDietListId() {
		return dietListId;
	}

	/**
	 * Set the dietListId of DietList.
	 *
	 * @param dietListId the dietListId to set
	 */
	public void setDietListId(Integer dietListId) {
		this.dietListId = dietListId;
	}

	/**
	 * Get the foodName of DietList.
	 *
	 * @return the foodName
	 */
	public String getFoodName() {
		return foodName;
	}

	/**
	 * Set the foodName of DietList.
	 *
	 * @param foodName the foodName to set
	 */
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	/**
	 * Get the foodDescription of DietList.
	 *
	 * @return the foodDescription
	 */
	public String getFoodDescription() {
		return foodDescription;
	}

	/**
	 * Set the foodDescription of DietList.
	 *
	 * @param foodDescription the foodDescription to set
	 */
	public void setFoodDescription(String foodDescription) {
		this.foodDescription = foodDescription;
	}

	/**
	 * Get the calories of DietList.
	 *
	 * @return the calories
	 */
	public Integer getCalories() {
		return calories;
	}

	/**
	 * Set the calories of DietList.
	 *
	 * @param calories the calories to set
	 */
	public void setCalories(Integer calories) {
		this.calories = calories;
	}

}
