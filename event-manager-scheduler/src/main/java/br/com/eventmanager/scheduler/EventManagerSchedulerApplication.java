package br.com.eventmanager.scheduler;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableRabbit
@EnableScheduling
@ComponentScan(basePackages = { "br.com.eventmanager.*" })
@EntityScan("br.com.eventmanager.*")   
@SpringBootApplication
public class EventManagerSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventManagerSchedulerApplication.class, args);
	}

}
