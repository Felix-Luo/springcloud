package com.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableHystrix // 启用 hystrix 熔断机制相关配置
@EnableDiscoveryClient
@SpringBootApplication
public class SpringcloudEurekaRibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudEurekaRibbonApplication.class, args);
	}

}
