package br.com.carnegieworks.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.carnegieworks.domain.exception.EntidadeEmUsoException;
import br.com.carnegieworks.domain.exception.ProdutoNaoEncontradoException;
import br.com.carnegieworks.domain.model.Produto;
import br.com.carnegieworks.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;

	private static final String MSG_PRODUTO_EM_USO = "Produto de código %d não pode ser removido, pois está em uso";

	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

	public void excluir(Long produtoId) {
		try {
			produtoRepository.deleteById(produtoId);

		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(produtoId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_PRODUTO_EM_USO, produtoId));
		}
	}

	public Produto buscarPorId(Long produtoId) {
		return produtoRepository.findById(produtoId).orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
	}

	public List<Produto> buscarTodos() {
		return produtoRepository.findAll();
	}

}
