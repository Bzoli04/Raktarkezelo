package com.example.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Ez az annotacio jelzi a Spring Bootnak, hogy ez az alkalmazas fo konfiguracios osztalya.
@SpringBootApplication
public class WarehouseApplication {

	// Az alkalmazas belepesi pontja: innen indul el a beagyazott szerver es a Spring kontener.
	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);
	}

}
