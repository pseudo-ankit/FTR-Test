package com.ftruser.ftr.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
@PropertySource("classpath:ValidationMessages.properties")
public class FtrUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(FtrUserApplication.class, args);
		System.out.println("hello");
	}

}
