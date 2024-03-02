package br.com.eventmanager.scheduler.service;

import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import br.com.eventmanager.scheduler.dto.EventoDTO;
import br.com.eventmanager.scheduler.repository.EventoRepository;
import br.com.eventmanager.scheduler.utils.CronUtils;
import jakarta.transaction.Transactional;

@Service
public class EventoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EventoService.class);
	private static final TimeZone TIMEZONE = TimeZone.getTimeZone("America/Sao_Paulo");

	private @Autowired TaskScheduler scheduler;
	private @Autowired EventoRepository repository;

	public void agendarInicioDeEvento(EventoDTO eventoDTO) {
		
		Runnable task = () -> {
			var eventoOptional = repository.findById(eventoDTO.getIdEvento());
			LOGGER.info("[EventoService][agendarInicioDeEvento] Atualizando Evento ID '{}' para ATIVO", eventoDTO.getIdEvento());

			eventoOptional.ifPresent(evento -> {
				evento.iniciarEvento();
				repository.save(evento);
			});

		};

		var cronExpression = CronUtils.convertOffsetDateTimeToCronExpression(eventoDTO.getData());
		var cronTrigger = new CronTrigger(cronExpression, TIMEZONE);
		
		LOGGER.info("[EventoService][agendarInicioDeEvento] Agendando atualizacao do Evento ID '{}' para ATIVO. Expressao CRON: {}", eventoDTO.getIdEvento(), cronExpression);

		scheduler.schedule(task, cronTrigger);

	}

	@Transactional
	public void agendarTerminoDeEvento(EventoDTO eventoDTO) {

		Runnable task = () -> {
			var eventoOptional = repository.findById(eventoDTO.getIdEvento());
			LOGGER.info("[EventoService][agendarInicioDeEvento] Atualizando Evento ID '{}' para INATIVO e FINALIZADO", eventoDTO.getIdEvento());

			eventoOptional.ifPresent(evento -> {
				evento.finalizarEvento();
				repository.save(evento);
			});
		};

		var cronExpression = CronUtils.convertOffsetDateTimeToCronExpression(eventoDTO.getData());
		var cronTrigger = new CronTrigger(cronExpression, TIMEZONE);
		
		LOGGER.info("[EventoService][agendarInicioDeEvento] Agendando atualizacao do Evento ID '{}' para INATIVO e FINALIZADO. Expressao CRON: {}", eventoDTO.getIdEvento(), cronExpression);

		scheduler.schedule(task, cronTrigger);

	}

}
