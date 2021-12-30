package com.infosys.ftr.vehicles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@PropertySource("classpath:ValidationMessages.properties")
public class VehiclesMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehiclesMsApplication.class, args);
	}

}
