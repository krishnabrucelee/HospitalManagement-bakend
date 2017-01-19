/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.RoomManagementDao;
import com.hospital.model.RoomManagement;
import com.hospital.service.RoomManagementService;

/**
 * @author Krishna
 *
 */
@Service
public class RoomManagementServiceImpl implements RoomManagementService {

	/**
	 * Floor dao
	 */
	@Autowired
	private RoomManagementDao roomManagementdao;
	
	@Override
	public RoomManagement addRoomManagement(RoomManagement roomManagement) {
		return roomManagementdao.addRoomManagement(roomManagement);
	}

}
