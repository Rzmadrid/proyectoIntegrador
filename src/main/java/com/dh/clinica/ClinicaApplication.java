package com.dh.clinica;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicaApplication {

	public static void main(String[] args) {

		//H2Connection.crearTablas();
		SpringApplication.run(ClinicaApplication.class, args);
	}

}
