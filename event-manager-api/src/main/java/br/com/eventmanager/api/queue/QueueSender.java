package br.com.eventmanager.api.queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueSender {

	@Autowired
    private RabbitTemplate rabbitTemplate;
    
    private static final String EVENT_STARTED_QUEUE_NAME = "event.started.queue";
    private static final String EVENT_FINISHED_QUEUE_NAME = "event.finished.queue";

    public void sendStartEvent(String eventoJson) {
        rabbitTemplate.convertAndSend(EVENT_STARTED_QUEUE_NAME, eventoJson);
    }
    
    public void sendFinishEvent(String eventoJson) {
        rabbitTemplate.convertAndSend(EVENT_FINISHED_QUEUE_NAME, eventoJson);
    }
}
