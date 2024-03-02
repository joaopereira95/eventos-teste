package br.com.eventmanager.scheduler.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import br.com.eventmanager.scheduler.dto.EventoDTO;
import br.com.eventmanager.scheduler.service.EventoService;
import br.com.eventmanager.scheduler.utils.Constants;

@Component
public class EventQueueConsumer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EventQueueConsumer.class);
	
	private @Autowired EventoService eventoService;
	private @Autowired Gson gson;

	@RabbitListener(queues = { Constants.FILA_INICIO_EVENTO })
	public void receiveStartEvent(@Payload String jsonEvento) {
		LOGGER.info("[EventQueueConsumer][receiveStartEvent] Mensagem recebida via fila '{}': {}", Constants.FILA_INICIO_EVENTO, jsonEvento);
		
		try {
			var evento = gson.fromJson(jsonEvento, EventoDTO.class);
			eventoService.agendarInicioDeEvento(evento);
			
		} catch (RuntimeException e) {
			LOGGER.error("[EventQueueConsumer][receiveStartEvent] Erro ao processar mensagem da fila", e);
		}
		
	}
	
	@RabbitListener(queues = { Constants.FILA_FIM_EVENTO })
	public void receiveFinishEvent(@Payload String jsonEvento) {
		LOGGER.info("[EventQueueConsumer][receiveFinishEvent] Mensagem recebida via fila '{}': {}", Constants.FILA_FIM_EVENTO, jsonEvento);
		
		try {
			var evento = gson.fromJson(jsonEvento, EventoDTO.class);
			eventoService.agendarTerminoDeEvento(evento);
			
		} catch(RuntimeException e) {
			LOGGER.error("[EventQueueConsumer][receiveFinishEvent] Erro ao processar mensagem da fila", e);
		}
	}
}
