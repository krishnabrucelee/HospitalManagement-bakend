/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.CssdDao;
import com.hospital.service.CssdService;

/**
 * @author Krishna
 *
 */
@Service
public class CssdServiceImpl implements CssdService {

	/**
	 * Cssd dao
	 */
	@Autowired
	CssdDao cssddao;
	
	@Override
	public JSONObject addCssd(JSONObject cssd) {
		return cssddao.addCssd(cssd);
	}

	@Override
	public JSONObject listCssd() {
		return cssddao.listCssd();
	}

	@Override
	public JSONObject updateCssd(JSONObject cssd) {
		return cssddao.updateCssd(cssd);
	}

	@Override
	public JSONObject deleteCssd(JSONObject cssdId) {
		return cssddao.deleteCssd(cssdId);
	}

}
