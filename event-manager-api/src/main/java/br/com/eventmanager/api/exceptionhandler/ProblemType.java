package br.com.eventmanager.api.exceptionhandler;

public enum ProblemType {

	DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"), 
	ERRO_DE_REGRA_DE_NEGOCIO("/erro-regra-negocio", "Erro de regra de negócio"), 
	NAO_ENCONTRADO("/nao-encontrado", "Recurso não encontrado"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema");

	private String title;
	private String uri;

	ProblemType(String path, String title) {
		this.uri = "https://eventos" + path;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}