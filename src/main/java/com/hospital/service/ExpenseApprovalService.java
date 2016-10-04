/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface ExpenseApprovalService {

	/**
	 * Create ExpenseApproval.
	 * 
	 * @param expenseApproval
	 * @return expenseApproval
	 */
	public JSONObject addExpenseApproval(JSONObject expenseApproval);

	/**
	 * List ExpenseApproval.
	 * 
	 * @param expenseApproval
	 * @return expenseApproval
	 */
	public JSONObject listExpenseApproval();

	/**
	 * Update ExpenseApproval.
	 * 
	 * @param expenseApproval
	 * @return expenseApproval
	 */
	public JSONObject updateExpenseApproval(JSONObject expenseApproval);

	/**
	 * Delete ExpenseApproval.
	 * 
	 * @param expenseApproval
	 * @return expenseApproval
	 */
	public JSONObject deleteExpenseApproval(JSONObject expenseApprovalId);

}
