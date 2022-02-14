package com.entrevista.prueba;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;



@SpringBootApplication
@OpenAPIDefinition
public class PruebaTecnicaApplication {
	
	@Bean
	public RestTemplate getResttemplate() {
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(PruebaTecnicaApplication.class, args);
	}
	

}
