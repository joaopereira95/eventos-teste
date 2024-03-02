package br.com.eventmanager.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.eventmanager.api.mapper.model.input.EventoInput;
import br.com.eventmanager.api.model.Evento;
import br.com.eventmanager.api.model.Instituicao;

@Component
public class EventoInputDisassembler {
	
	private @Autowired ModelMapper modelMapper;

	public Evento toDomainObject(EventoInput input) {
		return modelMapper.map(input, Evento.class);
	}

	public void copyToDomainObject(EventoInput input, Evento domainObject) {
		domainObject.setInstituicao(new Instituicao());
		modelMapper.map(input, domainObject);
	}
}
