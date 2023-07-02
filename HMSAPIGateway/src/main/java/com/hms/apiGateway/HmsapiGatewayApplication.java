package com.hms.apiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient 
public class HmsapiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(HmsapiGatewayApplication.class, args);
	}

}
