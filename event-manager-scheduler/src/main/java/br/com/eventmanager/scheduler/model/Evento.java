package br.com.eventmanager.scheduler.model;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "evento")
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "instituicao_id")
	private Instituicao instituicao;

	@Column(name = "data_atualizacao")
	private OffsetDateTime dataAtualizacao;

	@Column(name = "data_inicial")
	private OffsetDateTime dataInicial;

	@Column(name = "data_final")
	private OffsetDateTime dataFinal;

	private Boolean ativo;

	private Boolean finalizado = Boolean.FALSE;

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

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public OffsetDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(OffsetDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
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

	public void criarEvento() {
		ativo = false;
		finalizado = false;
	}

	public void iniciarEvento() {
		this.ativo = true;
		this.finalizado = false;
	}

	public void finalizarEvento() {
		this.ativo = false;
		this.finalizado = true;
	}

	public void definirSituacaoEvento(OffsetDateTime dataHoraAtual) {

		if (pendente(dataHoraAtual)) {
			criarEvento();

		} else if (emAndamento(dataHoraAtual)) {
			iniciarEvento();

		} else {
			finalizarEvento();
		}

	}

	public boolean emAndamento(OffsetDateTime dataHoraAtual) {
		return dataHoraAtual.isEqual(dataInicial) || dataHoraAtual.isEqual(dataFinal)
				|| (dataHoraAtual.isAfter(dataInicial) && dataHoraAtual.isBefore(dataFinal));
	}

	public boolean pendente(OffsetDateTime dataHoraAtual) {
		return dataHoraAtual.isBefore(dataInicial);
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", nome=" + nome + ", instituicao=" + instituicao + ", dataInicial=" + dataInicial
				+ ", dataFinal=" + dataFinal + ", ativo=" + ativo + ", finalizado=" + finalizado + "]";
	}

}
