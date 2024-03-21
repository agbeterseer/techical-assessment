package com.paymentgateway.gpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GpayApplication.class, args);
	}

	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}


}
