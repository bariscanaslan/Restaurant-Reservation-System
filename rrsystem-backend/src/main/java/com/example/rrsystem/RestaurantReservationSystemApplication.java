package com.example.rrsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RestaurantReservationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantReservationSystemApplication.class, args);
	}

}
