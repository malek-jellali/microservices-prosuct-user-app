package com.jellali.serviceproduit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceProduitApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProduitApplication.class, args);
	}

}
