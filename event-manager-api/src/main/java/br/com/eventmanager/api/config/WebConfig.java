package br.com.eventmanager.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

	private static final String ALLOWED_ORIGINS = "http://localhost:3000";

	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/eventos").allowedOrigins(ALLOWED_ORIGINS);				
				registry.addMapping("/instituicoes").allowedOrigins(ALLOWED_ORIGINS);
			}
		};
	}
	
}
