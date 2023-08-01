package com.example.datn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class BackendTravelApplication {

	public static void main(String[] args) {
			SpringApplication.run(BackendTravelApplication.class, args);
	}

}
