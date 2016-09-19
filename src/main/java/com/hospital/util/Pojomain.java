package com.hospital.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Pojomain extends HibernateUtil{
	
	public Pojomain(){
		HibernateUtil.getSessionFactory();
	}
	
	public void sample()
	{
		System.out.println("Sample Method");
	}
	
	public static void main(String[] args) {
		
		System.out.println("Inside main");
		SessionFactory sf=HibernateUtil.getSessionFactory();
		try {
			Session session=sf.openSession();
		} catch (NullPointerException e) {
			e.printStackTrace();
			
		}
		
		

	}

}
