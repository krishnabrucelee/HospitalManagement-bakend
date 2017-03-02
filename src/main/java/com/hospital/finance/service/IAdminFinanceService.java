/**
 * 
 */
package com.hospital.finance.service;

import org.json.simple.JSONObject;

/**
 * @author admin
 *
 */
public interface IAdminFinanceService {

	public JSONObject addFinancialTransaction(JSONObject transaction);
	public JSONObject getAllAccountType(); 
	public JSONObject getAllAccountDetailType(); 
	public JSONObject getAllTransactionsByAccountDetailType(JSONObject accountDetailType);
	
	public JSONObject deleteFinancialTransaction(JSONObject transactionDetails);
	public JSONObject generateFinancialReport(JSONObject reportDetails);
}
