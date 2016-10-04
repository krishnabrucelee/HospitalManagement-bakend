/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.ExpenseApprovalDao;
import com.hospital.service.ExpenseApprovalService;

/**
 * @author Krishna
 *
 */
@Service
public class ExpenseApprovalServiceImpl implements ExpenseApprovalService {

	/**
	 * ExpenseApproval dao
	 */
	@Autowired
	ExpenseApprovalDao expenseApprovaldao;
	
	@Override
	public JSONObject addExpenseApproval(JSONObject expenseApproval) {
		return expenseApprovaldao.addExpenseApproval(expenseApproval);
	}

	@Override
	public JSONObject listExpenseApproval() {
		return expenseApprovaldao.listExpenseApproval();
	}

	@Override
	public JSONObject updateExpenseApproval(JSONObject expenseApproval) {
		return expenseApprovaldao.updateExpenseApproval(expenseApproval);
	}

	@Override
	public JSONObject deleteExpenseApproval(JSONObject expenseApprovalId) {
		return expenseApprovaldao.deleteExpenseApproval(expenseApprovalId);
	}

}
