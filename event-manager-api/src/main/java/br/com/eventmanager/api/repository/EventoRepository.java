package br.com.eventmanager.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eventmanager.api.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

}
