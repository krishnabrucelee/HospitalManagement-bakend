/**
 * 
 */
package com.hospital.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.service.ExpenseApprovalService;

/**
 * @author Krishna
 *
 */
@Controller
public class ExpenseApprovalController {

	/**
	 * ExpenseApproval Service.
	 */
	@Autowired
	private ExpenseApprovalService expenseApprovalService;

	/**
	 * Create ExpenseApproval.
	 * 
	 * @param expenseApproval
	 * @return expenseApproval
	 */
	@RequestMapping(value = "/addExpenseApproval", method = RequestMethod.POST)
	public @ResponseBody JSONObject addExpenseApproval(@RequestBody JSONObject expenseApproval) {
		return expenseApprovalService.addExpenseApproval(expenseApproval);
	}

	/**
	 * List expenseApproval.
	 * 
	 * @param expenseApproval
	 * @return expenseApproval
	 */
	@RequestMapping(value = "/listExpenseApproval")
	public @ResponseBody JSONObject listExpenseApproval() {
		return expenseApprovalService.listExpenseApproval();
	}
	
	/**
	 * Update ExpenseApproval.
	 * 
	 * @param expenseApproval
	 * @return expenseApproval
	 */
	@RequestMapping(value = "/updateExpenseApproval")
	public @ResponseBody JSONObject updateExpenseApproval(@RequestBody JSONObject expenseApproval) {
		return expenseApprovalService.updateExpenseApproval(expenseApproval);
	}

	/**
	 * Delete expenseApproval.
	 * 
	 * @param expenseApproval
	 * @return expenseApproval
	 */
	@RequestMapping(value = "/deleteExpenseApproval", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteExpenseApproval(@RequestBody JSONObject expenseApprovalId) {
		return expenseApprovalService.deleteExpenseApproval(expenseApprovalId);
	}
}
