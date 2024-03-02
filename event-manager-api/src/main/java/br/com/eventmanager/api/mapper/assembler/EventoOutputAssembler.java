package br.com.eventmanager.api.mapper.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.eventmanager.api.mapper.model.output.EventoOutput;
import br.com.eventmanager.api.model.Evento;

@Component
public class EventoOutputAssembler {

	private @Autowired ModelMapper mapper;

	public EventoOutput toModel(Evento evento) {
		return mapper.map(evento, EventoOutput.class);
	}
	
	public List<EventoOutput> toList(List<Evento> eventos) {
		return eventos.stream().map(evento -> toModel(evento)).collect(Collectors.toList());
	}

}
