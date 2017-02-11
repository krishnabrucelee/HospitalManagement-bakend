/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.SurgeryRoomDao;
import com.hospital.service.SurgeryRoomService;

/**
 * @author Krishna
 *
 */
@Service
public class SurgeryRoomServiceImpl implements SurgeryRoomService {

	/**
	 * SurgeryRoom dao
	 */
	@Autowired
	SurgeryRoomDao surgeryRoomdao;
	
	@Override
	public JSONObject addSurgeryRoom(JSONObject surgeryRoom) {
		return surgeryRoomdao.addSurgeryRoom(surgeryRoom);
	}

	@Override
	public JSONObject listSurgeryRoom() {
		return surgeryRoomdao.listSurgeryRoom();
	}

	@Override
	public JSONObject updateSurgeryRoom(JSONObject surgeryRoom) {
		return surgeryRoomdao.updateSurgeryRoom(surgeryRoom);
	}

	@Override
	public JSONObject deleteSurgeryRoom(JSONObject surgeryRoomId) {
		return surgeryRoomdao.deleteSurgeryRoom(surgeryRoomId);
	}

}
