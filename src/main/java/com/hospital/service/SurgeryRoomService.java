/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna
 *
 */
public interface SurgeryRoomService {

	/**
	 * Create SurgeryRoom.
	 * 
	 * @param surgeryRoom
	 * @return surgeryRoom
	 */
	public JSONObject addSurgeryRoom(JSONObject surgeryRoom);

	/**
	 * List SurgeryRoom.
	 * 
	 * @param surgeryRoom
	 * @return surgeryRoom
	 */
	public JSONObject listSurgeryRoom();

	/**
	 * Update SurgeryRoom.
	 * 
	 * @param surgeryRoom
	 * @return surgeryRoom
	 */
	public JSONObject updateSurgeryRoom(JSONObject surgeryRoom);

	/**
	 * Delete SurgeryRoom.
	 * 
	 * @param surgeryRoom
	 * @return surgeryRoom
	 */
	public JSONObject deleteSurgeryRoom(JSONObject surgeryRoomId);

}
