package br.com.eventmanager.scheduler.queue;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.eventmanager.scheduler.utils.Constants;

@Configuration
public class EventQueueConfig {

	@Bean
    Queue createEventStartedQueue() {
        return new Queue(Constants.FILA_INICIO_EVENTO, true);
    }
	
	@Bean
    Queue createEventFinishedQueue() {
        return new Queue(Constants.FILA_FIM_EVENTO, true);
    }
	
}
