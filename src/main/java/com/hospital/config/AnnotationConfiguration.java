package com.hospital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.monitorjbl.json.JsonView;
import com.monitorjbl.json.JsonViewSerializer;

@Configuration
public class AnnotationConfiguration {
	@Bean(name="om")
	public ObjectMapper objectMapper(){
		return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	@Bean(name="jsonViewObjectMapper")
	public ObjectMapper jsonViewObjectMapper()
	{
		ObjectMapper mapper = new ObjectMapper();
    	SimpleModule module = new SimpleModule();
    	module.addSerializer(JsonView.class, new JsonViewSerializer());
    	mapper.registerModule(module);
		return mapper;
	}

}
