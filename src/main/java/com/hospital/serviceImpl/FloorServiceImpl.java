/**
 * 
 */
package com.hospital.serviceImpl;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.FloorDao;
import com.hospital.model.Floor;
import com.hospital.service.FloorService;

/**
 * @author Krishna
 *
 */
@Service
public class FloorServiceImpl implements FloorService {


	/**
	 * Floor dao
	 */
	@Autowired
	private FloorDao floordao;
	
	@Override
	public JSONObject addFloor(JSONObject floor) {
		return floordao.addFloor(floor);
	}

	@Override
	public JSONObject listFloor() {
		return floordao.listFloor();
	}

	@Override
	public JSONObject updateFloor(JSONObject floor) {
		return floordao.updateFloor(floor);
	}

	@Override
	public JSONObject deleteFloor(JSONObject floorId) {
		return floordao.deleteFloor(floorId);
	}

	@Override
	public JSONObject listRoomByFilter(JSONObject floor) {
		return floordao.listRoomByFilter(floor);
	}

	@Override
	public List<Floor> getRoomDetailsByWardNumber(JSONObject object) {
		return floordao.getRoomDetailsByWardNumber(object);
	}

	@Override
	public Floor getFloor(Integer floorId) {
		return floordao.getFloor(floorId);
	}

	@Override
	public JSONObject getRoomDetailsByWard(JSONObject object) {
		return floordao.getRoomDetailsByWard(object);
	}

}
