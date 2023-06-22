package com.acelerati.gestionacademia;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(
		title = "Api Academia",
		version = "1.0"
))
@SpringBootApplication
public class ApiAcademiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAcademiaApplication.class, args);
	}

}
