package com.purnima.jain.openapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpenApiApplication {

	private static final Logger logger = LoggerFactory.getLogger(OpenApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OpenApiApplication.class, args);
		logger.info("OpenApiApplication Started........");
	}

}
