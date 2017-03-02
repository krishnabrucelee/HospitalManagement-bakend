/**
 * 
 */
package com.hospital.finance.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.finance.dao.AdminFinanceDao;
import com.hospital.finance.service.IAdminFinanceService;

/**
 * @author admin
 *
 */
@Service
public class AdminFinanceServiceImpl implements IAdminFinanceService {

	@Autowired
	private AdminFinanceDao financeDao;
	
	@Override
	public JSONObject addFinancialTransaction(JSONObject transaction) {
		return financeDao.addFinancialTransaction(transaction);
	}
	
	@Override
	public JSONObject getAllAccountType() {
		return financeDao.getAllAccountType();
	}

	
	@Override
	public JSONObject getAllAccountDetailType() {
		return financeDao.getAllAccountDetailType();
	}

	@Override
	public JSONObject deleteFinancialTransaction(JSONObject transactionDetails) {
		return financeDao.deleteFinancialTransaction(transactionDetails);
	}

	@Override
	public JSONObject generateFinancialReport(JSONObject reportDetails) {
		return financeDao.generateFinancialReport(reportDetails);
	}

	@Override
	public JSONObject getAllTransactionsByAccountDetailType(JSONObject accountDetailType) {
		return financeDao.getAllTransactionsByAccountDetailType(accountDetailType);
	}

}
