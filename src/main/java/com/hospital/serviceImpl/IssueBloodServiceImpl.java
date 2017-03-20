/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.IssueBloodDao;
import com.hospital.model.IssueBlood;
import com.hospital.model.IssueBlood;
import com.hospital.service.IssueBloodService;

/**
 * @author Krishna
 *
 */
@Service
public class IssueBloodServiceImpl implements IssueBloodService {

	/**
	 * IssueBlood dao
	 */
	@Autowired
	IssueBloodDao issueBlooddao;
	
	@Override
	public JSONObject addIssueBlood(JSONObject issueBlood) {
		return issueBlooddao.addIssueBlood(issueBlood);
	}

	@Override
	public JSONObject listIssueBlood() {
		return issueBlooddao.listIssueBlood();
	}

	@Override
	public JSONObject updateIssueBlood(JSONObject issueBlood) {
		return issueBlooddao.updateIssueBlood(issueBlood);
	}

	@Override
	public JSONObject deleteIssueBlood(JSONObject issueBloodId) {
		return issueBlooddao.deleteIssueBlood(issueBloodId);
	}

	@Override
	public IssueBlood updateIssueBloodById(IssueBlood issueBlood) {
		return issueBlooddao.updateIssueBloodById(issueBlood);
	}

	@Override
	public Integer deleteIssueBloodById(Integer issueBloodId) {
		return issueBlooddao.deleteIssueBloodById(issueBloodId);
	}
}
