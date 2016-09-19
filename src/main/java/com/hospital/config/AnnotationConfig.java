package com.hospital.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnotationConfig {
	
	@Bean
	public SessionFactory sessionFacory(){
		return  new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
	}

}
