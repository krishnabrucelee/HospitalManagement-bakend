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

import com.hospital.service.ExpenseService;

/**
 * @author Krishna
 *
 */
@Controller
public class ExpenseController {

	/**
	 * Expense Service.
	 */
	@Autowired
	private ExpenseService expenseService;

	/**
	 * Create Expense.
	 * 
	 * @param expense
	 * @return expense
	 */
	@RequestMapping(value = "/addExpense", method = RequestMethod.POST)
	public @ResponseBody JSONObject addExpense(@RequestBody JSONObject expense) {
		return expenseService.addExpense(expense);
	}

	/**
	 * List expense.
	 * 
	 * @param expense
	 * @return expense
	 */
	@RequestMapping(value = "/listExpense")
	public @ResponseBody JSONObject listExpense() {
		return expenseService.listExpense();
	}
	
	/**
	 * Update Expense.
	 * 
	 * @param expense
	 * @return expense
	 */
	@RequestMapping(value = "/updateExpense")
	public @ResponseBody JSONObject updateExpense(@RequestBody JSONObject expense) {
		return expenseService.updateExpense(expense);
	}

	/**
	 * Delete expense.
	 * 
	 * @param expense
	 * @return expense
	 */
	@RequestMapping(value = "/deleteExpense", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteExpense(@RequestBody JSONObject expenseId) {
		return expenseService.deleteExpense(expenseId);
	}
}
