package br.com.eventmanager.api.mapper.model.output;

import java.time.OffsetDateTime;

public class EventoOutput {

	private Integer id;
	private String nome;
	private InstituicaoOutput instituicao;
	private OffsetDateTime dataInicial;
	private OffsetDateTime dataFinal;
	private Boolean ativo;
	private Boolean finalizado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public InstituicaoOutput getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoOutput instituicao) {
		this.instituicao = instituicao;
	}

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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	@Override
	public String toString() {
		return "EventoOutput [id=" + id + ", nome=" + nome + ", instituicao=" + instituicao + ", dataInicial="
				+ dataInicial + ", dataFinal=" + dataFinal + ", ativo=" + ativo + ", finalizado=" + finalizado + "]";
	}

}
