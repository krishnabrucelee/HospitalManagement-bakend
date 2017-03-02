/**
 * 
 */
package com.hospital.finance.dao;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.finanace.model.AccountDetailType;
import com.hospital.finanace.model.AccountType;
import com.hospital.finanace.model.FinanceTransaction;
import com.monitorjbl.json.JsonView;
import com.monitorjbl.json.Match;

/**
 * @author admin
 *
 */
@Repository
public class AdminFinanceDao {

	@Autowired
	SessionFactory sessionFactory;
	
	
	@Autowired
	@Qualifier("jsonViewObjectMapper")
	ObjectMapper jsonViewObjectMapper;

	
	@SuppressWarnings("unchecked")
	public JSONObject addFinancialTransaction(JSONObject transaction) {
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		try {			
					
			// 1. Load AccountDetailType
			// 2. Check amountType field : valid values are [debit, credit]
			// 3. If amountType field is debit means [ add balance amount + current Amount]
			// 4. If amountType field is credit means [ deduct balance amount -  current Amount]
			// 5. Add FinanceTransaction Table
			
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			
			Criteria cri = session.createCriteria(AccountDetailType.class);
			cri.setFetchMode("financeTransactions", FetchMode.JOIN);
			cri.add(Restrictions.eq("accountDetailTypeId", transaction.get("accountDetailTypeId")));
			
			List<AccountDetailType> accountDetailType = cri.list();
			
			if(accountDetailType.isEmpty())
			{
				result.put("status",false);
				result.put("reason","AccountDetailType is not found. Please check accountDetailTypeId field");
				return result;
			}
			
			AccountDetailType accountDetails = accountDetailType.get(0);
			
			double current_Balance = accountDetails.getBalance();
		
			double update_Balance = 0;
			
			FinanceTransaction financialTransaction = new FinanceTransaction();
			financialTransaction.setBegin_balance(current_Balance);			
			if(transaction.get("amountType").equals("debit"))
			{
				update_Balance = current_Balance + (double) transaction.get("amount");
				financialTransaction.setDebit((double) transaction.get("amount"));
				
			}
			else if (transaction.get("amountType").equals("credit"))
			{
				update_Balance = current_Balance - (double) transaction.get("amount");
				financialTransaction.setCredit((double) transaction.get("amount"));
			}
			else
			{
				result.put("status",false);
				result.put("reason","Possible amountType field values are [debit,credit]. But found is "+transaction.get("amountType"));
				return result;
			}
			
			financialTransaction.setTransactionDate(new Date());
			if(transaction.containsKey("notes"))
				financialTransaction.setNotes(transaction.get("notes").toString());
			
			// Set New Balance to AccountDetailType
			accountDetails.setBalance(update_Balance);
			
			financialTransaction.setAccountDetailType(accountDetails);
			
			session.save(financialTransaction);
			
		 	
			session.getTransaction().commit();
			
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("error",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session!= null && session.isOpen())
				session.close();
		}
		return result;
	}


	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getAllAccountType() {
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();			
			session.beginTransaction();
			
			Criteria cri = session.createCriteria(AccountType.class);
			//cri.setFetchMode("accountDetailedTyes", FetchMode.JOIN);
						
			List<AccountType> accountTypes =cri.list();
			
			if(accountTypes.isEmpty())
			{
				result.put("status",false);
				result.put("error","Account Type is Empty");
				return result;
			}
			
			String json = jsonViewObjectMapper.writeValueAsString(JsonView.with(accountTypes)
					.onClass(AccountType.class, Match.match().exclude("profileLossType","cashflowType"))
					.onClass(AccountDetailType.class, Match.match().exclude("financeTransactions","accountType"))					
					);
			
			result.put("result",jsonViewObjectMapper.readValue(json,new TypeReference<ArrayList<HashMap<String,Object>>>() {
			}));
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("error",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session!= null && session.isOpen())
				session.close();
		}
		return result;
	}


	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getAllAccountDetailType() {
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		try {			
			session = sessionFactory.getCurrentSession();			
			session.beginTransaction();
			
			Criteria cri = session.createCriteria(AccountDetailType.class);
			
			
			List<AccountDetailType> accountDetailsTypes = cri.list();
			
			if(accountDetailsTypes.isEmpty())
			{
				result.put("status",false);
				result.put("error","Account Type is Empty");
				return result;
			}
			
			String json = jsonViewObjectMapper.writeValueAsString(JsonView.with(accountDetailsTypes)
					.onClass(AccountDetailType.class, Match.match().exclude("accountType"))
					.onClass(FinanceTransaction.class, Match.match().exclude("accountDetailType"))
					);
			
			result.put("result",jsonViewObjectMapper.readValue(json,new TypeReference<ArrayList<HashMap<String,Object>>>() {
			}));
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("error",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session!= null && session.isOpen())
				session.close();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getAllTransactionsByAccountDetailType(JSONObject accountDetailType) {
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		try {			
			session = sessionFactory.getCurrentSession();			
			session.beginTransaction();
			
			Criteria cri = session.createCriteria(AccountDetailType.class);
			cri.add(Restrictions.eq("accountDetailTypeId", accountDetailType.get("accountDetailTypeId")));
			
			
			List<AccountDetailType> accountDetailsTypes = cri.list();
			
			if(accountDetailsTypes.isEmpty())
			{
				result.put("status",false);
				result.put("error","Account Type is Empty");
				return result;
			}
			
			
			String json = jsonViewObjectMapper.writeValueAsString(JsonView.with(accountDetailsTypes.get(0))
					.onClass(AccountDetailType.class, Match.match().exclude("accountType"))
					.onClass(FinanceTransaction.class, Match.match().exclude("accountDetailType"))
					);
			
			LinkedHashMap<String,Object> resultObj = jsonViewObjectMapper.readValue(json,new TypeReference<LinkedHashMap<String,Object>>() {});
			
			ArrayList<LinkedHashMap<String,Object>> transactionObj = (ArrayList<LinkedHashMap<String,Object>>)resultObj.get("financeTransactions");
			
			Collections.sort(transactionObj,(nv,ov)->{				
				return ((Integer)nv.get("financeTransactionId")).compareTo((int)ov.get("financeTransactionId"));
			});
			
			result.put("result",resultObj);
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("error",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session!= null && session.isOpen())
				session.close();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject deleteFinancialTransaction(JSONObject transactionDetails) {
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		try {			
			session = sessionFactory.getCurrentSession();			
			session.beginTransaction();
			
			Criteria cri = session.createCriteria(FinanceTransaction.class);
			cri.add(Restrictions.eq("financeTransactionId", transactionDetails.get("financeTransactionId")));
			
			List<FinanceTransaction> financialTransactionList = cri.list();
			
			if(financialTransactionList.isEmpty())
			{
				result.put("status",false);
				result.put("error","FinanceTransaction Type is Empty");
				return result;
			}
			
			FinanceTransaction matchFinancialTransaction =financialTransactionList.get(0);
			AccountDetailType accountDetailType = matchFinancialTransaction.getAccountDetailType();
			
			if(matchFinancialTransaction.getCredit() == 0)
    		{
    			// If Debit means Deduct Balance from Main Account
    			// Perform Debit Action		    			
    			double currentBalance = accountDetailType.getBalance();
    			accountDetailType.setBalance(currentBalance-matchFinancialTransaction.getDebit());	    			
    			
    		}
    		else 
    		{
    			// If Credit means Add Balance to Main Account
    			// Perform Credit Action
    			double currentBalance = accountDetailType.getBalance();
    			accountDetailType.setBalance(currentBalance+matchFinancialTransaction.getCredit());	
    			
    		}
			
			session.delete(matchFinancialTransaction);
			
			session.getTransaction().commit();
		}
		 catch (Exception e) {
				result.put("status",false);
				result.put("error",e.getMessage());
				e.printStackTrace();
			}
			finally{
				if(session!= null && session.isOpen())
					session.close();
			}
			return result;
	}	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONObject generateFinancialReport(JSONObject reportDetails) {
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		try {			
			session = sessionFactory.getCurrentSession();			
			session.beginTransaction();
			
			LocalDate fromDate = LocalDate.parse(reportDetails.get("startDate").toString(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			
			LocalDate toDate = LocalDate.parse(reportDetails.get("endDate").toString(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			
			
			Date MonthFromDate = Date.from(fromDate.atStartOfDay(ZoneId.of("UTC")).toInstant());
			
			Date toFromDate = Date.from(toDate.atStartOfDay(ZoneId.of("UTC")).toInstant());
			
			Criteria cri = session.createCriteria(FinanceTransaction.class);
			cri.add(Restrictions.ge("transactionDate",MonthFromDate ));
			cri.add(Restrictions.le("transactionDate",toFromDate ));
			
			List<FinanceTransaction> financeTransactionList = cri.list();
			
			
			if(financeTransactionList.isEmpty())
			{
				result.put("status",false);
				result.put("reason","financeTransactionList Table is Empty");
				return result;
			}
			
			String json = jsonViewObjectMapper.writeValueAsString(JsonView.with(financeTransactionList)
					.onClass(AccountDetailType.class, Match.match().exclude("financeTransactions"))
					.onClass(AccountType.class, Match.match().exclude("accountDetailedTyes"))					
					);
			Set<HashMap<String,Object>> accountsTypeSetObj =  jsonViewObjectMapper.readValue(json,new TypeReference<Set<HashMap<String,Object>>>(){});
			
			// 1. Group By accountDetailType 
			
			Map<Object, List<HashMap<String,Object>>> groupByAccountDetailType = 
					accountsTypeSetObj.stream().collect(Collectors.groupingBy(
							(p)->{
								HashMap<String,Object> accountDetailType = (HashMap<String, Object>) ((Map)p).get("accountDetailType");								
								return accountDetailType.get("name").toString();								
							})
						);
			
			// 2. Sort Ascending Transactions & Calculate Balance based on last transactions of AccountDetailType
			
			ArrayList<HashMap<String,Object>> balanceDetailsofAccountDetailTypes = new ArrayList<HashMap<String,Object>>();
			Set<Object> keys = groupByAccountDetailType.keySet();
			Iterator<Object> keyItr = keys.iterator();
			
			while(keyItr.hasNext())
			{
				String name = keyItr.next().toString();
				List<HashMap<String,Object>> values  = groupByAccountDetailType.get(name);
				
				Collections.sort(values,(o1,o2)->{				    
								return ((Integer)o1.get("financeTransactionId")).compareTo((Integer)o2.get("financeTransactionId"));
							    });
				int transctionList = values.size();
				
				int index = transctionList > 1 ? transctionList-1 : 0;
				
				HashMap<String,Object> lastTransactionDetails = values.get(index);
				HashMap<String,Object> balanceDetails  = new HashMap<String, Object>();
				balanceDetails.put("name",name);
				
				if(lastTransactionDetails.get("debit").toString().equals("0.0"))
				{
					// Perform Credit
					double begin_balance = (double) lastTransactionDetails.get("begin_balance");
					double credit_balance =(double) lastTransactionDetails.get("credit");
					balanceDetails.put("mainBalance",begin_balance-credit_balance);
				}
				else
				{
					// Perform Debit						
					double begin_balance = (double) lastTransactionDetails.get("begin_balance");
					double debit_balance =(double) lastTransactionDetails.get("debit");
					//accountDetails.put("mainBalance",sum);
					balanceDetails.put("mainBalance",begin_balance+debit_balance);
				}	
				
				
				// Attach accountDetailType ID & accountType ID
				HashMap<String,Object> accountDetailTypeDetails = (HashMap<String, Object>) lastTransactionDetails.get("accountDetailType");				
				HashMap<String,Object> accountTypeDetails = (HashMap<String, Object>) accountDetailTypeDetails.get("accountType");
				balanceDetails.put("accountDetailTypeId",accountDetailTypeDetails.get("accountDetailTypeId"));
				balanceDetails.put("accountTypeId",accountTypeDetails.get("accountTypeId"));
				
				balanceDetailsofAccountDetailTypes.add(balanceDetails);
				
			}
			//result.put("groupByAccountDetailType",groupByAccountDetailType);
			//result.put("balanceDetailsByAccountDetailType",balanceDetailsofAccountDetailTypes);
			
			// 3. Get All AccountType
			cri = session.createCriteria(AccountType.class);
			List<AccountType> Db_AccountTypes = cri.list();
			
			json = jsonViewObjectMapper.writeValueAsString(JsonView.with(Db_AccountTypes)
					.onClass(AccountDetailType.class, Match.match().exclude("accountType","financeTransactions"))					
					);
			
		
			List<HashMap<String,Object>> AccountTypes =  jsonViewObjectMapper.readValue(json,new TypeReference<List<HashMap<String,Object>>>(){});
			
			//result.put("defaultAccountTypeDetails",AccountTypes);
			
			// 4. Sum All Balance based on AccountTypes and assign them to accountTypes
			int totalAccountTypes = AccountTypes.size();			
			for(int i=0;i<totalAccountTypes;i++)
			{
				HashMap<String,Object> singleAccountTypes = AccountTypes.get(i);
				
				Integer accountTypeId = (int) singleAccountTypes.get("accountTypeId");
				
				double sum = balanceDetailsofAccountDetailTypes.stream().filter(v->{					
					boolean matched = v.get("accountTypeId").equals(accountTypeId);					
					return matched;
				}).collect(Collectors.summarizingDouble(v1->(double)v1.get("mainBalance"))).getSum();
				
				singleAccountTypes.put("balance",sum);
			}
			
			
			// 5. Group by ProfileLossType
			Map<Object, List<HashMap<String,Object>>> groupByProfileLossType = 
					AccountTypes.stream().collect(Collectors.groupingBy(
							(p)->{
								HashMap<String,Object> profitAndLoss =  (HashMap<String, Object>)  p.get("profileLossType");
								if(profitAndLoss == null)
								{
									return "Empty";
								}
								else
								{
									return profitAndLoss.get("headingName");
								}													
							})
						);
			
			// 5. Group by cashflowType
			Map<Object, List<HashMap<String,Object>>> groupByCashflowType = 
					AccountTypes.stream().collect(Collectors.groupingBy(
							(p)->{
								HashMap<String,Object> cashflow =  (HashMap<String, Object>)  p.get("cashflowType");
								if(cashflow == null)
								{
									return "Empty";
								}
								else
								{
									return cashflow.get("headingName");
								}													
							})
						);
			
			result.put("ProfileLossReport",groupByProfileLossType);	
			result.put("CashFlowReport",groupByCashflowType);	
		}
		catch (Exception e) {
			result.put("status",false);
			result.put("error",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session!= null && session.isOpen())
				session.close();
		}
		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public JSONObject generateFinancialReportUsingAccoutType(JSONObject reportDetails) {
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		try {			
			session = sessionFactory.getCurrentSession();			
			session.beginTransaction();
			
			LocalDate fromDate = LocalDate.parse(reportDetails.get("startDate").toString(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			
			LocalDate toDate = LocalDate.parse(reportDetails.get("endDate").toString(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			
			
			Date MonthFromDate = Date.from(fromDate.atStartOfDay(ZoneId.of("UTC")).toInstant());
			
			Date toFromDate = Date.from(toDate.atStartOfDay(ZoneId.of("UTC")).toInstant());
			
			
			Criteria cri = session.createCriteria(AccountType.class);
			cri.createAlias("accountDetailedTyes","accountDetailedTyes");
			cri.createAlias("accountDetailedTyes.financeTransactions", "financeTransactions");
			cri.add(Restrictions.ge("financeTransactions.transactionDate",MonthFromDate ));
			cri.add(Restrictions.le("financeTransactions.transactionDate",toFromDate ));
			
			
			
			List<AccountType> accountsType = cri.list();
			
			if(accountsType.isEmpty())
			{
				result.put("status",false);
				result.put("reason","AccountType Table is Empty");
				return result;
			}
			
			String json = jsonViewObjectMapper.writeValueAsString(JsonView.with(accountsType)
					//.onClass(AccountType.class, Match.match().exclude("profileLossType","cashflowType"))
					//.onClass(ProfitLossTypes.class, Match.match().exclude("*").include("headingName") )
					//.onClass(CashInflowandOutflowTypes.class, Match.match().exclude("*").include("headingName") )
					);
			Set<HashMap<String,Object>> accountsTypeSetObj =  jsonViewObjectMapper.readValue(json,new TypeReference<Set<HashMap<String,Object>>>(){});
			
			result.put("dbRetrieveResult",accountsTypeSetObj);
			
		}
		catch (Exception e) {
			result.put("status",false);
			result.put("error",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session!= null && session.isOpen())
				session.close();
		}
		return result;
		
	}	
	
	@SuppressWarnings("unchecked")
	public JSONObject generateFinancialReport1(JSONObject reportDetails) {
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		try {			
			session = sessionFactory.getCurrentSession();			
			session.beginTransaction();
			
			LocalDate fromDate = LocalDate.parse(reportDetails.get("startDate").toString(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			
			LocalDate toDate = LocalDate.parse(reportDetails.get("endDate").toString(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			
			
			Date MonthFromDate = Date.from(fromDate.atStartOfDay(ZoneId.of("UTC")).toInstant());
			
			Date toFromDate = Date.from(toDate.atStartOfDay(ZoneId.of("UTC")).toInstant());
			
			
			Criteria cri = session.createCriteria(AccountType.class);
			cri.createAlias("accountDetailedTyes","accountDetailedTyes");
			cri.createAlias("accountDetailedTyes.financeTransactions", "financeTransactions");
			cri.add(Restrictions.ge("financeTransactions.transactionDate",MonthFromDate ));
			cri.add(Restrictions.le("financeTransactions.transactionDate",toFromDate ));
			
			
			
			List<AccountType> accountsType = cri.list();
			
			if(accountsType.isEmpty())
			{
				result.put("status",false);
				result.put("reason","AccountType Table is Empty");
				return result;
			}
			
			String json = jsonViewObjectMapper.writeValueAsString(JsonView.with(accountsType)
					//.onClass(AccountType.class, Match.match().exclude("profileLossType","cashflowType"))
					//.onClass(ProfitLossTypes.class, Match.match().exclude("*").include("headingName") )
					//.onClass(CashInflowandOutflowTypes.class, Match.match().exclude("*").include("headingName") )
					);
			
		
			Set<HashMap<String,Object>> accountsTypeSetObj =  jsonViewObjectMapper.readValue(json,new TypeReference<Set<HashMap<String,Object>>>(){});
			
			List<HashMap<String,Object>> accountsTypeMapObj = new ArrayList<HashMap<String,Object>>(accountsTypeSetObj);
			
			for(int i=0;i<accountsTypeMapObj.size();i++)
			{
				HashMap<String,Object> accountDetails = accountsTypeMapObj.get(i);
				
				ArrayList<HashMap<String,Object>> subAccountTypes = (ArrayList<HashMap<String, Object>>) accountDetails.get("accountDetailedTyes");
				
				//System.out.println("subAccountTypes "+subAccountTypes);
				
				if(!subAccountTypes.isEmpty())
				{
				
					List<HashMap<String,Object>> sortedFinanceTransactions =  null;
					for (HashMap<String, Object> accountType : subAccountTypes) {
						ArrayList<HashMap<String,Object>> financeTransactions = (ArrayList<HashMap<String, Object>>) accountType.get("financeTransactions");
						
						//System.out.println("financeTransactions "+financeTransactions);
						
						if(financeTransactions != null)
						{
							sortedFinanceTransactions = financeTransactions.stream().sorted((o1,o2)->{				    
								return ((Integer)o1.get("financeTransactionId")).compareTo((Integer)o2.get("financeTransactionId"));
							    }).collect(
							    		Collectors.toList());
						}
						
					}
					
					//System.out.println("newAccountTypes"+sortedFinanceTransactions);
					
					if(sortedFinanceTransactions != null && sortedFinanceTransactions.size() != 0)
					{
						int index = sortedFinanceTransactions.size() > 1 ? sortedFinanceTransactions.size()-1 : 0;
						
						//System.out.println("newAccountTypes index is : "+index);
						
						HashMap<String,Object> lastTransactionDetails = sortedFinanceTransactions.get(index);
						
						//System.out.println("lastTransactionDetails index is : "+lastTransactionDetails);
						
						if(lastTransactionDetails.get("debit").toString().equals("0.0"))
						{
							// Perform Credit
							double begin_balance = (double) lastTransactionDetails.get("begin_balance");
							double credit_balance =(double) lastTransactionDetails.get("credit");
							accountDetails.put("mainBalance",begin_balance-credit_balance);
						}
						else
						{
							// Perform Debit						
							double begin_balance = (double) lastTransactionDetails.get("begin_balance");
							double credit_balance =(double) lastTransactionDetails.get("credit");
							//accountDetails.put("mainBalance",sum);
							accountDetails.put("mainBalance",begin_balance+credit_balance);
						}	
						System.out.println("accountDetails : "+accountDetails);
					}
					else
					{
						accountDetails.put("mainBalance",0);
					}
									
				}
				else
				{
					accountDetails.put("mainBalance",0);
				}	
			}
			
			// Prepare Profit & Loss Report
			
			// 1. Group By profitAndLoss
			Map<Object, List<HashMap<String,Object>>> groupByProfitLoss = 
					accountsTypeMapObj.stream().collect(Collectors.groupingBy((v)->{
						HashMap<String,Object> accountType = (HashMap<String, Object>) v;
						HashMap<String,Object> profitAndLoss =  (HashMap<String, Object>)  accountType.get("profileLossType");
						if(profitAndLoss == null)
						{
							return "Empty";
						}
						else
						{
							return profitAndLoss.get("headingName");
						}	
						
					}));
			
			// 2. Sum All the Values
			Set<Object> keys = groupByProfitLoss.keySet();
			
			Iterator<Object> keyItr = keys.iterator();
			
			ArrayList<HashMap<String,Object>> totalResultsofPL = new ArrayList<HashMap<String,Object>>();
			
			while(keyItr.hasNext())
			{
				String name = keyItr.next().toString();
				List<HashMap<String,Object>> values = groupByProfitLoss.get(name);
				
				HashMap<String,Object> singeResult = new HashMap<String,Object>();
				singeResult.put("name",name);
				if(!values.isEmpty())
				{					
					double sum = values.stream().mapToDouble((v)->{						
						return Double.parseDouble(v.get("mainBalance").toString());
						})
						.sum();
					singeResult.put("mainBalance",sum);
				}
				else
				{
					singeResult.put("mainBalance",0);
				}	
				totalResultsofPL.add(singeResult);
			}
			
			
			// Prepare cashflowType Report
			// 1. Group By cashflowType
			Map<Object, List<HashMap<String,Object>>> groupByCashFlow = 
					accountsTypeMapObj.stream().collect(Collectors.groupingBy((v)->{
						HashMap<String,Object> accountType = (HashMap<String, Object>) v;
						HashMap<String,Object> cashflowType =  (HashMap<String, Object>)  accountType.get("cashflowType");
						if(cashflowType == null)
						{
							return "Empty";
						}
						else
						{
							return cashflowType.get("headingName");
						}	
						
					}));
			
			// 2. Sum All the Values
			keys = groupByCashFlow.keySet();
			
			keyItr = keys.iterator();
			
			ArrayList<HashMap<String,Object>> totalResultsofCashFlow = new ArrayList<HashMap<String,Object>>();
			
			while(keyItr.hasNext())
			{
				String name = keyItr.next().toString();
				
				List<HashMap<String,Object>> values = groupByCashFlow.get(name);
				
				HashMap<String,Object> singeResult = new HashMap<String,Object>();
				singeResult.put("name",name);
				if(!values.isEmpty())
				{
					//int sum = values.stream().mapToInt((v)->(int)v.get("mainBalance")).sum();
					
					double sum = values.stream().mapToDouble((v)->{						
						return Double.parseDouble(v.get("mainBalance").toString());
						})
						.sum();
					
					singeResult.put("mainBalance",sum);
				}
				else
				{
					singeResult.put("mainBalance",0);
				}	
				totalResultsofCashFlow.add(singeResult);
			}
						
			
			//result.put("report", accountsTypeMapObj);
			result.put("groupByProfitLoss", groupByProfitLoss);
			result.put("groupByCashFlow", groupByCashFlow);
			result.put("ProfitReport", totalResultsofPL);
			result.put("CashFlowReport", totalResultsofCashFlow);
			
		} catch (Exception e) {
			result.put("status",false);
			result.put("error",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session!= null && session.isOpen())
				session.close();
		}
		return result;
	}

}
