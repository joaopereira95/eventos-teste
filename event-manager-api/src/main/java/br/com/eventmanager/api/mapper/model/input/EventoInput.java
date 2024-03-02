package br.com.eventmanager.api.mapper.model.input;

import java.time.OffsetDateTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EventoInput {

	@NotNull
	private OffsetDateTime dataInicial;

	@NotNull
	private OffsetDateTime dataFinal;

	@NotBlank
	private String nome;

	@Valid
	@NotNull
	private InstituicaoIdInput instituicao;

	public OffsetDateTime getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(OffsetDateTime dataInicial) {
		this.dataInicial = dataInicial;
	}

	public OffsetDateTime getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(OffsetDateTime dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public InstituicaoIdInput getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoIdInput instituicao) {
		this.instituicao = instituicao;
	}

	@Override
	public String toString() {
		return "EventoInput [dataInicial=" + dataInicial + ", dataFinal=" + dataFinal + ", nome=" + nome
				+ ", instituicao=" + instituicao + "]";
	}
	
}
