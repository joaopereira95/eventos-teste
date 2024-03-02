package br.com.eventmanager.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eventmanager.api.mapper.EventoInputDisassembler;
import br.com.eventmanager.api.mapper.assembler.EventoOutputAssembler;
import br.com.eventmanager.api.mapper.model.input.EventoInput;
import br.com.eventmanager.api.mapper.model.output.EventoOutput;
import br.com.eventmanager.api.service.EventoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/eventos", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EventoController.class);

	private @Autowired EventoInputDisassembler disassembler;
	private @Autowired EventoOutputAssembler assembler;
	private @Autowired EventoService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EventoOutput inserir(@Valid @RequestBody EventoInput input) {
		
		LOGGER.info("[EventoController][inserir] Inserindo novo evento. Dados de entrada: " + input);
		
		var evento = disassembler.toDomainObject(input);
		
		service.inserirEvento(evento);
		
		return assembler.toModel(evento);
	}
	
	@GetMapping
	public List<EventoOutput> buscarTodos() {
		return assembler.toList(service.buscarTodos());
	}
	
}
