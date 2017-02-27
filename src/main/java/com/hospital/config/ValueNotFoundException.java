package com.hospital.config;

public class ValueNotFoundException extends Exception {
	
	String errorMessage;

	public ValueNotFoundException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	 @Override
	    public String toString()
	    {
	        return errorMessage;
	    }

}
