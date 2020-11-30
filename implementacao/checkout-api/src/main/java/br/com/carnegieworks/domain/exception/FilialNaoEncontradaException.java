package br.com.carnegieworks.domain.exception;

public class FilialNaoEncontradaException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public FilialNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public FilialNaoEncontradaException(Long id) {
		this(String.format("Não existe um cadastro de uma filial com código %d", id));
	}
}
