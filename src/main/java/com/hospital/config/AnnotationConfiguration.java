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
	
	public static final String WIN_BASE_PATH_NURSEROSTERING = "C:\\Users\\admin\\Desktop\\Nurse Scheduling\\";
	public static final String LINUX_BASE_PATH_NURSEROSTERING = "";
	public static final String WIN_OUTPUT_PATH_NURSEROSTERING = "C:\\Users\\admin\\Desktop\\Nurse Scheduling\\";
	public static final String LINUX_OUTPUT_PATH_NURSEROSTERING = "";
	public static final String SOLVER_CONFIG  = "org/optaplanner/examples/nurserostering/solver/nurseRosteringSolverConfig.xml";
	
	
	@Bean(name="om")
	public ObjectMapper objectMapper(){
		return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	@Bean(name="jsonViewObjectMapper")
	public ObjectMapper jsonViewObjectMapper()
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    	SimpleModule module = new SimpleModule();
    	module.addSerializer(JsonView.class, new JsonViewSerializer());
    	mapper.registerModule(module);
		return mapper;
	}

}
