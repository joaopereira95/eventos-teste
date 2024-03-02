package br.com.eventmanager.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eventmanager.api.mapper.assembler.InstituicaoOutputAssembler;
import br.com.eventmanager.api.mapper.model.output.InstituicaoOutput;
import br.com.eventmanager.api.repository.InstituicaoRepository;

@RestController
@RequestMapping(path = "/instituicoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class InstituicaoController {

	private @Autowired InstituicaoOutputAssembler assembler;
	private @Autowired InstituicaoRepository repository;

	@GetMapping
	public List<InstituicaoOutput> buscarTodos() {
		return assembler.toList(repository.findAll());
	}

}
