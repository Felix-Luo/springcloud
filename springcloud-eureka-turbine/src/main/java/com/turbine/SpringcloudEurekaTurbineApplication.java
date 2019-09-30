package com.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableTurbine
@SpringBootApplication
public class SpringcloudEurekaTurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudEurekaTurbineApplication.class, args);
	}

}
