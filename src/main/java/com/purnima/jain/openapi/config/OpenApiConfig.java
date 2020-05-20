package com.purnima.jain.openapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components())
				.info(new Info()
						.title("OpenAPI Integration Application")
						.version("1.0")
						.description("Application for OpenAPI Integration")
						.license(new License().name("Apache 2.0").url("http://foo.bar"))
						.contact(new Contact().url("http://bar.foo").name("Purnima Jain").email("abc@gmail.com"))
						);
	}

}
