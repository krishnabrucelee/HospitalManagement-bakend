/**
 * 
 */
package com.hospital.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.hospital.model.Floor;

/**
 * @author Krishna
 *
 */
public interface FloorService {

	/**
	 * Create Floor.
	 * 
	 * @param Floor
	 * @return Floor
	 */
	public JSONObject addFloor(JSONObject floor);

	/**
	 * List Floor.
	 * 
	 * @param Floor
	 * @return Floor
	 */
	public JSONObject listFloor();

	/**
	 * Update Floor.
	 * 
	 * @param Floor
	 * @return Floor
	 */
	public JSONObject updateFloor(JSONObject floor);

	/**
	 * Delete Floor.
	 * 
	 * @param Floor
	 * @return Floor
	 */
	public JSONObject deleteFloor(JSONObject floorId);

	/**
	 * @param floor
	 * @return
	 */
	public JSONObject listRoomByFilter(JSONObject floor);

	/**
	 * @param floor
	 * @return
	 */
	public Floor getFloor(Integer floorId);
	
	/**
	 * @param object
	 */
	public List<Floor> getRoomDetailsByWardNumber(JSONObject object);

}
