/**
 * 
 */
package com.hospital.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Appointment;
import com.hospital.model.Doctor;
import com.hospital.model.DoctorAppointment;
import com.hospital.model.DoctorsDefaultSchedule;
import com.hospital.model.Patient;
import com.monitorjbl.json.JsonView;
import com.monitorjbl.json.Match;

/**
 * @author Krishna
 *
 */
@Repository
public class AppointmentDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;
	
	
	@Autowired
	@Qualifier("jsonViewObjectMapper")
	ObjectMapper jsonViewObjectMapper;

	static {
		System.out.println("class AppointmentDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addAppointment(JSONObject appointmentDetails) {
		/*JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Appointment appoint = om.convertValue(appointment, Appointment.class);
		// Load Doctor
		Doctor doctorDetails = session.load(Doctor.class, (Integer) appointment.get("doctor_id"));
		appoint.setDoctor(doctorDetails);
		// Load Patient
		Patient patientDetails = session.load(Patient.class, (Integer) appointment.get("patient_id"));
		appoint.setPatient(patientDetails);

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save appointments");
			status.put("success", "User details saved");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;*/
		
		JSONObject result = new JSONObject();
		result.put("status",true);
		Session session = null;
		try {
			
			session = this.sessionFactory.getCurrentSession();
			
			session.beginTransaction();
			
			DoctorAppointment doctorAppointment = jsonViewObjectMapper.convertValue(appointmentDetails, com.hospital.model.DoctorAppointment.class);
			
			Doctor doct_details = session.get(Doctor.class,(int)appointmentDetails.get("doctorId"));
			
			if(doct_details == null)
			{
				result.put("status",false);
				result.put("reason","Can't find Doctor details. Please check doctorId field");
				return result;
			}
			
			Patient patient_details = session.get(Patient.class,(int)appointmentDetails.get("patientId"));
			
			if(patient_details == null)
			{
				result.put("status",false);
				result.put("reason","Can't find Patient details. Please check patientId field");
				return result;
			}
			
				
			doctorAppointment.setDoctorDetails(doct_details);
			doctorAppointment.setPatientDetails(patient_details);
			
			doctorAppointment.setCreationDate(new Date());
			
			session.save(doctorAppointment);
			
			session.getTransaction().commit();
			
			System.out.println(doctorAppointment.getStarttime()+" "+doctorAppointment.getEndtime());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status",false);
			result.put("reason","error happend");
			result.put("message",e.getMessage());
		}
		return result;
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getDoctorAvaliablities(JSONObject doctor) {
		JSONObject result = new JSONObject();
		result.put("status", true);
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			
			LinkedHashMap<String, Object> output = new LinkedHashMap<String, Object>();
			
			String startDate = (String) doctor.get("date");
			
			LocalDate ld = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			
			ZonedDateTime startZDT = ld.atStartOfDay(ZoneId.of("UTC"));
			
			LocalDateTime startLDT = startZDT.toLocalDateTime();			
			
			LocalDateTime endLDT = startLDT.plusDays(1);	
			
			
			Date startDateObj = Date.from(startZDT.toInstant());
			
			Date endDateObj = Date.from(endLDT.toInstant(ZoneOffset.UTC));
			
			
			ArrayList<Object> emptyList = new ArrayList<Object>();
			
			// 1. Get Doctor Details
			Doctor doctorDetails = session.get(Doctor.class,(int)doctor.get("doctorId"));
			
			if(doctorDetails == null)
			{
				result.put("status",false);
				result.put("reason","doctor_details_id is wrong please check");
				return result;
			}
			
			
			// 2. Get Doctors Default Schedule & doctorsSchedule		
			
			Criteria cri = session.createCriteria(DoctorsDefaultSchedule.class);
			cri.add(Restrictions.eq("doctorDetails",doctorDetails)				
			);
			
			List<DoctorsDefaultSchedule> doctorDefaultSchedule = cri.list();	
			
			String jsonString = jsonViewObjectMapper.writeValueAsString(JsonView.with(doctorDefaultSchedule)
					.onClass(DoctorsDefaultSchedule.class,Match.match().exclude("doctorDetails"))
					);
			
			output.put("defaultSchdule", jsonViewObjectMapper.readValue(jsonString,new TypeReference<ArrayList<Map<String,Object>>>(){}));
			
			// 3. Get Doctor Appointments for particular Date
			cri = session.createCriteria(DoctorAppointment.class);			
			cri.add(
					Restrictions.and(
							Restrictions.ge("starttime", startDateObj),
							Restrictions.lt("starttime", endDateObj),
							Restrictions.eq("doctorDetails",doctorDetails)
					)
			);
			
			List<DoctorAppointment> doctorAppointments = cri.list();	
			
			output.put("doctorAppointments",emptyList);
			
			if(!doctorAppointments.isEmpty())
			{
				jsonString = jsonViewObjectMapper.writeValueAsString(JsonView.with(doctorAppointments)
						.onClass(DoctorAppointment.class, Match.match().exclude("doctorDetails","patientDetails"))						
						);
				
				output.put("doctorAppointments", jsonViewObjectMapper.readValue(jsonString,new TypeReference<ArrayList<HashMap<String,Object>>>(){}));
				
			}
			
			result.put("result",output);
			
		} catch (Exception e) {
			result.put("status", false);
			result.put("reason","Error Happend");
			result.put("message",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if(session.isOpen() && session.isConnected())
				session.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getDoctorAppointmentForCurrentDate(JSONObject doctorId) {
		JSONObject result = new JSONObject();
		result.put("status", true);
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			
			// 1. Get Doctor Details
			Doctor doctorDetails = session.get(Doctor.class,(int)doctorId.get("doctorId"));
			
			if(doctorDetails == null)
			{
				result.put("status",false);
				result.put("reason","doctor_details_id is wrong please check");
				return result;
			}
			
			
			LocalDate ld = LocalDate.now();
			
			Date currentDate = Date.from(ld.atStartOfDay(ZoneId.of("UTC")).toInstant());
			
			// 2. Get Doctor Appointments for particular Date
			Criteria cri = session.createCriteria(DoctorAppointment.class);
			cri.setFetchMode("patientDetails",FetchMode.JOIN);
			cri.add(
					Restrictions.and(
							Restrictions.ge("starttime",currentDate),
							Restrictions.le("starttime",Date.from(ld.atTime(23,59).atZone(ZoneId.of("UTC")).toInstant())),
							Restrictions.eq("doctorDetails",doctorDetails)
					)
			);
				
			List<DoctorAppointment> doctorAppointments = cri.list();	
			
			if(!doctorAppointments.isEmpty())
			{
				String jsonString = jsonViewObjectMapper.writeValueAsString(JsonView.with(doctorAppointments)
						.onClass(DoctorAppointment.class, Match.match().exclude("doctorDetails"))
						.onClass(Patient.class,  Match.match().exclude("*").include("email","patientId","patientName"))
						);
				
				result.put("result", jsonViewObjectMapper.readValue(jsonString,new TypeReference<ArrayList<HashMap<String,Object>>>(){}));
				
			}
			else
			{
				result.put("result",new ArrayList<>());
			}	
						
		}
		catch(Exception e){
			result.put("status", false);
			result.put("reason","Error Happend");
			result.put("message",e.getMessage());
			e.printStackTrace();
		}
		return result;
	}	
	
	public JSONObject listAppointment() {
		System.out.println("Inside Dao1Appointment");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Appointment> appointmentList = null;
		try {
			Query query = session.createQuery("FROM Appointment");
			appointmentList = query.list();
			status.put("Appointment", appointmentList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}

	public JSONObject updateAppointment(JSONObject appointment) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Appointment appointmentDetails = session.load(Appointment.class,
					(Integer) appointment.get("appointmentId"));
			session.update(appointmentDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}

	public Appointment getAppointment(Integer appointmentId) {
		Appointment appointment = null;
		try {
			session.beginTransaction();
			session.get(Appointment.class, appointmentId);
			appointment = (Appointment) session.get(Appointment.class, appointmentId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (appointment != null) {
			return appointment;
		} else {
			return null;
		}
	}

	public JSONObject deleteAppointment(JSONObject appointmentId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Appointment appointmentDetails = session.load(Appointment.class,
					(Integer) appointmentId.get("appointmentId"));
			session.delete(appointmentDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}
}
