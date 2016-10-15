///**
// * 
// */
//package com.hospital.config;
//
//import java.util.Enumeration;
//import java.util.Properties;
//import java.util.Set;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.hibernate.SessionFactory;
//import org.hibernate.internal.SessionFactoryImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
///**
// * @author prem
// *
// */
//@Component
//public class DBInterceptor extends HandlerInterceptorAdapter {
//
//	@Autowired
//	SessionFactory sessionfactory;
//	
//	@Override
//	public boolean preHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler) throws Exception {
//		long startTime = System.currentTimeMillis();
//		
//		System.out.println("Interceptor working");
//		
//		SessionFactoryImpl ss = (SessionFactoryImpl) sessionfactory;
//		Properties allprop = ss.getProperties();
////		Set<Object> keys = allprop.keySet();
////		for(Object k:keys){
////			String key = (String)k;
////			System.out.println(key+": "+allprop.getProperty(key));
////		}
//		
//		String url = allprop.getProperty("hibernate.connection.url"); 
//		String dbname=url.substring(url.lastIndexOf("/"));
//		
//		allprop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/testtwo");
//		
//		System.out.println(allprop.getProperty("hibernate.connection.url"));
//		
//		//if returned false, we need to make sure 'response' is sent
//		return true;
//	}
//}
