package br.com.eventmanager.api.mapper.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.eventmanager.api.mapper.model.output.InstituicaoOutput;
import br.com.eventmanager.api.model.Instituicao;

@Component
public class InstituicaoOutputAssembler {

	private @Autowired ModelMapper mapper;

	public InstituicaoOutput toModel(Instituicao instituicao) {
		return mapper.map(instituicao, InstituicaoOutput.class);
	}
	
	public List<InstituicaoOutput> toList(List<Instituicao> instituicoes) {
		return instituicoes.stream().map(instituicao -> toModel(instituicao)).collect(Collectors.toList());
	}

}
