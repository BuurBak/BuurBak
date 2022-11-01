package com.buurbak.api;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "BuurBak",
				version = "alpha",
				description = """
					BuurBak is an initiative by Open-ICT students to create a ride sharing platform for trailers.
				""",
				contact = @Contact(name = "Github", url = "https://github.com/BuurBak/BuurBak")
		))
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


}

