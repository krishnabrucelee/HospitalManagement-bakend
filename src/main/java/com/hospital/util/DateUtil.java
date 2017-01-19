/**
 * 
 */
package com.hospital.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Krishna
 *
 */
public class DateUtil {

	public static Date dateUtil (String dateInString) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = null;
		try {
			date = formatter.parse(dateInString);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return date;
	}
	
	public static Date dateTimeUtil(String dateInString) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = null;
		try {
			date = formatter.parse(dateInString);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return date;
	}
	
	public static Date InterNationalDateTimeUtil(String dateInString){ 
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date date = null;
		try {
			date = (Date) formatter.parse(dateInString);
			System.out.println(date);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * Calendar cal = Calendar.getInstance(); cal.setTime(date); String
		 * formatedDate = cal.get(Calendar.DATE) + "/" +
		 * (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
		 * System.out.println("formatedDate : " + formatedDate);
		 */
		return date;
	}
}
