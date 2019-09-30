package com.msg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class TbmsgEurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(TbmsgEurekaClientApplication.class, args);
	}

}
