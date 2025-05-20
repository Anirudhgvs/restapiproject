package com.classroom.restapiproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class RestAPiprojectApplication {

	private static final Logger log = LoggerFactory.getLogger(RestAPiprojectApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(RestAPiprojectApplication.class, args);

	}

}
