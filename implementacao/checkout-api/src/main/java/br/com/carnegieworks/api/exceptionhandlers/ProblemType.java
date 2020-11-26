package br.com.carnegieworks.api.exceptionhandlers;

import lombok.Getter;

@Getter
public enum ProblemType {

	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ENTIDADE_JA_CADASTRADA("/entidade-ja-cadastrada", "Entidade já cadastrada"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
	ERRO_VIOLACAO_RESTRICAO_ENTIDADE("/erro-violacao-restricao-entidade", "Violação de restrição de entidade");
	
	private String title;
	private String uri;
	
	ProblemType(String uri, String title) {
		this.uri = "https://algafood.com.br" + uri;
		this.title = title;
	}
	
}
