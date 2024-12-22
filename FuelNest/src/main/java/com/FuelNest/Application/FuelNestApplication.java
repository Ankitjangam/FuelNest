package com.FuelNest.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.FuelNest.Application.Model")
public class FuelNestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FuelNestApplication.class, args);
	}

}
