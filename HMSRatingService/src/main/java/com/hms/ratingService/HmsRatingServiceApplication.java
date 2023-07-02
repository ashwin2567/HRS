package com.hms.ratingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HmsRatingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HmsRatingServiceApplication.class, args);
	}

}
