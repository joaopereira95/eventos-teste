package br.com.eventmanager.api.mapper.model.input;

import jakarta.validation.constraints.NotNull;

public class InstituicaoIdInput {

	@NotNull
	private Integer id;

	public InstituicaoIdInput() {
		super();
	}

	public InstituicaoIdInput(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "InstituicaoIdInput [id=" + id + "]";
	}

}
