package br.com.eventmanager.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "br.com.eventmanager.*" })
@EntityScan("br.com.eventmanager.*")   
public class EventManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventManagerApiApplication.class, args);
	}

}
