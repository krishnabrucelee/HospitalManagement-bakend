/**
 * 
 */
package com.hospital.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Ambulance;
import com.hospital.model.RoomManagement;

/**
 * @author Krishna
 *
 */
public interface RoomManagementService {

	public RoomManagement addRoomManagement(RoomManagement roomManagement);
}
