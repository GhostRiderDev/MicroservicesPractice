package com.microservices.calification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CalificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalificationServiceApplication.class, args);
	}

}
