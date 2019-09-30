package com.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableHystrix // Feign默认是开启，这个注解可以不加的
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudEurekaFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudEurekaFeignApplication.class, args);
	}

}
