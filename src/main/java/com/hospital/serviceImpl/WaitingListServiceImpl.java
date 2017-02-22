/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.WaitingListDao;
import com.hospital.service.WaitingListService;

/**
 * @author Krishna
 *
 */
@Service
public class WaitingListServiceImpl implements WaitingListService {

	/**
	 * WaitingList dao
	 */
	@Autowired
	WaitingListDao waitingListdao;
	
	@Override
	public JSONObject addWaitingList(JSONObject waitingList) {
		return waitingListdao.addWaitingList(waitingList);
	}

	@Override
	public JSONObject listWaitingList() {
		return waitingListdao.listWaitingList();
	}

	@Override
	public JSONObject updateWaitingList(JSONObject waitingList) {
		return waitingListdao.updateWaitingList(waitingList);
	}

	@Override
	public JSONObject deleteWaitingList(JSONObject waitingListId) {
		return waitingListdao.deleteWaitingList(waitingListId);
	}

}
