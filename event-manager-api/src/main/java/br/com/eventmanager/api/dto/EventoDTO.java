package br.com.eventmanager.api.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;

public class EventoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idEvento;
	private String dataString;

	public EventoDTO(Integer idEvento, String dataString) {
		this.idEvento = idEvento;
		this.dataString = dataString;
	}

	public Integer getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}

	public String getDataString() {
		return dataString;
	}

	public void setDataString(String dataString) {
		this.dataString = dataString;
	}
	
	public OffsetDateTime getData() {
		return OffsetDateTime.parse(dataString);
	}

}
