package br.com.eventmanager.api.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;

import br.com.eventmanager.api.adapter.OffsetDateTimeAdapter;
import br.com.eventmanager.api.dto.EventoDTO;
import br.com.eventmanager.api.exception.BusinessException;
import br.com.eventmanager.api.model.Evento;
import br.com.eventmanager.api.queue.QueueSender;
import br.com.eventmanager.api.repository.EventoRepository;
import jakarta.transaction.Transactional;

@Service
public class EventoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EventoService.class);
	
	private @Autowired QueueSender queueSender;
	private @Autowired EventoRepository repository;
	
	public List<Evento> buscarTodos() {
		return repository.findAll();
	}

	@Transactional
	public void inserirEvento(Evento evento) {
		
		LOGGER.info("[EventoService][inserirEvento] Iniciando insercao de Evento");
		
		validarEvento(evento);

		var dataHoraAtual = OffsetDateTime.now();

		evento.definirSituacaoEvento(dataHoraAtual);

		evento = repository.save(evento);

		agendarAtualizacaoDeEventos(evento, dataHoraAtual);

		LOGGER.info("[EventoService][inserirEvento] Finalizando insercao de Evento");
		
	}
	
	private static void validarEvento(Evento evento) {
		
		LOGGER.info("[EventoService][validarEvento] Validando dados de Evento");

		if (evento == null) {
			throw new BusinessException("Evento é obrigatório");
		}
		
		if (evento.getInstituicao() == null || evento.getInstituicao().getId() == null) {
			throw new BusinessException("Instituição é obrigatória");
		}
		
		if (evento.getDataInicial() == null) {
			throw new BusinessException("Data inicial é obrigatória");
		}
		
		if (evento.getDataFinal() == null) {
			throw new BusinessException("Data final é obrigatória");
		}
		
		if (evento.getDataInicial().isEqual(evento.getDataFinal()) 
		 || evento.getDataInicial().isAfter(evento.getDataFinal())) {
			throw new BusinessException("A data final deve ser após a data inicial");
		}
	}
	
	/**
	 * Agenda as acoes para atualizar os eventos na data de inicio / fim
	 * @param evento
	 * @param dataHoraAtual
	 */
	private void agendarAtualizacaoDeEventos(Evento evento, OffsetDateTime dataHoraAtual) {
		
		LOGGER.info("[EventoService][agendarAtualizacaoDeEventos] Iniciando agendamento de atualizacao do Evento");
		
		var builder = new GsonBuilder().registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter());
		var gson = builder.create();	
		
		if (evento.pendente(dataHoraAtual)) {
			LOGGER.info("[EventoService][agendarAtualizacaoDeEventos] Evento PENDENTE. Agendando atualizacao de INICIO e FINALIZACAO do Evento");
			queueSender.sendStartEvent(gson.toJson(new EventoDTO(evento.getId(), evento.getDataInicial().toString())));
			queueSender.sendFinishEvent(gson.toJson(new EventoDTO(evento.getId(), evento.getDataFinal().toString())));
		}
		
		if (evento.emAndamento(dataHoraAtual)) {
			LOGGER.info("[EventoService][agendarAtualizacaoDeEventos] Evento EM ANDAMENTO. Agendando atualizacao de FINALIZACAO do Evento");
			queueSender.sendFinishEvent(gson.toJson(new EventoDTO(evento.getId(), evento.getDataFinal().toString())));
			
		} else {
			LOGGER.info("[EventoService][agendarAtualizacaoDeEventos] Evento FINALIZADO. Sem agendamentos para serem feitos.");
			
		}
		
		LOGGER.info("[EventoService][agendarAtualizacaoDeEventos] Finalizando agendamento de atualizacao do Evento");

	}
}
