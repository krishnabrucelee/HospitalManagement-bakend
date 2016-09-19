package com.hospital.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sf;
	static{
		try {
			sf = new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			System.out.println("Initial Session factory creation failed");
			e.printStackTrace();
		
		}
	}
	public static SessionFactory getSessionFactory(){
		return sf;
	}

}