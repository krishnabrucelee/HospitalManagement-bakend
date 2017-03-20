/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.ExpenseDao;
import com.hospital.service.ExpenseService;

/**
 * @author Krishna
 *
 */
@Service
public class ExpenseServiceImpl implements ExpenseService {

	/**
	 * Expense dao
	 */
	@Autowired
	ExpenseDao expensedao;
	
	@Override
	public JSONObject addExpense(JSONObject expense) {
		return expensedao.addExpense(expense);
	}

	@Override
	public JSONObject listExpense() {
		return expensedao.listExpense();
	}

	@Override
	public JSONObject updateExpense(JSONObject expense) {
		return expensedao.updateExpense(expense);
	}

	@Override
	public JSONObject deleteExpense(JSONObject expenseId) {
		return expensedao.deleteExpense(expenseId);
	}

}
