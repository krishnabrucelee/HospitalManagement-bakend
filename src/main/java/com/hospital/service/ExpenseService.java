/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface ExpenseService {

	/**
	 * Create Expense.
	 * 
	 * @param expense
	 * @return expense
	 */
	public JSONObject addExpense(JSONObject expense);

	/**
	 * List Expense.
	 * 
	 * @param expense
	 * @return expense
	 */
	public JSONObject listExpense();

	/**
	 * Update Expense.
	 * 
	 * @param expense
	 * @return expense
	 */
	public JSONObject updateExpense(JSONObject expense);

	/**
	 * Delete Expense.
	 * 
	 * @param expense
	 * @return expense
	 */
	public JSONObject deleteExpense(JSONObject expenseId);

}
