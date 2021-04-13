package com.minachi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AppMain {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppMain.class);
	
	public static void main(String[] args) {
		LOGGER.info("START RUNNING SPRING BOOT APPLICATION");
		SpringApplication.run(AppMain.class, args);
	}
}
