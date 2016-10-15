package com.hospital.config;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MyRoutingDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		String language = LocaleContextHolder.getLocale().getLanguage();
		System.out.println("Language obtained: "+ language);
		return language;
	}
}
