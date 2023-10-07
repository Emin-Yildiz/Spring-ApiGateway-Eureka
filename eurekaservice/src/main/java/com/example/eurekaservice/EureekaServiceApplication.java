package com.example.eurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EureekaServiceApplication {

	//TODO eureka'da api gateway'e istek at
	public static void main(String[] args) {
		SpringApplication.run(EureekaServiceApplication.class, args);
	}

}
