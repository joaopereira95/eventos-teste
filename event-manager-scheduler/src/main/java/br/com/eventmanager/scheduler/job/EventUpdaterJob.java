package br.com.eventmanager.scheduler.job;

import java.time.OffsetDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.eventmanager.scheduler.dto.EventoPendenteDeAtualizacao;
import br.com.eventmanager.scheduler.repository.EventoRepository;

/**
 * Busca e atualiza os eventos que nao foram atualizados com sucesso atraves da atualizacao individual
 */
@Service
public class EventUpdaterJob {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EventUpdaterJob.class);
	
	private static final Long DELAY_SECONDS = 30L; // para dar tempo do job individual executar, caso esteja sendo executado
	
	private @Autowired EventoRepository repository;

	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	public void atualizarSituacaoEventos() {
		OffsetDateTime dataHoraAtual = OffsetDateTime.now();
		OffsetDateTime dataHoraAtualComDelay = dataHoraAtual.minusSeconds(DELAY_SECONDS);
		
		LOGGER.info("[EventUpdaterJob][atualizarSituacaoEventos] Data/hora atual: {}", dataHoraAtual);
		
		List<EventoPendenteDeAtualizacao> eventos = repository.buscarEventosPendentesDeAtualizacao(dataHoraAtualComDelay);
		
		LOGGER.info("[EventUpdaterJob][atualizarSituacaoEventos] Eventos pendentes de atualizacao: {}", eventos);
		
		eventos.stream().forEach(evento -> atualizarEvento(evento, dataHoraAtual));
		
	}
	
	/**
	 * Se o evento estiver com status ativo e ja terminou, atualiza para finalizado e inativo
	 * Se o evento estiver com status inativo e ja iniciou, atualiza para ativo
	 * @param evento
	 * @param dataHoraAtual
	 */
	private void atualizarEvento(EventoPendenteDeAtualizacao evento, OffsetDateTime dataHoraAtual) {
		
		LOGGER.info("[EventUpdaterJob][atualizarEvento] Atualizando evento: {}", evento);
		
		evento.atualizarAtributos(dataHoraAtual);
		
		LOGGER.info("[EventUpdaterJob][atualizarSituacaoEventos] Atualizando Evento ID {} para {}", evento.getId(), evento.getTipoAlteracao());
		
		if (EventoPendenteDeAtualizacao.TipoAlteracao.INICIADO.equals(evento.getTipoAlteracao())) {
			repository.atualizarEventoParaAtivo(evento.getId());
		} else {
			
			repository.atualizarEventoParaFinalizado(evento.getId());
		}
		
		LOGGER.info("[EventUpdaterJob][atualizarSituacaoEventos] Novos valores: ativo = {}, finalizado = {}", evento.isAtivo(), evento.isFinalizado());
		
	}
	
	
}
