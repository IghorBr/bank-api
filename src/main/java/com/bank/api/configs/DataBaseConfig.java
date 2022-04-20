package com.bank.api.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bank.api.services.impl.DBServiceImpl;

@Configuration
public class DataBaseConfig {

	@Autowired private DBServiceImpl dbService;
	
	@Bean
	public boolean instantiateDatabase() {
		dbService.instantiateTestDatabase();		
		return true;
	}
	
}