package com.concurso.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.concurso")
public class SpringApiConsumidorWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringApiConsumidorWebApplication.class, args);
	}

}
