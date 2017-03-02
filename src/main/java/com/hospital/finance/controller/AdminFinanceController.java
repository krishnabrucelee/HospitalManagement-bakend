/**
 * 
 */
package com.hospital.finance.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.finance.service.IAdminFinanceService;

/**
 * @author admin
 *
 */

@Controller
public class AdminFinanceController {

	@Autowired
	private IAdminFinanceService adminFinanceService;
	
	@RequestMapping(value = "/addFinancialTransaction", method = RequestMethod.POST)
	public @ResponseBody JSONObject addFinancialTransaction(@RequestBody JSONObject financialTransactionDetails) {
		return adminFinanceService.addFinancialTransaction(financialTransactionDetails);
	}

	@RequestMapping(value = "/getAllAccountType", method = RequestMethod.GET)
	public @ResponseBody JSONObject getAllAccountType() {
		return adminFinanceService.getAllAccountType();
	}
	
	@RequestMapping(value = "/getAllAccountDetailType", method = RequestMethod.GET)
	public @ResponseBody JSONObject getAllAccountDetailType() {
		return adminFinanceService.getAllAccountDetailType();
	}
	
	@RequestMapping(value = "/getAllTransactionsByAccountDetailType", method = RequestMethod.POST)
	public @ResponseBody JSONObject getAllTransactionsByAccountDetailType(@RequestBody JSONObject accountDetailType) {
		return adminFinanceService.getAllTransactionsByAccountDetailType(accountDetailType);
	}
	
	@RequestMapping(value = "/deleteFinancialTransaction", method = RequestMethod.POST)
	public @ResponseBody JSONObject deleteFinancialTransaction(@RequestBody JSONObject transactionDetails) {
		return adminFinanceService.deleteFinancialTransaction(transactionDetails);
	}
	
	@RequestMapping(value = "/generateFinancialReport", method = RequestMethod.POST)
	public @ResponseBody JSONObject generateFinancialReport(@RequestBody JSONObject reportDetails) {
		return adminFinanceService.generateFinancialReport(reportDetails);
	}
	
}
