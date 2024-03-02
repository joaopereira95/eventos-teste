package br.com.eventmanager.scheduler.dto;

import java.time.OffsetDateTime;

import jakarta.persistence.Transient;

public class EventoPendenteDeAtualizacao {
	
	public static enum TipoAlteracao {
		INICIADO, FINALIZADO;
	};
	
	private Integer id;
	private OffsetDateTime dataInicial;
	private OffsetDateTime dataFinal;
	private Boolean ativo;
	private Boolean finalizado = false;
	private TipoAlteracao tipoAlteracao;

	public EventoPendenteDeAtualizacao(Integer id, OffsetDateTime dataInicial, OffsetDateTime dataFinal,
			Boolean ativo) {
		this.id = id;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.ativo = ativo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}
	
	@Transient
	public TipoAlteracao getTipoAlteracao() {
		return tipoAlteracao;
	}

	@Transient
	public void atualizarAtributos(OffsetDateTime dataHoraAtual) {
		if (!ativo && (dataHoraAtual.isBefore(dataFinal) || dataHoraAtual.isEqual(dataFinal))) {
			ativo = true;
			tipoAlteracao = TipoAlteracao.INICIADO;
			
		} else {
			ativo = false;
			finalizado = true;
			tipoAlteracao = TipoAlteracao.FINALIZADO;
		}
	}

	@Override
	public String toString() {
		return "EventoPendenteDeAtualizacao [id=" + id + ", dataInicial=" + dataInicial + ", dataFinal=" + dataFinal
				+ ", ativo=" + ativo + ", finalizado=" + finalizado + "]";
	}

}
