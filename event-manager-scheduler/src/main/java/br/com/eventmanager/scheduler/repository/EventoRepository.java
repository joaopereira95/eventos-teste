package br.com.eventmanager.scheduler.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.eventmanager.scheduler.dto.EventoPendenteDeAtualizacao;
import br.com.eventmanager.scheduler.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

	@Query("""			
			SELECT new br.com.eventmanager.scheduler.dto.EventoPendenteDeAtualizacao(id, dataInicial, dataFinal, ativo) 
			FROM Evento 
			WHERE finalizado = false 
			AND
			(ativo = false AND :dataHoraAtualComDelay >= dataInicial)
			OR
			(ativo = true AND :dataHoraAtualComDelay >= dataFinal)
			""")
	List<EventoPendenteDeAtualizacao> buscarEventosPendentesDeAtualizacao(OffsetDateTime dataHoraAtualComDelay);
	
	@Modifying
	@Query("""
			UPDATE Evento e SET e.ativo = true WHERE e.id = :id
			""")
	@Transactional
	int atualizarEventoParaAtivo(Integer id);
	
	@Modifying
	@Query("""
			UPDATE Evento e SET e.ativo = false, e.finalizado = true WHERE e.id = :id
			""")
	@Transactional
	int atualizarEventoParaFinalizado(Integer id);
	
}
