/**
 * 
 */
package com.hospital.util;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * @author admin
 *
 */
public class DateSerializer  extends StdSerializer<Date>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DateSerializer(){
		super(Date.class,true);
	}
	@Override
	public void serialize(Date value, JsonGenerator gen,
			SerializerProvider provider) throws IOException {
		Instant ins = Instant.ofEpochMilli(value.getTime());
				
		gen.writeString(ins.atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
	}

}
